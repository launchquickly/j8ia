package com.launchquickly.j8ia.ch7;

import java.util.function.Function;

public class ParallelStreamsHarness {

	public static long measureSumPerf(final Function<Long, Long> adder, final long n) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			final long start = System.nanoTime();
			final long sum = adder.apply(n);
			final long duration = (System.nanoTime() - start) / 1_000_000;
			System.out.println(String.format("Result %d", sum));
			if (duration < fastest) {
				fastest = duration;
			}
		}
		return fastest;
	}

}
