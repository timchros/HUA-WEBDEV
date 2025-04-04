package test;

import java.util.HashMap;
import java.util.Map;

public class MainProgram {
	
	// Data 
	private static final Map<Integer, UserData> map = new HashMap<>();	
	static {
		map.put(1, new UserData("John", "male", 25));
		map.put(2, new UserData("Maria", "female", 23));
		map.put(3, new UserData("Eleni", "female", 30));
	}
	
	public static void main(String[] args) {		
		// Input - Read from Command Line Arguments
		if (args.length != 1) {
			System.out.println("Wrong number of arguments !");
			return; 
		}
		System.out.println("Input: " + args[0]);
		final int userID = Integer.parseInt(args[0]);
		
		// Process - Find relevant data
		final UserData ud = map.get(userID);
		
		// Output - Show user data to standard output
		System.out.println("Output: " + ud);

	}

}


