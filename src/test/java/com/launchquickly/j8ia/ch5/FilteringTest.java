package com.launchquickly.j8ia.ch5;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.launchquickly.j8ia.ch4.Dish;
import com.launchquickly.j8ia.ch4.Menu;

public class FilteringTest {
	
	@Test
	public void filterUniqueElements() {
		final List<Integer> numbers =  Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		numbers.stream()
				.filter(i -> i % 2 == 0)
				.distinct()
				.forEach(System.out::println);
	}
	
	@Test
	public void filterVegetarians() {
		final List<Dish> vegetarianDishes = Menu.get().stream()
														.filter(Dish::isVegetarian)
														.collect(toList());
		
		System.out.println(vegetarianDishes);
	}
	
	@Test
	public void limitFilter() {
		Menu.get().stream()
				.filter(d -> d.getCalories() > 300)
				.limit(3)
				.forEach(System.out::println);
	}
	
	@Test
	public void skipAndFilter() {
		Menu.get().stream()
				.filter(d -> d.getCalories() > 300)
				.skip(2)
				.forEach(System.out::println);
	}


}
