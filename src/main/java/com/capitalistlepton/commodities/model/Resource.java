package com.capitalistlepton.commodities.model;

public class Resource implements Comparable<Resource> {
	
	/** English name for the resource */
	private final String name;
	/** Three letter symbol for the resource */
	private final String symbol;
	/** Price at which to buy or sell the resource */
	private double price;
	
	public Resource(String name, String symbol, double price) {
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
	 */
	public void setPrice(double newPrice) {
		price = newPrice;
	}
	
	/**
	 * Changes the price by the specified amount
	 * 
	 * @param dPrice amount to change the price by
	 */
	public void changePrice(double dPrice) {
		price += dPrice;
	}
	
	@Override
	public String toString() {
//		return symbol + ": $" + price;
		return String.format("%s: $%.2f", symbol, price);
	}

	@Override
	public int compareTo(Resource r) {
		return this.symbol.compareTo(r.symbol);
	}
}
