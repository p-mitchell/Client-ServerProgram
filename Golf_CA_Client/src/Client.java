import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



public class Client 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		String host = "127.0.0.1";	// code for setting up the client/server
		int portNumber = 9876;		// port number common for connection
		
		Socket clientSocket;
		PrintWriter out;
		BufferedReader in;
		BufferedReader stdIn;
		InputStreamReader ir;
		
		try
		{		// set up connection
			clientSocket = new Socket(host, portNumber);
			
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			ir = new InputStreamReader(clientSocket.getInputStream());
			in = new BufferedReader(ir);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			
			out.println("initlogin"); // initialise connection with common word
			
			while(true)
			{		// display menu for the switch and the options available
				System.out.println("1. Register Player");
				System.out.println("2. Enter Score");
				System.out.println("3. Who is winning");
				System.out.print("Enter choice: ");
					// converts the user input to an integer
				int userInput = Integer.parseInt(stdIn.readLine());
				
				switch (userInput) 
				{
				case 1:		// register a player
					System.out.println("REGISTRATION");
					out.println("register");				// key words used for connection to server
					
					System.out.println("Enter your name: ");// take input from the user and send it over to the server
					String name = stdIn.readLine();
					out.println(name);
					
					System.out.println("Your competition code is: " + in.readLine());	// return the line from the client
					break;
					
				case 2:		// update a players score
					System.out.println("SCORE UPDATE");
					out.println("score");
					
					System.out.println("Enter your competition code: ");
					String inputCompCode = stdIn.readLine();
					out.println(inputCompCode);								// take in two inputs from the user
																			// send both over, server will receive them in order
					System.out.println("Enter your score: ");
					String inputScore = stdIn.readLine();
					out.println(inputScore);
					
					System.out.println(in.readLine());					// print the return line from the server
					
					break;
					
				case 3:		// find out who is currently winning
					System.out.println("WINNING SCORE");
					out.println("win");
					
					System.out.println(in.readLine() + " is winning!");				// print two returns from the server in order
					System.out.println("With a score of: " + in.readLine());
					break;

				default:
					break;
				} // end switch
			} // end while
			
			
		} // end try
		catch (IOException e)
		{	// exception catch, output which class the error has occurred in
			System.out.println("CLIENT ERROR: " + e.getMessage());
		} // end catch

	} // end main

} // end client