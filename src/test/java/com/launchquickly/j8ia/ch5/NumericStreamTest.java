package com.launchquickly.j8ia.ch5;

import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class NumericStreamTest {

	@Test
	public void countEvenNumbers() {
		final IntStream evenNumbers = IntStream.rangeClosed(1, 100)
				                               .filter(n -> n % 2 == 0);

		assertEquals(50, evenNumbers.count());
	}
	
	@Test
	public void pythagoreanTriples() {
		final Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
				                                           .boxed()
				                                           .flatMap(a ->
				                                               IntStream.rangeClosed(a, 100)
				                                                        .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
				                                        		        .mapToObj(b -> new int[] {a, b, (int) Math.sqrt(a*a + b*b)})
				                                        		   
				                                        		   );
		pythagoreanTriples.limit(5)
							.forEach(t -> System.out.println(String.format("%d, %d, %d", t[0], t[1], t[2])));
	}
	
	@Test
	public void pythagoreanTriples2() {
		final Stream<double[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
				                                           .boxed()
				                                           .flatMap(a ->
				                                               IntStream.rangeClosed(a, 100)
				                                        		        .mapToObj(b -> new double[] {a, b, Math.sqrt(a*a + b*b)})
				                                        		        .filter(t -> t[2] % 1 == 0)
				                                        		   
				                                        		   );
		pythagoreanTriples.limit(5)
							.forEach(t -> System.out.println(String.format("%2.1f, %2.1f, %2.1f", t[0], t[1], t[2])));
	}

}
