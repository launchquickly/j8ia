package com.launchquickly.j8ia.ch1;

import java.util.Currency;

public class Transaction {

	private final Currency currency;
	private final Integer price;

	public Transaction(final Currency c, final int p) {
		this.currency = c;
		this.price = p;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public Integer getPrice() {
		return this.price;
	}

	@Override
	public String toString() {
		return "Transaction[currency=" + this.currency.getCurrencyCode() + ",price=" + this.price + "]";
	}

}
