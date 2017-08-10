package com.launchquickly.j8ia.ch5;

import java.util.Arrays;
import java.util.List;

public class Transactions {

	public static List<Transaction> get() {
		final Trader raoul = new Trader("Raoul", "Cambridge");
		final Trader mario = new Trader("Mario", "Milan");
		final Trader alan = new Trader("Alan", "Cambridge");
		final Trader brian = new Trader("Brian", "Cambridge");

		return Arrays.asList(
				new Transaction(brian, 2011, 300), 
				new Transaction(raoul, 2012, 1000),
				new Transaction(raoul, 2011, 400), 
				new Transaction(mario, 2012, 710), 
				new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950));
	}

}
