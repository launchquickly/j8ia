package com.launchquickly.j8ia.ch7;

public class WordCounter {

	private final int counter;
	private final boolean lastSpace;

	public WordCounter(final int c, final boolean lstSpc) {
		this.counter = c;
		this.lastSpace = lstSpc;
	}

	public WordCounter accumulate(final Character c) {
		if (Character.isWhitespace(c)) {
			return this.lastSpace ? 
					         this : 
					         new WordCounter(this.counter, true);
		}
		return this.lastSpace ? 
				         new WordCounter(this.counter + 1, false) : 
				         this;
	}

	public WordCounter combine(final WordCounter wc) {
		return new WordCounter(this.counter + wc.counter, wc.lastSpace);
	}

	public int getCounter() {
		return this.counter;
	}
}
