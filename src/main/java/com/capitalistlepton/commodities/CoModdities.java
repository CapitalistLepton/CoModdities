package com.capitalistlepton.commodities;

import java.util.Scanner;

import com.capitalistlepton.commodities.model.Company;
import com.capitalistlepton.commodities.model.Market;
import com.capitalistlepton.commodities.model.Market.Action;

public class CoModdities {
	
	private static Market m;

	public static void main(String[] args) {
		m = new Market();
		System.out.println(m.ticker());
		Company co = new Company("Cobalt Corportaion, Inc.", 500.0);
		Scanner in = new Scanner(System.in);
		while (co.getBalance() >= 0) {
			System.out.println(co.stats());
			System.out.println();
			System.out.println(m.ticker());
			System.out.println();
			System.out.print("(B)uy or (S)ell? ");
			String choice = in.next();
			in.nextLine(); // read in new line
			while (!choice.equalsIgnoreCase("b") && !choice.equalsIgnoreCase("s")) {
				System.out.print("Enter 'b' for buying or 's' for selling: ");
				choice = in.next();
			}
			if (choice.equalsIgnoreCase("b")) {
				performAction(in, Market.BUY, m, co, getSymbol(in));
			} else if (choice.equalsIgnoreCase("s")) {
				performAction(in, Market.SELL, m, co, getSymbol(in));
			}
		}
		in.close();
	}
	
	/**
	 * Returns the symbol to work with
	 * 
	 * @param in Scanner of the user's input
	 * @return String symbol to use
	 */
	private static String getSymbol(Scanner in) {
		String symbol = null;
		while(!m.contains(symbol)) {
			System.out.print("Symbol? ");
			symbol = in.nextLine();
		}
		return symbol;
	}
	
	/**
	 * Performs the specified Action on the other given parameters
	 * 
	 * @param in Scanner of the user's input
	 * @param a Action to perform
	 * @param m Market to use
	 * @param co Company to use
	 * @param symbol String symbol to use
	 */
	private static void performAction(Scanner in, Action a, Market m, Company co, String symbol) {
		System.out.print("Amount? ");
		int amt = in.nextInt();
		in.nextLine(); // read in new line
		while (amt < 0  || !a.action(m, co, symbol, amt)) {
			System.out.print("Amount? ");
			amt = in.nextInt();
			in.nextLine(); // read in new line
		}
	}
}
