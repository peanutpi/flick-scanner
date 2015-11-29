package com.pk.version.storage;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pk.flick.config.ConfigReader;
import com.pk.flick.model.Head;

/**
 * TODO
 * 
 * @author Pratik
 *
 */
public class VersionStorage {

    static final String FD_ROOT_DIRECTORY = "\\.fd";
    static final String FD_DIRECTORY_STRUCTURE = FD_ROOT_DIRECTORY + "\\versions";
    static final String APP_DATA_PATH = ConfigReader.getAppDataDir();
    static final String TARGET_DIR = ConfigReader.getVersionDirectory();
    static final String HEAD_FILE = ConfigReader.getHead();
    static final Logger LOGGER = LoggerFactory.getLogger(VersionStorage.class);

    /**
     * TODO
     */
    public static void setupRepository() {
        File folder = new File(APP_DATA_PATH);
        boolean isReadable = folder.canRead();
        boolean isWritable = folder.canWrite();

        if (!isReadable || !isWritable) {
            LOGGER.error("Sorry, but i don't have permission to read,write in the directory : {}", APP_DATA_PATH);
            System.exit(1);
        }

        String fullPath = TARGET_DIR;

        File folders = new File(fullPath);
        if (folders.exists()) {
            LOGGER.info("directory already there.");
            createHead();
            return;
        }

        if (folders.mkdirs()) {
            LOGGER.info("directory : {} successfully created", fullPath);
            createHead();
            return;
        }

        LOGGER.info("Issue creating directory : {}", fullPath);
        System.exit(-1);

    }

    private static void createHead() {
        File head = new File(HEAD_FILE);
        if (!head.exists()) {
            try {
                head.createNewFile();
                Head.createInstance();
            } catch (IOException e) {
                LOGGER.error("Failed to create a new HEAD file", e);
                System.exit(-1);
            }
        }
    }
}
