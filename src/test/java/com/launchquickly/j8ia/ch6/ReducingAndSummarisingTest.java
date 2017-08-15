package com.launchquickly.j8ia.ch6;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;

import org.junit.Test;

import com.launchquickly.j8ia.ch4.Dish;
import com.launchquickly.j8ia.ch4.Menu;

public class ReducingAndSummarisingTest {

	@Test
	public void averagingIntExample() {
		final double avgCalories = Menu.get().stream().collect(averagingInt(Dish::getCalories));
		
		assertEquals(466.66, avgCalories, 0.01);
	}

	@Test
	public void countingExample() {
		final long noOfDishes = Menu.get().stream().collect(counting());

		assertEquals(9l, noOfDishes);
	}

	@Test
	public void countStreamExample() {
		final long noOfDishes = Menu.get().stream().count();

		assertEquals(9l, noOfDishes);
	}

	@Test
	public void joiningDelimitedExample() {
		final String menu = Menu.get().stream().map(Dish::getName).collect(joining(", "));
		
		assertEquals("pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon", menu);
	}
	
	@Test
	public void joiningSimpleExample() {
		final String shortMenu = Menu.get().stream().map(Dish::getName).collect(joining());
		
		assertEquals("porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon", shortMenu);
	}
	
	@Test
	public void maxByExample() {
		final Comparator<Dish> calorieComparator = Comparator.comparingInt(Dish::getCalories);
		final Optional<Dish> mostCaloriesDish = Menu.get().stream()
				                                          .collect(maxBy(calorieComparator));

		assertTrue(mostCaloriesDish.isPresent());
		assertEquals("pork", mostCaloriesDish.get().getName());
	}
	
	@Test
	public void minByExample() {
		final Comparator<Dish> calorieComparator = Comparator.comparingInt(Dish::getCalories);
		final Optional<Dish> mostCaloriesDish = Menu.get().stream()
				                                          .collect(minBy(calorieComparator));

		assertTrue(mostCaloriesDish.isPresent());
		assertEquals("season fruit", mostCaloriesDish.get().getName());
	}
	
	@Test
	public void reducingFindMostCaloriesExample() {
		final Optional<Dish> mostCaloriesDish = Menu.get().stream()
				                                          .collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
		
		assertTrue(mostCaloriesDish.isPresent());
		assertEquals("pork", mostCaloriesDish.get().getName());
	}
	
	@Test
	public void reducingToTotalCaloriesExample() {
		final int totalCalories = Menu.get().stream()
				                            .collect(reducing(0, Dish::getCalories, Integer::sum));
		
		assertEquals(4200, totalCalories);
	}
	
	@Test
	public void summarizingIntExample() {
		final IntSummaryStatistics menuStatistics = Menu.get().stream()
				                                              .collect(summarizingInt(Dish::getCalories));
		
		assertEquals(9l, menuStatistics.getCount());
		assertEquals(4200, menuStatistics.getSum());
		assertEquals(466.66, menuStatistics.getAverage(), 0.01);
		assertEquals(800, menuStatistics.getMax());
		assertEquals(120, menuStatistics.getMin());
	}
	
	@Test
	public void summingIntExample() {
		final int totalCalories = Menu.get().stream()
				                            .collect(summingInt(Dish::getCalories));

		assertEquals(4200, totalCalories);
	}
	
	@Test
	public void sumStreamExample() {
		final int totalCalories = Menu.get().stream()
				                            .mapToInt(Dish::getCalories)
				                            .sum();
		
		assertEquals(4200, totalCalories);
	}

}
