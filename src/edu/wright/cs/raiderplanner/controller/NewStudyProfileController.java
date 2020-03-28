/*
 * Copyright (C) 2017 - Benjamin Dickson, Andrew Odintsov, Zilvinas Ceikauskas,
 * Bijan Ghasemi Afshar
 *
 * Copyright (C) 2019 - Nathan Dunn
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



package edu.wright.cs.raiderplanner.controller;

import com.github.plushaze.traynotification.animations.Animations;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import edu.wright.cs.raiderplanner.model.Event;
import edu.wright.cs.raiderplanner.model.HubFile;
import edu.wright.cs.raiderplanner.model.Module;
import edu.wright.cs.raiderplanner.model.MultilineString;
import edu.wright.cs.raiderplanner.model.VersionControlEntity;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This class creates the items needed for the popup GUI.
 */
public class NewStudyProfileController implements Initializable {
	public static DataFormat format = new DataFormat("object/Requirement");

	private HubFile hubFile;

	// Buttons:
	@FXML
	private Button submit;

	// Panes:
	@FXML
	private GridPane pane;

	// Text:
	@FXML
	private ComboBox<String> semesterType;
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker endDate;
	@FXML
	private TextField profileName;
	@FXML
	private TextField year;

	// Labels:
	@FXML
	private Label title;

	/**
	 * Getter for HubFile.
	 *
	 * @return the HubFile object being managed by this controller.
	 */
	public HubFile getHubFile() {
		return hubFile;
	}

	/**
	 * Handle changes to the input fields.
	 */
	public void handleChange() {
		// Try to unlock:
		unlockSubmit();
	}

	/**
	 * Used to test all user entries needed before allowing the ok/submit button to be pressed.
	 * Checks all input fields for incorrect data, including whether weighting is an Integer.
	 * @return true if unlock is successful, or false if not.
	 */
	public boolean unlockSubmit() {
		if (!this.profileName.getText().trim().isEmpty() && this.profileName.getText() != null
				&& !this.year.getText().trim().isEmpty() && this.year.getText() != null
				&& !this.startDate.getValue().isBefore(LocalDate.now())
				&& !this.endDate.getValue().isBefore(LocalDate.now())
				&& this.semesterType.getSelectionModel().getSelectedIndex() != -1) {
			this.submit.setDisable(false);
			return true;
		} else {
			this.submit.setDisable(true);
			return false;
		}
	}

	/**
	 * Submit the form and create a new Task.
	 */
	public void handleSubmit() {
		TrayNotification trayNotif = new TrayNotification();
		trayNotif.setTitle("Raider Planner");
		trayNotif.setRectangleFill(Paint.valueOf("#2A9A84"));
		trayNotif.setAnimation(Animations.POPUP);
		trayNotif.setNotification(Notifications.SUCCESS);
		trayNotif.showAndDismiss(Duration.seconds(2));

		if (this.hubFile == null) {
			ArrayList<Module> moduleList = new ArrayList();
			ArrayList<VersionControlEntity> vceList = new ArrayList();
			ArrayList<Event> eventList = new ArrayList();
			int semesterTypeInt = 1;
			if (semesterType.getValue().equals("Fall")) {
				semesterTypeInt = 1;
			} else if (semesterType.getValue().equals("Spring")) {
				semesterTypeInt = 2;
			} else {
				semesterTypeInt = 3;
			}
			// TODO "Date to Date" should be changed to represent
			// actual given date values
			MultilineString dl = new MultilineString("Date to Date");
			String ul = "SP_" + this.year.getText() + "_" + semesterTypeInt;
			// Create a new HubFile:
			this.hubFile = new HubFile(1,Integer.parseInt(this.year.getText()), semesterTypeInt,
					moduleList, vceList, eventList, this.profileName.getText(), dl, ul);
			// =================
			trayNotif.setMessage("Study Profile Successfully Created");
		}
		Stage stage = (Stage) this.submit.getScene().getWindow();
		stage.close();
	}

	/**
	 * Handle Quit button.
	 */
	public void handleQuit() {
		Stage stage = (Stage) this.submit.getScene().getWindow();
		stage.close();
	}

	/**
	 * Limits number of characters typed in all textArea/textfields.
	 */
	public void limitTextInput() {
		this.profileName.setTextFormatter(new TextFormatter<String>(change
				-> change.getControlNewText().length() <= 100 ? change : null));
		this.year.setTextFormatter(new TextFormatter<String>(change
				-> change.getControlNewText().length() <= 50 ? change : null));
	}

	/**
	 * Validate data in the startDate field.
	 */
	public void validateStartDate() {
		if (this.startDate.getValue().isBefore(LocalDate.now())) {
			this.startDate.setStyle("-fx-border-color:red;");
			this.submit.setDisable(true);
		} else {
			this.startDate.setStyle("");
			this.handleChange();
		}
	}

	/**
	 * Validate data in the endDate field.
	 */
	public void validateEndDate() {
		if (this.endDate.getValue().isBefore(LocalDate.now())) {
			this.endDate.setStyle("-fx-border-color:red;");
			this.submit.setDisable(true);
		} else {
			this.endDate.setStyle("");
			this.handleChange();
		}
	}

	/**
	 * Constructor for the NewStudyProfileController.
	 */
	public NewStudyProfileController() {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.semesterType.getItems().addAll("Fall", "Spring", "Summer");

		// Button actions:

		// Handle Task details:
		if (this.hubFile != null) {
			// Disable/modify elements:
			this.title.setText("Create a Study Profile");
			// Fill in data:
			this.profileName.setText(this.hubFile.getSemesterName());
			this.year.setText(Integer.toString(this.hubFile.getYear()));
			this.semesterType.getSelectionModel().select(this.hubFile.getSemester());
			this.startDate.setValue(this.hubFile.getStartDate().toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate());
			this.endDate.setValue(this.hubFile.getEndDate().toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate());
			// =================
		}
		// =================

		Platform.runLater(() -> this.pane.requestFocus());
	}
}
