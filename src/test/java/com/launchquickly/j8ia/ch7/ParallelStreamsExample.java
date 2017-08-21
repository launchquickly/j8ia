package com.launchquickly.j8ia.ch7;

import static com.launchquickly.j8ia.ch7.ParallelStreamsHarness.measureSumPerf;

import org.junit.Test;

public class ParallelStreamsExample {

	@Test
	public void runIterativeSum() {
		System.out.println(String.format("Iterative sum done in: %d msecs",
				measureSumPerf(ParallelStreams::iterativeSum, 10_000_000)));
	}

	@Test
	public void runParallelRangedSum() {
		System.out.println(String.format("Ranged parallel sum done in: %d msecs",
				measureSumPerf(ParallelStreams::parallelRangedSum, 10_000_000)));
	}
	
	@Test
	public void runParallelSum() {
		System.out.println(String.format("Parallel sum done in: %d msecs",
				measureSumPerf(ParallelStreams::parallelSum, 10_000_000)));
	}
	
	@Test
	public void runRangedSum() {
		System.out.println(String.format("Ranged sum done in: %d msecs",
				measureSumPerf(ParallelStreams::rangedSum, 10_000_000)));
	}

	@Test
	public void runSequentialSum() {
		System.out.println(String.format("Sequential sum done in: %d msecs",
				measureSumPerf(ParallelStreams::sequentialSum, 10_000_000)));
	}
	
	@Test
	public void runSideEffectParallelSum() {
		System.out.println(String.format("SideEffect parallel sum done in: %d msecs",
				measureSumPerf(ParallelStreams::sideEffectParallelSum, 10_000_000)));
	}
	
	@Test
	public void runSideEffectSum() {
		System.out.println(String.format("SideEffect sum done in: %d msecs",
				measureSumPerf(ParallelStreams::sideEffectSum, 10_000_000)));
	}

}
