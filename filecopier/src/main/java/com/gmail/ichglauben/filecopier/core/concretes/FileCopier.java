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

/**
 * The class provides a single static method - copy.
 * 
 * @see java.nio
 * @see java.nio.channels.FileChannel
 * @see java.nio.file.Path
 * @see java.io.File;
 * @see java.io.FileInputStream;
 * @see java.io.FileOutputStream;
 * @author Rick Walker
 * @version 0.1
 * @since 5/7/2016
 */
public class FileCopier extends CustomClass {
	private static FileCopier copier = new FileCopier();

	/**
	 * Static method - makes a copy of the source at the destination
	 * 
	 * @param source
	 *            The file to be copied
	 * @param destination
	 *            The copy of the source at the new location
	 */
	public static void copy(String source, String destination) {
		FileChannel input = null;
		FileChannel output = null;
		if (sourceFileIsValid(source, destination)) {
			destination = checkExtension(source, destination);
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

	private static String checkExtension(String source, String destination) {
		if (destination.lastIndexOf(".") == -1)
			destination += FileExtensionExtractor.extractExtension(source);
		return destination;
	}

	private static boolean sourceFileIsValid(String source, String destination) {
		return (null != source && null != destination) && (!source.equals("") && !destination.equals(""))
				&& (source.length() > 0 && destination.length() > 0) && (PathValidator.isAFile(source));
	}

	/** Single private constructor */
	private FileCopier() {
		super();
	}

	/**
	 * 
	 * Returns an instance of this class.
	 * 
	 * @return FileCoper instance
	 */
	public static FileCopier getInstance() {
		return copier;
	}

	public String toString() {
		return "File Copier";
	}
}
