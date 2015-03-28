package main;

import com.leapmotion.leap.*;

public class GestureController extends Listener {

	private Controller controller;

	/* Configure the accepted gesture types. */
	public void onConnect(Controller controller) {
		this.controller = controller;
		System.out.println("Application connected");
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
	}

	/* Act on a captured frame */
	public void onFrame(Controller controller) {
		Frame frame = controller.frame();
		determineGestureFromFrame(frame);
	}

	/* Map action to a sound */
	private void determineGestureFromFrame(Frame frame) {
		for (Gesture gesture : frame.gestures()) {
			switch (gesture.type()) {
				case TYPE_SWIPE:
					System.out.println("34_Get_Treasure_Box.wav");
					new SoundPlayer("34_Get_Treasure_Box.wav").start();
					break;
				case TYPE_KEY_TAP:
					System.out.println("Beat");
					new SoundPlayer("beatwav.wav").start();
					break;
				default:
					System.out.println(gesture.type());
					break;
			}
		}
	}
}
