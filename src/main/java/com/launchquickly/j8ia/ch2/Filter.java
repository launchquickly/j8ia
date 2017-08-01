package com.launchquickly.j8ia.ch2;

import java.util.ArrayList;
import java.util.List;

public class Filter {

	public static <T> List<T> filter(final List<T> list, final Predicate<T> p) {
		final List<T> result = new ArrayList<>();
		for (T e : list) {
			if (p.test(e)) {
				result.add(e);
			}
		}
		return result;
	}

}
