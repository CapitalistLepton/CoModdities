package com.capitalistlepton.commodities.model;

public class Company extends ResourceContainer {

	/** The name of the company. */
	private String name;
	/** How much money the company has. */
	private double balance;
	
	public Company(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}
	
	public double getBalance() {
		return balance;
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
