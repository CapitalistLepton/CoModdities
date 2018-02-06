package com.capitalistlepton.commodities.model;

/**
 * Resource class representing some tradable resource.
 * 
 * @author Zane Littrell
 * @version 0.0.1
 */
public class Resource implements Comparable<Resource> {
  
  /** Minimum amount for a price. */
  private static final double MIN_AMOUNT = 0.01;
  
  /** English name for the resource. */
  private final String name;
  /** Three letter symbol for the resource. */
  private final String symbol; 
  /** Price at which to buy or sell the resource. */
  private double price;

  /**
   * Creates a new Resource if the parameter are valid.
   * 
   * @param name String English name for the resource
   * @param symbol String three letter symbol for the resource
   * @param price int price for the resource
   * @throws IllegalArgumentException if the price is < 0.01 or if the symbol 
   *     isn't three uppercase letters
   */
  public Resource(final String name, final String symbol, final double price) 
      throws IllegalArgumentException {
    if (price < MIN_AMOUNT) {
      throw new IllegalArgumentException("Price must be >= 0.01");
    }
    if (!symbol.matches("[A-Z]{3}")) {
      throw new IllegalArgumentException("Symbol must be 3 uppercase letters");
    }
    this.name = name;
    this.symbol = symbol;
    this.price = price;
  }

  /**
   * Returns the name of the resource.
   * 
   * @return name of the resource.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the three letter symbol of the resource.
   * 
   * @return three letter symbol of the resource.
   */
  public String getSym() {
    return symbol;
  }

  /**
   * Returns the price of the resource.
   * 
   * @return price of the resource.
   */
  public double getPrice() {
    return price;
  }

  /**
   * Sets the price to the new specified price.
   * 
   * @param newPrice new price for the resource
   * @throws IllegalArgumentException if the given price is < 0.01
   */
  public void setPrice(final double newPrice) throws IllegalArgumentException {
    if (newPrice < MIN_AMOUNT) {
      throw new IllegalArgumentException("Price must be >= 0.01");
    }
    price = newPrice;
  }

  /**
   * Changes the price by the specified amount.
   * 
   * @param deltaPrice amount to change the price by
   * @throws IllegalArgumentException if the final price is < 0.01
   */
  public void changePrice(final double deltaPrice) throws IllegalArgumentException {
    if (price + deltaPrice < MIN_AMOUNT) {
      throw new IllegalArgumentException("Price must be >= 0.01");
    }
    price += deltaPrice;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return String.format("%s: $%.2f", symbol, price);
  }

  /** Compares the symbols of two resources. */
  @Override
  public int compareTo(final Resource resource) {
    return this.symbol.compareTo(resource.symbol);
  }
}
