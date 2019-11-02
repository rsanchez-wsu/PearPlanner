/*
 * Copyright (C) 2018 - Michael Pantoja
 *
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

import edu.wright.cs.raiderplanner.controller.MenuController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * This is a class to handle the code for the chat feature.
 * @author MichaelPantoja
 */
public class ChatController {
	//chat variables
	private static final GridPane userMessagePane = new GridPane();
	private static final HBox spacingBox = new HBox();
	private static TextField tfMessageToSend = new TextField();
	private static TextArea msgArea = new TextArea();
	private static final Button sendButton = new Button("Send");
	private static Socket sock;
	private static int port;
	private static OutputStream output;
	private static PrintWriter printOutput;
	private static InputStream incoming;
	private static Scanner incomingMessage;
	private static String hostName;
	private static String userName;

	/**
	 * Default Constructor.
	 */
	public ChatController() {
	}

	/**
	 * This will prevent the message area from being edited and set the size for all the buttons
	 * This method will also create padding between the text and message areas and the send button.
	 */
	public static void createUserMessagePane() {
		msgArea.setEditable(false);
		tfMessageToSend.setPrefWidth(800);
		userMessagePane.setPadding(new Insets(10, 10, 10, 10));
		sendButton.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, null, null)));
		spacingBox.setPadding(new Insets(0, 5, 0, 5));
		userMessagePane.add(tfMessageToSend, 0, 0);
		userMessagePane.add(spacingBox, 1, 0);
		userMessagePane.add(sendButton, 2, 0);
		sendButton.setMinWidth(100);
		sendButton.setDefaultButton(true);
		setupServerConnection();
	}

	/**
	 * This method opens up a connection to the chat server from this client.
	 */
	private static void setupServerConnection() {
		// Establish connection
		port = 8080;
		hostName = MenuController.getHostName();
		userName = MenuController.getUserName();
		try {
			// Handle input
			sock = new Socket("localhost", port);
			output = sock.getOutputStream();
			printOutput = new PrintWriter(output, true);

			// Handle incoming messages
			incoming = sock.getInputStream();
			incomingMessage = new Scanner(incoming);

			// Send hostname across to serverMain
			// This is done before client created, so it won't go to screen
			printOutput.println(hostName + "," + userName);
			printOutput.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create background thread to grab incoming messages
		new Thread(() -> {
			while (true) {
				if (incomingMessage.hasNext()) {
					DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
					LocalDateTime time = LocalDateTime.now();
					String[] tokens = incomingMessage.nextLine().split(",");
					String user;
					String message;
					if (tokens.length == 2) {
						user = tokens[0];
						message = tokens[1];
					} else {
						user = "";
						message = tokens[0];
					}

					msgArea.appendText(user + ": " + message);
					msgArea.appendText("\t\t\t" + date.format(time) + "\n");
				}
			}
		}).start();
	}

	/**
	 * This will load the msg_area which is where the user will see messages from other users and
	 * him or herself. This will also load the text field where the user will be able to send his or
	 * her own message to peers.
	 */
	public static void createMainPane() {
		MenuController.getMainPane().setCenter(msgArea);
		MenuController.getMainPane().setBottom(userMessagePane);
	}

	/**
	 *  This will take in the action of when the send button is pressed. If a user sends a message,
	 *  the line of text will append to the chat log so the user can see what they sent. It follows
	 *  the format of USER: sentence time/date.
	 *  The text box with the user input will be set back to blank after a message is sent.
	 *  Fixed by: Austin Naas
	 */
	public static void sendButtonAction(String userName) {
		sendButton.setOnAction((ActionEvent exception1) -> {
			DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime time = LocalDateTime.now();
			if ((!tfMessageToSend.getText().equals(""))) {
				msgArea.appendText(userName + ": " + tfMessageToSend.getText());
				msgArea.appendText("\t" + date.format(time) + "\n");
				tfMessageToSend.setText("");
			}
		});
	}
}