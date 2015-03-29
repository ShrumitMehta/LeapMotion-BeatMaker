package main;

import com.leapmotion.leap.*;

/*
 * Handles gesture signals coming from the Leap Motion.
 * */

public class GestureController extends Listener {

	/* Configure the accepted gesture types. */
	@Override
	public void onConnect(Controller controller) {
		System.out.println("Application connected");
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
	}

	/* Act on a captured frame */
	@Override
	public void onFrame(Controller controller) {
		Frame frame = controller.frame();
		determineGestureFromFrame(frame);
		determineActionFromFrame(frame);
	}

	/* Map action to a sound */
	private void determineGestureFromFrame(Frame frame) {
		for (Gesture gesture : frame.gestures()) {
			switch (gesture.type()) {
				case TYPE_SWIPE:
					System.out.println("SWIPE");
					new SoundPlayer("triple.wav").start();
					break;
				case TYPE_CIRCLE:
					System.out.println("CIRCLE");
					new SoundPlayer("melodic.wav").start();					
					break;
				case TYPE_KEY_TAP:
					System.out.println("KEY TAP");
					new SoundPlayer("10sec.wav").start();
					break;
				case TYPE_SCREEN_TAP:
					System.out.println("SCREEN TAP");
					new SoundPlayer("scratch.wav").start();
					break;
				default:
					System.out.println(gesture.type());
					break;
			}
		}
	}

	/* Map action to a sound */
	private void determineActionFromFrame(Frame frame) {

		for (Hand h : frame.hands()) {

			/* Only run this if the last action has been delayed */
			if (h.isValid()) {
				/* Left hand fist */
				if (h.isLeft() && h.grabStrength() == 1.0) {
					System.out.println("Left hand fist" + ": "
							+ h.grabStrength());
				}

				/* Left hand not fist */
				else if (h.isLeft() && h.grabStrength() == 0.0) {
					System.out.println("Left hand not fist" + ": "
							+ h.grabStrength());
				}

				/* Right hand fist */
				else if (h.isRight() && h.grabStrength() == 1.0) {
					System.out.println("Right hand fist" + ": "
							+ h.grabStrength());
//					System.exit(0);
				}

				/* Right hand not fist */
				else if (h.isRight() && h.grabStrength() == 0.0) {
					System.out.println("Right hand not fist" + ": "
							+ h.grabStrength());
				}
			}
		}
	}
}
