package com.launchquickly.j8ia.ch5;

import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;

import org.junit.Test;

public class NumericStreamTest {

	@Test
	public void countEvenNumbers() {
		final IntStream evenNumbers = IntStream.rangeClosed(1, 100)
				                               .filter(n -> n % 2 == 0);

		assertEquals(50, evenNumbers.count());
	}

}
