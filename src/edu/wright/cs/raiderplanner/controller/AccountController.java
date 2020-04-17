/*
 * Copyright (C) 2020 - Benjamin Dickson, Andrew Odintsov, Zilvinas Ceikauskas,
 * Bijan Ghasemi Afshar, Alena Brand, Daniel Bleigh, Sierra Sprungl, Nathan Griffith, Bryten Jones
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

package edu.wright.cs.raiderplanner.controller;

import edu.wright.cs.raiderplanner.model.Account;
import edu.wright.cs.raiderplanner.model.Person;
import edu.wright.cs.raiderplanner.view.UiManager;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Handle actions associated with the GUI window for creating new accounts. This includes validating
 * the data contained in the various text fields, retrieving the validated data, and storing the
 * submitted data to the proper objects.
 * UPDATE: Functionality to create a notification email when all data contained in the various text
 * fields has be validated and an account has been successfully created was added. This email will
 * be sent to whatever email address is provided at sign-up.
 *
 * @author Zilvinas Ceikauskas
 */

public class AccountController implements Initializable {
	@FXML
	private TextField accountNo;
	@FXML
	private ComboBox<String> salutation;
	@FXML
	private TextField fullName;
	@FXML
	public TextField email;
	@FXML
	private TextField passwordId;
	@FXML
	private TextField majorId;
	@FXML
	private CheckBox famLast;
	@FXML
	private Button submit;
	@FXML
	private GridPane pane;
	@FXML
	private Alert invalidInputAlert = new Alert(AlertType.ERROR);
	@FXML
	private Alert emptyNameAlert = new Alert(AlertType.CONFIRMATION);


	//public String UserEmail = this.email.getText();

	//UserEmail = this.email.getText();


	private Account account;
	private boolean success = false;


	/**
	 * Getter for Account.
	 *
	 * @return the Account object being managed by this controller.
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * Getter for Success.
	 *
	 * @return true if the last submit operation succeeded, false otherwise.
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Determines if the user has entered a valid salutation by calling the validateSalutation()
	 * from the Person Class in Model, which checks that the entered Salutation only contains a
	 * combination of upper/lower case letters and returns a boolean value. Then sets the style so
	 * it is cohesive.
	 *
	 * @return true if the user entered a valid salutation.
	 */
	public boolean validateSalutation() {
		if (this.salutation.getValue() == null) {
			return false;
		} else {
			this.salutation.setStyle("");
			return true;
		}
	}

	/**
	 * Determines if the user has entered a valid name by calling the validateName() from the Person
	 * Class in Model, which checks that the entered Name only contains a combination of spaces and
	 * upper/lower case letters and returns a boolean value. Then sets the style so it is cohesive.
	 *
	 * @return True if the user entered a valid name.
	 */
	public boolean validateName() {
		if (!Person.validName(this.fullName.getText().trim())) {
			return false;
		} else {
			this.fullName.setStyle("");
			return true;
		}
	}

	/**
	 * Determines if the user has entered a valid password by checking to see if the
	 * password contains
	 * an upper case letter, number, and is more than six characters. If the password does not fit
	 * these requirements than isSuccess returns false.
	 *
	 * @return True if user entered a valid password
	 */
	public boolean validatePassword() {
		if (this.passwordId.getText().trim().isEmpty()) {
			return false;
		} else if (!this.passwordId.getText().contains("0")
				&& !this.passwordId.getText().contains("1")
				&& !this.passwordId.getText().contains("2")
				&& !this.passwordId.getText().contains("3")
				&& !this.passwordId.getText().contains("4")
				&& !this.passwordId.getText().contains("5")
				&& !this.passwordId.getText().contains("6")
				&& !this.passwordId.getText().contains("7")
				&& !this.passwordId.getText().contains("8")
				&& !this.passwordId.getText().contains("9")) {
			System.out.println("Password must contain a number");
			return false;
		} else if (!this.passwordId.getText().contains("A")
				&& !this.passwordId.getText().contains("B")
				&& !this.passwordId.getText().contains("C")
				&& !this.passwordId.getText().contains("D")
				&& !this.passwordId.getText().contains("E")
				&& !this.passwordId.getText().contains("F")
				&& !this.passwordId.getText().contains("G")
				&& !this.passwordId.getText().contains("H")
				&& !this.passwordId.getText().contains("I")
				&& !this.passwordId.getText().contains("J")
				&& !this.passwordId.getText().contains("K")
				&& !this.passwordId.getText().contains("L")
				&& !this.passwordId.getText().contains("M")
				&& !this.passwordId.getText().contains("N")
				&& !this.passwordId.getText().contains("O")
				&& !this.passwordId.getText().contains("P")
				&& !this.passwordId.getText().contains("Q")
				&& !this.passwordId.getText().contains("R")
				&& !this.passwordId.getText().contains("S")
				&& !this.passwordId.getText().contains("T")
				&& !this.passwordId.getText().contains("U")
				&& !this.passwordId.getText().contains("V")
				&& !this.passwordId.getText().contains("V")
				&& !this.passwordId.getText().contains("W")
				&& !this.passwordId.getText().contains("X")
				&& !this.passwordId.getText().contains("Y")
				&& !this.passwordId.getText().contains("Z")) {
			System.out.println("Password must contain a capital letter");
			return false;
		} else if (this.passwordId.getText().length() < 6
				|| this.passwordId.getText().length() > 16) {
			return false;
		} else {
			//this.passwordId.setStyle("");
			return true;
		}
	}

	/**
	 * Determines if the user has entered a valid major by checking that the field is not empty.
	 * Then sets the style so it is cohesive. TODO: Create a dropdown menu so that the user can
	 * select from a list of majors
	 *
	 * @return True if the user entered a valid major.
	 */
	public boolean validateMajor() {
		if (this.majorId.getText().trim().isEmpty()) {
			return false;
		} else {
			// this.major.setStyle("");
			return true;
		}
	}

	/**
	 * Determines if the user has entered a valid email checking if the textfield is empty and by
	 * calling the validateEmail() from the Person Class in Model, which uses the EmailValidator
	 * (Apache Commons Validator 1.6 API) to check that the email is valid and returns a boolean
	 * value. Then sets the style so it is cohesive.
	 *
	 * @return True if the user entered a valid email.
	 */
	public boolean validateEmail() {
		if (this.email.getText().isEmpty()) {
			return false;
		} else if (!this.email.getText().contains("@")) {
			return false;
		} else if (this.email.getText().trim().isEmpty()
				|| Person.validEmail(this.email.getText().trim())) {
			this.email.setStyle("");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determines if the user has entered a valid campus username by checking that the length of the
	 * text is 7, that the first character is a 'w', that the next 3 characters are digits, and that
	 * the last 3 characters are letters and lower case. Then sets the style so it is cohesive.
	 *
	 * @return True if the user entered a valid account number.
	 */
	public boolean validateNumber() {
		if (accountNo.getText().trim().length() == 7) {
			if (accountNo.getText().trim().charAt(0) != 'w') {
				return false;
			} else {
				for (int i = 1; i < 4; ++i) {
					if (!Character.isDigit(accountNo.getText().trim().charAt(i))) {
						return false;
					}
				}
				for (int i = 4; i < 7; ++i) {
					if (!Character.isLetter(accountNo.getText().trim().charAt(i))) {
						return false;
					} else if (!Character.isLowerCase(accountNo.getText().trim().charAt(i))) {
						return false;
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * Gets the users email address, sets up the SMTP server to send out email to user upon
	 * creation of new account. Sets up email to be sent and sets-up and applies HTML styling
	 * to email.
	 *
	 *@return Nothing
	 */
	public void sendEmailhtml() {
		final String backgroundColor = "<body style='background-color:grey;'>";
		final String image = "<img src='https://en.wikipedia.org/wiki/Wright_State_Raiders#/media/"
				+ "File:Wright_State_Raiders_logo.svg' alt='Raider'>";
		final String fontSize = "<font size='6'>Hello,we are sending you this email to confirm "
				+ "that " + "you have succussfully signedup for RaiderPlanner</font>";
		final String smtp_Server = "smtp server";
		final String username = "raiderplanner3120@gmail.com";
		final String password = "Ngbjss3120";
		final String Email_From = "raiderplanner3120@gmail.com";
		final String Email_To = email.getText();
		final String Email_To_cc = "";
		final String Email_Subject = "Welcome To Raider Planner";
		final String Email_Text = backgroundColor + image + fontSize + "Happy Studying," + "\n"
				+ "The RaiderPlanner Team" + "\n"
				+ "Here are your credentials, please do not lose these, your eyes only!"
				+ "\n" + "Email: " + email.getText() + "\n" + "Wright State Username: "
				+ accountNo.getText() + "\n" +  "Password" + passwordId.getText()
				+ "\n" + "Major: " + majorId.getText();
		Properties properties = System.getProperties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

	 	Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator() {
	 		@Override
			protected PasswordAuthentication getPasswordAuthentication() {
	 			return new PasswordAuthentication(username,password);
	 		}
	 		});
	 		try {
	 			MimeMessage message = new MimeMessage(session);
	 			message.setFrom(new InternetAddress(username));
	 			message.addRecipient(Message.RecipientType.TO, new InternetAddress(Email_To));
	 			message.setSubject(Email_Subject);
	 			MimeMultipart part = new MimeMultipart();
	 			// message.setText(Email_Text);
	 			String sb = "<head>" + "<style type=\"text/css\">" + " .red { color: #f00; }"
	 					+ "</style>" + "</head>"
	 					+ "<img src=\"cid:image\">" + "<h1 class=\red\">" + message.getSubject()
	 					+ "</h1>"  +  "<p>" + "Hello, "
	 					+ fullName.getText()
	 					+ " we are sending you this email to confirm that you have succussfully</h1> "
	 					+ " signed"+" up for RaiderPlanner!!" + "\n" + "Happy Studying," + "\n"
	 					+ "The RaiderPlanner Team" + "\n"
	 					+ "Here are your credentials, please do not lose these, your eyes only!"
	 					+ "\n" + "<ul><strong><li>Email: " + email.getText() + "\n</li>"
	 					+ "<li>Wright State Username: "
	 					+ accountNo.getText() + "\n</li>" +  "<li>Password:" + passwordId.getText()
	 					+ "\n</li>" + "<li>Major: " + majorId.getText()
	 					+ "</li></strong></ul>.</p>" + "<footer>"
	 					+ "RaiderPlanner@CopyRight 2020" + "</footer>";
	 			BodyPart messageBodyPart = new MimeBodyPart();
	 			messageBodyPart.setContent(sb, "text/html; charset=utf-8");
	 			part.addBodyPart(messageBodyPart);
	 			messageBodyPart = new MimeBodyPart();
	 			DataSource fds = new FileDataSource("/Users/Twili/git/RaiderPlanner/src/edu/wright/cs/"
	 					+ "raiderplanner/content/raiderlogo.png");
	 			messageBodyPart.setDataHandler(new DataHandler(fds));
	 			messageBodyPart.setHeader("Content-ID", "<image>");
	 			part.addBodyPart(messageBodyPart);
	 			message.setContent(part);
	 			Transport.send(message);
	 			System.out.println("message sent");
	 		}		catch (MessagingException ex) {
	 						ex.printStackTrace();
	 				}
		}



	/**
	 * Handles the actions taken when the user tries to submit a new account. The appropriate
	 * warnings and errors are displayed if the user enters incorrect information. If a user enters
	 * an invalid input, they will be taken back to the page, to change fields. UPDATE: If the a
	 * user enters valid input for all fields and an account is successfully created a confirmation
	 * email is sent to the email provided by the user.
	 * @return nothing
	 */
	public void handleSubmit() {
		String invalidMessage = "";
		boolean validSuccess = true;
		boolean validName = true;
		if (!validateNumber()) {
			invalidMessage += "Please enter a valid Campus Username\n";
			validSuccess = false;
		}
		if (!validateEmail()) {
			invalidMessage += "Please enter a valid Email\n";
			validSuccess = false;
		}
		if (!validateSalutation()) {
			invalidMessage += "Please enter a valid Salutation\n";
			validSuccess = false;
		}
		if (!validateMajor()) {
			invalidMessage += "Please enter a valid Major\n";
			validSuccess = false;
		}
		if (!validatePassword()) {
			if (this.passwordId.getText().trim().isEmpty()) {
				invalidMessage += "Password field is empty\n";
				validSuccess = false;
			} else if (!this.passwordId.getText().contains("1")
					&& !this.passwordId.getText().contains("2")
					&& !this.passwordId.getText().contains("3")
					&& !this.passwordId.getText().contains("4")
					&& !this.passwordId.getText().contains("5")
					&& !this.passwordId.getText().contains("6")
					&& !this.passwordId.getText().contains("7")
					&& !this.passwordId.getText().contains("8")
					&& !this.passwordId.getText().contains("9")
					&& !this.passwordId.getText().contains("0")) {
				invalidMessage += "Password must have a number\n";
				validSuccess = false;
			} else if (!this.passwordId.getText().contains("A")
					&& !this.passwordId.getText().contains("B")
					&& !this.passwordId.getText().contains("C")
					&& !this.passwordId.getText().contains("D")
					&& !this.passwordId.getText().contains("E")
					&& !this.passwordId.getText().contains("F")
					&& !this.passwordId.getText().contains("G")
					&& !this.passwordId.getText().contains("H")
					&& !this.passwordId.getText().contains("I")
					&& !this.passwordId.getText().contains("J")
					&& !this.passwordId.getText().contains("K")
					&& !this.passwordId.getText().contains("L")
					&& !this.passwordId.getText().contains("M")
					&& !this.passwordId.getText().contains("N")
					&& !this.passwordId.getText().contains("O")
					&& !this.passwordId.getText().contains("P")
					&& !this.passwordId.getText().contains("Q")
					&& !this.passwordId.getText().contains("R")
					&& !this.passwordId.getText().contains("S")
					&& !this.passwordId.getText().contains("T")
					&& !this.passwordId.getText().contains("U")
					&& !this.passwordId.getText().contains("V")
					&& !this.passwordId.getText().contains("V")
					&& !this.passwordId.getText().contains("W")
					&& !this.passwordId.getText().contains("X")
					&& !this.passwordId.getText().contains("Y")
					&& !this.passwordId.getText().contains("Z")) {
				invalidMessage += "Password must contain a capital letter";
				validSuccess = false;
			} else if (this.passwordId.getText().length() < 6
					|| this.passwordId.getText().length() > 16) {
				invalidMessage += "Password must be between 6 and 16 characters\n";
				validSuccess = false;
			}

		}
		if (this.fullName.getText().trim().isEmpty()) {
			if (!this.handleEmptyName()) {
				validName = false;
			}
		}
		if (validSuccess && validName) {
			Person pers = new Person(this.salutation.getSelectionModel().getSelectedItem().trim(),
					this.fullName.getText().trim(), this.famLast.isSelected(),
					this.majorId.getText().trim());
			this.account = new Account(pers, this.accountNo.getText().trim());
			this.success = true;
			sendEmailhtml();
		}
	}

	/**
	 * Handle Quit button.
	 */
	public void handleQuit() {
		Stage stage = (Stage) this.submit.getScene().getWindow();
		stage.close();
	}

	/**
	 * Displays dialog and handles appropriate user choices if the full name field is empty.
	 *
	 * @return True if the user selects Okay
	 */
	public boolean handleEmptyName() {
		emptyNameAlert.setContentText("Are you sure you don't want to use your name?");
		Optional<ButtonType> result = emptyNameAlert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> this.pane.requestFocus());
		Tooltip tooltip = new Tooltip("Checked to indicate that family "
				+ "name comes last; Not checked to indicate it comes first");
		famLast.setTooltip(tooltip);
		submit.defaultButtonProperty().bind(submit.focusedProperty());
		submit.setOnAction(e -> {
			if (submit.isFocused()) {
				handleSubmit();
			}
		});
	}
}