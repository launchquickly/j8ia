package com.launchquickly.j8ia.ch2;

import com.launchquickly.j8ia.ch1.Apple;

public class AppleSimpleFormatter implements AppleFormatter {

	@Override
	public String accept(final Apple apple) {
		return String.format("An apple of %dg", apple.getWeight());
	}

}
