package com.launchquickly.j8ia.ch3;

import static com.launchquickly.j8ia.ch1.Apple.map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

import com.launchquickly.j8ia.ch1.Apple;

public class MethodReferenceTest {
	
	@Test
	public void buildApples() {
		final List<Integer> weights = Arrays.asList(7, 3, 4, 10);
		final List<Apple> apples = map(weights, Apple::new);
		
		
		assertEquals(4, apples.size());
		for (Integer w : weights) {
			assertTrue(apples.stream().anyMatch(a -> a.getWeight().equals(w)));
		}
	}
	
	@Test
	public void buildFruitLater() {
		final Map<String, Function<Integer, Fruit>> map = new HashMap<>();
		map.put("apple", Apple::new);
		map.put("orange", Orange::new);
		
		assertNotNull(map.get("apple").apply(23));
		assertNotNull(map.get("orange").apply(35));
		assertNotNull(map.get("apple").apply(62));
	}
	
	@Test
	public void defaultConstructorRef() {
		final Supplier<Apple> c1 = Apple::new;
		final Apple a1 = c1.get();
		
		assertNotNull(a1);
	}
	
	@Test
	public void instanceMethodOfArbitaryTypeExample() {
		final List<String> str = Arrays.asList("a", "b", "A", "B");
		str.sort(String::compareToIgnoreCase);
		
		// equivalent of:
		// str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
		
		assertEquals("a", str.get(0));
		assertEquals("A", str.get(1));
		assertEquals("b", str.get(2));
		assertEquals("B", str.get(3));
	}
	
	@Test
	public void nonDefaultConstructorRef() {
		final BiFunction<String, Integer, Apple> c = Apple::new;
		final Apple a = c.apply("green", 10);
		
		assertNotNull(a);
	}
	
	@Test
	public void staticMethodExample() {
		final String str = "28";
		final Function<String, Integer> stringToInteger = Integer::parseInt;
		
		// equivalent of:
		// final Function<String, Integer> stringToInteger = (String s) -> Integer.parseInt(s);
		
		assertEquals(Integer.valueOf(28), stringToInteger.apply(str));
	}
	
	

}
