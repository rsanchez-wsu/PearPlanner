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
import java.util.Scanner;

/**
 * This class creates the chat server.
 * @author lukeg
 */
public class ServerMain {
	private static final String serverName = "SERVER";
	private static Scanner in = new Scanner(System.in);
	private static ServerSocket ss = null;
	private static volatile String toSend = "";

	private static ServerHandler handler;


	/**
	 * This starts the socket server listening for connections.
	 */
	public static void main(String[] args) {
		handler = new ServerHandler();
		handler.start();

		int port = 8080;

		// Create the socket server
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOError occured. Aborted.");
			System.exit(1);
		}

//		while(true) {
//			try {
//				//Connect
//				Socket conn = ss.accept();
//				System.out.println("Connected With:");
//
//				//Create writer
//				OutputStream out = conn.getOutputStream();
//				PrintWriter pWriter = new PrintWriter(out, true);
//
//				//Create reader
//				InputStream is= conn.getInputStream();
//				BufferedReader bufferedReader = new BufferedReader(
//						new InputStreamReader(is));
//
//				//Spin up client
//				Client client = new Client(pWriter, bufferedReader);
//				handler.addClient(client);
//			}catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}


		new Thread(() -> {
			while (true) {
				try {
					//Connect
					Socket conn = ss.accept();
					System.out.println("Connected With:");

					//Create writer
					OutputStream out = conn.getOutputStream();
					PrintWriter prtWriter = new PrintWriter(out, true);

					//Create reader
					InputStream is = conn.getInputStream();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(is));

					//Spin up client
					Client client = new Client(prtWriter, bufferedReader);

					synchronized (handler) {
						handler.addClient(client);
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}).start();

		// Get server input on this thread
//		while (true) {
//			toSend = in.nextLine();
//		}


	}

	/**
	 * Spawn a new client reader thread.
	 */
	private static void spawnClientReaderThread(BufferedReader recieve) {
		// Spawn new background thread to handle receipt
		new Thread(() -> {
			String incoming;
			while (true) {
				try {
					if ((incoming = recieve.readLine()) != null) {
						System.out.println(incoming);
						toSend = incoming;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * Spawn a new client writer thread.
	 */
	private static void spawnClientWriterThread(PrintWriter pw) {
		new Thread(() -> {
			String lastSent = "";
			while (true) {
				// Check if we've already send the current message
				if (lastSent != toSend) {
					// Iterate across all outputs to send out messages
					pw.println(serverName + "," + toSend);
					pw.flush();
					lastSent = toSend;
				}
			}
		}).start();
	}

}
