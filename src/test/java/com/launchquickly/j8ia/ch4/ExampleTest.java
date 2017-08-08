package com.launchquickly.j8ia.ch4;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ExampleTest {

	@Test
	public void nonStreamExample() {
		final List<Dish> lowCaloricDishes = new ArrayList<>();
		for (Dish d : Menu.get()) {
			if (d.getCalories() < 400) {
				lowCaloricDishes.add(d);
			}
		}

		Collections.sort(lowCaloricDishes, (d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()));

		final List<String> lowCaloricDishesName = new ArrayList<>();
		for (Dish d : lowCaloricDishes) {
			lowCaloricDishesName.add(d.getName());
		}

		System.out.println(lowCaloricDishes);
	}

	@Test
	public void parallelStreamExample() {
		final List<String> lowCaloricDishesName = Menu.get().parallelStream()
																.filter(d -> d.getCalories() < 400)
																.sorted(comparing(Dish::getCalories))
																.map(Dish::getName)
																.collect(toList());

		System.out.println(lowCaloricDishesName);
	}

	@Test
	public void streamExample() {
		final List<String> lowCaloricDishesName = Menu.get().stream()
																.filter(d -> d.getCalories() < 400)
																.sorted(comparing(Dish::getCalories))
																.map(Dish::getName)
																.collect(toList());

		System.out.println(lowCaloricDishesName);
	}

}
