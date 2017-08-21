package com.launchquickly.j8ia.ch7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinSum {

	public static long forkJoinSum(final long n) {
		final long[] numbers = LongStream.rangeClosed(1, n)
				                         .toArray();
		final ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
		return new ForkJoinPool().invoke(task);
	}

}
