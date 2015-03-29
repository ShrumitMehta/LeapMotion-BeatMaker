package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;

import javax.sound.sampled.SourceDataLine;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionListener;

import com.leapmotion.leap.*;

/*
 A controller which handles built-in 
 hand gestures from the LeapMotion.
 */

public class GestureController extends Listener {

	private JFrame jframe = new JFrame();
	private boolean isLooping;
	private boolean paused;
	private JButton loop;
	private JButton pause;
	private SoundPlayer player2;
	private int selected;
	private String[] files;
	private JScrollPane fileList;
	private JList<String> list;

	public GestureController() {
		this.isLooping = false;
		this.paused = false;
		this.selected = 0;
		String[] array = new String[4];
		array[0] = "beatwav.wav";
		array[1] = "guitar.wav";
		array[2] = "chord.wav";
		array[3] = "wobble.wav";
		this.files = array;
		this.list = new JList<String>(array);
		setUpGui();
	}

	private void setUpGui() {
		JPanel titlePanel = new JPanel();
		titlePanel.setSize(jframe.getWidth(), 30);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		titlePanel.setBackground(new Color(255, 255, 255));

		JLabel label = new JLabel("LEAP");
		label.setFont(new Font("lrg", Font.PLAIN, 30));
		label.setForeground(new Color(0, 0, 0));
		titlePanel.add(label);
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		JLabel labelm = new JLabel("MOTION");
		labelm.setFont(new Font("sml", Font.PLAIN, 12));
		labelm.setForeground(new Color(93, 170, 0));
		titlePanel.add(labelm);
		labelm.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.setBackground(new Color(244, 244, 244));

		JLabel labelb = new JLabel("BeatMaker");
		labelb.setFont(new Font("sml", Font.PLAIN, 50));
		labelb.setForeground(new Color(238, 24, 24));
		pane.add(labelb);
		labelb.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		loop = new JButton("Loop Sounds");
		pane.add(loop);
		loop.setAlignmentX(JButton.CENTER_ALIGNMENT);

		loop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (isLooping) {
					loop.setText("Loop sounds");
				} else {
					loop.setText("Play Once");
				}
				isLooping = !isLooping;
			}
		});
		
		pause = new JButton("Pause Loops");
		pane.add(pause);
		pause.setAlignmentX(JButton.CENTER_ALIGNMENT);

		pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (paused) {
					loop.setText("Play Loops");
					player2.setPause(true);
				} else {
					loop.setText("Pause Loops");
					player2.setPause(false);
				}
				paused = !paused;
			}
		});
		
		fileList = new JScrollPane(list);
		pane.add(fileList);
		fileList.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);
		list.setSelectedIndex(selected);
		
		jframe.setLayout(new BorderLayout());
		jframe.add(titlePanel, BorderLayout.NORTH);
		jframe.add(pane, BorderLayout.CENTER);
		jframe.setBackground(new Color(17, 17, 17));
		jframe.setVisible(true);
		jframe.setTitle("LeapMotion BEATMAKER - Version 0.1");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		jframe.setBounds((int) (width * 0.1), (int) (height * 0.1),
				(int) (width * 0.8), (int) (height * 0.8));
		jframe.pack();
		jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

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
	}

	/* Map action to a sound */
	private void determineGestureFromFrame(Frame frame) {
		for (Gesture gesture : frame.gestures()) {
			switch (gesture.type()) {
			case TYPE_SCREEN_TAP:
				System.out.println("Screen Tap");
				selected = selected + 1;
				if (selected >= files.length){
					selected = 0;
				}
				list.setSelectedIndex(selected);
				break;
			case TYPE_KEY_TAP:
				System.out.println("Tap");
				player2 = new SoundPlayer(files[selected]);
				player2.setLooping(isLooping);
				player2.start();
				break;
			default:
				System.out.println(gesture.type());
				break;
			}
		}
	}

}
