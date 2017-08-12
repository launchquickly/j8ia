package com.launchquickly.j8ia.ch5;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Test;

public class BuildingStreamsTest {
	
	@Test
	public void arrayStream() {
		final int[] numbers = {2, 3, 5, 7, 11, 13};
		final int sum = Arrays.stream(numbers)
				              .sum();
		
		assertEquals(41, sum);
	}
	
	@Test
	public void fromFile() throws IOException, URISyntaxException {
		long uniqueWords = 0;
		try (Stream<String> lines = 
				              Files.lines(Paths.get(ClassLoader.getSystemResource("data.txt").toURI()), Charset.defaultCharset())) {
			uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
						       .distinct()
						       .count();
		}
		
		assertEquals(3l, uniqueWords);
	}
	
	@Test
	public void ofStream() {
		final Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
		stream.map(String::toUpperCase)
		      .forEach(System.out::println);
	}

}
