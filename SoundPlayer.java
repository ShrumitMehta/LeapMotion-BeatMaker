package main;

import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SoundPlayer extends Thread {

	private final int BUFFER_SIZE = 128000;
	private File soundFile;
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	private SourceDataLine sourceLine;
	private String strFileName;
	private boolean looping;
	private boolean pause;

	public SoundPlayer(String name) {
		this.strFileName = name;
	}

	public String getStrFileName() {
		return strFileName;
	}

	public SourceDataLine getDataLine() {
		return sourceLine;
	}

	public void setLooping(boolean b) {
		looping = b;
	}
	
	public void setPause(boolean b){
		pause = b;
	}

	public void run() {

		if (looping) {
			// for (int i = 0; i < 10; i++){
			while (looping) {
				if (sourceLine != null && sourceLine.isActive()) {

				} else {
					play();
				}
			}
		} else {
			play();
		}
	}

	/**
	 * @param filename
	 *            the name of the file that is going to be played
	 */
	public void play() {

		try {
			soundFile = new File(getStrFileName());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		try {
			audioStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		audioFormat = audioStream.getFormat();

		DataLine.Info info = new DataLine.Info(SourceDataLine.class,
				audioFormat);
		try {
			sourceLine = (SourceDataLine) AudioSystem.getLine(info);
			sourceLine.open(audioFormat);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		int nBytesRead = 0;
		byte[] abData = new byte[BUFFER_SIZE];
		while (nBytesRead != -1) {
			try {
				nBytesRead = audioStream.read(abData, 0, abData.length);
				sourceLine.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (nBytesRead >= 0) {
				@SuppressWarnings("unused")
				int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
			}
		}

		sourceLine.drain();
		sourceLine.close();
	}
}
