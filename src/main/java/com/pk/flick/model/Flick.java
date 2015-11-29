package com.pk.flick.model;

import java.io.File;
import java.nio.file.Path;

public class Flick {

    private String name;
    private long size;
    private Path parent;

    public Flick(String name, long size, Path parent) {
        this.name = name;
        this.size = size;
        this.parent = parent;
    }

    public Flick(File flick) {
        this.name = flick.getName();
        this.size = flickSizeInMB(flick.length());
        this.parent = flick.getParentFile().toPath();
    }

    private long flickSizeInMB(long length) {
        long lengthInKB = length / 1024;
        long lengthInMB = lengthInKB / 1024;
        return lengthInMB;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public Path getParent() {
        return parent;
    }

    public String getSerialiseString() {
        return name + ":" + size + ":" + parent;
    }

    @Override
    public String toString() {
        return "Flick [name=" + name + ", size=" + size + ", parent=" + parent + "]";
    }

}
