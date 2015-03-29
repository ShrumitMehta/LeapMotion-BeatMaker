clean:
	rm *.class

soundTest: SoundPlayer.java
	javac SoundPlayer.java && java SoundPlayer
