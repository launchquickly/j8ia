package com.launchquickly.j8ia.ch3;

import java.util.Comparator;

import com.launchquickly.j8ia.ch1.Apple;

public class AppleComparator implements Comparator<Apple> {

	@Override
	public int compare(final Apple a1, final Apple a2) {
		return a1.getWeight().compareTo(a2.getWeight());
	}

}
