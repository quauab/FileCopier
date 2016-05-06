package com.gmail.ichglauben.filecopier.core.concretes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

import com.gmail.ichglauben.filecopier.core.utils.abstracts.CustomClass;

public class FileCopier extends CustomClass {
	private static FileCopier copier = new FileCopier();

	public static void copy(String source, String destination) {
		FileChannel input = null;
		FileChannel output = null;

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
				print("\nError attempting to close the input and/or output stream\n" + exception.getLocalizedMessage());
			}
		}
	}

	public static void copy(Path source, Path destination) {
		FileChannel input = null;
		FileChannel output = null;

		try {
			input = new FileInputStream(source.toAbsolutePath().toString()).getChannel();
			output = new FileOutputStream(destination.toAbsolutePath().toString()).getChannel();
			output.transferFrom(input, 0, input.size());
		} catch (IOException ioe) {
			print("\nError attempting to copy file\n" + ioe.getLocalizedMessage());
		} finally {
			try {
				input.close();
				output.close();
			} catch (IOException exception) {
				print("\nError attempting to close the input and/or output stream\n" + exception.getLocalizedMessage());
			}
		}
	}

	public static void copy(File source, File destination) {
		FileChannel input = null;
		FileChannel output = null;

		try {
			input = new FileInputStream(source.getAbsolutePath().toString()).getChannel();
			output = new FileOutputStream(destination.getAbsolutePath().toString()).getChannel();
			output.transferFrom(input, 0, input.size());
		} catch (IOException ioe) {
			print("\nError attempting to copy file\n" + ioe.getLocalizedMessage());
		} finally {
			try {
				input.close();
				output.close();
			} catch (IOException exception) {
				print("\nError attempting to close the input and/or output stream\n" + exception.getLocalizedMessage());
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
