package com.launchquickly.j8ia.ch5;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class PuttingIntoPracticeTest {
	
	@Test
	public void _1_FindAllTransactionsFor2011SortedByValueAscending() {
		final List<Transaction> result = Transactions.get().stream()
															.filter(t -> t.getYear() == 2011)
															.sorted(comparing(Transaction::getValue))
															.collect(toList());
		assertEquals(2, result.size());
		for (Transaction t : result) {
			assertEquals(2011, t.getYear());
		}
		assertEquals("Brian", result.get(0).getTrader().getName());
		assertEquals(300, result.get(0).getValue());
		assertEquals("Raoul", result.get(1).getTrader().getName());
		assertEquals(400, result.get(1).getValue());
	}
	
	@Test
	public void _2_FindUniqueCities() {
		
		final List<String> result = Transactions.get().stream()
											.map(t -> t.getTrader().getCity())
											.distinct()
											.collect(toList());
		
		assertEquals(2, result.size());
		assertTrue(result.stream().anyMatch(t -> t.equals("Cambridge")));
		assertTrue(result.stream().anyMatch(t -> t.equals("Milan")));
	}
	
	@Test
	public void _3_FindAllCambridgeTradersSortedByName() {
		final List<Transaction> result = Transactions.get().stream()
															.filter(t -> "Cambridge".equals(t.getTrader().getCity()))
															.sorted((t1, t2) -> t1.getTrader().getName().compareTo(t2.getTrader().getName()))
															.collect(toList());
		assertEquals(4, result.size());
		for (Transaction t : result) {
			assertEquals("Cambridge", t.getTrader().getCity());
		}
		assertEquals("Alan", result.get(0).getTrader().getName());
		assertEquals(950, result.get(0).getValue());
		assertEquals("Brian", result.get(1).getTrader().getName());
		assertEquals(300, result.get(1).getValue());
		
		assertEquals("Raoul", result.get(2).getTrader().getName());
		assertEquals(1000, result.get(2).getValue());
		assertEquals("Raoul", result.get(3).getTrader().getName());
		assertEquals(400, result.get(3).getValue());
	}
	
	@Test
	public void _4_BuildAlphabeticalStringFromTradersName() {
		final String result = Transactions.get().stream()
							                     .map(t -> t.getTrader().getName())
							                     .distinct()
							                     .sorted((t1, t2) -> t1.compareTo(t2))
							                     .reduce("", (a, b) -> a + b);
		
		assertEquals("AlanBrianMarioRaoul", result);			
	}
	
	@Test
	public void _5_AreThereAnyTradersFromMilan() {
		
		assertTrue(Transactions.get().stream().anyMatch(t -> "Milan".equals(t.getTrader().getCity())));
	}
	
	@Test
	public void _6_PrintCambridgeTradersTransactionValues() {
		Transactions.get().stream()
		                  .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
		                  .map(v -> v.getValue())
		                  .forEach(System.out::println);
	}
	
}
