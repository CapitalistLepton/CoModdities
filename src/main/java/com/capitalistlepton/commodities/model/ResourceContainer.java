package com.capitalistlepton.commodities.model;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Container that holds resources.
 * 
 * @author Zane Littrell
 * @version 0.0.1
 */
public class ResourceContainer {

  /** Map of all the resources and their respective symbols. */
  private static final SortedMap<String, Resource> RESOURCES = new TreeMap<String, Resource>();
  
  static {
    if (RESOURCES.isEmpty()) {
      RESOURCES.put("LUM", new Resource("Lumber", "LUM", 1.0));
      RESOURCES.put("IRO", new Resource("Iron", "IRO", 10.0));
      RESOURCES.put("STO", new Resource("Stone", "STO", 1.0));
      RESOURCES.put("STL", new Resource("Steel", "STL", 30.0));
      RESOURCES.put("COA", new Resource("Coal", "COA", 15.0));
      RESOURCES.put("CHA", new Resource("Charcoal", "CHA", 8.0));
      RESOURCES.put("PIG", new Resource("Pig Iron", "PIG", 25.0));
    }
  }
  
  /** Maps the symbol of all the resources to their stored amount. */
  private SortedMap<String, Integer> amounts; 

  /**
   * Creates a new container with 0 of each resource possible.
   */
  public ResourceContainer() {
    this(0);
  }

  /**
   * Creates a new container with defaultAmount of each resource possible.
   * 
   * @param defaultAmount int amount of each resource in the initial container.
   */
  public ResourceContainer(final int defaultAmount) {

    amounts = new TreeMap<String, Integer>();
    for (final String symbol: RESOURCES.keySet()) {
      amounts.put(symbol, defaultAmount);
    }
  }

  /**
   * Returns the amount stored of the specified symbol.
   * 
   * @param symbol String symbol of the Resource to search for.
   * @return int amount of how many units are stored.
   */
  public int amountOf(final String symbol) {
    int result;
    if (amounts.containsKey(symbol)) {
      result = amounts.get(symbol);
    } else {
      result = 0;
    }
    return result;
  }

  /**
   * Adds the number of specified resources to the inventory.
   * 
   * @param resource Resource to add to the inventory
   * @param amount positive integer amount of the resource being added
   * @throws IllegalArgumentException if amount < 0
   */
  public void addResource(final Resource resource, final int amount) {
    addResource(resource.getSym(), amount);
  }

  /**
   * Adds the number of specified resources to the inventory.
   * 
   * @param symbol String symbol of Resource to add to the inventory.
   * @param amount positive integer amount of the resource being added.
   * @throws IllegalArgumentException if amount < 0.
   */
  public void addResource(final String symbol, final int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount must be >= 0");
    }
    if (amounts.containsKey(symbol)) {
      amounts.put(symbol, amounts.get(symbol) + amount);
    } else {
      amounts.put(symbol, amount);
    }
  }

  /**
   * Returns the Resource associated with the given symbol.
   * 
   * @param symbol String symbol of Resource to lookup.
   * @return Resource object.
   */
  public static Resource getResource(final String symbol) {
    return RESOURCES.get(symbol);
  }

  /**
   * Removes the number of specified resources to the inventory.
   * 
   * @param resource Resource to remove from the inventory.
   * @param amount positive integer amount of the resource being removed.
   * @throws IllegalArgumentException if amount < 0.
   */
  public void removeResource(final Resource resource, final int amount) {
    removeResource(resource.getSym(), amount);
  }

  /**
   * Removes the number of specified resources to the inventory.
   * 
   * @param amount positive integer amount of the resource being removed.
   * @throws IllegalArgumentException if amount < 0.
   */
  public void removeResource(final String symbol, final int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount must be >= 0");
    }
    if (amounts.containsKey(symbol)) {
      if (amounts.get(symbol) >= amount) {
        amounts.put(symbol, amounts.get(symbol) - amount);
      } else {
        throw new IllegalArgumentException("Not enough resources to remove");
      }
    } else {
      throw new IllegalArgumentException("Resource " + symbol 
          + " is not in the inventory");
    }
  }

  /**
   * Returns a copy of the amounts map. This is all the symbols of resources 
   * paired with their amounts.
   * 
   * @return a SortedMap with all the symbols of resources paired with 
   *     their amounts.
   */
  public SortedMap<String, Integer> currentAmounts() {
    return new TreeMap<String, Integer>(amounts);
  }

  /**
   * Copy of all the possible resources.
   * 
   * @return SortedMap of all the possible resources and their symbols.
   */
  public static SortedMap<String, Resource> allResources() {
    return new TreeMap<String, Resource>(RESOURCES);
  }

  /** 
   * Returns a String with all the symbols of resources and the prices separated by 
   * two spaces.
   * 
   * @return String ticker of this container.
   */
  public String ticker() { 
    final StringBuilder output = new StringBuilder();
    for (final String symbol: amounts.keySet()) {
      output.append(symbol).append(": $");
      output.append(String.format("%.2f", getResource(symbol).getPrice())); 
      output.append('/');
      output.append(amounts.get(symbol));
      output.append(" units ");
    }
    return output.toString();
  }

  /**
   * Returns whether or not that resource exists in the universe of all resources. 
   * 
   * @param resource name of resource to search for.
   * @return if the resource was found.
   */
  public static boolean resourceExists(final String resource) {
    return RESOURCES.containsKey(resource);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    final StringBuilder output = new StringBuilder();
    for (final String symbol: amounts.keySet()) {
      output.append(symbol);
      output.append(": ");
      output.append(amounts.get(symbol));
      output.append(" unit(s) ");
    }
    return output.toString();
  }

}
