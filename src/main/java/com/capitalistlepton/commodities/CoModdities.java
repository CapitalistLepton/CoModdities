package com.capitalistlepton.commodities;

import com.capitalistlepton.commodities.model.AbstractMarketAction;
import com.capitalistlepton.commodities.model.Company;
import com.capitalistlepton.commodities.model.Market;
import java.util.Scanner;

/**
 * Runner class for CoModdities.
 * 
 * @author Zane Littrell
 * @version 0.0.1
 */

public final class CoModdities {
  
  private CoModdities() { }
  
  /**
   * Main method.
   * 
   * @param args command line arguments.
   */
  public static void main(final String[] args) {
    final Market market = new Market();
    System.out.println(market.ticker());
    final Company company = new Company("Cobalt Corportaion, Inc.", 500.0);
    final Scanner input = new Scanner(System.in);
    while (company.getBalance() >= 0) {
      System.out.println(company.stats());
      System.out.println();
      System.out.println(market.ticker());
      System.out.println();
      System.out.print("(B)uy or (S)ell? ");
      String choice = input.next();
      input.nextLine(); // read in new line
      while (!"b".equalsIgnoreCase(choice) && !"s".equalsIgnoreCase(choice)) {
        System.out.print("Enter 'b' for buying or 's' for selling: ");
        choice = input.next();
      }
      if ("b".equalsIgnoreCase(choice)) {
        performAction(input, AbstractMarketAction.BUY, market, company, getSymbol(market, input));
      } else if ("s".equalsIgnoreCase(choice)) {
        performAction(input, AbstractMarketAction.SELL, market, company, getSymbol(market, input));
      }
    }
    input.close();
  }

  /**
   * Returns the symbol to work with.
   * 
   * @param input Scanner of the user's input
   * @return String symbol to use
   */
  private static String getSymbol(final Market market, final Scanner input) {
    String symbol = null;
    while (!market.contains(symbol)) {
      System.out.print("Symbol? ");
      symbol = input.nextLine();
    }
    return symbol;
  }

  /**
   * Performs the specified Action on the other given parameters.
   *
   * @param input Scanner of the user's input
   * @param action Action to perform
   * @param market Market to use
   * @param company Company to use
   * @param symbol String symbol to use
   */
  private static void performAction(final Scanner input, final AbstractMarketAction action, 
      final Market market, final Company company, final String symbol) {
    System.out.print("Amount? ");
    int amount = input.nextInt();
    input.nextLine(); // read in new line
    while (!action.validator(company, market, symbol, amount)) {
      System.out.print("Amount? ");
      amount = input.nextInt();
      input.nextLine(); // read in new line
    }
    try {
      action.perform(company, market, symbol, amount);
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }
}
