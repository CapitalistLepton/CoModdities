package com.capitalistlepton.commodities.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for Market.
 * 
 * @author Zane Littrell
 */
public class MarketTest { //NOPMD

  /** Threshold for comparing two double values. */
  private static final double THRESHOLD = 0.000001;
  /** Stone symbol. */
  private static final String STONE = "STO";
  /** Lumber symbol. */
  private static final String LUMBER = "LUM";
  /** Iron symbol. */
  private static final String IRON = "IRO";

  /** Market 1 for testing. */
  private Market market1;
  /** Company 1 for testing. */
  private Company co1;
  /** Company 2 for testing. */  
  private Company co2;
  /** Company 3 for testing. */
  private Company co3;
  /** Company 4 for testing. */
  private Company co4;


  /**
   * Run before each test.
   */
  @Before
  public void setUp() {
    market1 = new Market();
    co1 = new Company("Test 1", 500);
    co2 = new Company("Test 2", 500);
    co3 = new Company("Test 3", 10);
    co4 = new Company("Test 4", 1);
    co4.addResource(STONE, 48);
  }

  /**
   * Test contains method.
   */
  @Test
  public void testContainsActual() {
    assertTrue(market1.contains(LUMBER));
  }

  /**
   * Test contains method with invalid symbol.
   */
  @Test
  public void testContainsFake() {
    assertFalse(market1.contains("BLA"));
  }
  
  /**
   * Test contains method with null.
   */
  @Test
  public void testContainsNull() {
    assertFalse(market1.contains(null));
  }

  /**
   * Test buy action.
   */
  @Test
  public void testBuyProper() {
    AbstractMarketAction.BUY.perform(co1, market1, LUMBER, 20);
    assertEquals(480, co1.getBalance(), THRESHOLD);
  }

  /**
   * Test buy action  with low funds.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testBuyLowFunds() {
    AbstractMarketAction.BUY.perform(co3, market1, IRON, 10);
  }

  /**
   * Test buy action with too many resources.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testBuyTooMany() {
    AbstractMarketAction.BUY.perform(co1, market1, LUMBER, 200);
  }

  /**
   * Test buy action validator.
   */
  @Test
  public void testBuyValid() {
    assertTrue(AbstractMarketAction.BUY.validator(co1, market1, LUMBER, 20));
  }

  /**
   * Test buy action validator with negative amount.
   */
  @Test
  public void testBuyNegativeAmount() {
    assertFalse(AbstractMarketAction.BUY.validator(co1, market1, LUMBER, -1));
  }

  /**
   * Test buy action validator with low funds.
   */
  @Test
  public void testBuyTooMuch() {
    assertFalse(AbstractMarketAction.BUY.validator(co3, market1, IRON, 10));
  }

  /**
   * Test buy action validator with too many resources.
   */
  @Test
  public void testBuyTooLittleMarket() {
    assertFalse(AbstractMarketAction.BUY.validator(co1, market1, LUMBER, 200));
  }

  /**
   * Test sell action.
   */
  @Test
  public void testSellProper() {
    AbstractMarketAction.SELL.perform(co4, market1, STONE, 45);
    assertEquals(46, co4.getBalance(), THRESHOLD);
  }

  /**
   * Test sell action on company with 0 resources.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testSellNoRes() {
    AbstractMarketAction.SELL.perform(co2, market1, LUMBER, 30);
  }

  /**
   * Test sell action with too many resources.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testSellTooFew() {
    AbstractMarketAction.SELL.perform(co4, market1, STONE, 90);
  }

  /**
   * Test sell validator.
   */
  @Test
  public void testSellValid() {
    assertTrue(AbstractMarketAction.SELL.validator(co4, market1, STONE, 45));
  }

  /**
   * Test sell validator on negative resource amount.
   */
  @Test
  public void testSellTooLittle() {
    assertFalse(AbstractMarketAction.SELL.validator(co4, market1, STONE, -1));
  }

  /**
   * Test sell validator on too many resources.
   */
  @Test
  public void testSellTooMany() {
    assertFalse(AbstractMarketAction.SELL.validator(co4, market1, STONE, 450));
  }
}
