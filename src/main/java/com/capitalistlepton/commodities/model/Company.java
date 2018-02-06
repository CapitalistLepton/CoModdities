package com.capitalistlepton.commodities.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Company object that represents a company with a name, balance, and factories.
 * 
 * @author Zane Littrell
 * @version 0.0.1
 */
public class Company extends ResourceContainer {

  /** The name of the company. */
  private final String name;
  /** How much money the company has. */
  private double balance;
  /** All factories owned by this company. */
  private final Set<Factory> factories;

  /**
   * Creates a new Company Object.
   * 
   * @param name the name of the Company.
   * @param balance the current balance of the Company.
   * @throws NullPointerException if name is null.
   * @throws IllegalArgumentException if balance &lt; 0 or name is empty.
   */
  public Company(final String name, final double balance) {
    super();
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be an empty string");
    }
    this.name = name;
    this.balance = isValidBalance(balance);
    this.factories = new HashSet<Factory>();
  }
  
  /**
   * Returns the name of the company.
   * 
   * @return the name of the company.
   */
  public String getName() {
    return name;
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
    return new HashSet<>(factories);
  }

  /**
   * Adds the given Factory to the Company.
   * 
   * @param factory Factory to add to the Company.
   * @throws NullPointerException if factory is null.
   */
  public void addFactory(final Factory factory) {
    factories.add(Objects.requireNonNull(factory));
  }

  /**
   * Changes the company's balance by the specified amount.
   * 
   * @param deltaBalance double amount to change the company's balance by
   * @return new balance of the company
   * @throws IllegalArgumentException if balance + dBalance &lt; 0
   */
  public double changeBalance(final double deltaBalance) {
    return balance = isValidBalance(balance + deltaBalance);
  }
  
  /**
   * Sets the current balance of the company.
   * 
   * @param newBalance new balance of the company.
   * @throws IllegalArgumentException if newBalance &lt; 0
   */
  public void setBalance(final double newBalance) {
    balance = isValidBalance(newBalance);
  }

  /**
   *  Returns a String representation of the company and its assets.
   *  
   *  @return String representation of the company.
   */
  public String stats() {
    final StringBuilder stats = new StringBuilder();
    stats.append(name).append(": $").append(String.format("%.2f", balance)).append("\nInv: ")
         .append(this);
    return stats.toString();
  }
  
  /**
   * Tests if the given balance is a valid amount or not.
   * 
   * @param balance balance to check
   * @return the balance if it is valid
   * @throws IllegalArgumentException if balance &lt; 0
   */
  private double isValidBalance(final double balance) {
    if (balance < 0) {
      throw new IllegalArgumentException("The balance cannot be negative");
    }
    return balance;
  }
}
