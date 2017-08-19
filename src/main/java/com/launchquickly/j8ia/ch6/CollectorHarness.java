package com.launchquickly.j8ia.ch6;

public class CollectorHarness {

	public static void main(final String[] args) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			final long start = System.nanoTime();
			// new PrimePartitioner().partitionPrimes(1_000_000);
			new PrimePartitioner().partitionPrimesWithCustomCollector(1_000_000);
			final long duration = (System.nanoTime() - start) / 1_000_000;
			if (duration < fastest) {
				fastest = duration;
			}
		}
		System.out.println(String.format("Fastest execution done in %d msecs", fastest));
	}

}
