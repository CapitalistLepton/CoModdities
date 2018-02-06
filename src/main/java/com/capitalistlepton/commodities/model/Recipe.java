package com.capitalistlepton.commodities.model;

import java.util.Objects;
import java.util.SortedMap;

/**
 * Represents a recipe that creates a new resource given some input reources.
 * 
 * @author Zane Littrell
 * @version 0.0.1
 */
public final class Recipe {

  /** Requirements for steel recipe. */
  private static ResourceContainer steelRequirements;
  /** Requirements for pig iron recipe. */
  private static ResourceContainer pigRequirements;
  /** Requirements for charcoal recipe. */
  private static ResourceContainer charRequirements;

  static {
    steelRequirements = new ResourceContainer();
    steelRequirements.addResource("COA", 1);
    steelRequirements.addResource("IRO", 3);

    pigRequirements = new ResourceContainer();
    pigRequirements.addResource("CHA", 2);
    pigRequirements.addResource("IRO", 3);

    charRequirements = new ResourceContainer();
    charRequirements.addResource("LUM", 2);
  }

  /** Steel recipe. */
  public static final Recipe STEEL = new Recipe(steelRequirements, 
      ResourceContainer.getResource("STL"), 2);
  /** Pig iron recipe. */
  public static final Recipe PIG_IRON = new Recipe(steelRequirements, 
      ResourceContainer.getResource("PIG"), 1);
  /** Charcoal recipe. */
  public static final Recipe CHARCOAL = new Recipe(steelRequirements, 
      ResourceContainer.getResource("CHA"), 1);
  
  /** Minimum output amount. */
  private static final int MIN_AMOUNT_OUTPUT = 1;

  /** Required resources to create output. */
  private final ResourceContainer requirements;
  /** Resulting resource of the recipe. */
  private final Resource output;
  /** Amount of output resource created. */
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
  private Recipe(final ResourceContainer requirements, final Resource output, 
      final int outputAmount) {
    this.requirements = Objects.requireNonNull(requirements);
    this.output = Objects.requireNonNull(output);
    if (outputAmount < MIN_AMOUNT_OUTPUT) {
      throw new IllegalArgumentException("outputAmount must be >= 1");
    }
    this.outputAmount = outputAmount;
  }

  /**
   * Returns whether or not the requirements are met in the given ResourceContainer.
   * 
   * @param contain ResourceContainer to check.
   * @return whether or not the requirements are met.
   */
  public boolean validate(final ResourceContainer contain) {
    final SortedMap<String, Integer> amounts = requirements.currentAmounts();
    boolean result = true;
    for (final String symbol: amounts.keySet()) { 
      if (contain.amountOf(symbol) < amounts.get(symbol)) {
        result = false;
      }
    }
    return result;
  }

  /**
   * Creates the output and puts it in the given ResourceContainer if the requirements are met.
   * 
   * @param contain ResourceContainer to use.
   */
  public void create(final ResourceContainer contain) {
    final SortedMap<String, Integer> amounts = requirements.currentAmounts();
    for (final String symbol: amounts.keySet()) {
      contain.removeResource(symbol, amounts.get(symbol));
    }
    contain.addResource(output, outputAmount);
  }

  /**
   * Returns a String of the required resources and their corresponding amounts.
   * 
   * @return String of the required resources and their corresponding amounts.
   */
  public String requirementsString() {
    return requirements.toString();
  }

}
