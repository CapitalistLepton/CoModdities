package com.capitalistlepton.commodities.model;

/**
 * Market class representing a stock market.
 * 
 * @author Zane Littrell
 * @version 0.0.1
 */
public class Market extends ResourceContainer {

  /** Amount of each type of resource that the market starts with. */
  private static final int STARTING_AMOUNTS = 40;

  /**
   * Creates a new market with the default starting amount of each resource.
   */
  public Market() {
    super(STARTING_AMOUNTS);
  }

  /** 
   * Returns if the given symbol is traded on this market.
   * 
   * @param symbol String symbol to check for
   * @return boolean value of whether the symbol is traded on this market
   */
  public boolean contains(final String symbol) {
    boolean result;
    if (symbol == null) {
      result = false;
    } else {
      result = ResourceContainer.resourceExists(symbol);
    }
    return result;
  }

}
