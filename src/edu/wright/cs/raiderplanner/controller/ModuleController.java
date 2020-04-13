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

import edu.wright.cs.raiderplanner.model.Module;
import edu.wright.cs.raiderplanner.model.Person;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Nathan Dunn.
 */
public class ModuleController implements Initializable {
	private Module module;
	private boolean success = false;

	/**
	 * Standard method for getting module.
	 *
	 * @return module that was created.
	 */
	public Module getModule() {
		return this.module;
	}

	/**
	 * Getter for checking if module controller initialized successful.
	 *
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
	private TextArea details;
	@FXML
	private TextField name;
	@FXML
	private TextField code;
	@FXML
	private TextField organizer;
	@FXML
	private TextField salutation;
	@FXML
	private TextField organizerName;
	@FXML
	private TextField majorId;

	// Labels:
	@FXML
	private Label title;

	// Tooltips:
	@FXML
	private Label nameTooltip;
	@FXML
	private Label codeTooltip;
	@FXML
	private Label headingTooltip;
	@FXML
	private Label organizerTooltip;

	/**
	 * Handle changes to the input fields.
	 */
	public void handleChange() {
		// Check the input fields:
		if (!this.name.getText().trim().isEmpty()
				&& !this.code.getText().trim().isEmpty()
				&& !this.salutation.getText().isEmpty()
				&& !this.organizerName.getText().trim().isEmpty()) {
			this.submit.setDisable(false);
		}
	}

	/**
	 * Submit the form and create a new module.
	 */
	public void handleSubmit() {
		if (this.module == null) {
			// Create a new Module
			Person organizer = new Person(this.salutation.getText(),
					this.organizerName.getText(), true, this.code.getText());

			this.module = new Module(organizer, this.code.getText());
			this.module.setName(this.name.getText());
			this.module.setDetails(this.details.getText());

		} else {

			Person organizer = new Person(this.salutation.getText(),
					this.organizerName.getText(), true, null);

			// Update the current Module:
			this.module.setOrganiser(organizer);
			this.module.setModuleCode(this.code.getText());
			this.module.setName(this.name.getText());
			this.module.setDetails(this.details.getText());
		}

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

	// Constructors:

	/**
	 * Constructor for the ModuleController.
	 */
	public ModuleController() {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Initialize tooltip messages:
		nameTooltip.setTooltip(new Tooltip("Enter the name of the module."));
		codeTooltip.setTooltip(new Tooltip("Enter the code of the module."));
		organizerTooltip.setTooltip(new Tooltip("Enter the name of the module "
				+ "organizer."));
		headingTooltip.setTooltip(new Tooltip("A Module is synonymous to a class. "
				+ "A module is composed of assignments in which a student can create study "
				+ "plans for."));

		Platform.runLater(() -> this.pane.requestFocus());
	}

}
