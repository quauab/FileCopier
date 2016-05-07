package com.gmail.ichglauben.filecopier;

import java.io.File;
import java.nio.file.Path;

import org.junit.Test;

import com.gmail.ichglauben.filecopier.core.concretes.FileCopier;
import com.gmail.ichglauben.filecopier.core.utils.abstracts.CustomClass;
import com.gmail.ichglauben.filecopier.core.utils.concretes.GlobalConstants;

public class FileCopierTest extends CustomClass {
	FileCopier copier = FileCopier.getInstance();

	@Test
	public void testCopyMethod() {
		String f1 = "K:\\media\\graphics\\jpegs\\cats\\lynxs\\lynx.jpg";
		String f2 = "K:\\media\\graphics\\jpegs\\Chkf.jpg";
		String dest1 = GlobalConstants.USRHOME + GlobalConstants.FILESEPARATOR + "orchard";
		String dest2 = GlobalConstants.USRHOME + GlobalConstants.FILESEPARATOR + "goldenrod";
		
		copier.copy(f2, dest1);
		copier.copy(f1, dest2);
		
		for (File file : new File(GlobalConstants.USRHOME).listFiles()) {
			Path p = file.toPath();
			String n = p.getFileName().toString();
			
			if (n.equals("orchard.jpg") || n.equals("goldenrod.jpg")) {
				print(n);
			}
		}
	}

}
