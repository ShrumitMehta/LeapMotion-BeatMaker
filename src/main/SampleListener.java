package main;

import com.leapmotion.leap.*;

public class SampleListener extends Listener {

	public void onConnect(Controller controller) {
		System.out.println("Connected");
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
	}

	public void onFrame(Controller controller) {
		Frame frame = controller.frame();
		
		HandList hands = frame.hands();
		Hand firstHand = hands.get(0);
		float x = firstHand.palmPosition().getX();
		
		MusicPlayer p = new MusicPlayer();
		
		for(Gesture gesture : frame.gestures()){
		    switch (gesture.type()) {
		    case TYPE_CIRCLE:
		    	System.exit(0);
		    	break;
		    case TYPE_KEY_TAP:
		    	p.play("hihat.wav");
		    	break;
		    default:
		    	System.out.println(gesture.type());
		    }
		}
	}
	
	

}
