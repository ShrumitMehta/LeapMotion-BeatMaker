package main;

import com.leapmotion.leap.*;

public class SampleListener extends Listener {

	/* Configure the Leap Motion. */
	public void onConnect(Controller controller) {
		System.out.println("Connected");
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
	}

	/* Detect gestures in frames. */
	public void onFrame(Controller controller) {

		Frame frame = controller.frame();
		MakeSound p = new MakeSound();

		for(Gesture gesture : frame.gestures()){
		    switch (gesture.type()) {
			    case TYPE_SWIPE:
			    	System.out.println("Exiting");
			    	System.exit(0);
			    	break;
			    case TYPE_KEY_TAP:
			    	System.out.println("Drum hit!");
			    	p.playSound("disconnect_x.wav");
			    	break;
			    default:
			    	System.out.println(gesture.type());
		    }
		}
	}
}
