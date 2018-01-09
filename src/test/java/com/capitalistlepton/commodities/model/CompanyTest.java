package com.capitalistlepton.commodities.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CompanyTest {
	
	private static final String LUMBER = "LUM";
	
	private Company co1;
	private Company co2;
	private Company co3;

	@Before
	public void setUp() throws Exception {
		co1 = new Company("Test 1", 500);
		co2 = new Company("Test 2", 500);
		co3 = new Company("Test 3", 500);
	}

	@Test
	public void testGetBalance() {
		assertEquals(500, co1.getBalance(), 0.01);
	}

	@Test
	public void testChangeBalance() {
		co1.changeBalance(30);
		co1.changeBalance(-50);
		assertEquals(480, co1.getBalance(), 0.01);
	}

	@Test
	public void testAddResource() {
		try {
			co1.addResource(LUMBER, -45);
			fail("Exception not thrown");
		} catch (IllegalArgumentException e) {}
		co1.addResource(LUMBER, 45);
		assertEquals(45, co1.getAmount(LUMBER));
	}

	@Test
	public void testRemoveResourceProper() {
		co2.addResource(LUMBER, 45);
		try {
			co2.removeResource(LUMBER, -45);
			fail("Exception not thrown");
		} catch (IllegalArgumentException e) {}
		co2.removeResource(LUMBER, 45);
		assertEquals(0, co2.getAmount(LUMBER));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveResourceEmpty() {
		co3.removeResource(LUMBER, -45);
	}

	@Test
	public void testAmountOf() {
		assertEquals(0, co3.getAmount(LUMBER));
	}
}
