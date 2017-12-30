package com.capitalistlepton.commodities.model;

import java.util.SortedMap;
import java.util.TreeMap;

public class Company {

	/** The name of the company */
	private String name;
	/** How much money the company has */
	private double balance;
	/** SortedMap of how many of each resource the company currently owns */
	private SortedMap<Resource, Integer> inventory;
	
	public Company(String name, double balance) {
		this.name = name;
		this.balance = balance;
		this.inventory = new TreeMap<Resource, Integer>();
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
	 * @param r Resource to add to the inventory
	 * @param amount positive integer amount of the resource being added
	 */
	public void addResource(Resource r, int amount) throws IllegalArgumentException {
		if (amount < 0) {
			throw new IllegalArgumentException("Amount must be >= 0");
		}
		if (inventory.containsKey(r)) {
			inventory.put(r, inventory.get(r) + amount);
		} else {
			inventory.put(r, amount);
		}
	}
	
	/**
	 * Removes the number of specified resources to the inventory
	 * 
	 * @param r Resource to remove from the inventory
	 * @param amount positive integer amount of the resource being removed
	 */
	public void removeResource(Resource r, int amount) throws IllegalArgumentException {
		if (amount < 0) {
			throw new IllegalArgumentException("Amount must be >= 0");
		}
		if (inventory.containsKey(r)) {
			inventory.put(r, inventory.get(r) - amount);
		} else {
			throw new IllegalArgumentException("Resource " + r.getName() + " is not in the company's inventory");
		}
	}
	
	/**
	 * Returns the amount of the specified resource in the inventory
	 * 
	 * @param r Resource to check
	 * @return amount of Resources
	 */
	public int amountOf(Resource r) {
		return inventory.get(r);
	}
	
	/** Returns a String representation of the company and its assets */
	public String stats() {
		StringBuilder stats = new StringBuilder();
		stats.append(name);
		stats.append(": $");
		stats.append(String.format("%.2f", balance));
		stats.append("\n");
		stats.append("Inv: ");
		for (Resource r: inventory.keySet()) {
			stats.append(r.getName());
			stats.append(": ");
			stats.append(inventory.get(r));
			stats.append(" units ");
		}
		return stats.toString();
	}
}
