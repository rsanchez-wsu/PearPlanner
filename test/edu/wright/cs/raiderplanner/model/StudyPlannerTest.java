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

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bijan on 06/05/2017.
 */

public class StudyPlannerTest {

	/**
	 * Test of getCalendar method, of class StudyPlanner.
	 */
	@Test
	public void testGetCalendar() {
		System.out.println("getCalendar");
		StudyPlanner instance = null;
		ArrayList<Event> expResult = null;
		ArrayList<Event> result = instance.getCalendar();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getListOfStudyProfileNames method, of class StudyPlanner.
	 */
	@Test
	public void testGetListOfStudyProfileNames() {
		System.out.println("getListOfStudyProfileNames");
		StudyPlanner instance = null;
		String[] expResult = null;
		String[] result = instance.getListOfStudyProfileNames();
		assertArrayEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getStudyProfiles method, of class StudyPlanner.
	 */
	@Test
	public void testGetStudyProfiles() {
		System.out.println("getStudyProfiles");
		StudyPlanner instance = null;
		StudyProfile[] expResult = null;
		StudyProfile[] result = instance.getStudyProfiles();
		assertArrayEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getTimeSpent method, of class StudyPlanner.
	 */
	@Test
	public void testGetTimeSpent() {
		System.out.println("getTimeSpent");
		Module module = null;
		StudyPlanner instance = null;
		int expResult = 0;
		int result = instance.getTimeSpent(module);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of containsStudyProfile method, of class StudyPlanner.
	 */
	@Test
	public void testContainsStudyProfile() {
		System.out.println("containsStudyProfile");
		int syear = 0;
		int ssem = 0;
		StudyPlanner instance = null;
		boolean expResult = false;
		boolean result = instance.containsStudyProfile(syear, ssem);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of addEventToCalendar method, of class StudyPlanner.
	 */
	@Test
	public void testAddEventToCalendar() {
		System.out.println("addEventToCalendar");
		Event event = null;
		StudyPlanner instance = null;
		instance.addEventToCalendar(event);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getCurrentStudyProfile method, of class StudyPlanner.
	 */
	@Test
	public void testGetCurrentStudyProfile() {
		System.out.println("getCurrentStudyProfile");
		StudyPlanner instance = null;
		StudyProfile expResult = null;
		StudyProfile result = instance.getCurrentStudyProfile();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getUserName method, of class StudyPlanner.
	 */
	@Test
	public void testGetUserName() {
		System.out.println("getUserName");
		StudyPlanner instance = null;
		String expResult = "";
		String result = instance.getUserName();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getNotifications method, of class StudyPlanner.
	 */
	@Test
	public void testGetNotifications() {
		System.out.println("getNotifications");
		StudyPlanner instance = null;
		Notification[] expResult = null;
		Notification[] result = instance.getNotifications();
		assertArrayEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getUnreadNotifications method, of class StudyPlanner.
	 */
	@Test
	public void testGetUnreadNotifications() {
		System.out.println("getUnreadNotifications");
		StudyPlanner instance = null;
		Notification[] expResult = null;
		Notification[] result = instance.getUnreadNotifications();
		assertArrayEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getDeadlineNotifications method, of class StudyPlanner.
	 */
	@Test
	public void testGetDeadlineNotifications() {
		System.out.println("getDeadlineNotifications");
		StudyPlanner instance = null;
		HashMap expResult = null;
		HashMap result = instance.getDeadlineNotifications();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getQuantityTypes method, of class StudyPlanner.
	 */
	@Test
	public void testGetQuantityTypes() {
		System.out.println("getQuantityTypes");
		StudyPlanner instance = null;
		ArrayList<QuantityType> expResult = null;
		ArrayList<QuantityType> result = instance.getQuantityTypes();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getTaskTypes method, of class StudyPlanner.
	 */
	@Test
	public void testGetTaskTypes() {
		System.out.println("getTaskTypes");
		StudyPlanner instance = null;
		ArrayList<TaskType> expResult = null;
		ArrayList<TaskType> result = instance.getTaskTypes();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setCurrentStudyProfile method, of class StudyPlanner.
	 */
	@Test
	public void testSetCurrentStudyProfile_StudyProfile() {
		System.out.println("setCurrentStudyProfile");
		StudyProfile profile = null;
		StudyPlanner instance = null;
		boolean expResult = false;
		boolean result = instance.setCurrentStudyProfile(profile);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setCurrentStudyProfile method, of class StudyPlanner.
	 */
	@Test
	public void testSetCurrentStudyProfile_String() {
		System.out.println("setCurrentStudyProfile");
		String profileId = "";
		StudyPlanner instance = null;
		boolean expResult = false;
		boolean result = instance.setCurrentStudyProfile(profileId);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of addStudyProfile method, of class StudyPlanner.
	 */
	@Test
	public void testAddStudyProfile() {
		System.out.println("addStudyProfile");
		StudyProfile profile = null;
		StudyPlanner instance = null;
		instance.addStudyProfile(profile);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of addNotification method, of class StudyPlanner.
	 */
	@Test
	public void testAddNotification() {
		System.out.println("addNotification");
		Notification notification = null;
		StudyPlanner instance = null;
		instance.addNotification(notification);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of addActivity method, of class StudyPlanner.
	 */
	@Test
	public void testAddActivity() {
		System.out.println("addActivity");
		Activity activity = null;
		StudyPlanner instance = null;
		instance.addActivity(activity);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of addToVersionControlLibrary method, of class StudyPlanner.
	 */
	@Test
	public void testAddToVersionControlLibrary() {
		System.out.println("addToVersionControlLibrary");
		VersionControlEntity vce = null;
		StudyPlanner instance = null;
		boolean expResult = false;
		boolean result = instance.addToVersionControlLibrary(vce);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of emptyVersionControlLibrary method, of class StudyPlanner.
	 */
	@Test
	public void testEmptyVersionControlLibrary() {
		System.out.println("emptyVersionControlLibrary");
		StudyPlanner instance = null;
		boolean expResult = false;
		boolean result = instance.emptyVersionControlLibrary();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of rebuildVersionControlLibrary method, of class StudyPlanner.
	 */
	@Test
	public void testRebuildVersionControlLibrary() {
		System.out.println("rebuildVersionControlLibrary");
		StudyPlanner instance = null;
		instance.rebuildVersionControlLibrary();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setVersion method, of class StudyPlanner.
	 */
	@Test
	public void testSetVersion() {
		System.out.println("setVersion");
		int newVersion = 0;
		StudyPlanner instance = null;
		boolean expResult = false;
		boolean result = instance.setVersion(newVersion);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getVersion method, of class StudyPlanner.
	 */
	@Test
	public void testGetVersion() {
		System.out.println("getVersion");
		StudyPlanner instance = null;
		int expResult = 0;
		int result = instance.getVersion();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}