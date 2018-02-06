package com.capitalistlepton.commodities.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for Factory.
 * 
 * @author Zane Littrell
 */
public class FactoryTest { // NOPMD

  /** Factory 1 for testing. */
  private Factory fac1;
  /** Factory 2 for testing. */
  private Factory fac2;
  /** Factory 3 for testing. */
  private Factory fac3;
  /** Factory 4 for testing. */
  private Factory fac4;

  /**
   * Run before each test.
   */
  @Before
  public void setUp() {
    fac1 = new Factory("Test 1 Factory", Arrays.asList(Recipe.STEEL));
    fac2 = new Factory("Test 2 Factory", new ArrayList<Recipe>());
    fac3 = new Factory("Test 1 Factory", Arrays.asList(Recipe.STEEL));
    fac4 = new Factory("Test 4 Factory", Arrays.asList(Recipe.STEEL));
  }

  /**
   * Test method for {@link com.capitalistlepton.commodities.model
   *   .Factory#Factory(String, java.util.List)}.
   */
  @Test (expected = NullPointerException.class)
  public void testFactoryNullName() {
    new Factory(null, Arrays.asList(Recipe.STEEL));
  }

  /**
   * Test method for {@link com.capitalistlepton.commodities.model
   *   .Factory#Factory(String, java.util.List)}.
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
    assertEquals("Test 1 Factory", fac1.getName());
  }

  /**
   * Test method for {@link com.capitalistlepton.commodities.model.Factory#getRecipes()}.
   */
  @Test
  public void testGetRecipes() {
    assertEquals(Arrays.asList(Recipe.STEEL), fac1.getRecipes());
  }


  /**
   * Test method for {@link com.capitalistlepton.commodities.model
   *   .Factory#createRecipe(com.capitalistlepton.commodities.model.Recipe)}.
   */
  @Test
  public void testCreateRecipePass() {
    fac1.addResource("COA", 6);
    fac1.addResource("IRO", 6);
    assertTrue(fac1.createRecipe(Recipe.STEEL));
    assertEquals(2, fac1.amountOf("STL"));
  }

  /**
   * Test method for {@link com.capitalistlepton.commodities.model
   *   .Factory#createRecipe(com.capitalistlepton.commodities.model.Recipe)}.
   */
  @Test
  public void testCreateRecipeFail() {
    fac1.addResource("LUM", 6);
    fac1.addResource("IRO", 2);
    assertFalse(fac1.createRecipe(Recipe.STEEL));
    assertEquals(0, fac1.amountOf("STL"));
  }

  /**
   * Test method for {@link com.capitalistlepton.commodities.model
   *   .Factory#createRecipe(com.capitalistlepton.commodities.model.Recipe)}.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCreateRecipeNoRecipe() {
    assertFalse(fac1.createRecipe(null));
  }

  /**
   * Test method for {@link com.capitalistlepton.commodities.model.Factory#equals(Object)}.
   */
  @Test
  public void testEqualsTransitive() {
    assertEquals(fac1, fac3);
    assertEquals(fac3, fac1);
  }

  /**
   * Test method for {@link com.capitalistlepton.commodities.model.Factory#equals(Object)}.
   */
  @Test
  public void testEqualsReflexive() {
    assertEquals(fac1, fac1);
  }

  /**
   * Test method for {@link com.capitalistlepton.commodities.model.Factory#equals(Object)}.
   */
  @Test
  public void testEqualsFalse() {
    assertFalse(fac1.equals(fac2));
  }

  /**
   * Test method for {@link com.capitalistlepton.commodities.model.Factory#equals(Object)}.
   */
  @Test
  public void testEqualsNull() {
    assertFalse(fac1.equals(null));
  }

  /**
   * Test method for {@link com.capitalistlepton.commodities.model.Factory#equals(Object)}.
   */
  @Test
  public void testEqualsWrongClass() {
    assertFalse(fac1.equals(new Object()));
  }

  /**
   * Test method for {@link com.capitalistlepton.commodities.model.Factory#equals(Object)}.
   */
  @Test
  public void testEqualsWrongName() {
    assertFalse(fac1.equals(fac4));
  }
  
  /**
   * Test that hashcode is the same for equal objects.
   */
  @Test
  public void testHashCode() {
    assertEquals(fac1.hashCode(), fac3.hashCode());
  }

}
