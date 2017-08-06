package com.launchquickly.j8ia.ch3;

import static org.junit.Assert.assertEquals;

import java.util.function.Function;

import org.junit.Test;

public class TransformationPipelineTest {

	@Test
	public void addHeaderAddFooter() {
		final Function<String, String> addHeader = Letter::addHeader;
		final Function<String, String> transformationPipeline = addHeader.andThen(Letter::addFooter);

		assertEquals("From Raoul, Mario and Alan: labda Kind regards", transformationPipeline.apply("labda"));
	}

	@Test
	public void addHeaderCheckSpellingAddFooter() {
		final Function<String, String> addHeader = Letter::addHeader;
		final Function<String, String> transformationPipeline = addHeader.andThen(Letter::checkSpelling)
				                                                         .andThen(Letter::addFooter);

		assertEquals("From Raoul, Mario and Alan: lambda Kind regards", transformationPipeline.apply("labda"));
	}
}
