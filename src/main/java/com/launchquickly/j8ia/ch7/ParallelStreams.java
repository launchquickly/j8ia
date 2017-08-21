package com.launchquickly.j8ia.ch7;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams {

	public static long iterativeSum(final long n) {
		long result = 0;
		for (long i = 1L; i <= n; i++) {
			result += i;
		}
		return result;
	}

	public static long parallelRangedSum(final long n) {
		return LongStream.rangeClosed(1L, n)
				         .parallel()
				         .reduce(0L, Long::sum);
	}
	
	public static long parallelSum(final long n) {
		return Stream.iterate(1L, i -> i + 1)
				     .limit(n)
				     .parallel()
				     .reduce(0L, Long::sum);
	}
	
	public static long rangedSum(final long n) {
		return LongStream.rangeClosed(1L, n)
				         .reduce(0L, Long::sum);
	}

	public static long sequentialSum(final long n) {
		return Stream.iterate(1L, i -> i + 1)
				     .limit(n)
				     .reduce(0L, Long::sum);
	}
	
	public static long sideEffectParallelSum(final long n) {
		final Accumulator accumulator = new Accumulator();
		LongStream.rangeClosed(1L, n)
		          .parallel()
		          .forEach(accumulator::add);
		return accumulator.total;
	}
	
	public static long sideEffectSum(final long n) {
		final Accumulator accumulator = new Accumulator();
		LongStream.rangeClosed(1L, n)
		          .forEach(accumulator::add);
		return accumulator.total;
	}

}
class Accumulator {
	
	long total = 0;
	
	public void add(final long value) {
		this.total += value;
	}
}
