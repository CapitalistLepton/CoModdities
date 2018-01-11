package com.capitalistlepton.commodities.model;

public abstract class MarketAction {
	
	public static final MarketAction BUY = new MarketAction() {
		
		/**
		 * Validates buying the amount of specified Resource on the given Company and Market.
		 * 
		 * @param co Company to buy for.
		 * @param m Market to buy from.
		 * @param symbol String symbol of Resource to buy.
		 * @param amount int amount of Resource being bought.
		 * @throws IllegalArgumentException if amount &lt; 0 or amount &gt; co.amountOf(symbol)
		 * or if co.getBalance is too low.
		 */
		@Override
		public boolean validator(Company co, Market m, String symbol, int amount) {
			return (amount >= 0 && amount <= m.amountOf(symbol) 
					&& co.getBalance() >= amount * ResourceContainer.getResource(symbol).getPrice());
		}

		/**
		 * Buys the amount of specified Resource on the given Company and Market.
		 * 
		 * @param co Company to buy for.
		 * @param m Market to buy from.
		 * @param symbol String symbol of Resource to buy.
		 * @param amount int amount of Resource being bought.
		 * @throws IllegalArgumentException if amount &lt; 0 or amount &gt; co.amountOf(symbol)
		 * or if co.getBalance is too low.
		 */
		@Override
		public void perform(Company co, Market m, String symbol, int amount) {
			Resource r = ResourceContainer.getResource(symbol);
			co.addResource(symbol, amount);
			co.changeBalance(-1 * amount * r.getPrice());
			m.removeResource(symbol, amount);
		}
	};
	
	public static final MarketAction SELL = new MarketAction() {
		/**
		 * Validates selling the amount of specified Resource on the given Company and Market.
		 * 
		 * @param co Company to sell from.
		 * @param m Market to sell to.
		 * @param symbol String symbol of Resource to sell.
		 * @param amount int amount of Resource being sold.
		 * @throws IllegalArgumentException if amount &lt; 0 or amount &gt; m.amountOf(symbol)
		 */
		@Override
		public boolean validator(Company co, Market m, String symbol, int amount) {
			return (co.amountOf(symbol) >= amount && amount >= 0);
		}

		/**
		 * Sells the amount of specified Resource on the given Company and Market.
		 * 
		 * @param co Company to sell from.
		 * @param m Market to sell to.
		 * @param symbol String symbol of Resource to sell.
		 * @param amount int amount of Resource being sold.
		 * @throws IllegalArgumentException if amount &lt; 0 or amount &gt; m.amountOf(symbol)
		 */
		@Override
		public void perform(Company co, Market m, String symbol, int amount) {
			Resource r = ResourceContainer.getResource(symbol);
			co.removeResource(symbol, amount);
			co.changeBalance(amount * r.getPrice());
			m.addResource(symbol, amount);
		}
	};
	
	private MarketAction() { }
	
	/** 
	 * Validates the given parameters to make sure perform can run.
	 * 
	 * @param co Company to perform action on.
	 * @param m Market to perform action on.
	 * @param symbol String symbol of Resource to use.
	 * @param amount int amount of Resource being used.
	 * @return whether or not the parameters are valid.
	 */
	public abstract boolean validator(Company co, Market m, String symbol, int amount);
	
	/**
	 * Performs this action on the given Company and Market.
	 * 
	 * @param co Company to perform action on.
	 * @param m Market to perform action on.
	 * @param symbol String symbol of Resource to use.
	 * @param amount int amount of Resource being used.
	 * @throws IllegalArgumentException if amount &lt; 0 or is in other ways invalid.
	 */
	public abstract void perform(Company co, Market m, String symbol, int amount);
	
}
