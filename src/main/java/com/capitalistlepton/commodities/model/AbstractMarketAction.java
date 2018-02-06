package com.capitalistlepton.commodities.model;

/**
 * Abstract class defining MarketAction.
 * 
 * @author Zane Littrell
 * @version 0.0.1
 */
public abstract class AbstractMarketAction {
  
  /** MarketAction representing buying. */
  public static final AbstractMarketAction BUY = new AbstractMarketAction() {

    /**
     * Validates buying the amount of specified Resource on the given 
     * Company and Market.
     * 
     * @param company Company to buy for.
     * @param market Market to buy from.
     * @param symbol String symbol of Resource to buy.
     * @param amount int amount of Resource being bought.
     * @return whether or not the parameters are valid (if amount &lt; 0 
     *     or amount &gt; co.amountOf(symbol) or if co.getBalance is too low).
     */
    @Override
    public boolean validator(final Company company, final Market market, final String symbol, 
        final int amount) {
      return amount >= 0 && amount <= market.amountOf(symbol) 
          && company.getBalance() 
            >= amount * ResourceContainer.getResource(symbol).getPrice();
    }

    /**
     * Buys the amount of specified Resource on the given Company and Market.
     * 
     * @param company Company to buy for.
     * @param market Market to buy from.
     * @param symbol String symbol of Resource to buy.
     * @param amount int amount of Resource being bought.
     * @throws IllegalArgumentException if amount &lt; 0 or amount &gt; 
     *     co.amountOf(symbol) or if co.getBalance is too low.
     */
    @Override
    public void perform(final Company company, final Market market, 
        final String symbol, final int amount) {
      final Resource resource = ResourceContainer.getResource(symbol);
      company.addResource(symbol, amount);
      company.changeBalance(-1 * amount * resource.getPrice());
      market.removeResource(symbol, amount);
    }
  };

  /** MarketAction representing selling. */
  public static final AbstractMarketAction SELL = new AbstractMarketAction() {
    /**
     * Validates selling the amount of specified Resource on the given 
     * Company and Market.
     * 
     * @param company Company to sell from.
     * @param market Market to sell to.
     * @param symbol String symbol of Resource to sell.
     * @param amount int amount of Resource being sold.
     * @return whether or not the parameters are valid (if amount &lt; 0 or 
     *     amount &gt; m.amountOf(symbol)).
     */
    @Override
    public boolean validator(final Company company, final Market market, final String symbol, 
        final int amount) {
      return company.amountOf(symbol) >= amount && amount >= 0;
    }

    /**
     * Sells the amount of specified Resource on the given Company and Market.
     * 
     * @param company Company to sell from.
     * @param market Market to sell to.
     * @param symbol String symbol of Resource to sell.
     * @param amount int amount of Resource being sold.
     * @throws IllegalArgumentException if amount &lt; 0 or 
     *     amount &gt; m.amountOf(symbol).
     */
    @Override
    public void perform(final Company company, final Market market, 
        final String symbol, final int amount) {
      final Resource resource = ResourceContainer.getResource(symbol);
      company.removeResource(symbol, amount);
      company.changeBalance(amount * resource.getPrice());
      market.addResource(symbol, amount);
    }
  };

  private AbstractMarketAction() { }

  /** 
   * Validates the given parameters to make sure perform can run.
   * 
   * @param company Company to perform action on.
   * @param market Market to perform action on.
   * @param symbol String symbol of Resource to use.
   * @param amount int amount of Resource being used.
   * @return whether or not the parameters are valid.
   */
  public abstract boolean validator(final Company company, final Market market, 
      final String symbol, final int amount);

  /**
   * Performs this action on the given Company and Market.
   * 
   * @param company Company to perform action on.
   * @param market Market to perform action on.
   * @param symbol String symbol of Resource to use.
   * @param amount int amount of Resource being used.
   * @throws IllegalArgumentException if amount &lt; 0 or is in other ways invalid.
   */
  public abstract void perform(final Company company, final Market market, 
      final String symbol, final int amount);

}
