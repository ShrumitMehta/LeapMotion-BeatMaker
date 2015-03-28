package main;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;

import sun.audio.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {

	public MusicPlayer(){
		
	}
	
	public void play(String fileName){
		try {
			String filePath = "./resources/" + fileName;
			InputStream in = getClass().getClassLoader().getResourceAsStream(filePath);
			AudioPlayer.player.start(in);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
