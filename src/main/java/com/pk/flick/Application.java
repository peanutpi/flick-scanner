package com.pk.flick;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.util.Collections;
import java.util.Set;

import uk.co.caprica.mediascanner.MediaScanner;
import uk.co.caprica.mediascanner.domain.MediaEntry;
import uk.co.caprica.mediascanner.domain.MediaSet;
import fr.clunven.mediainfo.domain.MovieMetadata;

public class Application {
    private static final Set<FileVisitOption> FILE_VISIT_OPTION = Collections.emptySet();
    private static final int MAX_DEPTH = 6;

    public static void main(String[] args) throws IOException {
        if (args.length <= 0) {
            return;
        }
        // VersionStorage.setupRepository();

        MediaScanner scanner = MediaScanner.create();
        for (String dir : args) {
            scanner.directory(dir);
        }

        MediaSet mediaSet = scanner.matching("glob:**/*.{webm,mkv,flv,avi,mov,wmv,mp4,m4p,m4v,mpg,mpeg,mpeg,3gp}")
                .findMedia().collectMeta().mediaSet();

        for (MediaEntry mediaEntry : mediaSet) {
            System.out.println("Meta Data Scanning Started.");
            String path = mediaEntry.file().toString();
            System.out.println(new MovieMetadata(path).toString());
        }

        // List<Matcher> matchers = new ArrayList<Matcher>();
        // matchers.add(new MediaFileMatcher());
        // DirIterator iterator = new DirIterator(matchers);
        //
        // for (String dir : args) {
        // System.out.println("Directory to scan : " + dir);
        // Path startPath = Paths.get(dir);
        // Files.walkFileTree(startPath, FILE_VISIT_OPTION, MAX_DEPTH,
        // iterator);
        // }
        // Set<Flick> flicks = iterator.done();
        //
        // VersionCreator.instance().addFlicks(flicks).done();
    }
}
