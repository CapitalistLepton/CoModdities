package com.capitalistlepton.commodities.model;

public class Company {

	/** The name of the company. */
	private String name;
	/** How much money the company has. */
	private double balance;
	/** Inventory of all the current resources in the company. */
	private ResourceContainer inventory;
	
	public Company(String name, double balance) {
		this.name = name;
		this.balance = balance;
		this.inventory = new ResourceContainer();
	}
	
	public double getBalance() {
		return balance;
	}
	
	/**
	 * Changes the company's balance by the specified amount
	 * 
	 * @param dBalance amount to change the company's balance by
	 * @return new balance of the company
	 */
	public double changeBalance(double dBalance) {
		return balance += dBalance;
	}
	
	/**
	 * Adds the number of specified resources to the inventory
	 * 
	 * @param symbol String symbol of Resource to add to the inventory
	 * @param amount positive integer amount of the resource being added
	 * @throws IllegalArgumentException if amount < 0
	 */
	public void addResource(String symbol, int amount) {
		inventory.addResource(symbol, amount);
	}
	
	/**
	 * Removes the number of specified resources to the inventory
	 * 
	 * @param symbol String symbol of Resource to remove from the inventory
	 * @param amount positive integer amount of the resource being removed
	 * @throws IllegalArgumentException if amount < 0
	 */
	public void removeResource(String symbol, int amount) {
		inventory.removeResource(symbol, amount);
	}
	
	/**
	 * Returns the amount of the specified resource in the inventory. Returns 0 if the resource is
	 * not in the inventory.
	 * 
	 * @param symbol String symbol of Resource to check
	 * @return amount of Resources
	 */
	public int getAmount(String symbol) {
		return inventory.getAmount(symbol);
	}
	
	/** Returns a String representation of the company and its assets */
	public String stats() {
		StringBuilder stats = new StringBuilder();
		stats.append(name);
		stats.append(": $");
		stats.append(String.format("%.2f", balance));
		stats.append("\n");
		stats.append("Inv: ");
		stats.append(inventory);
		return stats.toString();
	}
}
