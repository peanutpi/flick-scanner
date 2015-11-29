package com.pk.flick.filematcher;

import uk.co.caprica.mediascanner.domain.MediaEntry;
import uk.co.caprica.mediascanner.meta.MetaProvider;
import fr.clunven.mediainfo.domain.MovieMetadata;

public class MetaDataProvider implements MetaProvider {

    @Override
    public void addMeta(MediaEntry mediaEntry, Object arg1) {
        System.out.println("Meta Data Scanning Started.");
        String path = mediaEntry.file().toString();
        System.out.println(new MovieMetadata(path).toString());
    }

}
