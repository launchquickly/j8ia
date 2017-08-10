package com.launchquickly.j8ia.ch5;

public class Transaction {

	private final Trader trader;
	private final int year;
	private final int value;

	public Transaction(final Trader t, final int y, final int v) {
		this.trader = t;
		this.year = y;
		this.value = v;
	}

	public Trader getTrader() {
		return this.trader;
	}

	public int getValue() {
		return this.value;
	}

	public int getYear() {
		return this.year;
	}

	@Override
	public String toString() {
		return String.format("{%s ,year: %d, value: %d}", this.trader, this.year, this.value);
	}

}
