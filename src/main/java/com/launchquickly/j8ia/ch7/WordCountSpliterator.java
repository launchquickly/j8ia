package com.launchquickly.j8ia.ch7;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCountSpliterator implements Spliterator<Character> {

	private final String string;
	private int currentChar = 0;

	public WordCountSpliterator(final String str) {
		this.string = str;
	}

	@Override
	public int characteristics() {
		return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
	}

	@Override
	public long estimateSize() {
		return this.string.length() - this.currentChar;
	}

	@Override
	public boolean tryAdvance(final Consumer<? super Character> action) {
		action.accept(this.string.charAt(this.currentChar++));
		return this.currentChar < this.string.length();
	}

	@Override
	public Spliterator<Character> trySplit() {
		final int currentSize = this.string.length() - this.currentChar;
		if (currentSize < 10) {
			return null;
		}
		for (int splitPos = currentSize / 2 + this.currentChar; splitPos < this.string.length(); splitPos++) {
			if (Character.isWhitespace(this.string.charAt(splitPos))) {
				final Spliterator<Character> spliterator = new WordCountSpliterator(
						this.string.substring(this.currentChar, splitPos));
				this.currentChar = splitPos;
				return spliterator;
			}
		}
		return null;
	}

}
