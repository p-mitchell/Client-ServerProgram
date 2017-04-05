import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;



public class MenuRunnable implements Runnable
{
	protected Socket clientSocket = null;
	ArrayList<Player> golfers;
	
	public MenuRunnable(Socket clientSocket, ArrayList<Player> golfers)	// constructor for the 'MenuRunnable' class
	{
		this.clientSocket = clientSocket;
		this.golfers = golfers;
	} // end constructor
	
	public void run()		// run method 
	{
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream(), true);
			
			if (in.readLine().equals("initlogin")) // initial connection word with the client
			{
				
				while(true)
				{
					String userInput = in.readLine();		// take user input from the client
					switch (userInput) 						// input used for the server switch
					{
					
					case "register":		// 'register' word matches the case 1 in the client switch
						String name;
						Player temp;
						name = in.readLine();		// store input from the client as 'name'
						temp = new Player(name);	// use as the parameter for a temporary person object
						golfers.add(temp);			// add them to the 'golfers' array
						out.println(temp.code);		// return the random generated competition code
						break;
					
						
					case "score":			// 'score' switch 
						String compCode;
						int score;
						compCode = in.readLine();					// read in the participants competition code
						score = Integer.parseInt(in.readLine());	// read in their score and save as an integer
						for (int i = 0; i < golfers.size(); i++ )	// loop looking through all the golfers
						{
							Player temp1 = golfers.get(i);			// create a temporary player 
							if (temp1.code.equals(compCode))		// if the entered code matches someones stored code
							{
								temp1.score = score;					// update the score with the entered score
								int position = golfers.indexOf(temp1);	
								golfers.set(position, temp1);			//update the details for the temporary person in the correct index
							} // end if
						} // end for
						out.println("Score Registered!");
						break;
					
						
						
					case "win":
						int bestScore = 1000;
						Player winner = null;									// set the winning score as a big score
						for (int i = 0; i < golfers.size(); i++ )		// go through each of the players in the golfers ArrayList
						{
							Player temp1 = golfers.get(i);			// create a temporary player object for each index
							if (temp1.score < bestScore)				// if their score is lower than the current lowest score
							{
								bestScore = temp1.score;				// store their score as the best score 
								winner = temp1;							// update temp1 as the winner
							}
						}
						out.println(winner.name);			// send out the name and score of the winning entry
						out.println(winner.score);
						break;
						
					} // end switch
				} // end while
				
				
				
				
			} // end if
		} // end try
		catch (Exception e) 
		{
			System.out.println("RegisterRunnable ERROR: " + e.getMessage());
		} // end catch
		
	} // end run
	
} // end RegisterRunnable
