package com.launchquickly.j8ia.ch3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Test;

public class ExecuteAroundPatternTest {

	@Test
	public void processFile() throws IOException {
		final String result = ExecuteAroundPattern.processFile();
		assertNotNull(result);
		assertEquals("One", result);
	}

	@Test
	public void processFileLambdaFirstLine() throws IOException {
		final String result = ExecuteAroundPattern.processFile((BufferedReader br) -> br.readLine());
		assertNotNull(result);
		assertEquals("One", result);
	}

	@Test
	public void processFileLambdaTwoLines() throws IOException {
		final String result = ExecuteAroundPattern.processFile((BufferedReader br) -> br.readLine() + br.readLine());
		assertNotNull(result);
		assertEquals("OneTwo", result);
	}

}
