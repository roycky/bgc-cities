

import java.io.IOException;

public class Connected {

	public static void main(String[] args) {
		
		if(args.length < 3) {
			System.out.println("Usage: java Connected <filename> <cityname1> <cityname2>");
			System.exit(-1);
		}
		
		String file = args[0];
		String c1 = args[1];
		String c2 = args[2];
		
		CitiesConnection connection = null;
		
		try {
			connection = new CitiesConnection(file);
		} catch (IOException e) {
			System.out.println("Cannot read file: " + file);
			e.printStackTrace();
			System.exit(-1);
		}
		
		boolean isConnected = connection.isConnected(c1, c2);
		
		String message = "no";
		if(isConnected) {
			message = "yes";
		}
		
		System.out.println(message);
		
	}

}
