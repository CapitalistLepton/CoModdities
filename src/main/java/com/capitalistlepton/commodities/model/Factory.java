package com.capitalistlepton.commodities.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Factory object which can craft higher tier resources using recipes.
 * 
 * @author Zane Littrell
 */
public class Factory extends ResourceContainer {
	
	private final List<Recipe> recipes;
	private final String name;

	/**
	 * Creates a new Factory with the given list of recipes.
	 * 
	 * @param recipes List of all possible recipes for the Factory.
	 * @throws NullPointerException when name or recipes are null.
	 */
	public Factory(String name, List<Recipe> recipes) {
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
		return recipes;
	}
	
	/**
	 * Attempts to create the specified Recipe. Returns false if the Recipe could not be made.
	 * 
	 * @param r Recipe to make.
	 * @throws IllegalArgumentException if r is not in the Factory's list of recipes.
	 * @return whether or not the creation was successful.
	 */
	public boolean createRecipe(Recipe r) {
		if (!recipes.contains(r)) {
			throw new IllegalArgumentException("That recipe isn't in this Factory's list "
					+ "of recipes");
		}
		if (r.validate(this)) {
			r.create(this);
			return true;
		} else {
			return false;
		}
	}
	
}
