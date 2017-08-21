package com.launchquickly.j8ia.ch7;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

	private static final long THRESHOLD = 10_000L;

	private static final long serialVersionUID = 1L;

	private final long[] numbers;
	private final int start;
	private final int end;

	public ForkJoinSumCalculator(final long[] nos) {
		this(nos, 0, nos.length);
	}

	private ForkJoinSumCalculator(final long[] nos, final int s, final int e) {
		this.numbers = nos;
		this.start = s;
		this.end = e;
	}

	@Override
	protected Long compute() {
		final int length = this.end - this.start;
		if (length < THRESHOLD) {
			return computeSequentially();
		}

		final ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(this.numbers, this.start,
				this.start + length / 2);
		leftTask.fork();
		final ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(this.numbers, this.start + length / 2,
				this.end);
		final Long rightResult = rightTask.compute();
		final Long leftResult = leftTask.join();
		return leftResult + rightResult;
	}

	private long computeSequentially() {
		long sum = 0;
		for (int i = this.start; i < this.end; i++) {
			sum += this.numbers[i];
		}
		return sum;
	}

}
