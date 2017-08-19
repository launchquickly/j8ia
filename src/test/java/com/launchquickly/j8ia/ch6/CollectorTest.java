package com.launchquickly.j8ia.ch6;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.launchquickly.j8ia.ch4.Dish;
import com.launchquickly.j8ia.ch4.Menu;

public class CollectorTest {
	
	@Test
	public void customerCollectorExample() {
		final List<Dish> dishes = Menu.get().stream()
				                            .collect(new ToListCollector<>());
		
		assertEquals(9, dishes.size());
	}

}
