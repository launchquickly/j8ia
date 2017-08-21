package com.launchquickly.j8ia.ch7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.junit.Test;

public class SpliteratorExample {
	
	private final String SENTENCE = " Nel   mezzo del cammin  di nostra  vita " +
	                                "mi   ritrovai in una   selva oscura" +
			                        " che la  dritta via era   smarrita ";
	
	@Test
	public void parallelBrokenWordCount() {
		final Stream<Character> stream = IntStream.range(0, SENTENCE.length())
				                                  .mapToObj(SENTENCE::charAt);
		
		assertNotEquals(19, WordCount.countWords(stream.parallel()));
	}
	
	@Test
	public void parallelWordCountWithCustomeSpliterator() {
		final Spliterator<Character> spliterator = new WordCountSpliterator(SENTENCE);
		final boolean parallel = true;
		final Stream<Character> stream = StreamSupport.stream(spliterator, parallel);
		
		assertEquals(19, WordCount.countWords(stream));
	}
	
	@Test
	public void sequentialWordCount() {
		final Stream<Character> stream = IntStream.range(0, SENTENCE.length())
				                                  .mapToObj(SENTENCE::charAt);
		
		assertEquals(19, WordCount.countWords(stream));
	}

}
