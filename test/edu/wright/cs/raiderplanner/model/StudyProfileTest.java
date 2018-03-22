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

import java.util.ArrayList;

/**
 * Created by bijan on 06/05/2017.
 */

public class StudyProfileTest {

	/**
	 * Test of getModules method, of class StudyProfile.
	 */
	@Test
	public void testGetModules() {
		System.out.println("getModules");
		StudyProfile instance = null;
		Module[] expResult = null;
		Module[] result = instance.getModules();
		assertArrayEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getMilestones method, of class StudyProfile.
	 */
	@Test
	public void testGetMilestones() {
		System.out.println("getMilestones");
		StudyProfile instance = null;
		Milestone[] expResult = null;
		Milestone[] result = instance.getMilestones();
		assertArrayEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getExtensions method, of class StudyProfile.
	 */
	@Test
	public void testGetExtensions() {
		System.out.println("getExtensions");
		StudyProfile instance = null;
		ExtensionApplication[] expResult = null;
		ExtensionApplication[] result = instance.getExtensions();
		assertArrayEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getCalendar method, of class StudyProfile.
	 */
	@Test
	public void testGetCalendar() {
		System.out.println("getCalendar");
		StudyProfile instance = null;
		ArrayList<Event> expResult = null;
		ArrayList<Event> result = instance.getCalendar();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
	/**
	 * Test of getTasks method, of class StudyProfile.
	 */
	@Test
	public void testGetTasks() {
		System.out.println("getTasks");
		StudyProfile instance = null;
		ArrayList<Task> expResult = null;
		ArrayList<Task> result = instance.getTasks();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of isCurrent method, of class StudyProfile.
	 */
	@Test
	public void testIsCurrent() {
		System.out.println("isCurrent");
		StudyProfile instance = null;
		boolean expResult = false;
		boolean result = instance.isCurrent();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setCurrent method, of class StudyProfile.
	 */
	@Test
	public void testSetCurrent() {
		System.out.println("setCurrent");
		boolean current = false;
		StudyProfile instance = null;
		instance.setCurrent(current);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of addEventToCalendar method, of class StudyProfile.
	 */
	@Test
	public void testAddEventToCalendar() {
		System.out.println("addEventToCalendar");
		Event event = null;
		StudyProfile instance = null;
		instance.addEventToCalendar(event);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getName method, of class StudyProfile.
	 */
	@Test
	public void testGetName() {
		System.out.println("getName");
		StudyProfile instance = null;
		String expResult = "";
		String result = instance.getName();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getYear method, of class StudyProfile.
	 */
	@Test
	public void testGetYear() {
		System.out.println("getYear");
		StudyProfile instance = null;
		int expResult = 0;
		int result = instance.getYear();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getSemesterNo method, of class StudyProfile.
	 */
	@Test
	public void testGetSemesterNo() {
		System.out.println("getSemesterNo");
		StudyProfile instance = null;
		int expResult = 0;
		int result = instance.getSemesterNo();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of matches method, of class StudyProfile.
	 */
	@Test
	public void testMatches() {
		System.out.println("matches");
		int myear = 0;
		int msemesterNo = 0;
		StudyProfile instance = null;
		boolean expResult = false;
		boolean result = instance.matches(myear, msemesterNo);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of addMilestone method, of class StudyProfile.
	 */
	@Test
	public void testAddMilestone() {
		System.out.println("addMilestone");
		Milestone milestone = null;
		StudyProfile instance = null;
		instance.addMilestone(milestone);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of removeMilestone method, of class StudyProfile.
	 */
	@Test
	public void testRemoveMilestone() {
		System.out.println("removeMilestone");
		Milestone milestone = null;
		StudyProfile instance = null;
		boolean expResult = false;
		boolean result = instance.removeMilestone(milestone);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of open method, of class StudyProfile.
	 */
	@Test
	public void testOpen() {
		System.out.println("open");
		MenuController.Window current = null;
		StudyProfile instance = null;
		instance.open(current);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

}