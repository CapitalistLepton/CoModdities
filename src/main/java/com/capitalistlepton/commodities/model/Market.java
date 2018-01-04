package com.capitalistlepton.commodities.model;

import java.util.SortedMap;
import java.util.TreeMap;

public class Market {
	
	/** Map of all possible resources on the market and their respective symbols. */
	private SortedMap<String, Resource> resources;
	/** Maps the symbol of all possible resources to the amount available in the market. */
	private SortedMap<String, Integer> amounts;
	
	public Market() {
		resources = new TreeMap<String, Resource>();
		resources.put("LUM", new Resource("Lumber", "LUM", 1.0));
		resources.put("IRO", new Resource("Iron", "IRO", 10.0));
		resources.put("STO", new Resource("Stone", "STO", 1.0));
		amounts = new TreeMap<String, Integer>();
		for (String s: resources.keySet()) {
			amounts.put(s, 50);
		}
	}
	
	/** Returns a String with all the symbols of resources and the prices separated by two spaces */
	public String ticker() {
		StringBuilder output = new StringBuilder();
		for (Resource r: resources.values()) {
			output.append(r);
			output.append(" ");
			output.append(amounts.get(r.getSym()));
			output.append("u  ");
		}
		return output.toString();
	}

	/** 
	 * Returns if the given symbol is traded on this market
	 * 
	 * @param symbol String symbol to check for
	 * @return boolean value of whether the symbol is traded on this market
	 */
	public boolean contains(String symbol) {
		if (symbol == null) {
			return false;
		}
		return resources.containsKey(symbol);
	}
	
	/** Interface for all actions that companies can perform on the market */
	public interface Action {
		public boolean action(Market m, Company co, String sym, int amount);
	}
	
	/** Action to buy a resource */
	public static final Action BUY = new Action() {
		public boolean action(Market m, Company co, String sym, int amount) {
			if (amount <= m.amounts.get(sym) && co.getBalance() >= amount * m.resources.get(sym).getPrice()) {
				try {
					co.addResource(m.resources.get(sym), amount);
					co.changeBalance(-1 * amount * m.resources.get(sym).getPrice());
					m.amounts.put(sym, m.amounts.get(sym) - amount);
					return true;
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
					return false;
				}
			} else {
				return false;
			}
		}
	};

	/** Action to buy a resource */
	public static final Action SELL = new Action() {
		public boolean action(Market m, Company co, String sym, int amount) {
			if (co.amountOf(m.resources.get(sym)) >= amount) {
				try {
					co.removeResource(m.resources.get(sym), amount);
					co.changeBalance(amount * m.resources.get(sym).getPrice());
					m.amounts.put(sym, m.amounts.get(sym) + amount);
					return true;
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
					return false;
				}
			} else {
				return false;
			}
		}
	};
}
