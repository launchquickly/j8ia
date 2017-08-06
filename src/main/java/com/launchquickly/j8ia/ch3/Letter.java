package com.launchquickly.j8ia.ch3;

public class Letter {

	public static String addFooter(final String text) {
		return String.format("%s Kind regards", text);
	}

	public static String addHeader(final String text) {
		return String.format("From Raoul, Mario and Alan: %s", text);
	}

	public static String checkSpelling(final String text) {
		return text.replaceAll("labda", "lambda");
	}

}
