package com.capitalistlepton.commodities.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CompanyTest {
	
	private static final String LUMBER = "LUM";
	private static final double THRESHOLD = 0.000001;
	
	private Company co1;
	private Company co2;
	private Company co3;

	@Before
	public void setUp() {
		co1 = new Company("Test 1", 500);
		co2 = new Company("Test 2", 500);
		co3 = new Company("Test 3", 500);
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

	@Test
	public void testAddResource() {
		co1.addResource(LUMBER, 45);
		assertEquals(45, co1.getAmount(LUMBER));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddResourceNegative() {
		co1.addResource(LUMBER, -45);
	}

	@Test
	public void testRemoveResourceProper() {
		co2.addResource(LUMBER, 45);
		co2.removeResource(LUMBER, 35);
		assertEquals(10, co2.getAmount(LUMBER));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveResourceTooMuch() {
		co2.addResource(LUMBER, 45);
		co2.removeResource(LUMBER, 55);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveResourceNegative() {
		co2.removeResource(LUMBER, -45);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveResourceEmpty() {
		co3.removeResource(LUMBER, 5);
	}

	@Test
	public void testAmountOf() {
		assertEquals(0, co3.getAmount(LUMBER));
	}
}
