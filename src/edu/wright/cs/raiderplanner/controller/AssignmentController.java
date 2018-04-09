package edu.wright.cs.raiderplanner.controller;

import edu.wright.cs.raiderplanner.model.QuantityType;
import edu.wright.cs.raiderplanner.model.Room;
import edu.wright.cs.raiderplanner.model.Task;
import edu.wright.cs.raiderplanner.model.Assignment;
import edu.wright.cs.raiderplanner.model.Coursework;
import edu.wright.cs.raiderplanner.model.Deadline;
import edu.wright.cs.raiderplanner.model.Event;
import edu.wright.cs.raiderplanner.model.Exam;
import edu.wright.cs.raiderplanner.model.ExamEvent;
import edu.wright.cs.raiderplanner.model.Extension;
import edu.wright.cs.raiderplanner.model.Person;
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
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
	@FXML private TextField setBy;

	// Labels:
	@FXML private Label title;


	/**
	 * Handle changes to the input fields.
	 */
	public void handleChange() {
		if (!this.name.getText().trim().isEmpty()
				&& !this.date.getValue().isBefore(LocalDate.now())
				&& !this.weighting.getText().trim().isEmpty()
				&& !this.setBy.getText().trim().isEmpty()
				&& this.assignmentType.getSelectionModel().getSelectedIndex() != -1) {

			this.submit.setDisable(false);

		}

		if (this.name.getText().trim().isEmpty()
				|| this.date.getValue().isBefore(LocalDate.now())
				|| this.weighting.getText().trim().isEmpty()
				|| this.setBy.getText().trim().isEmpty()
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
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy'T00:00:00Z'");
			LocalDate ldeadline = this.date.getValue();
			String sdeadline = ldeadline.format(formatter);
			Deadline deadline = new Deadline(sdeadline);
			Person person = new Person("", this.setBy.getText(), true);
			if (this.assignmentType.getValue() == "Coursework") {
				ArrayList<Extension> cextentions = new ArrayList<Extension>();
				this.assignment = new Coursework(Integer.parseInt(this.weighting.getText()), person, person, person, 0, null, deadline, cextentions);
				this.assignment.setName(this.name.getText());
				// TODO Add dates
			} else if (this.assignmentType.getValue() == "Exam") {
				Room room = null;
				ExamEvent examEvent = new ExamEvent(sdeadline, room, 0);
				this.assignment = new Exam(Integer.parseInt(this.weighting.getText()), person, person, person, 0, examEvent);
				this.assignment.setName(this.name.getText());
			}
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
		String[] placeholder = {"Coursework", "Exam"};
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
			this.setBy.setEditable(false);
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