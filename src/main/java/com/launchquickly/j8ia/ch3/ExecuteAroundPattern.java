package com.launchquickly.j8ia.ch3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAroundPattern {

	public static String processFile() throws IOException {
		final File file = new File(ExecuteAroundPattern.class.getResource("/data.txt").getFile());
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			return br.readLine();
		}
	}

	public static String processFile(final BufferedReaderProcessor p) throws IOException {
		final File file = new File(ExecuteAroundPattern.class.getResource("/data.txt").getFile());
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			return p.process(br);
		}
	}

}
