package com.launchquickly.j8ia.ch5;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.launchquickly.j8ia.ch4.Dish;
import com.launchquickly.j8ia.ch4.Menu;

public class MapTest {
	
	public static void printArray(final int[] values) {
		System.out.println(Arrays.toString(values));
	}
	
	@Test
	public void createNumberPairs() {
		final List<Integer> numbers1 = Arrays.asList(1, 2, 3);
		final List<Integer> numbers2 = Arrays.asList(3, 4);
		final List<int[]> pairs = numbers1.stream()
									       .flatMap(i -> numbers2.stream()
															       .map(j -> new int[] {i, j})
											        )
									       .collect(toList());
				
		pairs.stream()
				.forEach(MapTest::printArray);
	}
	
	@Test
	public void createNumberPairsDivBy3() {
		final List<Integer> numbers1 = Arrays.asList(1, 2, 3);
		final List<Integer> numbers2 = Arrays.asList(3, 4);
		final List<int[]> pairs = numbers1.stream()
									       .flatMap(i -> numbers2.stream()
									    		                   .filter(j -> (i + j) % 3 == 0)
															       .map(j -> new int[] {i, j})
											        )
									       .collect(toList());
				
		pairs.stream()
				.forEach(MapTest::printArray);
	}
	
	@Test
	public void flatMapWordsToLetters() {
		final String[] words = { "Goodbye", "World" };
		Arrays.stream(words)
				.map(word -> word.split(""))
				.flatMap(Arrays::stream)
				.distinct()
				.forEach(System.out::println);
	}
	
	@Test
	public void mapDishNameToLength() {
		Menu.get().stream()
				.map(Dish::getName)
				.map(String::length)
				.forEach(System.out::println);
	}
	
	@Test
	public void mapDishToName() {
		Menu.get().stream()
					.map(Dish::getName)
					.forEach(System.out::println);
	}
	
	@Test
	public void mapWordToLength() {
		final List<String> words = Arrays.asList("Java8", "Lambda", "In", "Action");
		words.stream()
				.map(String::length)
				.forEach(System.out::println);
	}

}
