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

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

/**
 * Created by bijan on 06/05/2017.
 */
public class StudyProfileTest {
	private StudyProfile studyProfile;
	private HubFile hubFile;
	private Person corganiser;
	private Module module;
	private List<ExtensionApplication> e2;
	private List<VersionControlEntity> u2;
	private ArrayList<Module> moduleList;
	private Module[] moduleArray;

	@BeforeEach
	public void setUp() throws Exception {
		studyProfile = new StudyProfile(hubFile);
		studyProfile.se
		corganiser = new Person("Mr.", "Greene", true);
		e2 = new ArrayList<>();
		u2 = new ArrayList<>();
		hubFile = new HubFile(1, e2, u2);
		module = new Module(corganiser, "sampleCModuleCode");
		moduleList = new ArrayList<>();
		moduleArray = new Module[moduleList.size()];

	}
	

	@Test
	public void getModulesTest() throws Exception {
		Module[] temp = new Module[moduleList.size()];
		temp = moduleList.toArray(temp);
		final Module[] expResult = temp;
		final Module[] result = studyProfile.getModules();
		assertEquals(expResult, result);
		
	}

	@Test
	public void getMilestonesTest() {
		
	}

	@Test
	public void getExtensionsTest() {
		
	}
	
	@Test
	public void getCalendarTest() {
		
	}
	
	@Test
	public void getTasksTest() {
		
	}
	
	@Test
	public void isCurrentTest() {
		
	}
	
	@Test
	public void setCurrentTest() {
		
	}
	
	@Test
	public void addEventToCalendarTest() {
		
	}
	
	@Test
	public void getNameTest() {
		
	}
	
	@Test
	public void getYearTest() {
		
	}
	
	@Test
	public void getSemesterNoTest() {
		
	}
	
	@Test
	public void matchesTest() {
		
	}
	
	@Test
	public void addMilestoneTest() {
		
	}
	
	@Test
	public void removeMilestoneTest() {
		
	}
	
	@Test
	public void openTest() {
		
	}
	
	@Test
	public void studyProfileTest() {
		
	}
}