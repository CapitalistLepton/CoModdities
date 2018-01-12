package com.capitalistlepton.commodities.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Factory
 * 
 * @author Zane Littrell
 */
public class FactoryTest {
	
	private Factory f1;
	private Factory f2;
	private Factory f3;
	private Factory f4;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		f1 = new Factory("Test 1 Factory", Arrays.asList(Recipe.STEEL));
		f2 = new Factory("Test 2 Factory", new ArrayList<Recipe>());
		f3 = new Factory("Test 1 Factory", Arrays.asList(Recipe.STEEL));
		f4 = new Factory("Test 4 Factory", Arrays.asList(Recipe.STEEL));
	}
	
	/**
	 * Test method for {@link com.capitalistlepton.commodities.model.Factory#Factory(String, java.util.List)
	 */
	@Test (expected = NullPointerException.class)
	public void testFactoryNullName() {
		new Factory(null, Arrays.asList(Recipe.STEEL));
	}
	
	/**
	 * Test method for {@link com.capitalistlepton.commodities.model.Factory#Factory(String, java.util.List)
	 */
	@Test (expected = NullPointerException.class)
	public void testFactoryNullRecipes() {
		new Factory("Fail", null);
	}

	/**
	 * Test method for {@link com.capitalistlepton.commodities.model.Factory#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals("Test 1 Factory", f1.getName());
	}

	/**
	 * Test method for {@link com.capitalistlepton.commodities.model.Factory#getRecipes()}.
	 */
	@Test
	public void testGetRecipes() {
		assertEquals(Arrays.asList(Recipe.STEEL), f1.getRecipes());
	}

	
	/**
	 * Test method for {@link com.capitalistlepton.commodities.model.Factory#createRecipe(com.capitalistlepton.commodities.model.Recipe)}.
	 */
	@Test
	public void testCreateRecipePass() {
		f1.addResource("LUM", 6);
		f1.addResource("IRO", 6);
		assertTrue(f1.createRecipe(Recipe.STEEL));
		assertEquals(2, f1.amountOf("STL"));
	}
	
	/**
	 * Test method for {@link com.capitalistlepton.commodities.model.Factory#createRecipe(com.capitalistlepton.commodities.model.Recipe)}.
	 */
	@Test
	public void testCreateRecipeFail() {
		f1.addResource("LUM", 6);
		f1.addResource("IRO", 2);
		assertFalse(f1.createRecipe(Recipe.STEEL));
		assertEquals(0, f1.amountOf("STL"));
	}
	
	/**
	 * Test method for {@link com.capitalistlepton.commodities.model.Factory#createRecipe(com.capitalistlepton.commodities.model.Recipe)}.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testCreateRecipeNoRecipe() {
		assertFalse(f1.createRecipe(null));
	}
	
	/**
	 * Test method for {@link com.capitalistlepton.commodities.model.Factory#equals(Object)}.
	 */
	@Test
	public void testEqualsTransitive() {
		assertEquals(f1, f3);
		assertEquals(f3, f1);
	}
	
	/**
	 * Test method for {@link com.capitalistlepton.commodities.model.Factory#equals(Object)}.
	 */
	@Test
	public void testEqualsReflexive() {
		assertEquals(f1, f1);
	}
	
	/**
	 * Test method for {@link com.capitalistlepton.commodities.model.Factory#equals(Object)}.
	 */
	@Test
	public void testEqualsFalse() {
		assertFalse(f1.equals(f2));
	}
	
	/**
	 * Test method for {@link com.capitalistlepton.commodities.model.Factory#equals(Object)}.
	 */
	@Test
	public void testEqualsNull() {
		assertFalse(f1.equals(null));
	}
	
	/**
	 * Test method for {@link com.capitalistlepton.commodities.model.Factory#equals(Object)}.
	 */
	@Test
	public void testEqualsWrongClass() {
		assertFalse(f1.equals("hello"));
	}
	
	/**
	 * Test method for {@link com.capitalistlepton.commodities.model.Factory#equals(Object)}.
	 */
	@Test
	public void testEqualsWrongName() {
		assertFalse(f1.equals(f4));
	}

}
