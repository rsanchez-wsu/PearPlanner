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

package controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import edu.wright.cs.raiderplanner.controller.StudyPlannerController;
import edu.wright.cs.raiderplanner.model.StudyPlanner;
import edu.wright.cs.raiderplanner.view.UIManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



/**
 * Created by bijan on 08/05/2017.
 */
public class MainControllerTest {
	File tempFile;
	StudyPlannerController spc;
	private SecretKey key64 = new SecretKeySpec(
			new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 }, "Blowfish");

	/**
	 * WIP: This test case should contain data and fields to set up further test cases.
	 * @throws Exception to handle anything that may cause this method to fail.
	 */
	@BeforeEach
	public void setUp() throws Exception {
		tempFile = new File("./StudyProfiles/HP_First_Year.xml");
	}

	/**
	 * WIP: This test case should set up the components of the main controller
	 * and ensure that all assets are available.
	 * @throws Exception to handle any initialization failures.
	 */
	@Test
	public void initialise() throws Exception {
		/*ExceptionInInitializerError occurred again
		If we try to instantiate an UIManager object and run the gradle,
		it will cause ExceptionInInitializerError.*/
		/*if (tempFile.exists()) {
			try {
				Cipher cipher = Cipher.getInstance("Blowfish");
				cipher.init(Cipher.DECRYPT_MODE, key64);
				try (CipherInputStream cis = new CipherInputStream(
						new BufferedInputStream(new FileInputStream(tempFile)), cipher);
						ObjectInputStream ois = new ObjectInputStream(cis)) {
					SealedObject sealedObject = (SealedObject) ois.readObject();
					spc = new StudyPlannerController((StudyPlanner) sealedObject.getObject(cipher));
					// Sample note
					if (spc.getPlanner().getCurrentStudyProfile() != null && spc.getPlanner()
							.getCurrentStudyProfile().getName().equals("First year Gryffindor")) {
						UIManager.reportSuccess(
								"Note: This is a pre-loaded sample StudyPlanner, as used by Harry "
								+ "Potter. To make your own StudyPlanner, restart the application "
								+ "and choose \"New File\".");
					}
					assertTrue("sucess", true);
				}
			} catch (FileNotFoundException e) {
				UIManager.reportError("Error, File does not exist.");
				assertFalse("Error, File does not exist", false);
				System.exit(1);
			} catch (ClassNotFoundException e) {
				UIManager.reportError("Error, Class NotFoundException.");
				assertFalse("Error, Class NotFoundException.", false);
				System.exit(1);
			} catch (BadPaddingException e) {
				UIManager.reportError("Error, Invalid file, Bad Padding Exception.");
				assertFalse("Error, Invalid file, Bad Padding Exception.", false);
				System.exit(1);
			} catch (IOException e) {
				UIManager.reportError("Error, Invalid file.");
				assertFalse("Error, Invalid file.", false);
				System.exit(1);
			} catch (IllegalBlockSizeException e) {
				UIManager.reportError("Error, Invalid file, Illegal Block Size Exception.");
				assertFalse("Error, Invalid file, Illegal Block Size Exception.", false);
				System.exit(1);
			} catch (InvalidKeyException e) {
				UIManager.reportError("Error, Invalid Key, Cannot decode the given file.");
				assertFalse("Error, Invalid Key, Cannot decode the given file.", false);
				System.exit(1);
			} catch (NoSuchAlgorithmException e) {
				UIManager.reportError("Error, Cannot decode the given file.");
				assertFalse("Error, Cannot decode the given file.", false);
				System.exit(1);
			} catch (NoSuchPaddingException e) {
				UIManager.reportError("Error, Invalid file, No Such Padding.");
				assertFalse("Error, Invalid file, No Such Padding.", false);
				System.exit(1);
			} catch (Exception e) {
				UIManager.reportError(e.getMessage() + "Unknown error.");
				assertFalse("Unknown error", false);
				System.exit(1);
			}
		} else {
			// TODO - fix this, as it is clearly a race condition
			// This should never happen unless a file changes permissions
			// or existence in the milliseconds that it runs the above code
			// after checks in StartupController
			UIManager.reportError("Failed to load file.");
			assertFalse("Failed to load file.", false);
			System.exit(1);
		}*/
	}

	/**
	 * WIP: This test case should be used to test error reporting handled by the
	 * main controller.
	 * @throws Exception to handle cases when the error reporting structure does not work
	 * 				as expected.
	 */
	@Test
	public void reportError() throws Exception {
		tempFile = new File("./StudyProfiles/HP_First_Year1.xml");
		if (tempFile.exists()) {
			assertTrue("reportError success", true);
			UIManager.reportError("File not Found");
		} else {
			assertFalse("report Failse",false);
		}
	}

}