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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Model.HubFile;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by bijan on 08/05/2017.
 */
public class DataControllerTest {

	File tempFile = new File(".\\StudyProfiles\\HP_First_Year.xml");
	/**
	 * WIP: This test class should set up required data and fields.
	 * @throws Exception to handle when the test setup fails for any given reason.
	 */

	@BeforeEach
	public void setUp() throws Exception {
	}

	/**
	 * WIP: This test case should verify that there is a settings file present.
	 * @throws Exception to handle when there is no settings file.
	 */
	@Test
	public void existingSettingsFile() throws Exception {
		assertTrue(tempFile.exists());
	}

	/**
	 * WIP: This test case should verify that the UI nodes are reachable.
	 * @throws Exception to handle when the nodes can not be retrieved.
	 */
	@Test
	public void getNodes() throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(tempFile);
		doc.getDocumentElement().normalize();

		Node rootElement = doc.getDocumentElement();
		NodeList nodes = XmlController.getNodes(rootElement);
		assertTrue(nodes != null);

	}

	/**
	 * WIP: This test case should verify that the nodes gathered are valid.
	 * @throws Exception to handle when an invalid node is found.
	 */
	@Test
	public void validNodeList() throws Exception {
	}

	/**
	 * WIP: This test case should attempt to load a sample hub file (a default, for instance).
	 * @throws Exception to handle when the hub file cannot be properly instantiated.
	 */
	@Test
	public void loadHubFileTest() throws Exception {
		Constructor constructor = DataController.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);

		HubFile hub = DataController.loadHubFile(tempFile);
		assertNotNull(hub);
	}

}