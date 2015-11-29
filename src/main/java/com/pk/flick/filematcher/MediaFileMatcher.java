package com.pk.flick.filematcher;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MediaFileMatcher implements Matcher {

	private static String[] extentions = { "webm", "mkv", "flv", "avi", "mov",
			"wmv", "mp4", "m4p", "m4v", "mpg", "mpeg", "mpeg", "3gp", };
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MediaFileMatcher.class);

	private static Set<String> extentionSet = new HashSet<String>(
			Arrays.asList(extentions));

	public boolean match(Path path) {
		if (path == null) {
			return false;
		}
		String fileName = path.toString();
		LOGGER.trace("Processing : " + fileName);

		int i = fileName.lastIndexOf('.');
		String extention = null;
		if (i > 0) {
			extention = fileName.substring(i + 1);
		}
		LOGGER.trace("Extention : {} found for file : {}", extention, fileName);
		return extentionSet.contains(extention);
	}

}
