package com.launchquickly.j8ia.ch1;

import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class StreamTest {

	private List<Transaction> transactions;

	@Test
	public void classicFilterAndGroupBy() {
		final Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();

		for (Transaction transaction : this.transactions) {
			if (transaction.getPrice() > 1000) {
				final Currency currency = transaction.getCurrency();
				List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
				if (null == transactionsForCurrency) {
					transactionsForCurrency = new ArrayList<>();
					transactionsByCurrencies.put(currency, transactionsForCurrency);
				}
				transactionsForCurrency.add(transaction);
			}
		}

		System.out.println("Classic: " + transactionsByCurrencies);
	}

	@Test
	public void lambdaFilterAndGroupBy() {
		final Map<Currency, List<Transaction>> transactionsByCurrencies = this.transactions.stream()
				.filter((Transaction t) -> t.getPrice() > 1000).collect(groupingBy(Transaction::getCurrency));
		System.out.println("Lambda: " + transactionsByCurrencies);
	}

	@Before
	public void setUp() {
		this.transactions = new ArrayList<>();

		final Currency gbp = Currency.getInstance("GBP");
		final Currency eur = Currency.getInstance("EUR");
		final Currency nok = Currency.getInstance("NOK");
		final Currency usd = Currency.getInstance("USD");

		final Currency[] currencies = { gbp, eur, nok, usd };

		for (int i = 0; i < 10; i++) {
			final Currency currency = currencies[new Random().nextInt(4)];
			this.transactions.add(new Transaction(currency, new Random().nextInt(5000)));
		}
	}

}
