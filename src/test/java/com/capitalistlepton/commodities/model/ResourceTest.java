package com.capitalistlepton.commodities.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ResourceTest {
	
	private Resource lum;
	private Resource sto;
	private Resource iro;
	private Resource lumFake;

	@Before
	public void setUp() throws Exception {
		lum = new Resource("Lumber", "LUM", 5);
		sto = new Resource("Stone", "STO", 1);
		iro = new Resource("Iron", "IRO", 1);
		lumFake = new Resource("Fake", "LUM", 1012);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testResourceWrongPrice() {
		new Resource("Lies", "LIE", 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testResourceShortSymbol() {
		new Resource("Lies", "LI", 10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testResourceWrongCaseSymbol() {
		new Resource("Lies", "lie", 10);
	}
	
	@Test
	public void testGetName() {
		assertEquals("Lumber", lum.getName());
	}

	@Test
	public void testGetSym() {
		assertEquals("LUM", lum.getSym());
	}

	@Test
	public void testGetPrice() {
		assertEquals(5, lum.getPrice(), 0.01);
	}

	@Test
	public void testSetPriceProper() {
		sto.setPrice(5);
		assertEquals(5, sto.getPrice(), 0.01);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetPriceZero() {
		sto.setPrice(0);
	}	

	@Test
	public void testChangePriceProper() {
		sto.setPrice(0.01);
		sto.changePrice(1.99);
		assertEquals(2, sto.getPrice(), 0.01);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testChangePriceTooLow() {
		sto.setPrice(50);
		sto.changePrice(-50.01);
	}

	@Test
	public void testToString() {
		assertEquals("LUM: $5.00", lum.toString());
	}

	@Test
	public void testGreaterThan() {
		assertTrue(sto.compareTo(lum) > 0 && lum.compareTo(iro) > 0);
	}
	
	@Test
	public void testLessThan() {
		assertTrue(lum.compareTo(sto) < 0 && iro.compareTo(lum) < 0);
	}
	
	@Test
	public void testEqualTo() {
		assertTrue(lum.compareTo(lumFake) == 0);
	}

}
