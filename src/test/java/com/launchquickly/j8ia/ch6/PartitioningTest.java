package com.launchquickly.j8ia.ch6;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import com.launchquickly.j8ia.ch4.Dish;
import com.launchquickly.j8ia.ch4.Menu;

public class PartitioningTest {
	
	@Test
	public void collectingAndThenExample() {
		final Map<Boolean, Dish> mostCaloricType = Menu.get().stream()
				                                             .collect(partitioningBy(Dish::isVegetarian,
				                                                        		     collectingAndThen(
				                                                        		          maxBy(comparingInt(Dish::getCalories)), Optional::get
				                                                        		     )
				                                                      ));
		
		assertEquals("pizza", mostCaloricType.get(Boolean.TRUE).getName());
		assertEquals("pork", mostCaloricType.get(Boolean.FALSE).getName());
	}
	
	@Test
	public void partitioningMultiLevelExample() {
		final Map<Boolean, Map<Boolean, List<Dish>>> veggieDishesHighCal = Menu.get().stream()
				                                                                    .collect(partitioningBy(Dish::isVegetarian,
				                                                                    		                partitioningBy(d -> d.getCalories() > 500)));
		
		assertEquals(2, veggieDishesHighCal.get(Boolean.TRUE).get(Boolean.TRUE).size());
		assertEquals(2, veggieDishesHighCal.get(Boolean.TRUE).get(Boolean.FALSE).size());
		assertEquals(2, veggieDishesHighCal.get(Boolean.FALSE).get(Boolean.TRUE).size());
		assertEquals(3, veggieDishesHighCal.get(Boolean.FALSE).get(Boolean.FALSE).size());
	}
	
	@Test
	public void partitioningWithSubgroupCountExample() {
		final Map<Boolean, Long> veggieDishCount = Menu.get().stream()
				                                             .collect(partitioningBy(Dish::isVegetarian,
				                                            		                 counting()));
		
		assertEquals(Long.valueOf(4), veggieDishCount.get(Boolean.TRUE));
		assertEquals(Long.valueOf(5), veggieDishCount.get(Boolean.FALSE));
	}
	
	@Test
	public void partitionMenuExample() {
		final Map<Boolean, List<Dish>> partitionedMenu = Menu.get().stream()
				                                                   .collect(partitioningBy(Dish::isVegetarian));
		
		assertEquals(4, partitionedMenu.get(Boolean.TRUE).size());
		assertEquals(5, partitionedMenu.get(Boolean.FALSE).size());
	}
	
	@Test
	public void partitionWithGroupingByExample() {
		final Map<Boolean, Map<Dish.Type, List<Dish>>> veggieDishesByType = Menu.get().stream()
				                                                                      .collect(
				                                                                    		  partitioningBy(Dish::isVegetarian,
				                                                                    				         groupingBy(Dish::getType)));
		
		assertEquals(4, veggieDishesByType.get(Boolean.TRUE).get(Dish.Type.OTHER).size());
		assertEquals(2, veggieDishesByType.get(Boolean.FALSE).get(Dish.Type.FISH).size());
		assertEquals(3, veggieDishesByType.get(Boolean.FALSE).get(Dish.Type.MEAT).size());
	}

}
