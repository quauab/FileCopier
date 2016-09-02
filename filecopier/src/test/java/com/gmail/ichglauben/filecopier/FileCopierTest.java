package com.gmail.ichglauben.filecopier;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Path;

import org.junit.Test;

import com.gmail.ichglauben.filecopier.core.concretes.FileCopier;
import com.gmail.ichglauben.filecopier.core.utils.abstracts.CustomClass;
import com.gmail.ichglauben.filecopier.core.utils.concretes.GlobalConstants;

public class FileCopierTest extends CustomClass {
	ClassLoader loader = getClass().getClassLoader();
	FileCopier copier = FileCopier.getInstance();
	String uh = GlobalConstants.USRHOME;
	File lynx = new File(loader.getResource("lynx.jpg").getFile());
	File tiger = new File(loader.getResource("tiger.jpg").getFile());
	 
	@Test
	public void testCopyMethod() {
		String lynxDestination = uh + lynx.toPath().getFileName().toString();
		
		String tigerDestination = uh + tiger.toPath().getFileName().toString();
				
		copier.copy(lynx.getAbsolutePath(), lynxDestination);
		
		copier.copy(tiger.getAbsolutePath(), tigerDestination);				
	}
	
	@Test
	public void testCopyMethodMakeFileCopies() {
		String lynxDestination = uh + lynx.toPath().getFileName().toString();
				
		for (int i = 0; i<13; i++) {
			copier.copy(lynx.getAbsolutePath(), lynxDestination);
		}
	}

}
