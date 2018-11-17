/*

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

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Handle actions associated with the GUI window for creating new accounts.
 * This includes validating the data contained in the various text fields,
 * retrieving the validated data, and storing the submitted data to the proper
 * objects.
 *
 * @author Zilvinas Ceikauskas
 */
public class AccountLoader implements Initializable {
	@FXML private ComboBox<String> option;
	@FXML private Button submit;
	@FXML private GridPane pane;
	@FXML private Alert invalidInputAlert = new Alert(AlertType.ERROR);
	@FXML private Alert emptyNameAlert = new Alert(AlertType.CONFIRMATION);
	@FXML private String load;
	@FXML private String create;
	
	private boolean success = false;
	private int selection;
	
	public boolean isSuccess() {
		return success;
	}
	
	public void Account() {

		if (this.option.getId().equals("load")) {
			 selection=1;
		}
		else if (this.option.getSelectionModel().getSelectedItem().trim().equals("Create")){
			selection= 2;
		}
		else { 
			selection= 3;
		}
	}

	public boolean validChoice() {
		if(selection!=0) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Handle Quit button.
	 */
	public void handleQuit() {
		Stage stage = (Stage) this.submit.getScene().getWindow();
		stage.close();
	}
	public boolean handleSubmit() {
		if(selection==1) {
			return false;
		}
		else{
			return true;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> this.pane.requestFocus());
		submit.defaultButtonProperty().bind(submit.focusedProperty());
		submit.setOnAction(e -> {
			if (submit.isFocused()) {
				handleSubmit();
			}
		});
	}
}