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

package View;

import Controller.DataController;
import Controller.StudyPlannerController;
import Model.HubFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by bendickson on 5/4/17.
 */
public class ConsoleIo {
	static ArrayList<String> logged = new ArrayList<>();

	/**
	 * Retrieve user input.
	 *
	 * @param message Message to be shown
	 * @return retrieved String
	 */
	public static String getDataString(String message) {
		Scanner scan = new Scanner(System.in);
		String r = "";
		while (r.equals("")) {
			System.out.println(message);
			r = scan.nextLine();
		}
		scan.close();
		return r;
	}

	/**
	 * Get yes/no input.
	 *
	 * @param message Message to be shown.
	 * @return true for yes, false for no.
	 */
	public static boolean getDataBool(String message) {
		Scanner scan = new Scanner(System.in);
		String r = "";
		while (!(r.equals("y") || r.equals("n"))) {
			System.out.println(message);
			r = scan.next();
		}
		scan.close();
		return r.equals("y");
	}

	/**
	 * How many lines have been written to the log.
	 *
	 * @return number of lines.
	 */
	public static int getLogSize() {
		return logged.size();
	}

	/**
	 * Save the full log to a file.
	 *
	 * @param filePath file path
	 */
	public static void saveLog(String filePath) {
		saveLog(filePath, 0, logged.size());
	}

	/**
	 * Save specific lines to a file.
	 *
	 * @param filePath  file path
	 * @param startLine starting line
	 * @param endLine   end line
	 */
	public static void saveLog(String filePath, int startLine, int endLine) {
		if (startLine < 0) {
			startLine = 0;
		}
		if (endLine > logged.size()) {
			endLine = logged.size();
		}
		int i = startLine - 1;
		try {
			File logFile = new File(filePath);
			FileOutputStream fos = new FileOutputStream(logFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			while (++i < endLine) {
				if (i != startLine) {
					bw.newLine();
				}
				bw.write(logged.get(i));
			}
			bw.close();
		} catch (Exception e) {
			setConsoleMessage("File not written", true);
		}
	}

	/**
	 * Display a message on a console.
	 *
	 * @param message message to be displayed.
	 */
	public static void setConsoleMessage(String message) {
		System.out.println(message);
	}

	/**
	 * Display a message with an option of saving it to a log.
	 *
	 * @param message	message to be displayed (or logged)
	 * @param logMessage whether to log it
	 */
	public static void setConsoleMessage(String message, boolean logMessage) {
		System.out.println(message);
		if (logMessage) {
			logged.add(message);
		}
	}

	/**
	 * Get user selection of a menu option.
	 *
	 * @param menuOptions available options
	 * @return user selection
	 */
	public static int getMenuOption(String[] menuOptions) {
		Scanner scan = new Scanner(System.in);
		int option = -1;
		while (option < 0 || option >= menuOptions.length) {
			System.out.println("Please select an option\n");
			option = -1;
			while (++option < menuOptions.length) {
				System.out.printf("%d - " + menuOptions[option] + "\n", option);
			}
			option = scan.nextInt();
		}
		scan.close();
		return option;
	}


	// Console view below
	/**get menu options in main menu.
	 * @return String
	 */
	public static String view_main() {
		View.ConsoleIo.setConsoleMessage("MAIN MENU");
		// list of options
		String[] menuOptions = {"Create Study Profile",
				"View Study Profile", "View Notifications", "Quit Program"};
		int choice = View.ConsoleIo.getMenuOption(menuOptions);

		return menuOptions[choice];

	}

	/**get menu options in create a study profile menu.
	 * @return String
	 */
	public static String view_createSp() {
		View.ConsoleIo.setConsoleMessage("CREATE A STUDY PROFILE");
		// list of options
		String[] menuOptions = {"Load Study Profile File", "Return to Main Menu"};
		int choice = View.ConsoleIo.getMenuOption(menuOptions);

		return menuOptions[choice];

	}

	/** view of view study planner.
	 * @param Spc	study planner controller
	 * @return String
	 */
	public static String view_viewSp(StudyPlannerController Spc) {
		View.ConsoleIo.setConsoleMessage("VIEW A STUDY PROFILE");
		String[] studyProfiles = Spc.getPlanner().getListOfStudyProfileNames();
		int i = -1, ii = studyProfiles.length;

		if (ii < 1) {
			View.ConsoleIo.setConsoleMessage("No existing study profiles");
		}

		String[] menuOptions = new String[ii + 1];
		while (++i < ii) {
			menuOptions[i] = studyProfiles[i];
		}
		menuOptions[ii] = "Return to Main Menu";

		int m = -1;
		while (m < ii) {
			m = View.ConsoleIo.getMenuOption(menuOptions);
/*			if (m < ii) {

			}*/
		}

		return menuOptions[ii];
	}

	/** view of load study planner.
	 * @param Spc	study planner controller
	 * @return String
	 */
	public static String view_loadSp(StudyPlannerController Spc) {
		View.ConsoleIo.setConsoleMessage("LOAD A STUDY PROFILE");

		String filename = getDataString("Enter filepath:");
		File tempFile = new File(filename);
		HubFile fileData = DataController.loadHubFile(tempFile);
		while (!filename.equals("") && fileData == null) {
			filename = getDataString("File not valid, enter a different filepath:");
			tempFile = new File(filename);
			fileData = DataController.loadHubFile(tempFile);
		}

		System.out.println(fileData.toString(true));

		return "Return to Main Menu";
	}
}
