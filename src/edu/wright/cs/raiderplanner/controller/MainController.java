/*
 * Copyright (C) 2017 - Benjamin Dickson, Andrew Odintsov, Zilvinas Ceikauskas,
 * Bijan Ghasemi Afshar
 *
 * Copyright (C) 2018 - Ian Mahaffy, Gage Berghoff
 * Copyright (C) 2020 - Joshua Ehlinger
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

package edu.wright.cs.raiderplanner.controller;

import edu.wright.cs.raiderplanner.model.Account;
import edu.wright.cs.raiderplanner.model.Event;
import edu.wright.cs.raiderplanner.model.HubFile;
import edu.wright.cs.raiderplanner.model.ICalExport;
import edu.wright.cs.raiderplanner.model.Notification;
import edu.wright.cs.raiderplanner.model.Settings;
import edu.wright.cs.raiderplanner.model.StudyPlanner;
import edu.wright.cs.raiderplanner.rest.ResponseProcessor;
import edu.wright.cs.raiderplanner.util.RaiderException;
import edu.wright.cs.raiderplanner.view.UiManager;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * A helper class of static methods and fields which are used to handle the
 * loading and saving of application state data.
 *
 * @author Ben Dickson
 */
public class MainController {

	/**
	 * Private constructor to prevent object instantiation.
	 */
	private MainController() {
	}

	// Create ResponseProcessor object
	//static ResponseProcessor responseProcessor = new ResponseProcessor();

	// TODO - Determine if this really should be public
	public static UiManager ui = new UiManager();
	public static Settings settings = new Settings();

	// TODO - StudyPlannerController is a public class; determine if managing an
	// instance in this way is best
	private static StudyPlannerController spc;

	// TODO - Use an approach where a key is generated and stored on the user's system
	// Used for serialization:
	private static SecretKey key64 = new SecretKeySpec(
			new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 }, "Blowfish");
	private static File plannerFile = null;

	// Create ResponseProcessor object to call REST consumption methods
	static ResponseProcessor responseProcessor = new ResponseProcessor();

	/**
	 * Returns the StudyPlannerController managed by this MainController.
	 *
	 * @return the StudyPlannerController managed by this MainController.
	 */
	public static StudyPlannerController getSpc() {
		return spc;
	}

	/**
	 * Sets the StudyPlannerController managed by this MainController.
	 *
	 * @param newSpc the new StudyPlannerController.
	 */
	public static void setSpc(StudyPlannerController newSpc) {
		spc = newSpc;
	}

	/**
	 * Initializes the Study Planner by either registering a new account or
	 * importing an existing Study Planner file.
	 * @throws Exception e if there is an issue registering a new account or importing a file
	 */
	public static void initialise() {
		if (settings.getAccountStartup() == true) {
			File file = new File(settings.getDefaultFilePath());
			if (file.exists() && !file.isDirectory()) {
				plannerFile = file;
			} else {
				boolean noAccount = false;
				File[] files = UiManager.getSavesFolder().listFiles();
				if (files != null) {
					if (files.length == 0) {
						noAccount = true;
					}
					if (files.length == 1
							&& files[0].getName().contains("SamplePlanner.dat")) {
						noAccount = true;
					}
					if (files.length >= 1) {
						try {
							noAccount = MainController.ui.accountStart();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				if (noAccount) {
					try {
						Account newAccount = MainController.ui.createAccount(noAccount);
						StudyPlannerController study =
								new StudyPlannerController(newAccount);
						// Welcome notification:
						Notification not =
								new Notification("Welcome!", new GregorianCalendar(),
								"Thank you for using RaiderPlanner!");
						study.getPlanner().addNotification(not);
						MainController.setSpc(study);
						plannerFile = MainController.ui.savePlannerFileDialog();
						//Create Study Profile
						HubFile hubFile = null;
						try {
							hubFile = ui.createStudyProfile();
						} catch (IOException e) {
							UiManager.reportError("There was a problem creating the Study Profile");
						}
						//StudyProfile profile = new StudyProfile(hubFile);
						WriteStudyProfile studyProfile = new WriteStudyProfile(hubFile);
						if (plannerFile != null) {
							if (plannerFile.getParentFile().exists()) {
								if (plannerFile.getParentFile().canRead()) {
									if (plannerFile.getParentFile().canWrite()) {
										MainController.save();
										loadFile(plannerFile);
									} else {
										UiManager.reportError("Directory can't be written to.");
									}
								} else {
									UiManager.reportError("Directory cannot be read from.");
								}
							} else {
								UiManager.reportError("Directory does not exist.");
							}
						}
						/*This is catching a general exception because the
						 * createAccount method throws a general exception*/
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					long modifiedTime = Long.MIN_VALUE;
					File modifiedFile = new File("");
					for (int i = 0; i < files.length; ++i) {
						if (files[i].lastModified() > modifiedTime) {
							modifiedFile = files[i];
							modifiedTime = files[i].lastModified();
						}
					}
					plannerFile = modifiedFile;
					// If a file is present:
					loadFile(plannerFile);
				}
			}
		} else {
			boolean noAccount = false;
			File[] files = MainController.ui.getSavesFolder().listFiles();
			if (files != null) {
				if (files.length == 0) {
					noAccount = true;
				}
				if (files.length == 1 && files[0].getName().contains("SamplePlanner.dat")) {
					noAccount = true;
				}
			}
			if (noAccount) {
				try {
					Account newAccount = MainController.ui.createAccount(noAccount);
					StudyPlannerController study = new StudyPlannerController(newAccount);
					// Welcome notification:
					Notification not = new Notification("Welcome!", new GregorianCalendar(),
							"Thank you for using RaiderPlanner!");
					study.getPlanner().addNotification(not);
					MainController.setSpc(study);
					plannerFile = MainController.ui.savePlannerFileDialog();
					if (plannerFile != null) {
						if (plannerFile.getParentFile().exists()) {
							if (plannerFile.getParentFile().canRead()) {
								if (plannerFile.getParentFile().canWrite()) {
									MainController.save();
									loadFile(plannerFile);
								} else {
									UiManager.reportError("Directory can not be written to.");
								}
							} else {
								UiManager.reportError("Directory cannot be read from.");
							}

						} else {
							UiManager.reportError("Directory does not exist.");
						}
					}
					/*This is catching a general exception because the
					 * createAccount method throws a general exception*/
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				long modifiedTime = Long.MIN_VALUE;
				File modifiedFile = new File("");
				for (int i = 0; i < files.length; ++i) {
					if (files[i].lastModified() > modifiedTime) {
						modifiedFile = files[i];
						modifiedTime = files[i].lastModified();
					}
				}
				plannerFile = modifiedFile;
				// If a file is present:
				loadFile(plannerFile);
			}
		}
	}

	/**
	 * Decrypts a file and loads it.
	 * @param plannerFile the file to be loaded
	 */
	public static void loadFile(File plannerFile) {
		if (plannerFile.exists()) {
			try {
				Cipher cipher = Cipher.getInstance("Blowfish");
				cipher.init(Cipher.DECRYPT_MODE, key64);
				try (CipherInputStream cis = new CipherInputStream(
						new BufferedInputStream(new FileInputStream(plannerFile)), cipher);
						ObjectInputStream ois = new ObjectInputStream(cis)) {
					SealedObject sealedObject = (SealedObject) ois.readObject();
					spc = new StudyPlannerController((StudyPlanner) sealedObject.getObject(cipher));
					// Sample note for Harry Potter HUB file
					if (spc.getPlanner().getCurrentStudyProfile() != null && spc.getPlanner()
							.getCurrentStudyProfile().getName().equals("First Year Gryffindor")) {
						UiManager.reportSuccess(
								"Note: This is a pre-loaded sample StudyPlanner, as used by Harry "
								+ "Potter. To make your own StudyPlanner, restart the application "
								+ "and choose \"New File\".");
					}
					// Sample notes for 2019 WSU HUB file
					if (spc.getPlanner().getCurrentStudyProfile() != null && spc.getPlanner()
							.getCurrentStudyProfile().getName().equals("Fall 2019 Semester")) {
						UiManager.reportSuccess(
								"Note: This is a sample StudyPlanner that is pre-loaded with"
								+ " WSU information. "
								+ "To make your own StudyPlanner, restart the application "
								+ "and choose \"New File\".");
					}
				}
			} catch (FileNotFoundException e) {
				UiManager.reportError("Cannot find this file.","file not found exception when "
						+ "trying loadfile, most likely due to invalid parameters");
				System.exit(1);
			} catch (ClassNotFoundException e) {
				UiManager.reportError("Error, Cannot find the study planner.", "class not found"
						+ " exception thrown when trying loadfile, most likely due to problems"
						+ " reading the object input stream as a sealed object");
				System.exit(1);
			} catch (BadPaddingException e) {
				UiManager.reportError("Error, Cannot decode the given file.", "bad padding"
						+ " exception thrown when trying loadfile, most likely due to "
						+ "problems with the cipher");
				System.exit(1);
			} catch (IOException e) {
				UiManager.reportError("Error, Invalid file.", "IO exception thrown when trying"
						+ " loadfile, most likely due to an invalide file or problems with "
						+ "one of the input streams");
				System.exit(1);
			} catch (IllegalBlockSizeException e) {
				UiManager.reportError("Error, Object too large, cannot decode the file.",
						"Illegal block size exception thrown when trying loadfile, most "
						+ "likely due to a problem with constructing a sealed object");
				System.exit(1);
			}  catch (InvalidKeyException e) {
				UiManager.reportError("Error, Invalid Key, Cannot decode the given file.",
						"invalid key exception thrown when trying loadfile, most likely due "
						+ "to using an invalid key while initialising the the cypher");
				System.exit(1);
			} catch (NoSuchAlgorithmException e) {
				UiManager.reportError("Error, Cannot decode the given file.", "no such "
						+ "algorithm exception thrown when trying loadfile, most likely "
						+ "due to the program not being able to find the indicated "
						+ "cypher while initialising the the cypher");
				System.exit(1);
			} catch (NoSuchPaddingException e) {
				UiManager.reportError("Error, Cannot decode the given file",  "no such"
						+ " padding exception thrown when trying loadfile, most likely"
						+ " due to problems with the parameters used in the get "
						+ "instance call made while initialising the the cypher");
				System.exit(1);
			}  catch (Exception e) {
				UiManager.reportError(e.getMessage() + "Unknown error.", e.getMessage()
						+ "unknown error occured while trying loadfile");
				System.exit(1);
			}
		} else {
			// TODO - fix this, as it is clearly a race condition
			// This should never happen unless a file changes permissions
			// or existence in the milliseconds that it runs the above code
			// after checks in StartupController
			UiManager.reportError("Failed to load file.");
			System.exit(1);
		}
	}

	/**
	 * Display the main menu.
	 * @throws IOException e if the file doesnt exist. Any other exceptions gets error message.
	 */
	public static void main() throws Exception {
		try {
			ui.mainMenu();
		} catch (IOException e) {
			UiManager.reportError("File does not exist: " + e.getMessage());
		} catch (RaiderException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Display the main menu.
	 * Stage is already present.
	 * @throws IOException e if the file doesnt exist. Any other exceptions gets error message.
	 */
	public static void showMain() throws Exception {
		try {
			ui.showMain();
		} catch (IOException e) {
			UiManager.reportError("File does not exist: " + e.getMessage());
		} catch (RaiderException e) {
			UiManager.reportError(e.getMessage());
		}
	}

	/**
	 * Display the settings menu.
	 * Stage is already present.
	 * @throws IOException e if the file doesnt exist. Any other exceptions gets error message.
	 */
	public static void showSettings() throws Exception {
		try {
			ui.showSettings();
		} catch (IOException e) {
			UiManager.reportError("File does not exist: " + e.getMessage());
		} catch (RaiderException e) {
			UiManager.reportError(e.getMessage());
		}
	}

	/**
	 * Handles importing a new file and if the file is imported it lets the user know.
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
				if (!fileData.isUpdate()
						&& !MainController.spc.createStudyProfile(fileData)) {
					UiManager.reportError("This Study Profile is already created!");
				} else {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Save the current state of the program to file.
	 * @return true for a successful save, false otherwise
	 * @throws Exception e if there was an issue with saving information
	 */
	public static boolean save() {
		try {
			spc.save(MainController.key64, MainController.plannerFile.getAbsolutePath());
			return true;
		} catch (Exception e) { // the save() method already catches an Exception. TODO maybe delete
			UiManager.reportError("FAILED TO SAVE YOUR DATA!");
			return false;
		}
	}

	/**
	 * Sets the planner file that is loaded/saved.
	 *
	 * @param file the File object from which the planner file will be loaded or
	 * 				to which it will be saved.
	 */
	public static void setPlannerFile(File file) {
		plannerFile = file;
	}

	/**
	 * Checks if a string can be converted to an Integer.
	 *
	 * @param value String to be tested
	 * @return True if string can be converted, false otherwise
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Apparently (according to Stackoverflow) the Java Standard library doesn't have a
	 * standard check for testing if a string value is a number or not?!)
	 *
	 * <p>Therefore, we are using this proposed isNumeric method from:
	 *
	 * <p>http://stackoverflow.com/a/1102916
	 *
	 * @param str String to be tested
	 * @return true the given String is numeric (i.e., can be parsed into a
	 * 				Double), false otherwise.
	 */
	public static boolean isNumeric(String str) {
		try {
			// No need to assign the result; the exception or lack of is what matters
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	* Launches the default browser to display a URI.
	*/
	public static void openHelpPage() {
		final Image icon = new Image("file:icon.png");
		String btnStyle = "-fx-pref-width: 100px; -fx-padding: 5; -fx-color: #ffffff;";
		final Button site = new Button("Website");
		site.setStyle(btnStyle);
		final Button pdf = new Button("User Manual");
		pdf.setStyle(btnStyle);
		VBox menuButtons = new VBox();
		menuButtons.getChildren().addAll(pdf, site);
		menuButtons.setSpacing(2);
		final Hyperlink link = new Hyperlink();
		final Hyperlink link1 = new Hyperlink();
		final Hyperlink link2 = new Hyperlink();
		final Hyperlink link3 = new Hyperlink();
		final Hyperlink link4 = new Hyperlink();
		final Hyperlink link5 = new Hyperlink();
		final Hyperlink link6 = new Hyperlink();
		final Hyperlink link7 = new Hyperlink();
		ArrayList positions = new ArrayList<>();
		positions = responseProcessor.retrievePositionTitles();
		ArrayList titles = (ArrayList) positions.get(0);
		Text titleText1 = new Text();
		Text titleText2 = new Text();
		Text titleText3 = new Text();
		String title1 = titles.get(0).toString();
		String title2 = (String) titles.get(1);
		String title3 = (String) titles.get(2);
		titleText1.setText(title1);
		titleText2.setText(title2);
		titleText3.setText(title3);
		ArrayList locations = (ArrayList) positions.get(1);
		Text locationText1 = new Text();
		Text locationText2 = new Text();
		Text locationText3 = new Text();
		String location1 = locations.get(0).toString();
		String location2 = locations.get(1).toString();
		String location3 = locations.get(2).toString();
		locationText1.setText(location1);
		locationText2.setText(location2);
		locationText3.setText(location3);
		ArrayList organizations = (ArrayList) positions.get(2);
		Text organizationText1 = new Text();
		Text organizationText2 = new Text();
		Text organizationText3 = new Text();
		String organization1 = organizations.get(0).toString();
		String organization2 = (String) organizations.get(1);
		String organization3 = (String) organizations.get(2);
		organizationText1.setText(organization1);
		organizationText2.setText(organization2);
		organizationText3.setText(organization3);

		Label tab1 = new Label("RaiderPlanner is an application based off of the Pear Planner "
				+ "to help students keep"
				+ " track of assignments and exams, allowing them to achieve their full academic"
				+ " potential. "
				+ "Current features include a calendar, an alarm, and a Gantt diagram generator"
				+ " to keep track of progress.");
		Label tab2 = new Label("How to use RaiderPlanner in 3 easy steps:"
				+ "\n" + "1: Enter valid information for all the fields on the startup page. "
						+ "All other information is optional." + "\n"
				+ "2: Choose the name of the file you want to save to." + "\n"
				+ "3: To unlock all the other features RaiderPlanner has to offer, click the"
				+ " import hub file button from the menu on the left"
				+  "\nNeed more help? Open up the user manual or RaiderPlanner Website here");
		Label tab3 = new Label("Planned features include a graduation planner, Pilot integration, "
				+ "and a schedule sharing feature. If you want to contribute to RaiderPlanner"
				+ " , follow the link below to our Github repository!");
		final Label tab4 = new Label("1. Can you give me a general overview of RaiderPlanner?\n");
		final Label tab5 = new Label("\n2. How do I create an account?\n" + "\n\tAnswer: Please "
				+ " see the 'Getting Started' tab listed above.  You will need to select "
				+ "a salutation, fill in yourname, a valid UID, and your email.address.\n\n");
		final Label tab6 = new Label("3. What is a HUB file used for?\n" + "\n\tAnswer: A hub file "
				+ "is an eXtensible Markup Language (XML) file, which is designed to store "
				+ "and transport data in a format very similar to Hyper Text Markup "
				+ "Language (HTML).  It carries the data that RaiderPlanner reads in about "
				+ "your courses and schedule.  If you enjoy software development, you can "
				+ "look up Java's DOM parser, and create a parser for ouropen source "
				+ "RaiderPlanner program.\n\n");
		final Label tab7 = new Label("4. Which HUB file should I select?\n" + "\n\tAnswer: "
				+ " Currently, there are several options including: HP_First_Year.xml, "
				+ " StudyProfile_2018_SPRING_UPDATE_001.xml StudyProfile_2018_SPRING.xml "
				+ " and test3.xml.\n\n");
		final Label tab8 = new Label("5. If I see an issue with RaiderPlanner, how can I "
				+ "report it?\n");
		final Label tab9 = new Label("\n6. I am a Software Developer or an aspiring Developer, "
				+ " and would like to contribute to RaiderPlanner, how can I help?\n"
				+ "\n\tAnswer:  You can click the link below and fork our project from GitHub."
				+ " Feel free to add suggestions, or fix bugs whether known or unknown!"
				+ " Then submit a pull request to have your changes made to RaiderPlanner!");
		final Label tab10 = new Label("If you are new to programming and want to learn and try"
				+ " to contribute to RaiderPlanner, there "
				+ "are many things you can do! RaiderPlanner uses the Java programming "
				+ "language and uses practices such as "
				+ "HTML, CSS, and others. Below are some links to get you started learning these "
				+ "practices used in RaiderPlanner!");
		tab1.setWrapText(true);
		tab2.setWrapText(true);
		tab3.setWrapText(true);
		tab4.setWrapText(true);
		tab5.setWrapText(true);
		tab6.setWrapText(true);
		tab7.setWrapText(true);
		tab8.setWrapText(true);
		tab9.setWrapText(true);
		tab10.setWrapText(true);
		VBox splitter1 = new VBox();
		splitter1.getChildren().add(tab1);
		VBox splitter2 = new VBox();
		splitter2.getChildren().add(tab2);
		splitter2.getChildren().add(menuButtons);
		VBox splitter3 = new VBox();
		splitter3.getChildren().add(tab3);
		splitter3.getChildren().add(link7);
		VBox splitter4 = new VBox();
		splitter4.getChildren().add(tab4);
		splitter4.getChildren().add(link);
		splitter4.getChildren().add(tab5);
		splitter4.getChildren().add(tab6);
		splitter4.getChildren().add(tab7);
		splitter4.getChildren().add(tab8);
		splitter4.getChildren().add(link1);
		splitter4.getChildren().add(tab9);
		splitter4.getChildren().add(link2);
		// splitter5 contains "Help me get a job" objects
		VBox splitter5 = new VBox();
		Hyperlink positionLink1 = new Hyperlink();
		Hyperlink positionLink2 = new Hyperlink();
		Hyperlink positionLink3 = new Hyperlink();
		splitter5.getChildren().add(titleText1);
		splitter5.getChildren().add(locationText1);
		splitter5.getChildren().add(organizationText1);
		splitter5.getChildren().add(positionLink1);
		splitter5.getChildren().add(titleText2);
		splitter5.getChildren().add(locationText2);
		splitter5.getChildren().add(organizationText2);
		splitter5.getChildren().add(positionLink2);
		splitter5.getChildren().add(titleText3);
		splitter5.getChildren().add(locationText3);
		splitter5.getChildren().add(organizationText3);
		splitter5.getChildren().add(positionLink3);
		TitledPane t1 = new TitledPane("What is RaiderPlanner?", splitter1);
		t1.setStyle("-fx-border-color: gold;");
		t1.setStyle("-fx-color: green;");
		splitter1.setStyle("-fx-background-color: #CBA052;");
		TitledPane t2 = new TitledPane("Getting Started",splitter2);
		t2.setStyle("-fx-border-color: gold;");
		t1.setStyle("-fx-color: green;");
		splitter1.setStyle("-fx-background-color: #CBA052;");
		TitledPane t3 = new TitledPane("Whats Next?", splitter3);
		t3.setStyle("-fx-border-color: gold;");
		t1.setStyle("-fx-color: green;");
		splitter1.setStyle("-fx-background-color: #CBA052;");
		TitledPane t4 = new TitledPane("Frequently Asked Questions", splitter4);
		t4.setStyle("-fx-border-color: gold;");
		t1.setStyle("-fx-color: green;");
		splitter1.setStyle("-fx-background-color: #CBA052;");
		TitledPane t5 = new TitledPane("Help find me a job!", splitter5);
		splitter1.setStyle("-fx-background-color: #CBA052;");
		t5.setStyle("-fx-border-color: gold;");
		t5.setStyle("-fx-color: green;");
		Accordion root = new Accordion();
		root.setStyle("-fx-background-color:#026937;");
		root.getPanes().addAll(t1, t2, t3, t4, t5);
		Scene scene = new Scene(root,600,600);
		Stage newStage = new Stage();
		newStage.setTitle("RaiderPlanner Help");
		newStage.setScene(scene);
		newStage.getIcons().add(icon);
		newStage.show();

		pdf.setOnAction((event) -> {
			if (Desktop.isDesktopSupported()) {
				try {
					File myFile = new
							File("Final Documents/"
									+ "User Manual RaiderPlanner.pdf");
					Desktop.getDesktop().open(myFile);
				} catch (IOException ex) {
					System.out.println("Error: user-manual not found");
				}
			}
		});
		site.setOnAction((event) -> {
			if (Desktop.isDesktopSupported()) {
				try {
					File myFile = new
							File("docs/index.html");
					Desktop.getDesktop().open(myFile);
				} catch (IOException ex) {
					System.out.println("Error: Website not found");
				}
			}
		});
		link.setText("\tWatch The Overview of RaiderPlanner on Youtube.");
		link.setOnAction((ActionEvent event) -> {
			try {
				String cc = "https://www.youtube.com/watch?v=-tkcqaEy2HU";
				System.out.println(cc);
				Desktop.getDesktop().browse(new URL("https://www.youtube.com/watch?v=-tkcqaEy2HU").toURI());
			} catch (IOException ex) {
				System.out.println("Error: Website not found");
			} catch (URISyntaxException ec) {
				System.out.println("Error: URI not found");
			}
		});
		link1.setText("\tClick here to submit an issue to be fixed in RaiderPlanner");
		link1.setOnAction((ActionEvent event) -> {
			try {
				Desktop.getDesktop().browse(new URL("https://github.com/gzdwsu/RaiderPlanner/issues").toURI());
			} catch (IOException ex) {
				System.out.println("Error: Website not found");
			} catch (URISyntaxException ec) {
				System.out.println("Error: URI not found");
			}
		});
		link2.setText("\tClick to view our project on GitHub");
		link2.setOnAction((ActionEvent event) -> {
			try {
				Desktop.getDesktop().browse(new URL("https://github.com/gzdwsu/RaiderPlanner").toURI());
			} catch (IOException ex) {
				System.out.println("Error: Website not found");
			} catch (URISyntaxException ec) {
				System.out.println("Error: URI not found");
			}
		});
		link3.setText("\tClick to find a local job on USAJobs.");
		link3.setOnAction((ActionEvent event) -> {
			try {
				Desktop.getDesktop().browse(new URL("https://www.google.com").toURI());
			} catch (IOException ex) {
				System.out.println("Error: Website not found");
			} catch (URISyntaxException ec) {
				System.out.println("Error: URI not found");
			}
		});
		link4.setText("\tClick here to learn Java");
		link4.setOnAction((ActionEvent event) -> {
			try {
				Desktop.getDesktop().browse(new URL("https://www.w3schools.com/java/").toURI());
			} catch (IOException ex) {
				System.out.println("Error: Website not found");
			} catch (URISyntaxException ec) {
				System.out.println("Error: URI not found");
			}
		});
		link5.setText("\tClick here to learn HTML");
		link5.setOnAction((ActionEvent event) -> {
			try {
				Desktop.getDesktop().browse(new URL("https://www.w3schools.com/html/").toURI());
			} catch (IOException ex) {
				System.out.println("Error: Website not found");
			} catch (URISyntaxException ec) {
				System.out.println("Error: URI not found");
			}
		});
		link6.setText("\tClick here to learn CSS Styling");
		link6.setOnAction((ActionEvent event) -> {
			try {
				Desktop.getDesktop().browse(new URL("https://www.w3schools.com/css/").toURI());
			} catch (IOException ex) {
				System.out.println("Error: Website not found");
			} catch (URISyntaxException ec) {
				System.out.println("Error: URI not found");
			}
		});
		link7.setText("\tGithub");
		link7.setOnAction((ActionEvent event) -> {
			try {
				Desktop.getDesktop().browse(new URL("https://github.com/gzdwsu/RaiderPlanner").toURI());
			} catch (IOException ex) {
				System.out.println("Error: Website not found");
			} catch (URISyntaxException ec) {
				System.out.println("Error: URI not found");
			}
		});
		ArrayList urls = (ArrayList) positions.get(3);
		String url1 = urls.get(0).toString().replace("\"", "");
		positionLink1.setText("Click to view Job Description");
		positionLink1.setOnAction((ActionEvent event) -> {
			try {
				Desktop.getDesktop().browse(new URL(url1).toURI());
			} catch (IOException ex) {
				System.out.println("Error: Website not found");
			} catch (URISyntaxException ec) {
				System.out.println("Error: URI not found");
			}
		});

		String url2 = urls.get(1).toString().replace("\"", "");
		positionLink2.setText("Click to view Job Description");
		positionLink2.setOnAction((ActionEvent event) -> {
			try {
				Desktop.getDesktop().browse(new URL(url2).toURI());
			} catch (IOException ex) {
				System.out.println("Error: Website not found");
			} catch (URISyntaxException ec) {
				System.out.println("Error: URI not found");
			}
		});

		String url3 = urls.get(2).toString().replace("\"", "");
		positionLink3.setText("Click to view Job Description");
		positionLink3.setOnAction((ActionEvent event) -> {
			try {
				Desktop.getDesktop().browse(new URL(url3).toURI());
			} catch (IOException ex) {
				System.out.println("Error: Website not found");
			} catch (URISyntaxException ec) {
				System.out.println("Error: URI not found");
			}
		});
	}

	/**
         * Function exports calendar ICS file to user defined location.
	 */
	public static void exportCalendar() {
		ICalExport icalExport = new ICalExport();
		try {
			ArrayList<Event> exportCal =
					getSpc().getPlanner().getCurrentStudyProfile().getCalendar();
			for (Event e : exportCal) {
				icalExport.createExportEvent(e);
			}
			icalExport.exportToFile(ui.saveIcsFileDialog());
			UiManager.reportSuccess("File Exported");
		} catch (NullPointerException e) {
			UiManager.reportError("Calendar does not exist! Export failed");
		}
	}
}
