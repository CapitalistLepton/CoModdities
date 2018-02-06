package com.capitalistlepton.commodities.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class CompanyTest {
	
	private static final String LUMBER = "LUM";
	private static final double THRESHOLD = 0.000001;
	private static final Factory STEEL_FACTORY = new Factory("Steel Factory", Arrays.asList(Recipe.STEEL));
	
	private Company co1;
	private Company co2;

	@Before
	public void setUp() {
		co1 = new Company("Test 1", 500);
		co2 = new Company("Test 2", 500);
		co2.addFactory(STEEL_FACTORY);
	}

	@Test
	public void testGetBalance() {
		assertEquals(500, co1.getBalance(), THRESHOLD);
	}

	@Test
	public void testChangeBalance() {
		co1.changeBalance(30);
		co1.changeBalance(-50);
		assertEquals(480, co1.getBalance(), THRESHOLD);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testChangeBalanceNegative() {
		co1.changeBalance(-500.001);
		assertEquals(0, co1.getBalance(), THRESHOLD);
	}

	@Test
	public void testAddResource() {
		co1.addResource(LUMBER, 45);
		assertEquals(45, co1.amountOf(LUMBER));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddResourceNegative() {
		co1.addResource(LUMBER, -45);
	}

	@Test
	public void testRemoveResourceProper() {
		co1.addResource(LUMBER, 45);
		co1.removeResource(LUMBER, 35);
		assertEquals(10, co1.amountOf(LUMBER));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveResourceTooMuch() {
		co1.addResource(LUMBER, 45);
		co1.removeResource(LUMBER, 55);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveResourceNegative() {
		co1.removeResource(LUMBER, -45);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveResourceEmpty() {
		co1.removeResource(LUMBER, 5);
	}

	@Test
	public void testAmountOf() {
		assertEquals(0, co1.amountOf(LUMBER));
	}
	
	@Test
	public void testGetFactories() {
		Set<Factory> f = new HashSet<Factory>();
		f.add(STEEL_FACTORY);
		assertEquals(f, co2.getFactories());
	}
	
	@Test
	public void testGetFactoriesEmpty() {
		assertEquals(new HashSet<Factory>(), co1.getFactories());
	}
	
	@Test
	public void testStats() {
		assertEquals("Test 1: $500.00\nInv: CHA: 0 unit(s) COA: 0 unit(s) IRO: 0 unit(s) LUM: 0 unit(s) PIG: 0 unit(s) STL: 0 unit(s) STO: 0 unit(s) ", co1.stats());
	}
}
