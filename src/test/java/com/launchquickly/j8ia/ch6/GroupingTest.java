package com.launchquickly.j8ia.ch6;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;

import com.launchquickly.j8ia.ch4.Dish;
import com.launchquickly.j8ia.ch4.Menu;

public class GroupingTest {
	
	@Test
	public void collectingAndThenExample() {
		final Map<Dish.Type, Dish> mostCaloricType = Menu.get().stream()
				                                                         .collect(groupingBy(Dish::getType,
				                                                        		             collectingAndThen(
				                                                        		            		 maxBy(comparingInt(Dish::getCalories)), Optional::get
				                                                        		            		 )
				                                                        		             ));
		assertEquals("salmon", mostCaloricType.get(Dish.Type.FISH).getName());
		assertEquals("pork", mostCaloricType.get(Dish.Type.MEAT).getName());
		assertEquals("pizza", mostCaloricType.get(Dish.Type.OTHER).getName());
	}

	@Test
	public void groupingByComplexClassifierExample() {
		final Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Menu.get().stream().collect(groupingBy(dish -> {
			                                                                                                        if (dish.getCalories() <= 400) { 
			                                                                                                        	return CaloricLevel.DIET; 
			                                                                                                        }
			                                                                                                        if (dish.getCalories() <= 700) { 
			                                                                                                        	return CaloricLevel.NORMAL; 
			                                                                                                        }
			                                                                                                        return CaloricLevel.FAT;
			                                                                                                      }
		                                                                                                 ));
		assertEquals(4, dishesByCaloricLevel.get(CaloricLevel.DIET).size());
		assertEquals(4, dishesByCaloricLevel.get(CaloricLevel.NORMAL).size());
		assertEquals(1, dishesByCaloricLevel.get(CaloricLevel.FAT).size());
	}
	
	@Test
	public void groupingByMaxByExample() {
		final Map<Dish.Type, Optional<Dish>> mostCaloricType = Menu.get().stream()
				                                                         .collect(groupingBy(Dish::getType,
				                                                        		             maxBy(comparingInt(Dish::getCalories))));
		assertEquals("salmon", mostCaloricType.get(Dish.Type.FISH).get().getName());
		assertEquals("pork", mostCaloricType.get(Dish.Type.MEAT).get().getName());
		assertEquals("pizza", mostCaloricType.get(Dish.Type.OTHER).get().getName());
	}
	
	@Test
	public void groupingByMethodReferenceExample() {
		final Map<Dish.Type, List<Dish>> dishesByType = Menu.get().stream().collect(groupingBy(Dish::getType));

		assertEquals(2, dishesByType.get(Dish.Type.FISH).size());
		assertEquals(3, dishesByType.get(Dish.Type.MEAT).size());
		assertEquals(4, dishesByType.get(Dish.Type.OTHER).size());
	}
	
	@Test
	public void groupingByMultilevelExample() {
		final Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = Menu.get().stream().collect(
				                                                                              groupingBy(Dish::getType,
				                                                                                         groupingBy(dish -> {
			                                                                                                                   if (dish.getCalories() <= 400) { 
			                                                                                                        	         return CaloricLevel.DIET; 
			                                                                                                                   }
			                                                                                                                   if (dish.getCalories() <= 700) { 
			                                                                                                        	         return CaloricLevel.NORMAL; 
			                                                                                                                   }
			                                                                                                                   return CaloricLevel.FAT;
			                                                                                                                 }
		                                                                                                 )));
		assertEquals(1, dishesByTypeCaloricLevel.get(Dish.Type.FISH).get(CaloricLevel.DIET).size());
		assertEquals(1, dishesByTypeCaloricLevel.get(Dish.Type.FISH).get(CaloricLevel.NORMAL).size());
		assertEquals(1, dishesByTypeCaloricLevel.get(Dish.Type.MEAT).get(CaloricLevel.DIET).size());
		assertEquals(2, dishesByTypeCaloricLevel.get(Dish.Type.OTHER).get(CaloricLevel.DIET).size());
	}
	
	@Test
	public void groupingByWithMappingExample() {
		final Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = Menu.get().stream()
				                                                                .collect(
				                                                                		groupingBy(Dish::getType, mapping(
				                                                                				dish -> {
                                                                                                    if (dish.getCalories() <= 400) { 
                                                                                         	         return CaloricLevel.DIET; 
                                                                                                    }
                                                                                                    if (dish.getCalories() <= 700) { 
                                                                                         	         return CaloricLevel.NORMAL; 
                                                                                                    }
                                                                                                    return CaloricLevel.FAT;
                                                                                                  },
				                                                                				toSet()
				                                                                	    )));
		
		assertEquals(2, caloricLevelsByType.get(Dish.Type.FISH).size());
		assertEquals(3, caloricLevelsByType.get(Dish.Type.MEAT).size());
		assertEquals(2, caloricLevelsByType.get(Dish.Type.OTHER).size());
	}
	
	@Test
	public void groupingByWithMappingUsingHashSetExample() {
		final Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = Menu.get().stream()
				                                                                .collect(
				                                                                		groupingBy(Dish::getType, mapping(
				                                                                				dish -> {
                                                                                                    if (dish.getCalories() <= 400) { 
                                                                                         	         return CaloricLevel.DIET; 
                                                                                                    }
                                                                                                    if (dish.getCalories() <= 700) { 
                                                                                         	         return CaloricLevel.NORMAL; 
                                                                                                    }
                                                                                                    return CaloricLevel.FAT;
                                                                                                  },
				                                                                				toCollection(HashSet::new)
				                                                                	    )));
		
		assertEquals(2, caloricLevelsByType.get(Dish.Type.FISH).size());
		assertEquals(3, caloricLevelsByType.get(Dish.Type.MEAT).size());
		assertEquals(2, caloricLevelsByType.get(Dish.Type.OTHER).size());
	}
	
	@Test
	public void groupingByWithReducingArgExample() {
		final Map<Dish.Type, Integer> totalCaloriesByType = Menu.get().stream()
				                                                      .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
		
		assertEquals(Integer.valueOf(750), totalCaloriesByType.get(Dish.Type.FISH));
		assertEquals(Integer.valueOf(1900), totalCaloriesByType.get(Dish.Type.MEAT));
		assertEquals(Integer.valueOf(1550), totalCaloriesByType.get(Dish.Type.OTHER));
	}
	
	@Test
	public void groupingByWithSubgroupsExample() {
		final Map<Dish.Type, Long> typesCount = Menu.get().stream()
				                                          .collect(groupingBy(Dish::getType, counting()));
		
		assertEquals(Long.valueOf(2), typesCount.get(Dish.Type.FISH));
		assertEquals(Long.valueOf(3), typesCount.get(Dish.Type.MEAT));
		assertEquals(Long.valueOf(4), typesCount.get(Dish.Type.OTHER));
	}

}
