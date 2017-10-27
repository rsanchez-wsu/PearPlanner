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

package Controller;

import Model.Account;
import Model.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bijan on 08/05/2017.
 */
public class StudyPlannerControllerTest {

	/**set up.
	 * @throws Exception catch any exception that occur
	 */
	@Before
	public void setUp() throws Exception {
		Account a = new Account(new Person("Mr", "Adrew", true), "100125464");
		StudyPlannerController studyPlannerController = new StudyPlannerController(a);
	}

	/**get study profile.
	 * @throws Exception catch any exception that occur
	 */
	@Test
	public void getStudyProfiles() throws Exception {
	}

	/**validate file.
	 * @throws Exception catch any exception that occur
	 */
	@Test
	public void fileValidation() throws Exception {
	}

	/**check whether that file exist.
	 * @throws Exception catch any exception that occur
	 */
	@Test
	public void containsStudyProfile() throws Exception {
	}

	/**get current version.
	 * @throws Exception catch any exception that occur
	 */
	@Test
	public void getCurrentVersion() throws Exception {
	}

	/**create new study profile.
	 * @throws Exception catch any exception that occur
	 */
	@Test
	public void createStudyProfile() throws Exception {
	}

	/**update study profile.
	 * @throws Exception catch any exception that occur
	 */
	@Test
	public void updateStudyProfile() throws Exception {
	}

	/**get list of tasks.
	 * @throws Exception catch any exception that occur
	 */
	@Test
	public void getListOfTasks() throws Exception {

	}

	/**add new activity.
	 * @throws Exception catch any exception that occur
	 */
	@Test
	public void newActivity() throws Exception {
	}

}