package com.capitalistlepton.commodities.model;

public class Market extends ResourceContainer {
	
//	/** Inventory containing all resources in the market. */
//	private ResourceContainer inventory;
	
	private static final int STARTING_AMOUNTS = 40;
	
	public Market() {
		super(STARTING_AMOUNTS);
//		inventory = new ResourceContainer();
	}
	
	/** Returns a String with all the symbols of resources and the prices separated by two spaces */
	public String ticker() {
//		StringBuilder output = new StringBuilder();
//		for (Resource r: resources.values()) {
//			output.append(r);
//			output.append(" ");
//			output.append(amounts.get(r.getSym()));
//			output.append("u  ");
//		}
		return super.toString();
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
		
//	/**
//	 * Buys the specified amount of the resource for Company co.
//	 * 
//	 * @param co Company which is buying.
//	 * @param symbol String symbol of resource to buy.
//	 * @param amount int amount bought.
//	 * @return boolean value of whether or not the transaction succeeded.
//	 */
//	public boolean buy(Company co, String symbol, int amount) {
//		Resource r = inventory.getResource(symbol);
//		if (buyValidator(co, symbol, amount)) {
//			try {
//				co.addResource(r, amount);
//				co.changeBalance(-1 * amount * r.getPrice());
//				inventory.removeResource(symbol, amount);
//				return true;
//			} catch (IllegalArgumentException e) {
//				System.out.println(e.getMessage());
//				return false;
//			}
//		} else {
//			return false;
//		}
//	}
	
//	/**
//	 * Checks whether the company can buy the specified amount of the resource.
//	 * 
//	 * @param co Company which is buying.
//	 * @param symbol String symbol of resource to buy.
//	 * @param amount int amount bought.
//	 * @return boolean value of whether or not the transaction is valid.
//	 */
//	public boolean buyValidator(Company co, String symbol, int amount) {
//		return (amount >= 0 && amount <= inventory.getAmount(symbol) 
//				&& co.getBalance() >= amount * inventory.getResource(symbol).getPrice());
//	}
	
//	/**
//	 * Sells the specified amount of the resource from Company co.
//	 * 
//	 * @param co Company which is selling.
//	 * @param symbol String symbol of resource to sell.
//	 * @param amount int amount sold.
//	 * @return boolean value of whether or not the transaction succeeded.
//	 */
//	public boolean sell(Company co, String symbol, int amount) {
//		int currentAmount = inventory.getAmount(symbol);
//		Resource r = inventory.getResource(symbol);
//		if (currentAmount >= amount) {
//			try {
//				co.removeResource(r, amount);
//				co.changeBalance(amount * r.getPrice());
//				inventory.addResource(r, amount);
//				return true;
//			} catch (IllegalArgumentException e) {
//				System.out.println(e.getMessage());
//				return false;
//			}
//		} else {
//			return false;
//		}
//	}
}
