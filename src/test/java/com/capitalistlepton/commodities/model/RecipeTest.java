package com.capitalistlepton.commodities.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Recipe.
 * 
 * @author Zane Littrell
 */
public class RecipeTest { // NOPMD

  /** Company 1 for testing. */
  private Company co1;
  /** Company 2 for testing. */
  private Company co2;

  /**
   * Set up before each test.
   */
  @Before
  public void setUp() {
    co1 = new Company("Test 1", 500);
    co1.addResource("LUM", 30);
    co1.addResource("STO", 30);

    co2 = new Company("Test 2", 500);
    co2.addResource("COA", 30);
    co2.addResource("IRO", 30);
  }

  /**
   * Test method for {@link com.capitalistlepton.commodities.model.Recipe
   *   #validate(com.capitalistlepton.commodities.model.ResourceContainer)}.
   */
  @Test
  public void testValidateTrue() {
    assertTrue(Recipe.STEEL.validate(co2));
  }

  /**
   * Test method without the requirements for {@link com.capitalistlepton.commodities.model.Recipe
   *   #validate(com.capitalistlepton.commodities.model.ResourceContainer)}.
   */
  @Test
  public void testValidateFalse() {
    assertFalse(Recipe.STEEL.validate(co1));
  }

  /**
   * Test method for {@link com.capitalistlepton.commodities.model.Recipe
   *   #create(com.capitalistlepton.commodities.model.ResourceContainer)}.
   */
  @Test
  public void testCreate() {
    Recipe.STEEL.create(co2);
    assertEquals(2, co2.amountOf("STL"));
  }

  /**
   * Test method without the requirements for {@link com.capitalistlepton.commodities.model.Recipe
   *   #create(com.capitalistlepton.commodities.model.ResourceContainer)}.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCreateFalse() {
    Recipe.STEEL.create(co1);
  }

  /**
   * Test method for {@link com.capitalistlepton.commodities.model.Recipe#requirement()}.
   */
  @Test
  public void testRequirements() {
    assertEquals("CHA: 0 unit(s) COA: 1 unit(s) IRO: 3 unit(s) LUM: 0 unit(s) "
        + "PIG: 0 unit(s) STL: 0 unit(s) STO: 0 unit(s) ", Recipe.STEEL.requirementsString());
  }

}
