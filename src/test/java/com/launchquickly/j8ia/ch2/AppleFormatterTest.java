package com.launchquickly.j8ia.ch2;

import static com.launchquickly.j8ia.ch1.Apple.prettyPrintApple;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.launchquickly.j8ia.ch1.Apple;

public class AppleFormatterTest {
	
	private List<Apple> inventory;
	
	@Test
	public void printUsingFancyFormatter() {
		prettyPrintApple(this.inventory, new AppleFancyFormatter());
	}
	
	@Test
	public void printUsingSimpleFormatter() {
		prettyPrintApple(this.inventory, new AppleSimpleFormatter());
	}
	
	@Before
	public void setUp() {
		this.inventory = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			final String color;
			if (i % 2 == 0) {
				color = "red";
			} else {
				color = "green";
			}
			this.inventory.add(new Apple(color, new Random().nextInt(300)));
		}
	}

}
