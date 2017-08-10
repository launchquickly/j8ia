package com.launchquickly.j8ia.ch5;

public class Trader {

	private final String name;
	private final String city;

	public Trader(final String n, final String c) {
		this.name = n;
		this.city = c;
	}

	public String getCity() {
		return this.city;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return String.format("Trader: %s in %s", this.name, this.city);
	}

}
