package main;

import com.leapmotion.leap.*;

public class SampleListener extends Listener {
	
	private Controller controller;
	private boolean loop = false;

	public void onConnect(Controller controller) {
		this.controller = controller;
		System.out.println("Connected");
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
	}

	public void onFrame(Controller controller) {
		Frame frame = controller.frame();
		for(Gesture gesture : frame.gestures()){
		    switch (gesture.type()) {
		    case TYPE_SCREEN_TAP:
		    	System.out.println("Exiting");
		    	System.exit(0);
		    	break;
		    case TYPE_KEY_TAP:
		    	new MakeSound("beatwav.wav").start();
		    	break;
		    case TYPE_CIRCLE:
		    	new MakeSound("car_horn_x.wav").start();
		    	break;
		    default:
		    	System.out.println(gesture.type());
		    }
		}
	}
	
}
