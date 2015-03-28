package main;

import com.leapmotion.leap.*;

public class GestureController extends Listener {

	private Controller controller;

	/* Configure the accepted gesture types. */
	protected void onConnect(Controller controller) {
		this.controller = controller;
		System.out.println("Application connected");
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
	}

	/* Act on a captured frame */
	protected void onFrame(Controller controller) {
		Frame frame = controller.frame();
		determineGestureFromFrame(frame);
	}

	/* Map action to a sound */
	private void determineGestureFromFrame() {
		for (Gesture gesture : frame.gestures()) {
			switch (gesture.type()) {
				case TYPE_SCREEN_TAP:
					System.out.println("Application exiting");
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
