package main;
import java.io.IOException;
import com.leapmotion.leap.*;


/*
	An application which listens to handles hand gestures. 
*/

public class Application {
	public static void main(String[] args){

		Controller controller 			  = new Controller();
		GestureController gestureListener = new GestureController();
		HandController handListener 	  = new HandController();
	
		/* Initiate action listeners */
		controller.addListener(gestureListener);
		controller.addListener(handListener);

		System.out.println("Press Enter to quit...");

		/* Read in hand commands*/
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* Terminate action listeners */
		controller.removeListener(gestureListener);
		controller.removeListener(handListener);
		
	}
}
