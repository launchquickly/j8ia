package com.launchquickly.j8ia.ch3;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfaceExample {

	public static <T> List<T> filter(final List<T> list, final Predicate<T> p) {
		final List<T> results = new ArrayList<>();
		for (T s : list) {
			if (p.test(s)) {
				results.add(s);
			}
		}
		return results;
	}

	public static <T> void forEach(final List<T> list, final Consumer<T> c) {
		for (T i : list) {
			c.accept(i);
		}
	}

	public static Function<BufferedReader, String> functionThatHandlesCheckedEx() {
		return (BufferedReader b) -> {
			try {
				return b.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		};
	}

	public static <T, R> List<R> map(final List<T> list, final Function<T, R> f) {
		final List<R> results = new ArrayList<>();
		for (T s : list) {
			results.add(f.apply(s));
		}
		return results;
	}

}
