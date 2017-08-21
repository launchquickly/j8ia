package com.launchquickly.j8ia.ch7;

import static com.launchquickly.j8ia.ch7.ParallelStreamsHarness.measureSumPerf;

import org.junit.Test;

public class ForkJoinExample {

	@Test
	public void sumExample() {
		System.out.println(
				String.format("ForkJoin sum done in: %d msecs", measureSumPerf(ForkJoinSum::forkJoinSum, 10_000_000)));
	}

}
