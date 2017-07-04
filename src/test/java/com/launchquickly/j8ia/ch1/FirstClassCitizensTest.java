package com.launchquickly.j8ia.ch1;

import java.io.File;
import java.io.FileFilter;

import org.junit.Test;

public class FirstClassCitizensTest {

	@Test
	public void classicListHiddenFiles() {
		final File[] hiddenFiles = new File(".").listFiles(new FileFilter() {

			@Override
			public boolean accept(final File file) {
				return file.isHidden();
			}
		});

		System.out.println(hiddenFiles);
	}

	@Test
	public void lambdaListHiddenFiles() {
		final File[] hiddenFiles = new File(".").listFiles(File::isHidden);

		System.out.println(hiddenFiles);
	}

}
