package com.launchquickly.j8ia.ch6;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
	
	private static boolean isPrime(final List<Integer> primes, final int candidate) {
		final int candidateRoot = (int) Math.sqrt((double) candidate); 
		return takeWhile(primes, i -> i <= candidateRoot)
				     .stream()
				     .noneMatch(i -> candidate % i == 0);
	}
	
	private static <A> List<A> takeWhile(final List<A> list, final Predicate<A> p) {
		int i = 0;
		for (A item : list) {
			if (!p.test(item)) {
				return list.subList(0, i);
			}
			i++;
		}
		return list;
	}

	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
			acc.get( isPrime(acc.get(true), candidate))
			   .add(candidate);
		};
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
	}

	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		// never used as algorithm is sequential
		return (Map<Boolean, List<Integer>> map1,
				Map<Boolean, List<Integer>> map2) -> {
					map1.get(true).addAll(map2.get(true));
					map1.get(false).addAll(map2.get(false));
					return map1;
				};
	}

	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		return Function.identity();
	}

	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		return () -> new HashMap<Boolean, List<Integer>>() {
			
			private static final long serialVersionUID = 1L;

		{
			put(true, new ArrayList<>());
			put(false, new ArrayList<>());
		}};
	}

}
