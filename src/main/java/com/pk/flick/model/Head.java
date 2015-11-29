package com.pk.flick.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import com.pk.flick.config.ConfigReader;
import com.pk.version.storage.VersionStorage;

@SuppressWarnings("serial")
public class Head implements SingletonSerializable {

    private Integer currentLocalVersion;
    private static Head instance = new Head();
    private static boolean initialised = false;

    private Head() {

    }

    public Integer getLocalVersion() {
        return currentLocalVersion;
    }

    public static void createInstance() {
        instance.initialize();
    }

    public static Head instance() {
        if (initialised)
            return instance;
        createInstance();
        return instance;
    }

    private Integer nextVersion() {
        return this.currentLocalVersion + 1;
    }

    public Integer getNextVersion() {
        return nextVersion();
    }

    @Override
    public void initialize() {
        String headPath = ConfigReader.getHead();
        File headFile = new File(headPath);
        if (!headFile.exists()) {
            VersionStorage.setupRepository();
        }

        try (Stream<String> lines = Files.lines(headFile.toPath());) {

            Optional<String> version = lines.filter(s -> s.contains("local:")).findFirst();

            if (version.isPresent()) {
                String localVersionStr = version.get();
                String localVersionInStr = localVersionStr.substring(localVersionStr.indexOf(':') + 1);
                this.currentLocalVersion = Integer.parseInt(localVersionInStr);
                initialised = true;
                return;
            }

            this.currentLocalVersion = -1;
            initialised = true;
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void serialise() {
        String headPath = ConfigReader.getHead();
        File headFile = new File(headPath);
        if (!headFile.exists()) {
            VersionStorage.setupRepository();
        }

        try {
            Files.write(headFile.toPath(), Arrays.asList("local:" + nextVersion()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
