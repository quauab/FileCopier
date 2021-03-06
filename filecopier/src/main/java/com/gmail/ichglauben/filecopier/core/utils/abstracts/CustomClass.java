package com.gmail.ichglauben.filecopier.core.utils.abstracts;

import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public abstract class CustomClass {
	private final static javax.swing.ImageIcon icon = createImageIcon("/large.gif");

	/**
	 * Default constructor
	 */
	public CustomClass() {
		super();
	}

	protected static void error(Exception e) {
		if (null != e) {
			String local_message = e.getLocalizedMessage();
			String message = e.getMessage();
			String cause = null;
			if (null != local_message) {
				if (local_message.equalsIgnoreCase(message)) {
					if (null != (cause = e.getCause().toString())) {
						print("\nError: " + local_message + "\nCause: " + cause + "\n");
					} else {
						print("\nError: " + local_message + "\n");
					}
				} else {
					print(local_message);
				}
			}
		}
		return;
	}

	protected static void print(Object o) {
		System.out.print(String.valueOf(o));
	}
	
	protected static void println(Object o) {
		System.out.println(String.valueOf(o));
	}

	protected static List<String> makeList(Map<String, String> hash) {
		List list = null;

		if (null != hash) {
			list = new ArrayList<String>();

			for (Map.Entry<String, String> me : hash.entrySet()) {
				String key = String.valueOf(me.getKey());
				String val = String.valueOf(me.getValue());
				String item = (key + "@" + val);
				list.add(item);
			}
		}
		return list;
	}

	protected static void alert(Object msg) {
		Object[] options = { "Acknowledge" };
		int n = javax.swing.JOptionPane.showOptionDialog(null, String.valueOf(msg), "Alert",
				javax.swing.JOptionPane.OK_OPTION, javax.swing.JOptionPane.PLAIN_MESSAGE, icon, options, options[0]);
	}

	protected static String stamp() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int mon = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DATE);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int min = now.get(Calendar.MINUTE);
		int sec = now.get(Calendar.SECOND);
		String y = String.valueOf(year);
		String m = String.valueOf(mon);
		String d = String.valueOf(day);
		String h = String.valueOf(hour);
		String mi = String.valueOf(min);
		String s = String.valueOf(sec);
		String date = new String(y + ":" + m + ":" + d + " " + h + ":" + mi + ":" + s);
		return date;
	}

	protected static void detectPlatform() {
		final String os = FileSystemConstants.OS;
		String windows = ".*windows.*";
		String linux = ".*linux.*";
		String gtk = ".*gtk.*";

		boolean matches = false;

		if ((matches = Pattern.matches(windows, os))) {
			setWindowsLookAndFeel();
		} else if ((matches = Pattern.matches(linux, os))) {
			setLinuxLookAndFeel();
		} else if ((matches = Pattern.matches(gtk, os))) {
			setGtkLookAndFeel();
		} else {
			setDefaultLookAndFeel();
		}
	}

	protected static void setDefaultLookAndFeel() {
		try {
			UIManager.setLookAndFeel(PlatformConstants.METAL);
			return;
		} catch (ClassNotFoundException e) {
			return;
		} catch (InstantiationException e) {
			return;
		} catch (UnsupportedLookAndFeelException ulf) {
			return;
		} catch (IllegalAccessException iae) {
			return;
		}
	}

	protected static void setGtkLookAndFeel() {
		try {
			UIManager.setLookAndFeel(PlatformConstants.GTK);
			return;
		} catch (ClassNotFoundException e) {
			return;
		} catch (InstantiationException e) {
			return;
		} catch (UnsupportedLookAndFeelException ulf) {
			return;
		} catch (IllegalAccessException iae) {
			return;
		}
	}

	protected static void setLinuxLookAndFeel() {
		try {
			UIManager.setLookAndFeel(PlatformConstants.LINUX);
			return;
		} catch (ClassNotFoundException e) {
			return;
		} catch (InstantiationException e) {
			return;
		} catch (UnsupportedLookAndFeelException ulf) {
			return;
		} catch (IllegalAccessException iae) {
			return;
		}
	}

	protected static void setWindowsLookAndFeel() {
		try {
			UIManager.setLookAndFeel(PlatformConstants.WIN);
			return;
		} catch (ClassNotFoundException e) {
			return;
		} catch (InstantiationException e) {
			return;
		} catch (UnsupportedLookAndFeelException ulf) {
			try {
				UIManager.setLookAndFeel(PlatformConstants.WINCLASSIC);
				return;
			} catch (ClassNotFoundException e) {
				return;
			} catch (InstantiationException e) {
				return;
			} catch (UnsupportedLookAndFeelException ulaf) {
				return;
			} catch (IllegalAccessException iae) {
				return;
			}
		} catch (IllegalAccessException iae) {
			return;
		}
	}

	protected static javax.swing.ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = CustomClass.class.getResource(path);
		if (imgURL != null) {
			return new javax.swing.ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file at: \n" + path);
			return null;
		}
	}

	public String toString() {
		return "Custom Utilities";
	}

	protected final static class PlatformConstants {
		public final static String GTK = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
		public final static String LINUX = "com.sun.java.swing.plat.linux.LinuxLookAndFeel";
		public final static String WIN = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		public final static String WINCLASSIC = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
		public final static String MOTIF = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		public final static String METAL = "javax.swing.plaf.metal.MetalLookAndFeel";
		public final static String NIMBUS = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
	}

	protected final static class FileSystemConstants {
		public final static String OS = System.getProperty("os.name").toLowerCase();
		public final static String OS_ARCH = System.getProperty("os.arch").toLowerCase();
		public final static String OS_VERSION = System.getProperty("os.version").toLowerCase();
		public final static String FILESEPARATOR = FileSystems.getDefault().getSeparator();
		public final static String USRDIR = System.getProperty("user.dir").toLowerCase() + FILESEPARATOR;
		public final static String USRHOME = System.getProperty("user.home").toLowerCase() + FILESEPARATOR;

	}

}
