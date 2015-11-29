package com.pk.flick.version;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import com.pk.flick.model.Flick;
import com.pk.flick.model.Head;
import com.pk.flick.version.model.Version;

public class VersionCreator {

    private Set<Flick> flicks;

    private VersionCreator() {
        flicks = new HashSet<Flick>();
    }

    public static VersionCreator instance() {
        return new VersionCreator();
    }

    public VersionCreator addFlick(File file) {
        flicks.add(new Flick(file));
        return this;
    }

    public VersionCreator addFlicks(Set<Flick> flicks) {
        this.flicks = flicks;
        return this;
    }

    public void done() {
        Integer nextVersion = Head.instance().getNextVersion();
        Version.newVersion(nextVersion, flicks).serialise();
        Head.instance().serialise();
    }

}
