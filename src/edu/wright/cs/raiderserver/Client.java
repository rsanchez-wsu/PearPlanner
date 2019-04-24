/*
 * Copyright (C) 2019
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

package edu.wright.cs.raiderserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class defines the implementation of a chat client thread.
 *
 * @author lukeg
 *
 */
public class Client extends Thread {

	private PrintWriter writer;
	private BufferedReader reader;
	private static volatile String toSend = "";

	private volatile Queue<String> outMessages;
	private volatile Queue<String> inMessages;

	/**
	 * Construct a client instance.
	 */
	public Client(PrintWriter writer, BufferedReader reader) {
		this.writer = writer;
		this.reader = reader;

		outMessages = new LinkedBlockingQueue<>();
		inMessages = new LinkedBlockingQueue<>();
	}

	/**
	 * Main client thread code. This is what executes when a new thread starts.
	 */
	public void run() {
		while (true) {
			try {

				while (reader.ready()) {
					inMessages.add(reader.readLine());
				}

				while (!outMessages.isEmpty()) {
					String msgString = outMessages.poll();
					writer.println(msgString);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
/*
		// Spawn new background thread to handle receipt
		new Thread(() -> {
			String incoming;
			while (true) {
				try {
					if ((incoming = reader.readLine()) != null) {
						System.out.println(incoming);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

		// Spawn new writer thread to handle output
		new Thread(() -> {
			String lastSent = "";
			while (true) {
				// Check if we've already send the current message
				if (lastSent != toSend) {
					// Iterate across all outputs to send out messages
					writer.println("SERVER" + "," + toSend);
					writer.flush();
					lastSent = toSend;
				}
			}
		}).start();
*/
	}

	/**
	 * Get message from the incoming client message queue.
	 */
	protected String getMessage() {
		return inMessages.poll();
	}

	/**
	 * Add outgoing message to client message queue.
	 */
	protected void addMessage(String message) {
		outMessages.add(message);
	}
}
