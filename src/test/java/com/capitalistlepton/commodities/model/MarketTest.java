package com.capitalistlepton.commodities.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MarketTest {

	private Market m1;
	private Company co1;
	private Company co2;
	private Company co3;
	private Company co4;
	
	
	@Before
	public void setUp() throws Exception {
		m1 = new Market();
		co1 = new Company("Test 1", 500);
		co2 = new Company("Test 2", 500);
		co3 = new Company("Test 3", 10);
		co4 = new Company("Test 4", 1);
		co4.addResource("STO", 48);
	}

	@Test
	public void testContainsActual() {
		assertTrue(m1.contains("LUM"));
	}
	
	@Test
	public void testContainsFake() {
		assertFalse(m1.contains("BLA"));
	}
	
	@Test
	public void testBuyProper() {
		assertTrue(MarketAction.BUY.perform(co1, m1, "LUM", 20) && co1.getBalance() == 480);
	}
	
	@Test
	public void testBuyLowFunds() {
		assertFalse(MarketAction.BUY.perform(co3, m1, "IRO", 10));
	}
	
	@Test
	public void testBuyTooMany() {
		assertFalse(MarketAction.BUY.perform(co1, m1, "LUM", 200));
	}
	
	@Test
	public void testSellProper() {
		assertTrue(MarketAction.SELL.perform(co4, m1, "STO", 45) && co4.getBalance() == 46);
	}
	
	@Test
	public void testSellNoRes() {
		assertFalse(MarketAction.SELL.perform(co2, m1, "LUM", 30));
	}
	
	@Test
	public void testSellTooFew() {
		assertFalse(MarketAction.SELL.perform(co4, m1, "STO", 90));
	}
}
