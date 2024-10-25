package server;

import java.io.*;
import java.net.*;

public class QuizServer{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		// Question Array
		String[] questions = {"What is the capital of Spain?", 
				"Does Mexicans speak fluent Spanish?", 
				"Did Tolstoy wrote Pride and Prejudice?"};
		
		// Answers Array
		String[] answers = {"madrid", "yes", "no"};
		
		double point = 0.0;
		
		int DEFAULT_PORT = 500; // determine a default port to start communication
		
		try (ServerSocket server1 = new ServerSocket(DEFAULT_PORT)) { // default port server to start communication
			System.out.printf("Listening default port %d to start communication.\n", DEFAULT_PORT); //print the port number to the console
			
			Socket client1 = server1.accept(); // accept client connection
			
			//Reading the port number given by the client
			BufferedReader input = new BufferedReader(new InputStreamReader(client1.getInputStream()));
			int port = Integer.parseInt(input.readLine()); // turn the string port number to integer to be able to use it to create new server
			
			
			//close the client server and input
			client1.close();
			input.close();
			server1.close();
			
			// server with the port initialized by the user
			try (ServerSocket server2 = new ServerSocket(port)) {
				System.out.printf("Listening port %d \n", port);
				Socket client2 = server2.accept();

				// Sending question to the client
				PrintWriter question = new PrintWriter(client2.getOutputStream(), true); 
				BufferedReader answer = new BufferedReader(new InputStreamReader(client2.getInputStream())); // get answer from the user
				question.println(questions[0]);
				
				// Reading the answer from the client
				
				String a1 = answer.readLine();
				// checking if the answer is q
				if (a1.equalsIgnoreCase("q")) {
					System.out.println("Client disconnected from the server.");
					// Send the current score to the client (0 in this case)
					question.println(point);
				}
				else {
					System.out.printf("Answer: %s\n", a1); //print the user's answer to the console
					if (a1.equalsIgnoreCase(answers[0])) {
						point += 33.33;
					}
					
					// Sending Another Question to the user
					question.println(questions[1]);
					
					// Reading data from the client
					String a2 = answer.readLine();
					
					if (a2.equalsIgnoreCase("q")) {
						System.out.println("Client disconnected from the server.");
						// Send the current score to the client 
						question.println(point);
					}
					else {
						System.out.printf("Answer: %s\n", a2); //print the user's answer to the console
						if (a2.equalsIgnoreCase(answers[1])) {
							point += 33.33;
						}
						
						question.println(questions[2]);
						String a3 = answer.readLine();
						
						if (a3.equalsIgnoreCase("q")) {
							System.out.println("Client disconnected from the server.");
							// Send the current score to the client 
							question.println(point);
						}
						else {
							System.out.printf("Answer: %s\n", a3); //print the user's answer to the console
							if (a3.equalsIgnoreCase(answers[2])) {
								point += 33.33;
							}
							question.println(point);
						}
					}
						
				}
				answer.close();
				question.close();
				client2.close();
				server2.close();
				
				
				
			}
			
			
			
		}
		
		
		
	}
	
}
