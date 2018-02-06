package com.capitalistlepton.commodities.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for Resource.
 *
 * @author Zane Littrell
 */
public class ResourceTest { // NOPMD
  /** Threshold for comparing doubles. */
  private static final double THRESHOLD = 0.000001;

  /** Lumber resource. */
  private Resource lum;
  /** Stone resource. */
  private Resource sto;
  /** Iron Resource. */
  private Resource iro;
  /** Resource similar to lumber but different. */
  private Resource lumFake;

  /**
   * Set up before each test.
   */
  @Before
  public void setUp() {
    lum = new Resource("Lumber", "LUM", 5);
    sto = new Resource("Stone", "STO", 1);
    iro = new Resource("Iron", "IRO", 1);
    lumFake = new Resource("Fake", "LUM", 1012);
  }

  /**
   * Test constructor with invalid price.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testResourceWrongPrice() {
    new Resource("Lies", "LIE", 0);
  }

  /**
   * Test constructor with invalid symbol length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testResourceShortSymbol() {
    new Resource("Lies", "LI", 10);
  }

  /**
   * Test constructor with invalid lowercase symbol.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testResourceWrongCaseSymbol() {
    new Resource("Lies", "lie", 10);
  }

  /**
   * Test getName method.
   */
  @Test
  public void testGetName() {
    assertEquals("Lumber", lum.getName());
  }

  /**
   * Test getSym method.
   */
  @Test
  public void testGetSym() {
    assertEquals("LUM", lum.getSym());
  }

  /**
   * Test getPrice method.
   */
  @Test
  public void testGetPrice() {
    assertEquals(5, lum.getPrice(), THRESHOLD);
  }

  /**
   * Test setPrice method.
   */
  @Test
  public void testSetPriceProper() {
    sto.setPrice(5);
    assertEquals(5, sto.getPrice(), THRESHOLD);
  }

  /**
   * Test setPrice method with invalid price.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetPriceZero() {
    sto.setPrice(0);
  }
  
  /**
   * Test changePrice method.
   */
  @Test
  public void testChangePriceProper() {
    sto.setPrice(0.01);
    sto.changePrice(1.99);
    assertEquals(2, sto.getPrice(), THRESHOLD);
  }

  /**
   * Test changePrice method with invalid price.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangePriceTooLow() {
    sto.setPrice(50);
    sto.changePrice(-50.01);
  }

  /**
   * Test toString method.
   */
  @Test
  public void testToString() {
    assertEquals("LUM: $5.00", lum.toString());
  }

  /**
   * Test compareTo method higher position.
   */
  @Test
  public void testGreaterThan() {
    assertTrue(sto.compareTo(lum) > 0);
    assertTrue(lum.compareTo(iro) > 0);
  }

  /**
   * Test compareTo method lower position.
   */
  @Test
  public void testLessThan() {
    assertTrue(lum.compareTo(sto) < 0);
    assertTrue(iro.compareTo(lum) < 0);
  }

  /**
   * Test compareTo method same position.
   */
  @Test
  public void testEqualTo() {
    assertEquals(0, lum.compareTo(lumFake));
  }

}
