package main;
import java.io.IOException;
import com.leapmotion.leap.*;

public class Sample {
	public static void main(String[] args){

		SampleListener listener = new SampleListener();
		Controller controller 	= new Controller();

		controller.addListener(listener);

		System.out.println("Press Enter to quit...");

		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		controller.removeListener(listener);
	}
}
