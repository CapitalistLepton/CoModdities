package com.capitalistlepton.commodities.model;

public class Market extends ResourceContainer {
		
	private static final int STARTING_AMOUNTS = 40;
	
	public Market() {
		super(STARTING_AMOUNTS);
	}
	
	/** 
	 * Returns if the given symbol is traded on this market
	 * 
	 * @param symbol String symbol to check for
	 * @return boolean value of whether the symbol is traded on this market
	 */
	public boolean contains(String symbol) {
		if (symbol != null) {
			return ResourceContainer.allResources().containsKey(symbol);
		} else {
			return false;
		}
	}
		
}
