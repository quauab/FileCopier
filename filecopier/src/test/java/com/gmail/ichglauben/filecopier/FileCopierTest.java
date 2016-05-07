package com.gmail.ichglauben.filecopier;

import java.io.File;
import java.net.URL;

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
		String dest = GlobalConstants.USRHOME + GlobalConstants.FILESEPARATOR + "wild";
		
		copier.copy(f2, dest);
		copier.copy(f1, "salami");
		
		for (File file : new File(GlobalConstants.USRHOME).listFiles()) {
			print(file.toPath().getFileName());
		}
	}

}
