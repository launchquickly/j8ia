package com.launchquickly.j8ia.ch1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.launchquickly.j8ia.ch2.AppleFormatter;

public class Apple {
	
	public static boolean isGreenApple(final Apple apple) {
		return "green".equals(apple.getColor());
	}

	public static boolean isHeavyApple(final Apple apple) {
		return apple.getWeight() > 150;
	}

	public static void prettyPrintApple(final List<Apple> inventory, final AppleFormatter formatter) {
		for (final Apple apple : inventory) {
			final String output = formatter.accept(apple);
			System.out.println(output);
		}
	}

	static List<Apple> filterApples(final List<Apple> inventory, final Predicate<Apple> p) {
		final List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	private final String color;
	private final Integer weight;

	public Apple(final String c, final int w) {
		this.color = c;
		this.weight = Integer.valueOf(w);
	}

	public String getColor() {
		return this.color;
	}

	public Integer getWeight() {
		return this.weight;
	}

	@Override
	public String toString() {
		return "Apple[color=" + this.color + ",weight=" + this.weight + "]";
	}

}
