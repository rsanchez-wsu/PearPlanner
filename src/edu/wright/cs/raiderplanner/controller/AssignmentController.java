package edu.wright.cs.raiderplanner.controller;

import edu.wright.cs.raiderplanner.model.QuantityType;
import edu.wright.cs.raiderplanner.model.Task;
import edu.wright.cs.raiderplanner.model.Assignment;
import edu.wright.cs.raiderplanner.model.Coursework;
import edu.wright.cs.raiderplanner.model.Exam;
import edu.wright.cs.raiderplanner.view.UIManager;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

/**
 * @author Katie
 *
 */
public class AssignmentController implements Initializable {
	private Assignment assignment;
	private boolean success = false;

	/**
	 * Default constructor.
	 */
	public AssignmentController() {
	}

	/**
	 * Constructor for an AssignmentController with an existing Assignment.
	 *
	 * @param assignment the Assignment which will be managed by the new controller
	 */
	public AssignmentController(Assignment assignment) {
		this.assignment = assignment;
	}

	/**
	 * @return the Assignment object being managed by this controller.
	 */
	public Assignment getAssignment() {
		return this.assignment;
	}

	/**
	 * @return true if the last submit operation succeeded, false otherwise.
	 */
	public boolean isSuccess() {
		return success;
	}

	// Panes:
	@FXML private GridPane pane;

	// Buttons:
	@FXML private Button submit;

	// Text:
	@FXML private TextField name;
	@FXML private ComboBox<String> assignmentType;
	@FXML private TextField weighting;
	@FXML private DatePicker date;

	// Labels:
	@FXML private Label title;


	/**
	 * Handle changes to the input fields.
	 */
	public void handleChange() {
		if (!this.name.getText().trim().isEmpty()
				&& !this.date.getValue().isBefore(LocalDate.now())
				&& !this.weighting.getText().trim().isEmpty()
				&& this.assignmentType.getSelectionModel().getSelectedIndex() != -1) {

			this.submit.setDisable(false);

		}

		if (this.name.getText().trim().isEmpty()
				|| this.date.getValue().isBefore(LocalDate.now())
				|| this.weighting.getText().trim().isEmpty()
				|| this.assignmentType.getSelectionModel().getSelectedIndex() == -1) {

			this.submit.setDisable(true);

		}
	}

	/**
	 * Validate data in the Duration field.
	 */
	public void validateWeighting() {
		if (!MainController.isNumeric(this.weighting.getText())
				|| Integer.parseInt(this.weighting.getText()) < 0) {
			this.weighting.setStyle("-fx-text-box-border:red;");
			this.submit.setDisable(true);
		} else {
			this.weighting.setStyle("");
			this.handleChange();
		}
	}


	/**
	 * Validate data in the Date field.
	 */
	public void validateDate() {
		if (this.date.getValue().isBefore(LocalDate.now())) {
			this.date.setStyle("-fx-border-color:red;");
			this.submit.setDisable(true);
		} else {
			this.date.setStyle("");
			this.handleChange();
		}
	}

	/**
	 * Handle the 'Add Assignment' button action.
	 */
	public void addTask() {
		// Table items:
		ObservableList<Task> list = FXCollections
				.observableArrayList(MainController.getSpc().getCurrentTasks());
		if (this.assignment != null) {
			list.removeAll(this.assignment.getTasks());
		}
		list.removeIf(e -> !e.dependenciesComplete());
		// =================
	}

	/**
	 * Submit the form and create a new Assignment.
	 */
	public void handleSubmit() {
		if (this.assignment == null) {			
			this.assignment = new Assignment(Integer.parseInt(this.weighting.getText()), null, null, null, 0);
			this.assignment.setName(this.name.getText());
			// TODO Add dates
		}

		this.success = true;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] placeholder = {"TODO", "Test"};
		this.assignmentType.getItems().addAll(placeholder);
		this.date.setValue(LocalDate.now());

		// Handle Assignment details:
		if (this.assignment != null) {
			// Disable/modify elements:
			this.title.setText("Assignment");
			this.name.setEditable(false);
			this.weighting.setEditable(false);
			this.date.setDisable(true);
			this.assignmentType.setDisable(true);
			// =================

			// Fill in data:
			this.name.setText(this.assignment.getName());

			// =================
		} else {
			this.handleChange();
		}
		// =================

		Platform.runLater(() -> this.pane.requestFocus());
	}
}