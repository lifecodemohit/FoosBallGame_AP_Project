package mainClass;

import gameGUI.Configuration;
import gameGUI.StartScreen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException,
			LineUnavailableException, UnsupportedAudioFileException,
			IOException {
		File audioFile = new File("backgroundSound.wav");
		AudioInputStream audioStream = AudioSystem
				.getAudioInputStream(audioFile);
		AudioFormat format = audioStream.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class, format);
		Clip audioClip = (Clip) AudioSystem.getLine(info);
		audioClip.open(audioStream);
		audioClip.start();
		audioClip.loop(30);

		StartScreen start = new StartScreen();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		start.dispose();

		Configuration.main(args);

	}
}
