package com.gmail.ichglauben.filecopier.core.concretes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.gmail.ichglauben.filecopier.core.utils.abstracts.CustomClass;
import com.gmail.ichglauben.filecopier.core.utils.concretes.FileExtensionExtractor;
import com.gmail.ichglauben.filecopier.core.utils.concretes.FileNameExtractor;
import com.gmail.ichglauben.filecopier.core.utils.concretes.GlobalConstants;
import com.gmail.ichglauben.pathvalidator.core.concretes.PathValidator;

/**
 * Copy a file.
 * @see java.nio
 * @see java.nio.channels.FileChannel
 * @see java.nio.file.Path
 * @see java.io.File
 * @see java.io.FileInputStream
 * @see java.io.FileOutputStream
 * @author <i>Rick Walker</i>
 * @version 0.1
 * @since 5/7/2016
 */
public class FileCopier extends CustomClass {
	private static FileCopier copier = new FileCopier();

	/**@param source
	 *            The file to be copied
	 * @param destination
	 *            The copied file destination
	 */
	public static void copy(String source, String destination) {
		FileChannel input = null;
		FileChannel output = null;
		if (paramsAreValid(source, destination)) {
			destination = checkExtension(source, destination);
			destination = checkDestinationExists(destination);
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
	 * This method fixes the destination's extension if missing.
	 * @param source <b><i>String</i></b> The file to be copied
	 * @param destination The copy of the source file at the new location
	 * @return String <b><i>String</i></b> The destination for the copied file*/
	private static String checkExtension(String source, String destination) {		
		if (destination.lastIndexOf(".") == -1)
			destination += FileExtensionExtractor.extractExtension(source);
		return destination;
	}
	
	/**This method checks if destination already exists.
	 * @param destination String The copy destination
	 * @return destination String*/
	private static String checkDestinationExists(String destination) {
		int howMany = 0;
		Path path = Paths.get(destination);
		String dir = path.getParent().toString() + GlobalConstants.FILESEPARATOR;
		String fileName = FileNameExtractor.extract(destination);
		String fileExt = FileExtensionExtractor.extractExtension(destination);
		
		if (PathValidator.pathExists(dir + fileName + fileExt)) {
			howMany += 1;
		}
		
		for (File f:new File(dir).listFiles()) {
			if (f.getAbsolutePath().equals(dir + fileName + (howMany + 1) + fileExt))
				howMany += 1;
		}
		
		switch (howMany) {
		case 0:
			return destination;
		
		default:
			destination = dir + fileName + (howMany + 1) + fileExt;
			break;
		}
		
		return destination;
	}
	
	/**
	 * This method checks the parameters.
	 * @param source <b><i>String</i></b> The source file to copy
	 * @param destination <b><i>String</i></b> The new location for the copied source file
	 * @return true if, and only if, both parameters are valid*/
	private static boolean paramsAreValid(String source, String destination) {
		return (null != source && null != destination) && (!source.equals("") && !destination.equals(""))
				&& (source.length() > 0 && destination.length() > 0) && (PathValidator.isAFile(source));
	}

	/** Single private constructor */
	private FileCopier() {
		super();
	}

	/**This method returns an instance of this class. 
	 * @return FileCopier instance*/
	public static FileCopier getInstance() {
		return copier;
	}

	public String toString() {
		return "File Copier";
	}
}
