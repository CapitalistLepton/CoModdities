package com.capitalistlepton.commodities.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Factory object which can craft higher tier resources using recipes.
 * 
 * @author Zane Littrell
 * @version 0.0.1
 */
public class Factory extends ResourceContainer {

  /** List of all possible recipes. */
  private final List<Recipe> recipes;
  /** Name of the factory. */
  private final String name;

  /** Factory that can make steel. */
  public static final Factory STEEL_FACTORY = new Factory("Steel Factory", 
      Arrays.asList(Recipe.STEEL));
  /** Factory that can make pig iron. */
  public static final Factory PIG_IRON_FACTORY = new Factory("Pig Iron Refinery", 
      Arrays.asList(Recipe.PIG_IRON));
  /** Factory that can make charcoal. */
  public static final Factory CHARCOAL_FACTORY = new Factory("Charcoal Manufactory", 
      Arrays.asList(Recipe.CHARCOAL));

  /**
   * Creates a new Factory with the given list of recipes.
   * 
   * @param recipes List of all possible recipes for the Factory.
   * @throws NullPointerException when name or recipes are null.
   */
  public Factory(final String name, final List<Recipe> recipes) {
    super();
    this.name = Objects.requireNonNull(name);
    this.recipes = Collections.unmodifiableList(Objects.requireNonNull(recipes));
  }

  /**
   * Returns the name of this Factory.
   * 
   * @return the name of this Factory.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the List of all possible recipes.
   * 
   * @return the List of all possible recipes.
   */
  public List<Recipe> getRecipes() {
    return new ArrayList<>(recipes);
  }

  /**
   * Attempts to create the specified Recipe. Returns false if the Recipe could 
   * not be made.
   * 
   * @param recipe Recipe to make.
   * @return whether or not the creation was successful.
   * @throws IllegalArgumentException if r is not in the Factory's list of recipes.
   */
  public boolean createRecipe(final Recipe recipe) {
    if (!recipes.contains(recipe)) {
      throw new IllegalArgumentException("That recipe isn't in this Factory's list "
          + "of recipes");
    }
    boolean result;
    if (recipe.validate(this)) {
      recipe.create(this);
      result = true;
    } else {
      result = false;
    }
    return result;
  }

  /**
   * {@inheritDoc}
   * <br>
   * <p>
   * Two Factories are equal when they have the same recipe list and name.
   * </p>
   */
  @Override
  public boolean equals(final Object other) {
    boolean result;
    if (other == this) {
      result = true;
    } else if (other == null || other.getClass() != this.getClass()) {
      result = false;
    } else {
      final Factory factory = (Factory) other;
      result = recipes.equals(factory.recipes) && name.equals(factory.name);
    }
    return result;
  }
  
  /**
   * {@inheritDoc}
   * <p>
   * Hash code is based off of the recipes and name instance variables.
   * </p>
   */
  @Override
  public int hashCode() {
    return Objects.hash(recipes, name);
  }

}
