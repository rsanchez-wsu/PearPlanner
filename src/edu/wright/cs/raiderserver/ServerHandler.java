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
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * This class defines the backend server implementation for the chat system.
 *
 * @author lukeg
 *
 */
public class ServerHandler extends Thread {
	private static final String serverName = "SERVER";

	private volatile List<Client> clients;

	private Queue<String> messages;

	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Construct a ServerHandler instance.
	 */
	public ServerHandler() {
		clients = Collections.synchronizedList(new ArrayList<Client>());
		messages = new LinkedList<>();
	}

	/**
	 * Server thread implementation.
	 */
	public void run() {
		Iterator<Client> iterator;
		while (true) {
			try {
				if (in.ready()) {
					messages.add(serverName + "," + in.readLine());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			synchronized (this) {
				iterator = clients.iterator();
				while (iterator.hasNext()) {
					Client client = iterator.next();

					String message = client.getMessage();
					if (message != null) {
						messages.add(message);
					}
				}

				while (!messages.isEmpty()) {
					String message = messages.poll();
					System.out.println(message);

					iterator = clients.iterator();
					while (iterator.hasNext()) {
						iterator.next().addMessage(message);
					}
				}
			}
		}
	}

	/**
	 * Add a client to the server client queue.
	 */
	protected void addClient(Client client) {
		clients.add(client);
		client.start();
	}
}
