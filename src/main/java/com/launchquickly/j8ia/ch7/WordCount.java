package com.launchquickly.j8ia.ch7;

import java.util.stream.Stream;

public class WordCount {
	
	public static int countWords(final Stream<Character> stream) {
		final WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
				                                      WordCounter::accumulate,
				                                      WordCounter::combine);
		return wordCounter.getCounter();
	}

}
