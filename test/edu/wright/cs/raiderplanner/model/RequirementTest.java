/*
 * Copyright (C) 2017 - Benjamin Dickson, Andrew Odintsov, Zilvinas Ceikauskas,
 * Bijan Ghasemi Afshar
 *
 *
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package edu.wright.cs.raiderplanner.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import edu.wright.cs.raiderplanner.controller.MenuController;

import org.junit.jupiter.api.Test;

/**
 * Created by bijan on 06/05/2017.
 */

public class RequirementTest {

	/**
	 * Test of isComplete method, of class Requirement.
	 */
	@Test
	public void testIsComplete() {
		System.out.println("isComplete");
		Requirement instance = null;
		boolean expResult = false;
		boolean result = instance.isComplete();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getQuantityType method, of class Requirement.
	 */
	@Test
	public void testGetQuantityType() {
		System.out.println("getQuantityType");
		Requirement instance = null;
		QuantityType expResult = null;
		QuantityType result = instance.getQuantityType();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getEstimatedTimeInHours method, of class Requirement.
	 */
	@Test
	public void testGetEstimatedTimeInHours() {
		System.out.println("getEstimatedTimeInHours");
		Requirement instance = null;
		double expResult = 0.0;
		double result = instance.getEstimatedTimeInHours();
		assertEquals(expResult, result, 0.0);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getInitialQuantity method, of class Requirement.
	 */
	@Test
	public void testGetInitialQuantity() {
		System.out.println("getInitialQuantity");
		Requirement instance = null;
		int expResult = 0;
		int result = instance.getInitialQuantity();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getRemainingQuantity method, of class Requirement.
	 */
	@Test
	public void testGetRemainingQuantity() {
		System.out.println("getRemainingQuantity");
		Requirement instance = null;
		int expResult = 0;
		int result = instance.getRemainingQuantity();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getActivityLog method, of class Requirement.
	 */
	@Test
	public void testGetActivityLog() {
		System.out.println("getActivityLog");
		Requirement instance = null;
		Activity[] expResult = null;
		Activity[] result = instance.getActivityLog();
		assertArrayEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of requirementProgress method, of class Requirement.
	 */
	@Test
	public void testRequirementProgress() {
		System.out.println("requirementProgress");
		Requirement instance = null;
		double expResult = 0.0;
		double result = instance.requirementProgress();
		assertEquals(expResult, result, 0.0);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setEstimatedTimeInHours method, of class Requirement.
	 */
	@Test
	public void testSetEstimatedTimeInHours() {
		System.out.println("setEstimatedTimeInHours");
		double estimatedTimeInHours = 0.0;
		Requirement instance = null;
		instance.setEstimatedTimeInHours(estimatedTimeInHours);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setInitialQuantity method, of class Requirement.
	 */
	@Test
	public void testSetInitialQuantity() {
		System.out.println("setInitialQuantity");
		int initialQuantity = 0;
		Requirement instance = null;
		instance.setInitialQuantity(initialQuantity);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setQuantityType method, of class Requirement.
	 */
	@Test
	public void testSetQuantityType() {
		System.out.println("setQuantityType");
		String quantityType = "";
		Requirement instance = null;
		instance.setQuantityType(quantityType);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of addActivity method, of class Requirement.
	 */
	@Test
	public void testAddActivity() {
		System.out.println("addActivity");
		Activity activity = null;
		Requirement instance = null;
		instance.addActivity(activity);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of update method, of class Requirement.
	 */
	@Test
	public void testUpdate() {
		System.out.println("update");
		Requirement instance = null;
		boolean expResult = false;
		boolean result = instance.update();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of toString method, of class Requirement.
	 */
	@Test
	public void testToString() {
		System.out.println("toString");
		Requirement instance = null;
		String expResult = "";
		String result = instance.toString();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of equals method, of class Requirement.
	 */
	@Test
	public void testEquals() {
		System.out.println("equals");
		Object object = null;
		Requirement instance = null;
		boolean expResult = false;
		boolean result = instance.equals(object);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of open method, of class Requirement.
	 */
	@Test
	public void testOpen() {
		System.out.println("open");
		MenuController.Window current = null;
		Requirement instance = null;
		instance.open(current);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

}
