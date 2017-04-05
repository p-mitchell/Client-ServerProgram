import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class SocketServer 
{
	int portNumber = 9876;					// matching port number to the client
	ServerSocket serverSocket = null;
	
	/**
	 * run server method
	 * create an arraylist to store all the golfers
	 * create a server socket to accept the connection
	 * instantiate the 'MenuRunnable' class
	 * make the program multithreaded
	 */
	public void runServer()
	
	{
		ArrayList<Player> golfers = new ArrayList<Player>();
		
		try
		{
			this.serverSocket = new ServerSocket(portNumber);
			
			while(true)
			{
				try
				{
				Socket clientSocket = serverSocket.accept();
				MenuRunnable m = new MenuRunnable(clientSocket, golfers);
				
				new Thread(m).start();
				} // end try 2
				catch (Exception e)
				{		// information for where exactly the error has occurred
					System.out.println("Server Socket ERROR 2: " + e);
				} // end catch 2
			} // end while
		} // end try 1
		catch (Exception e)
		{
			System.out.println("Server Socket ERROR 1: " + e);
		} // end catch 1
	} // end runServer
} // end serverSocket
