package com.pk.test;

import java.io.File;

import org.junit.Ignore;

public class Test {

    @org.junit.Test
    @Ignore
    public void test() {
        String appDataPath = System.getenv("APPDATA");
        File folder = new File(appDataPath);
        System.out.println(folder.canRead() + " " + folder.canWrite());

        String fullPath = appDataPath + "\\.fd\\versions";
        File folders = new File(fullPath);
        if (folders.exists()) {
            System.out.println("directories already there.");
            return;
        }

        if (folders.mkdirs()) {
            System.out.println("directory : " + fullPath + " successfully created");
        } else {
            System.out.println("issue creating directories");
        }
    }

    @org.junit.Test
    public void test2() {
        final String testStr = "Number %s";
        System.out.println(String.format(testStr, 4));
    }
}
