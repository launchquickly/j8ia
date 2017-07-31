package com.launchquickly.j8ia.ch2;

import static com.launchquickly.j8ia.ch1.Apple.prettyPrintApple;
import static com.launchquickly.j8ia.ch2.Filter.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.launchquickly.j8ia.ch1.Apple;

public class FilterTest {

	private List<Apple> inventory;

	@Test
	public void printRedApples() {
		final List<Apple> redApples = filter(this.inventory, (Apple apple) -> "red".equals(apple.getColor()));

		prettyPrintApple(redApples, new AppleFancyFormatter());
	}

	@Before
	public void setUp() {
		this.inventory = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			final String color;
			if (i % 2 == 0) {
				color = "red";
			} else {
				color = "green";
			}
			this.inventory.add(new Apple(color, new Random().nextInt(300)));
		}
	}

}
