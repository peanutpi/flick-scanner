package com.pk.flick.scanner.iterator;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pk.flick.filematcher.Matcher;
import com.pk.flick.model.Flick;

public class DirIterator extends SimpleFileVisitor<Path> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DirIterator.class);
    private List<Matcher> matchers;
    private Set<Flick> flicks;

    public DirIterator(List<Matcher> matchers) {
        this.matchers = matchers;
        flicks = new HashSet<Flick>();
    }

    // Compares the glob pattern against
    // the file or directory name.
    void find(Path file) {
        Path fileName = file.getFileName();
        for (Matcher matcher : matchers) {
            if (fileName != null & matcher.match(fileName)) {
                LOGGER.debug(file.getFileName().toString());
                flicks.add(new Flick(file.toFile()));
            }
        }
    }

    // Prints the total number of
    // matches to standard out.
    public Set<Flick> done() {
        LOGGER.debug("Final List");
        return flicks;
    }

    // Invoke the pattern matching
    // method on each file.
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        find(file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        LOGGER.error("File visit failed.", exc);
        return FileVisitResult.CONTINUE;
    }

}
