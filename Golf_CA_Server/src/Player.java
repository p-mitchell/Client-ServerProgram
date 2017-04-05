import java.util.Random;

public class Player 		// create a player class for people competing in the golf tournament 
{
	public String name;		// each of the variables
	public String code;
	public int score;
	
	public Player(String name)	// only takes name as an input parameter
	{
		this.name = name;
		this.code = genCode();	// persons code is set using the synchronised code generator
		this.score = 0;
	}
	
	private synchronized String genCode()								// code generator method set as private
	{																	// private so people can't change the code
		int charLength = 8;												// code creates a random 8 character number as each players competition code
		return String.valueOf(charLength < 1 ? 0 : new Random()			// 
				.nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)	// 
				+ (int) Math.pow(10, charLength - 1));					// 
	}
}
