package com.launchquickly.j8ia.ch3;

import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;

import com.launchquickly.j8ia.ch1.Apple;

public class AppleSortingTest {

	private List<Apple> inventory;

	@Before
	public void setUp() {

		final List<Integer> weights = Arrays.asList(21, 14, 5, 29);
		final Function<Integer, Apple> c = Apple::new;

		this.inventory = new ArrayList<>();
		for (Integer weight : weights) {
			this.inventory.add(c.apply(weight));
		}
	}

	@Test
	public void step1WithExplicitComparator() {
		this.inventory.sort(new AppleComparator());

		System.out.println(this.inventory);
	}

	@Test
	public void step2WithAnonymoutClass() {
		this.inventory.sort(new Comparator<Apple>() {
			public int compare(final Apple a1, final Apple a2) {
				return a1.getWeight().compareTo(a2.getWeight());
			}
		});

		System.out.println(this.inventory);
	}

	@Test
	public void step3_1WithLambdaExpression() {
		this.inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

		System.out.println(this.inventory);
	}

	@Test
	public void step3_2WithLambdaExpressionInferringType() {
		this.inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

		System.out.println(this.inventory);
	}

	@Test
	public void step3_3WithLambdaExpressionAndComparingHelper() {
		this.inventory.sort(comparing((a) -> a.getWeight()));

		System.out.println(this.inventory);
	}

	@Test
	public void step4WithLambdaExpressionAndMethodRef() {
		this.inventory.sort(comparing(Apple::getWeight));

		System.out.println(this.inventory);
	}

}
