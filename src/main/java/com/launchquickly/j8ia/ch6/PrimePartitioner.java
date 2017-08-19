package com.launchquickly.j8ia.ch6;

import static java.util.stream.Collectors.partitioningBy;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class PrimePartitioner {

	Map<Boolean, List<Integer>> partitionPrimes(final int n) {
		return IntStream.rangeClosed(2, n)
				        .boxed()
				        .collect(
				        		partitioningBy(candidate -> isPrime(candidate)));
	}

	private boolean isPrime(final int candidate) {
		final int candidateRoot = (int) Math.sqrt((double) candidate);
		return IntStream.rangeClosed(2, candidateRoot)
				        .noneMatch(i -> candidate % i == 0);
	}

}
