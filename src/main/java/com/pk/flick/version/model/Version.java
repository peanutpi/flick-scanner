package com.pk.flick.version.model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import com.pk.flick.config.ConfigReader;
import com.pk.flick.model.Flick;
import com.pk.flick.model.SingletonSerializable;

@SuppressWarnings("serial")
public class Version implements SingletonSerializable {

    private Set<Flick> flicks;
    private Long creationTime;
    private Integer versionNumber;
    private static Version instance;

    public static Version newVersion(Integer versionNumber, Set<Flick> flicks) {
        instance = new Version(versionNumber, flicks);
        return instance;
    }

    private Version(Integer versionNumber, Set<Flick> flicks) {
        this.versionNumber = versionNumber;
        this.flicks = flicks;
        this.creationTime = System.currentTimeMillis();
    }

    public Set<Flick> getFlicks() {
        return flicks;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public Integer getVersion() {
        return versionNumber;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void serialise() {

        File newVersionFile = new File(ConfigReader.getNewVersionPath(versionNumber));

        if (newVersionFile.exists()) {
            logger.error("Version : {} is already there. so there must be some issue. ", versionNumber);
            System.exit(1);
        }

        try {
            if (newVersionFile.createNewFile()) {
                PrintWriter pw = new PrintWriter(newVersionFile);
                for (Flick flick : flicks) {
                    pw.println(flick.getSerialiseString());
                }
                pw.close();
            }
        } catch (IOException e) {
            logger.error("Enable to create a new version : {}, There must be some issue. ", versionNumber);
            System.exit(1);
        }

    }
}
