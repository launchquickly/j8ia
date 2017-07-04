package com.launchquickly.j8ia.ch1;

import static com.launchquickly.j8ia.ch1.Apple.filterApples;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class AppleTest {

	private List<Apple> inventory;

	@Test
	public void classicSort() {

		Collections.sort(this.inventory, new Comparator<Apple>() {
			@Override
			public int compare(final Apple o1, final Apple o2) {
				return o1.getWeight().compareTo(o2.getWeight());
			}
		});

		System.out.println("Classic: " + this.inventory);
	}

	@Test
	public void filterGreenApples() {
		final List<Apple> results = filterApples(this.inventory, Apple::isGreenApple);

		System.out.println("Green Apples: " + results);
	}

	@Test
	public void filterHeavyApples() {
		final List<Apple> results = filterApples(this.inventory, Apple::isHeavyApple);

		System.out.println("Heavy Apples: " + results);
	}

	@Test
	public void lambdaFilterBrownOrLightApples() {
		final List<Apple> results = filterApples(this.inventory,
				(Apple a) -> a.getWeight() < 80 || "brown".equals(a.getColor()));

		System.out.println("Lambda Brown or Light Apples: " + results);
	}

	@Test
	public void lambdaFilterLightApples() {
		final List<Apple> results = filterApples(this.inventory, (Apple a) -> a.getWeight() < 150);

		System.out.println("Lambda Light Apples: " + results);
	}

	@Test
	public void lambdaFilterRedApples() {
		final List<Apple> results = filterApples(this.inventory, (Apple a) -> "red".equals(a.getColor()));

		System.out.println("Lambda Red Apples: " + results);
	}

	@Test
	public void lambdaSort() {
		this.inventory.sort(comparing(Apple::getWeight));

		System.out.println("Lambda: " + this.inventory);
	}

	@Test
	public void parallelProcessingOfApples() {
		final List<Apple> heavyApples = this.inventory.parallelStream().filter((Apple a) -> a.getWeight() > 150)
				.collect(toList());

		System.out.println("Parallel Processing: " + heavyApples);
	}

	@Test
	public void sequentialProcessingOfApples() {
		final List<Apple> heavyApples = this.inventory.stream().filter((Apple a) -> a.getWeight() > 150)
				.collect(toList());

		System.out.println("Sequential Processing: " + heavyApples);
	}

	@Before
	public void setUp() {
		this.inventory = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			final String color;
			if (i % 2 == 0) {
				color = "red";
			} else if (i > 6) {
				color = "brown";
			} else {
				color = "green";
			}
			this.inventory.add(new Apple(color, new Random().nextInt(300)));
		}
	}

}
