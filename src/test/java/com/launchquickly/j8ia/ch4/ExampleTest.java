package com.launchquickly.j8ia.ch4;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ExampleTest {
	
	@Test
	public void dishesByType() {
		final Map<Dish.Type, List<Dish>> dishesByType = Menu.get().stream()
													.collect(groupingBy(Dish::getType));
		
		System.out.println(dishesByType);
	}
	
	@Test
	public void intermediateOperations() {
		
		final List<String> names = Menu.get().stream()
												.filter(d -> {
													System.out.println("filtering" + d.getName());
													return d.getCalories() > 300;
												})
												.map(d -> {
													System.out.println("mapping" + d.getName());
													return d.getName();
												})
												.limit(3)
												.collect(toList());
		
		System.out.println(names);
	}

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

	@Test
	public void threeHighCalDishes() {
		
		final List<String> threeHighCalorieDishNames = Menu.get().stream()
																	.filter(d -> d.getCalories() > 300)
																	.map(Dish::getName)
																	.limit(3)
																	.collect(toList());
		
		System.out.println(threeHighCalorieDishNames);
	}

}
