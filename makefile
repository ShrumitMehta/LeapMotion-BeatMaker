clean:
	rm *.class

soundTest: MakeSound.java
	javac MakeSound.java && java MakeSound
