package com.launchquickly.j8ia.ch5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.launchquickly.j8ia.ch4.Menu;

public class ReducingTest {
	
	private List<Integer> numbers;
	
	@Test
	public void countDishes() {
		final int count = Menu.get().stream()
										.map(d -> 1)
										.reduce(0, Integer::sum);
		assertEquals(9, count);
	}
	
	@Test
	public void maximum() {
		final Optional<Integer> max = this.numbers.parallelStream()
				                                  .reduce(Integer::max);
		
		assertEquals(Integer.valueOf(5), max.get());
	}
	
	@Test
	public void minimum() {
		final Optional<Integer> min = this.numbers.stream()
				                                  .reduce(Integer::min);
		
		assertEquals(Integer.valueOf(1), min.get());
	}
	
	@Test
	public void product() {
		final int product = this.numbers.parallelStream()
				                        .reduce(1, (a, b) -> a * b);
		
		assertEquals(120, product);
	}
	
	@Before
	public void setUp() {
		this.numbers = Arrays.asList(4, 2, 5, 1, 3);
	}
	
	@Test
	public void sum() {
		final int sum = this.numbers.stream()
				                    .reduce(0, (a, b) -> a + b);
		
		assertEquals(15, sum);
	}
	
	@Test
	public void sum2() {
		final int sum = this.numbers.stream()
				                    .reduce(0, Integer::sum);
		
		assertEquals(15, sum);
	}
	
	@Test
	public void sumOptional() {
		final List<Integer> emptyList = Collections.emptyList();
		final Optional<Integer> result = emptyList.parallelStream()
													.reduce(Integer::sum);
		
		assertFalse(result.isPresent());
	}

}
