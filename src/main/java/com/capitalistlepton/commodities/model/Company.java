package com.capitalistlepton.commodities.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Company extends ResourceContainer {

	/** The name of the company. */
	private String name;
	/** How much money the company has. */
	private double balance;
	/** All factories owned by this company. */
	private Set<Factory> factories;
	
	public Company(String name, double balance) {
		this.name = name;
		this.balance = balance;
		this.factories = new HashSet<Factory>();
	}
	
	/**
	 * Returns the current balance of this Company.
	 * 
	 * @return the current balance of this Company.
	 */
	public double getBalance() {
		return balance;
	}
	
	/**
	 * Returns the Set of all Factories currently owned by this Company.
	 * 
	 * @return the Set of all Factories currently owned by this Company.
	 */
	public Set<Factory> getFactories() {
		return factories;
	}
	
	/**
	 * Adds the given Factory to the Company.
	 * 
	 * @param f Factory to add to the Company.
	 * @throws NullPointerException if f is null.
	 */
	public void addFactory(Factory f) {
		factories.add(Objects.requireNonNull(f));
	}
	
	/**
	 * Changes the company's balance by the specified amount
	 * 
	 * @param dBalance double amount to change the company's balance by
	 * @throws IllegalArgumentException if balance + dBalance &lt; 0
	 * @return new balance of the company
	 */
	public double changeBalance(double dBalance) {
		if (balance + dBalance < 0) {
			throw new IllegalArgumentException("The final balance cannot be negative");
		}
		return balance += dBalance;
	}
		
	/** Returns a String representation of the company and its assets */
	public String stats() {
		StringBuilder stats = new StringBuilder();
		stats.append(name);
		stats.append(": $");
		stats.append(String.format("%.2f", balance));
		stats.append("\n");
		stats.append("Inv: ");
		stats.append(this);
		return stats.toString();
	}
}
