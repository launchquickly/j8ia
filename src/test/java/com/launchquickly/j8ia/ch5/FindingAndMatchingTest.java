package com.launchquickly.j8ia.ch5;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.launchquickly.j8ia.ch4.Dish;
import com.launchquickly.j8ia.ch4.Menu;

public class FindingAndMatchingTest {

	@Test
	public void anyVegetarians() {
		
		assertTrue(Menu.get().stream().anyMatch(Dish::isVegetarian));
	}
	
	@Test
	public void findAnyVegetarianDish() {
		final Optional<Dish> dish = Menu.get().stream()
												.filter(Dish::isVegetarian)
												.findAny();
		
		if (dish.isPresent()) {
			System.out.println(dish);
		}
	}
	
	@Test
	public void findFirstSquareDivisibleByThree() {
		final List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
		someNumbers.stream()
					.map(x -> x * x)
					.filter(x -> x % 3 == 0)
					.findFirst()
					.ifPresent(System.out::println);
	}
	
	@Test
	public void isHealthyMenu() {
		
		assertTrue(Menu.get().stream().allMatch(d -> d.getCalories() < 1000));
	}
	
	@Test
	public void isUnhealthyMenu() {
		
		assertTrue(Menu.get().stream().noneMatch(d -> d.getCalories() >= 1000));
	}

}
