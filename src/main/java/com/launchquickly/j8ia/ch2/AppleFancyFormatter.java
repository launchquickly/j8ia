package com.launchquickly.j8ia.ch2;

import com.launchquickly.j8ia.ch1.Apple;

public class AppleFancyFormatter implements AppleFormatter {

	@Override
	public String accept(final Apple apple) {
		final String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
		return String.format("A %s %s apple", characteristic, apple.getColor());
	}

}
