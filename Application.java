package main;
import java.io.IOException;

import com.leapmotion.leap.*;

public class Application {
	public static void main(String[] args){

		Controller controller 			  = new Controller();
		GestureController gestureListener = new GestureController();
	
		/* Initiate action listeners */
		controller.addListener(gestureListener);
	
		System.out.println("Press Enter to quit...");

		/* Read in hand commands*/
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* Terminate listeners */
		controller.removeListener(gestureListener);	
	}
}
