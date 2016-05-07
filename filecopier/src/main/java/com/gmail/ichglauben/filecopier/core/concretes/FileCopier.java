package com.gmail.ichglauben.filecopier.core.concretes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

import com.gmail.ichglauben.filecopier.core.utils.abstracts.CustomClass;
import com.gmail.ichglauben.filecopier.core.utils.concretes.FileExtensionExtractor;
import com.gmail.ichglauben.filecopier.core.utils.concretes.PathValidator;

public class FileCopier extends CustomClass {
	private static FileCopier copier = new FileCopier();

	public static void copy(String source, String destination) {
		FileChannel input = null;
		FileChannel output = null;
		if ((null != source && null != destination) && (!source.equals("") && !destination.equals(""))
				&& (source.length() > 0 && destination.length() > 0) && (PathValidator.isAFile(source))) {
			if (destination.lastIndexOf(".") == -1)
				destination += FileExtensionExtractor.extractExtension(source);
			try {
				input = new FileInputStream(source).getChannel();
				output = new FileOutputStream(destination).getChannel();
				output.transferFrom(input, 0, input.size());
			} catch (IOException ioe) {
				print("\nError attempting to copy file\n" + ioe.getLocalizedMessage());
			} finally {
				try {
					input.close();
					output.close();
				} catch (IOException exception) {
					print("\nError attempting to close the input and/or output stream\n"
							+ exception.getLocalizedMessage());
				}
			}
		}
	}

	private FileCopier() {
		super();
	}

	public static FileCopier getInstance() {
		return copier;
	}

	public String toString() {
		return "File Copier";
	}
}
