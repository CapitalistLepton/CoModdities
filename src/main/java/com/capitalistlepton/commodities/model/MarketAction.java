package com.capitalistlepton.commodities.model;

public abstract class MarketAction {
	
	public static final MarketAction BUY = new MarketAction() {
		@Override
		public boolean validator(Company co, Market m, String symbol, int amount) {
			return (amount >= 0 && amount <= m.getAmount(symbol) 
					&& co.getBalance() >= amount * ResourceContainer.getResource(symbol).getPrice());
		}

		@Override
		public boolean perform(Company co, Market m, String symbol, int amount) {
			Resource r = ResourceContainer.getResource(symbol);
			if (validator(co, m, symbol, amount)) {
				try {
					co.addResource(symbol, amount);
					co.changeBalance(-1 * amount * r.getPrice());
					m.removeResource(symbol, amount);
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
	
	public static final MarketAction SELL = new MarketAction() {
		@Override
		public boolean validator(Company co, Market m, String symbol, int amount) {
			return (co.getAmount(symbol) >= amount && amount >= 0);
		}

		@Override
		public boolean perform(Company co, Market m, String symbol, int amount) {
			Resource r = ResourceContainer.getResource(symbol);
			if (validator(co, m, symbol, amount)) {
				try {
					co.removeResource(symbol, amount);
					co.changeBalance(amount * r.getPrice());
					m.addResource(symbol, amount);
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
	
	private MarketAction() { }
	
	public abstract boolean validator(Company co, Market m, String symbol, int amount);
	public abstract boolean perform(Company co, Market m, String symbol, int amount);
	
}
