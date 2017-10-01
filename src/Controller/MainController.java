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

import Model.HubFile;
import Model.StudyPlanner;
import View.UIManager;

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
 * Created by bendickson on 5/4/17.
 */
public class MainController {
	public static UIManager ui = new UIManager();

	private static StudyPlannerController SPC;

	// Used for serialization:
<<<<<<< HEAD
	private static SecretKey key64 = new SecretKeySpec(
			new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 }, "Blowfish");
	private static File plannerFile = null;
=======
	private static SecretKey key64 = new SecretKeySpec(new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07}, "Blowfish");
	private static String fileName = "StudyPlanner.dat";
>>>>>>> d7321420b270789891f801ee3b2d7cb96871204a

	/**
	 * Returns a StudyPlannerController.
	 *
	 * @return StudyPlannerController.
	 */
	public static StudyPlannerController getSPC() {
		return SPC;
	}

	/**
<<<<<<< HEAD
	 * Sets StudyPlannerController SPC
	 *
	 * @param s SPC is set to s.
	 */
	public static void setSPC(StudyPlannerController s) {
		SPC = s;
	}

	/**
=======
>>>>>>> d7321420b270789891f801ee3b2d7cb96871204a
	 * Initializes the Study Planner by either registering a new account or
	 * importing an existing Study Planner file.
	 */
	public static void initialise() {
<<<<<<< HEAD
		try {
			ui.showStartup();
=======
		File plannerFile = new File("StudyPlanner.dat");
		try {
>>>>>>> d7321420b270789891f801ee3b2d7cb96871204a
			// If a file is present:
			if (plannerFile.exists()) {
				Cipher cipher = Cipher.getInstance("Blowfish");
				cipher.init(Cipher.DECRYPT_MODE, key64);
<<<<<<< HEAD
				CipherInputStream cipherInputStream = new CipherInputStream(
						new BufferedInputStream(new FileInputStream(plannerFile)), cipher);
				ObjectInputStream inputStream = new ObjectInputStream(cipherInputStream);
				SealedObject sealedObject = (SealedObject) inputStream.readObject();
				SPC = new StudyPlannerController((StudyPlanner) sealedObject.getObject(cipher));

				// Sample note
				if (SPC.getPlanner().getCurrentStudyProfile() != null && SPC.getPlanner()
						.getCurrentStudyProfile().getName().equals("First year Gryffindor")) {
					UIManager.reportSuccess(
							"Note: This is a pre-loaded sample StudyPlanner, as used by Harry "
							+ "Potter. To make your own StudyPlanner, restart the application "
							+ "and choose \"New File\".");
				}

			} else {
				// This should never happen unless a file changes permissions or existence in the
				// miliseconds
				// that it runs the above code after checks in StartupController
				UIManager.reportError("Failed to load file.");
				System.exit(1);
			}

=======
				CipherInputStream cipherInputStream = new CipherInputStream(new BufferedInputStream(new FileInputStream(fileName)), cipher);
				ObjectInputStream inputStream = new ObjectInputStream(cipherInputStream);
				SealedObject sealedObject = (SealedObject) inputStream.readObject();
				SPC = new StudyPlannerController((StudyPlanner) sealedObject.getObject(cipher));
				/**
				 * Begin note to examiner:
				 */
				if (SPC.getPlanner().getCurrentStudyProfile() != null && SPC.getPlanner().getCurrentStudyProfile().getName().equals("First year Gryffindor")) {
					UIManager.reportSuccess("Note to examiner: This is a pre-loaded StudyPlanner, as used by Harry Potter, to reset the software delete or rename the 'StudyPlanner.dat' file in the root directory.");
				/**
				 * End note to examiner.
				 */
				}
			} else {
			// If not, prompt to create a new account:
			
				Account newAccount = ui.createAccount();
				SPC = new StudyPlannerController(newAccount);
				// Welcome notification:
				Notification not = new Notification("Welcome!", new GregorianCalendar(), "Thank you for using RaiderPlanner!");
				SPC.getPlanner().addNotification(not);
			}
>>>>>>> d7321420b270789891f801ee3b2d7cb96871204a
		} catch (FileNotFoundException e) {
			UIManager.reportError("File does not exist");
			System.exit(1);
		} catch (ClassNotFoundException e) {
			UIManager.reportError("Invalid file");
			System.exit(1);
		} catch (NoSuchAlgorithmException e) {
			UIManager.reportError("Cannot decode the given file");
			System.exit(1);
		} catch (BadPaddingException e) {
			UIManager.reportError("Invalid file");
			System.exit(1);
		} catch (InvalidKeyException e) {
			UIManager.reportError("Cannot decode the given file");
			System.exit(1);
		} catch (NoSuchPaddingException e) {
			UIManager.reportError("Invalid file");
			System.exit(1);
		} catch (IOException e) {
			UIManager.reportError("Invalid file");
			System.exit(1);
		} catch (IllegalBlockSizeException e) {
			UIManager.reportError("Invalid file");
			System.exit(1);
		} catch (Exception e) {
			UIManager.reportError(e.getMessage());
			System.exit(1);
		}
	}

	/**
	 * Display the main menu.
	 */
	public static void main() {
		try {
			ui.mainMenu();
		} catch (Exception e) {
			UIManager.reportError(e.getMessage());
		}
	}

	/**
	 * Handles importing a new file.
	 *
	 * @return whether imported successfully.
	 */
	public static boolean importFile() {
		// Call a dialog:
		File tempFile = ui.loadFileDialog();
		if (tempFile != null) {
			// If a file was selected, process the file:
			HubFile fileData = DataController.loadHubFile(tempFile);
			if (fileData != null) {
				if (!fileData.isUpdate() && !MainController.SPC.createStudyProfile(fileData)) {
					UIManager.reportError("This Study Profile is already created!");
				} else {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Save the current state of the program to file
	 *
	 * @return whether saved successfully.
	 */
	public static boolean save() {
		try {
<<<<<<< HEAD
			SPC.save(MainController.key64, MainController.plannerFile.getAbsolutePath());
=======
			SPC.save(MainController.key64, MainController.fileName);
>>>>>>> d7321420b270789891f801ee3b2d7cb96871204a
			return true;
		} catch (Exception e) {
			UIManager.reportError("FAILED TO SAVE YOUR DATA!");
			return false;
		}
	}

	/**
<<<<<<< HEAD
	 * Sets the planner file that is loaded/saved.
	 *
	 * @param file is the path used to load and save files.
	 */
	public static void setPlannerFile(File file) {
		plannerFile = file;
		return;
	}

	/**
	 * Apparently (according to Stackoverflow) the Java Standard library doesn't have a
	 * standard check for testing if a string value is a number or not?!)
	 *
	 * <p>Therefore, we are using this proposed isNumeric method from:
	 *
	 * <p>http://stackoverflow.com/a/1102916
=======
	 * Apparently (according to Stackoverflow) the Java Standard library doesn't have a
	 * standard check for testing if a string value is a number or not?!)
	 * <p>
	 * Therefore, we are using this proposed isNumeric method from:
	 * <p>
	 * http://stackoverflow.com/a/1102916
>>>>>>> d7321420b270789891f801ee3b2d7cb96871204a
	 *
	 * @param str String to be tested
	 * @return whether the given String is numeric.
	 */
	public static boolean isNumeric(String str) {
		try {
<<<<<<< HEAD
			// No need to assign the result; the exception or lack of is what matters
			Double.parseDouble(str);
=======
			double d = Double.parseDouble(str);
>>>>>>> d7321420b270789891f801ee3b2d7cb96871204a
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

<<<<<<< HEAD
}
=======
	/**
	 * Early console based implementation.
	 *
	 * @param menu menu option.
	 */
	private static void consoleUI(String menu) {
		while (!menu.equals("")) {
			switch (menu) {
			case "Quit Program":
				menu = "";
				break;
			case "Main Menu":
			case "Return to Main Menu":
				menu = View.ConsoleIO.view_main();
				break;
			case "Create Study Profile":
				menu = View.ConsoleIO.view_createSP();
				break;
			case "View Study Profile":
				menu = View.ConsoleIO.view_viewSP(SPC);
				break;
			case "Load Study Profile File":
				menu = View.ConsoleIO.view_loadSP(SPC);
			default:
				menu = "";
			}
		}
	}
}
>>>>>>> d7321420b270789891f801ee3b2d7cb96871204a
