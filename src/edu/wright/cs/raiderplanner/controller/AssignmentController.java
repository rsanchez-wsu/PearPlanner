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

import edu.wright.cs.raiderplanner.model.Assignment;
import edu.wright.cs.raiderplanner.model.Coursework;
import edu.wright.cs.raiderplanner.model.Deadline;
import edu.wright.cs.raiderplanner.model.Event;
import edu.wright.cs.raiderplanner.model.Extension;
import edu.wright.cs.raiderplanner.model.Module;
import edu.wright.cs.raiderplanner.model.Person;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Nathan Dunn on 18/4/2019.
 */
public class AssignmentController implements Initializable {

	private Module module;
	private Assignment assignment;
	private boolean success = false;

	/**
	 * Standard getter method for assignment.
	 * @return assignment that was created.
	 */
	public Assignment getAssignment() {
		return this.assignment;
	}

	/**
	 * Getter for checking if assignment controller initialized successful.
	 * @return boolean bases on initialized success
	 */
	public boolean isSuccess() {
		return success;
	}

	// Panes:
	@FXML
	private GridPane pane;

	// Buttons:
	@FXML
	private Button submit;
	@FXML
	private Button quit;

	// Text:
	@FXML
	private TextField name;
	@FXML
	private TextField weighting;
	// Labels:
	@FXML
	private Label title;

	// Dates;
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker dueDate;

	// Tooltips:
	@FXML
	private Label nameTooltip;
	@FXML
	private Label startTooltip;
	@FXML
	private Label dateTooltip;
	@FXML
	private Label weightingTooltip;
	@FXML
	private Label headingTooltip;

	/**
	 * Handle changes to the input fields.
	 */
	public void handleChange() {
		if (!this.name.getText().trim().isEmpty()
				&& !this.weighting.getText().trim().isEmpty()
				&& MainController.isNumeric(this.weighting.getText())
				&& MainController.isInteger(this.weighting.getText())
				&& Integer.parseInt(this.weighting.getText()) >= 0
				&& !this.startDate.getValue().isBefore(LocalDate.now())
				&& !this.dueDate.getValue().isBefore(LocalDate.now())
				&& this.startDate.getValue().isBefore(this.dueDate.getValue())) {
			this.submit.setDisable(false);
		} else {
			this.submit.setDisable(true);
		}
	}

	/**
	 * Submit the form and create a new assignment.
	 */
	public void handleSubmit() {

		// attributes for new assignment
		String stringName = name.getText();
		int weight = Integer.parseInt(weighting.getText());
		LocalDate localStartDate = dueDate.getValue();
		LocalDate localDueDate = dueDate.getValue();
		Deadline deadline = new Deadline(localDueDate.format(DateTimeFormatter
				.ofPattern("MM/dd/yyyy")) + "T00:00:01Z");
		Person organizer = module.getOrganiser();

		// Create a new assignment
		this.assignment = new Coursework(weight, organizer, organizer,
				organizer, 0, new Event(localStartDate.toString()),
				deadline, new ArrayList<Extension>());
		this.assignment.setName(stringName);

		this.success = true;
		Stage stage = (Stage) this.submit.getScene().getWindow();
		stage.close();
	}

	/**
	 * Handle Quit button.
	 */
	public void handleQuit() {
		Stage stage = (Stage) this.quit.getScene().getWindow();
		stage.close();
	}

	/**
	 * Constructor for the AssignmentController.
	 * @param module - Module of this assignment.
	 */
	public AssignmentController(Module module) {
		this.module = module;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Initialize tooltip messages:
		nameTooltip.setTooltip(new Tooltip("Enter the name of the assignment."));
		startTooltip.setTooltip(new Tooltip("Enter the start date of the assignment."));
		dateTooltip.setTooltip(new Tooltip("Enter the due date of the assignment."));
		weightingTooltip.setTooltip(new Tooltip("Enter the weight of the assignment."));
		headingTooltip.setTooltip(new Tooltip("Add an assignment to your module."));
		Platform.runLater(() -> this.pane.requestFocus());
	}

}
