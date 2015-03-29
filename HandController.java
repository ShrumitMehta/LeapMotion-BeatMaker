package main;
import com.leapmotion.leap.*;

/*
	A controller which handles custom defined 
	hand gestures using the LeapMotion logic.
*/

public class HandController extends Listener {

	private Controller controller;

	/* Configure the accepted gesture types. */
	@Override
	public void onConnect(Controller controller) {
		this.controller = controller;
		System.out.println("Application connected");
	}

	/* Act on a captured frame */
	@Override
	public void onFrame(Controller controller) {
		Frame frame = controller.frame();
		determineActionFromFrame(frame);
	}

	/* Map action to a sound */
	private void determineActionFromFrame(Frame frame) {
		for (Hand h : frame.hands()) {
			if (h.isValid()) {
				/* Left hand fist */
				if (h.isLeft() && h.grabStrength() == 0.0) {
					System.out.println("Left hand fist");		
				}
				
				/* Left hand not fist */
				else if (h.isLeft() && h.grabStrength() == 1.0) {
					System.out.println("Left hand not fist");		
				}
				
				/* Right hand fist */
				else if (h.isRight() && h.grabStrength() == 0.0) {
					System.out.println("Right hand fist");						
				}
				
				/* Right hand not fist */
				else if (h.isRight() && h.grabStrength() == 1.0) {
					System.out.println("Right hand not fist");				
				}				
			}
		}
	}
}
