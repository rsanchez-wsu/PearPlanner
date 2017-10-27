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

package Model;

import Controller.MainController;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

/**
 * Created by bijan on 06/05/2017.
 */
@Ignore
public class VersionControlEntityTest extends ApplicationTest {

	VersionControlEntity versionControlEntity = new VersionControlEntity();

	/**set up.
	 * @throws Exception catch any exception that occur
	 */
	@Before
	public void setUp() throws Exception {
		versionControlEntity = new VersionControlEntity();
	}


	@Override
	public void start(Stage stage) throws Exception {
		MainController.initialise();
		MainController.isNumeric("23");
	}

	@Test
	public void findAndUpdate() throws Exception {
		assertFalse(VersionControlEntity.findAndUpdate(versionControlEntity));

		versionControlEntity.setUId("99856-ID");
		assertTrue(VersionControlEntity.findAndUpdate(versionControlEntity));
	}

	@Test
	public void inLibrary() throws Exception {
		versionControlEntity.setUId("1234-ID");
		assertTrue(VersionControlEntity.inLibrary("1234-ID"));

		assertFalse(VersionControlEntity.inLibrary("10132-ID"));
		versionControlEntity.setUId("10132-ID");
		assertTrue(VersionControlEntity.inLibrary("10132-ID"));
	}

	@Test
	public void getVersion() throws Exception {
		assertEquals(0, versionControlEntity.getVersion());
	}

	@Test
	public void getUId() throws Exception {
		assertEquals(null, versionControlEntity.getUId());
	}

	@Test
	public void setUId() throws Exception {
		// Testing setUID with one argument
		versionControlEntity.setUId("1234-ID");
		assertEquals("1234-ID", versionControlEntity.getUId());

		// Testing the duplication
		versionControlEntity.setUId("1234-ID");

		// Testing setUID with two argument
		assertEquals(true, versionControlEntity.setUId("95657-ID",1));
		assertEquals("95657-ID", versionControlEntity.getUId());
		assertEquals(1, versionControlEntity.getVersion());

		// Testing the duplication
//		assertFalse(versionControlEntity.setUID("5678-ID", 2));
	}

	/**set all the fields to null.
	 * @throws Exception catch any exception that occur
	 */
	@After
	public void tearDown() throws Exception {
		versionControlEntity = null;
	}
}