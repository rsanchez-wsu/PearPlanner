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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * This class creates the chat server.
 * @author lukeg
 */
public class ServerMain {
	private static ServerSocket ss = null;
	private static HashMap<String, ServerHandler> handlerMap;

	/**
	 * This starts the socket server listening for connections.
	 */
	public static void main(String[] args) {
		handlerMap = new HashMap<>();

		int port = 8080;

		// Create the socket server
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOError occurred. Aborted.");
			System.exit(1);
		}

		new Thread(() -> {
			while (true) {
				try {
					//Connect
					Socket conn = ss.accept();

					//Create reader
					InputStream is = conn.getInputStream();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(is));

					// Block, waiting for client to send over hostname
					String in = bufferedReader.readLine();
					String[] names = in.split(",");
					String hostname = names[0];
					String username = names[1];

					System.out.println("Connected With: " + username);
					System.out.println("Host: " + hostname);

					// Determine if we need to create new handler for this room
					if (!handlerMap.containsKey(hostname)) {
						handlerMap.put(hostname, new ServerHandler());
						handlerMap.get(hostname).start();
					}

					//Create writer
					OutputStream out = conn.getOutputStream();
					PrintWriter prtWriter = new PrintWriter(out, true);

					//Spin up client
					Client client = new Client(username, prtWriter, bufferedReader);

					synchronized (handlerMap) {
						handlerMap.get(hostname).addClient(client);
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}).start();
	}
}
