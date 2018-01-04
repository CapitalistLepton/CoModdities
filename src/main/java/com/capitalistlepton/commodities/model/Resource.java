package com.capitalistlepton.commodities.model;

public class Resource implements Comparable<Resource> {
	
	/** English name for the resource */
	private final String name;
	/** Three letter symbol for the resource */
	private final String symbol;
	/** Price at which to buy or sell the resource */
	private double price;
	
	/**
	 * Creates a new Resource if the parameter are valid.
	 * 
	 * @param name String English name for the resource
	 * @param symbol String three letter symbol for the resource
	 * @param price int price for the resource
	 * @throws IllegalArgumentException if the price is < 0.01 or if the symbol isn't three 
	 * uppercase letters
	 */
	public Resource(String name, String symbol, double price) throws IllegalArgumentException {
		if (price < 0.01) {
			throw new IllegalArgumentException("Price must be >= 0.01");
		}
		if (!symbol.matches("[A-Z]{3}")) {
			throw new IllegalArgumentException("Symbol must be 3 uppercase letters");
		}
		this.name = name;
		this.symbol = symbol;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSym() {
		return symbol;
	}
	
	public double getPrice() {
		return price;
	}
	
	/**
	 * Sets the price to the new specified price
	 * 
	 * @param newPrice new price for the resource
	 * @throws IllegalArgumentException if the given price is < 0.01
	 */
	public void setPrice(double newPrice) throws IllegalArgumentException {
		if (newPrice < 0.01) {
			throw new IllegalArgumentException("Price must be >= 0.01");
		}
		price = newPrice;
	}
	
	/**
	 * Changes the price by the specified amount
	 * 
	 * @param dPrice amount to change the price by
	 * @throws IllegalArgumentException if the final price is < 0.01
	 */
	public void changePrice(double dPrice) throws IllegalArgumentException {
		if (price + dPrice < 0.01) {
			throw new IllegalArgumentException("Price must be >= 0.01");
		}
		price += dPrice;
	}
	
	@Override
	public String toString() {
		return String.format("%s: $%.2f", symbol, price);
	}

	/** Compares the symbols of two resources. */
	@Override
	public int compareTo(Resource r) {
		return this.symbol.compareTo(r.symbol);
	}
}
