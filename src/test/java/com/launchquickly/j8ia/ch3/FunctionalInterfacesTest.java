package com.launchquickly.j8ia.ch3;

import static com.launchquickly.j8ia.ch3.FunctionalInterfaceExample.filter;
import static com.launchquickly.j8ia.ch3.FunctionalInterfaceExample.forEach;
import static com.launchquickly.j8ia.ch3.FunctionalInterfaceExample.map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Test;

public class FunctionalInterfacesTest {

	@Test
	public void consumerExample() {
		final Consumer<Integer> printInt = (Integer i) -> System.out.println(i);
		forEach(Arrays.asList(1, 2, 3, 4, 5), printInt);
	}

	@Test
	public void functionExample() {
		final Function<String, Integer> strLengthFunc = (String s) -> s.length();
		final List<Integer> wordLengths = map(Arrays.asList("One", "Four", "Three"), strLengthFunc);

		assertEquals(3, wordLengths.size());
		assertTrue(wordLengths.stream().anyMatch(i -> i == 3));
		assertTrue(wordLengths.stream().anyMatch(i -> i == 4));
		assertTrue(wordLengths.stream().anyMatch(i -> i == 5));
	}

	@Test
	public void predicateExample() {
		final Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
		final List<String> nonEmpty = filter(Arrays.asList("", "Two", "", "Four"), nonEmptyStringPredicate);

		assertEquals(2, nonEmpty.size());
		assertTrue(nonEmpty.stream().anyMatch(s -> s.equals("Two")));
		assertTrue(nonEmpty.stream().anyMatch(s -> s.equals("Four")));
	}

}
