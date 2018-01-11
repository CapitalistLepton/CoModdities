package com.capitalistlepton.commodities.model;

import java.util.Objects;
import java.util.SortedMap;

public final class Recipe {
	
	private static ResourceContainer steelRequirements;
	
	static {
		steelRequirements = new ResourceContainer();
		steelRequirements.addResource("LUM", 1);
		steelRequirements.addResource("IRO", 3);
	}
	
	public static final Recipe STEEL = new Recipe(steelRequirements, ResourceContainer.getResource("STL"), 2);
	
	private final ResourceContainer requirements;
	private final Resource output;
	private final int outputAmount;
	
	/**
	 * Creates a new recipe with the given parameters.
	 * 
	 * @param requirements ResourceContainer of the amounts of each Resource required.
	 * @param output Resource to create if the requirements are met.
	 * @param outputAmount int amount of output to create.
	 * @throws NullPointerException if requirements or output is null.
	 * @throws IllegalArgumentException if outputAmount &lt; 1.
	 */
	private Recipe(ResourceContainer requirements, Resource output, int outputAmount) {
		this.requirements = Objects.requireNonNull(requirements);
		this.output = Objects.requireNonNull(output);
		if (outputAmount < 1) {
			throw new IllegalArgumentException("outputAmount must be >= 1");
		}
		this.outputAmount = outputAmount;
	}
	
	/**
	 * Returns whether or not the requirements are met in the given ResourceContainer.
	 * 
	 * @param rc ResourceContainer to check.
	 * @return whether or not the requirements are met.
	 */
	public boolean validate(ResourceContainer rc) {
		SortedMap<String, Integer> amounts = requirements.currentAmounts();
		for (String symbol: amounts.keySet()) {
			if (rc.amountOf(symbol) < amounts.get(symbol)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Creates the output and puts it in the given ResourceContainer if the requirements are met.
	 * 
	 * @param rc ResourceContainer to use.
	 */
	public void create(ResourceContainer rc) {
		SortedMap<String, Integer> amounts = requirements.currentAmounts();
		for (String symbol: amounts.keySet()) {
			rc.removeResource(symbol, amounts.get(symbol));
		}
		rc.addResource(output, outputAmount);
	}
	
	/**
	 * Returns a String of the required resources and their corresponding amounts.
	 * 
	 * @return String of the required resources and their corresponding amounts.
	 */
	public String requirements() {
		return requirements.toString();
	}

}
