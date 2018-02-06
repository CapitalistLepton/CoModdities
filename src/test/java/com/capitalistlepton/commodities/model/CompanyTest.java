package com.capitalistlepton.commodities.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for the Company class.
 *
 * @author Zane Littrell
 */
public class CompanyTest { // NOPMD

  /** Lumber symbol. */
  private static final String LUMBER = "LUM";
  /** Threshold for determining equals between doubles. */
  private static final double THRESHOLD = 0.000001;
  /** Steel factory. */
  private static final Factory STEEL_FACTORY = new Factory("Steel Factory", 
      Arrays.asList(Recipe.STEEL));

  /** Company 1 for testing. */
  private Company co1;
  /** Company 2 for testing. */
  private Company co2;

  /**
   * Run before each test method.
   */
  @Before
  public void setUp() {
    co1 = new Company("Test 1", 500);
    co2 = new Company("Test 2", 500);
    co2.addFactory(STEEL_FACTORY);
  }
  
  /**
   * Tests the constructor.
   */
  @Test
  public void testCompany() {
    assertEquals("Test 1", co1.getName());
    assertEquals(500, co1.getBalance(), THRESHOLD);
    assertEquals(new HashSet<>(), co1.getFactories());
  }
  
  /**
   * Tests the constructor with a null name.
   */
  @Test (expected = NullPointerException.class)
  public void testCompanyNullName() {
    new Company(null, 500);
  }
  
  /**
   * Tests the constructor with an empty name.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCompanyEmptyName() {
    new Company("", 500);
  }
  
  /**
   * Tests the constructor with a negative balance.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCompanyNegBal() {
    new Company("Fail", -500);
  }
  
  /**
   * Tests the changeBalanace and implicitly tests getBalance methods.
   */
  @Test
  public void testChangeBalance() {
    co1.changeBalance(30);
    co1.changeBalance(-50);
    assertEquals(480, co1.getBalance(), THRESHOLD);
  }

  /**
   * Tests changeBalance with a negative parameter.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testChangeBalanceNegative() {
    co1.changeBalance(-500.001);
    assertEquals(0, co1.getBalance(), THRESHOLD);
  }
  
  /**
   * Tests setBalance method.
   */
  @Test
  public void testSetBalance() {
    co1.setBalance(450);
    assertEquals(450, co1.getBalance(), THRESHOLD);
  }
  
  /**
   * Tests setBalance method with a negative amount.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testSetBalanceNegative() {
    co1.setBalance(-450);
    assertEquals(-450, co1.getBalance(), THRESHOLD);
  }
  
  /**
   * Tests addResource method.
   */
  @Test
  public void testAddResource() {
    co1.addResource(LUMBER, 45);
    assertEquals(45, co1.amountOf(LUMBER));
  }

  /**
   * Tests addResource method with a negative amount.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testAddResourceNegative() {
    co1.addResource(LUMBER, -45);
  }
  
  /**
   * Tests removeResource method.
   */
  @Test
  public void testRemoveResourceProper() {
    co1.addResource(LUMBER, 45);
    co1.removeResource(LUMBER, 35);
    assertEquals(10, co1.amountOf(LUMBER));
  }

  /**
   * Tests removeResource method with too large an amount.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testRemoveResourceTooMuch() {
    co1.addResource(LUMBER, 45);
    co1.removeResource(LUMBER, 55);
  }

  /**
   * Tests removeResource method with a negative amount.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testRemoveResourceNegative() {
    co1.removeResource(LUMBER, -45);
  }

  /**
   * Tests removeResource method on an empty container.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveResourceEmpty() {
    co1.removeResource(LUMBER, 5);
  }

  /**
   * Tests amountOf method.
   */
  @Test
  public void testAmountOf() {
    assertEquals(0, co1.amountOf(LUMBER));
  }

  /**
   * Test getFactories method.
   */
  @Test
  public void testGetFactories() {
    final Set<Factory> factory = new HashSet<Factory>();
    factory.add(STEEL_FACTORY);
    assertEquals(factory, co2.getFactories());
  }
  
  /**
   * Tests stats method.
   */
  @Test
  public void testStats() {
    assertEquals("Test 1: $500.00\nInv: CHA: 0 unit(s) COA: 0 unit(s) IRO: 0 unit(s) "
        + "LUM: 0 unit(s) PIG: 0 unit(s) STL: 0 unit(s) STO: 0 unit(s) ", co1.stats());
  }
}
