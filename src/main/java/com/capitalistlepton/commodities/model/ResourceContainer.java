package com.capitalistlepton.commodities.model;

import java.util.SortedMap;
import java.util.TreeMap;

public class ResourceContainer {
	
	/** Map of all the resources and their respective symbols. */
	private static final SortedMap<String, Resource> RESOURCES = new TreeMap<String, Resource>();
	/** Maps the symbol of all the resources to their stored amount. */
	private SortedMap<String, Integer> amounts;
	
	public ResourceContainer() {
		if (RESOURCES.isEmpty()) {
			RESOURCES.put("LUM", new Resource("Lumber", "LUM", 1.0));
			RESOURCES.put("IRO", new Resource("Iron", "IRO", 10.0));
			RESOURCES.put("STO", new Resource("Stone", "STO", 1.0));
		}
		amounts = new TreeMap<String, Integer>();
	}
	
	/**
	 * Returns the amount stored of the specified symbol.
	 * 
	 * @param symbol String symbol of the Resource to search for.
	 * @return int amount of how many units are stored.
	 */
	public int getAmount(String symbol) {
		if (!amounts.containsKey(symbol)) {
			return 0;
		} else {
			return amounts.get(symbol);
		}
	}
	
	/**
	 * Adds the number of specified resources to the inventory.
	 * 
	 * @param r Resource to add to the inventory
	 * @param amount positive integer amount of the resource being added
	 * @throws IllegalArgumentException if amount < 0
	 */
	public void addResource(Resource r, int amount) {
		addResource(r.getSym(), amount);
	}
	
	/**
	 * Adds the number of specified resources to the inventory.
	 * 
	 * @param symbol String symbol of Resource to add to the inventory.
	 * @param amount positive integer amount of the resource being added.
	 * @throws IllegalArgumentException if amount < 0.
	 */
	public void addResource(String symbol, int amount) {
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
	public Resource getResource(String symbol) {
		return RESOURCES.get(symbol);
	}
	
	/**
	 * Removes the number of specified resources to the inventory.
	 * 
	 * @param r Resource to remove from the inventory.
	 * @param amount positive integer amount of the resource being removed.
	 * @throws IllegalArgumentException if amount < 0.
	 */
	public void removeResource(Resource r, int amount) {
		removeResource(r.getSym(), amount);
	}
	
	/**
	 * Removes the number of specified resources to the inventory
	 * 
	 * @param r Resource to remove from the inventory.
	 * @param amount positive integer amount of the resource being removed.
	 * @throws IllegalArgumentException if amount < 0.
	 */
	public void removeResource(String symbol, int amount) {
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
			throw new IllegalArgumentException("Resource " + symbol + 
					" is not in the inventory");
		}
	}
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		for (String symbol: amounts.keySet()) {
			output.append(symbol);
			output.append(": ");
			output.append(amounts.get(symbol));
			output.append(" units ");
		}
		return output.toString();
	}

}
