package com.launchquickly.j8ia.ch5;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
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
	public void fibonacciGenerate() {
		final IntSupplier fib = new IntSupplier() {
			
			private int previous = 0;
			private int current = 1;
			
			@Override
			public int getAsInt() {
				final int oldPrevious = this.previous;
				final int nextValue = this.previous + this.current;
				this.previous = this.current;
				this.current = nextValue;
				return oldPrevious;
			}
		};
		
		IntStream.generate(fib)
		         .limit(10)
		         .forEach(System.out::println);
	}
	
	@Test
	public void fibonacciIterate() {
		Stream.iterate(new int[] {0,  1}, t -> new int[] {t[1], t[0] + t[1]})
		      .limit(10)
		      .map(t -> t[0])
		      .forEach(System.out::println);
	}
	
	@Test
	public void fibonacciTuplesIterate() {
		Stream.iterate(new int[] {0,  1}, t -> new int[] {t[1], t[0] + t[1]})
		      .limit(20)
		      .forEach(t -> System.out.println(String.format("(%d, %d)", t[0], t[1])));
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
	public void generate() {
		Stream.generate(Math::random)
		      .limit(5)
		      .forEach(System.out::println);
	}
	
	@Test
	public void iterate() {
		Stream.iterate(0,  n -> n + 2)
		      .limit(10)
		      .forEach(System.out::println);
	}
	
	@Test
	public void ofStream() {
		final Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
		stream.map(String::toUpperCase)
		      .forEach(System.out::println);
	}

}
