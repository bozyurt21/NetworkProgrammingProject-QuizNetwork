package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class QuizClient {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		String host = "localhost";
		int DEFAULT_PORT = 500; // initialize default port number to start communication
		
		try(Socket client1 = new Socket(host, DEFAULT_PORT)) { // connect to the server by default port
			System.out.printf("Connected to the server on default port %d\n", DEFAULT_PORT);
			
			Scanner in = new Scanner(System.in);
			System.out.println("Please enter a port number: ");
			int port = in.nextInt();
			in.nextLine(); // properly handling the string input

			// send the initialize port to the server
			PrintWriter output = new PrintWriter(client1.getOutputStream(), true); 
			output.println(port);
			
			client1.close();
			output.close();
			
			Thread.sleep(1000); // 1 second delay so that it will not try to connect to the port before server accept the client!
			// Create new client socket with provided port
			try (Socket client2 = new Socket(host, port)) {
				BufferedReader question = new BufferedReader(new InputStreamReader(client2.getInputStream()));
				PrintWriter answer = new PrintWriter(client2.getOutputStream(), true); 
				
				String q1 = question.readLine();
				System.out.printf("%s (q to quit)\n", q1);
				
				//sending answer to the server
				
				System.out.println("Your Answer: ");
				String a1 = in.nextLine();
				answer.println(a1);
				
				
				// checking if the answer is q
				if (a1.equalsIgnoreCase("q")) {
					// read score from the server
					String s1 = question.readLine();
					System.out.printf("Your score is: %s\n", s1);
					System.out.println("Connection with the server is closed.");

				}
				else {
					// Read the data from the server
					String q2 = question.readLine();
					System.out.printf("%s (q for quit)\n", q2); 
					
					// Send the user's answer to the server
					System.out.println("Your Answer: ");
					String a2 = in.nextLine();
					answer.println(a2);
					if (a2.equalsIgnoreCase("q")) {
						// read score from the server
						String s2 = question.readLine();
						System.out.printf("Your score is: %s\n", s2);
						System.out.println("Connection with the server is closed.");
					}
					else {
						// Read data from the client
						String q3 = question.readLine();
						System.out.printf("%s (q for quit)\n", q3); 
						// Send the user's answer to the server
						System.out.println("Your Answer: ");
						String a3 = in.nextLine();
						answer.println(a3);
						
						if (a3.equalsIgnoreCase("q")) {
							String s3 = question.readLine();
							System.out.printf("Your score is: %s\n", s3);
							System.out.println("Connection with the server is closed.");
						}
						else {
							String s3 = question.readLine();
							System.out.println("The Correct answers are Madrid. Yes. No.");
							System.out.printf("Your score is: %s\n", s3);
						}
						
					}
				}
				in.close();
				answer.close();
				question.close();
				client2.close();
			}
			
		}
		


	}

}
