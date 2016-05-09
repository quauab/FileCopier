package com.gmail.ichglauben.filecopier.core.concretes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import com.gmail.ichglauben.filecopier.core.utils.abstracts.CustomClass;
import com.gmail.ichglauben.filecopier.core.utils.concretes.FileExtensionExtractor;
import com.gmail.ichglauben.pathvalidator.core.concretes.PathValidator;

/**
 * Use this class to create a copy of a file in a different location. This class provides 1 static method.
 * @see java.nio
 * @see java.nio.channels.FileChannel
 * @see java.nio.file.Path
 * @see java.io.File
 * @see java.io.FileInputStream
 * @see java.io.FileOutputStream
 * @author Rick Walker
 * @version 0.1
 * @since 5/7/2016
 */
public class FileCopier extends CustomClass {
	private static FileCopier copier = new FileCopier();

	/**
	 * Use this method to make a copy of the source at the destination.
	 * @param source
	 *            The file to be copied
	 * @param destination
	 *            The copy of the source at the new location
	 */
	public static void copy(String source, String destination) {
		FileChannel input = null;
		FileChannel output = null;
		if (paramsAreValid(source, destination)) {
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

	/**
	 * This method is used internally to fix the extension of the destination
	 * file path if it's invalid.
	 * @param source The file to be copied
	 * @param destination The copy of the source file at the new location
	 * @return String The destination for the copied file*/
	private static String checkExtension(String source, String destination) {
		if (destination.lastIndexOf(".") == -1)
			destination += FileExtensionExtractor.extractExtension(source);
		return destination;
	}
	
	/**
	 * This method is used internally to validate the source and destination parameters
	 * for the copy method.
	 * @param source The file to copy
	 * @param destination The new location for the copied source file
	 * @return true if, and only if, both parameters are valid*/
	private static boolean paramsAreValid(String source, String destination) {
		return (null != source && null != destination) && (!source.equals("") && !destination.equals(""))
				&& (source.length() > 0 && destination.length() > 0) && (PathValidator.isAFile(source));
	}

	/** Single private constructor */
	private FileCopier() {
		super();
	}

	/**Use this method to assign an instance of this class to a variable. 
	 * @return FileCoper instance*/
	public static FileCopier getInstance() {
		return copier;
	}

	public String toString() {
		return "File Copier";
	}
}
