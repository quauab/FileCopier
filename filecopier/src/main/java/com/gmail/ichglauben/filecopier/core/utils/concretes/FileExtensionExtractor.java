package com.gmail.ichglauben.filecopier.core.utils.concretes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileExtensionExtractor {
	private FileExtensionExtractor() {
		super();
	}

	public static String extractExtension(String file_path) {
		if (null != file_path && (!file_path.isEmpty()) && (file_path.length() > 0)) {
			Path path = Paths.get(file_path);
			if (Files.exists(path, LinkOption.NOFOLLOW_LINKS) && Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS)) {
				String name = path.getFileName().toString();
				int index = name.lastIndexOf(".");
				if (index != -1) {
					return name.substring(index);
				}
			}
		}
		return null;
	}

	public static String extractExtension(Path path) {
		if (null != path) {
			if (Files.exists(path, LinkOption.NOFOLLOW_LINKS) && Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS)) {
				String name = path.getFileName().toString();
				int index = name.lastIndexOf(".");
				if (index != -1) {
					return name.substring(index);
				}
			}
		}
		return null;
	}

	public static String extractExtension(File file) {
		if (null != file) {
			if (Files.exists(file.toPath(), LinkOption.NOFOLLOW_LINKS) && Files.isRegularFile(file.toPath(), LinkOption.NOFOLLOW_LINKS)) {
				String name = file.toPath().getFileName().toString();
				int index = name.lastIndexOf(".");
				if (index != -1) {
					return name.substring(index);
				}
			}
		}
		return null;
	}

}
