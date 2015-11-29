package com.pk.flick.config;

public class ConfigReader {

    static final String APP_DATA_DIRECTORY = System.getenv("APPDATA");
    static final String FD_ROOT_DIRECTORY = APP_DATA_DIRECTORY + "\\.fd";
    static final String FD_VERSION_DIRECTORY = FD_ROOT_DIRECTORY + "\\versions";
    static final String FD_NEW_VERSION_FILE = FD_VERSION_DIRECTORY + "\\%d.ver";
    static final String FD_HEAD = FD_ROOT_DIRECTORY + "\\head";

    public static String getAppDataDir() {
        return APP_DATA_DIRECTORY;
    }

    public static String getRootDirectory() {
        return FD_ROOT_DIRECTORY;
    }

    public static String getVersionDirectory() {
        return FD_VERSION_DIRECTORY;
    }

    public static String getHead() {
        return FD_HEAD;
    }

    public static String getNewVersionPath(Integer newVersion) {
        return String.format(FD_NEW_VERSION_FILE, newVersion);
    }

}
