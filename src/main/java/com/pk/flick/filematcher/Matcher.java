package com.pk.flick.filematcher;

import java.nio.file.Path;

public interface Matcher {
	public boolean match(Path path);
}
