package gameengine.application.view;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

/** Defines a class that allows to create a GameSound object.
 * This sound clip can either be played or stopped
 * @author Cédric Abdelbaki
 * @version v0.1
 */
public class GameSound {

	// An audio clip to play or stop
	private Clip audioClip;

	/** Constructor : Creates a sound object using an audio file path specified as a parameter
	 * @param soundFilePath- The path to the audio file to play
	 */
	public GameSound(String soundFilePath) {
        File audioFile = new File(soundFilePath);
        AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(audioFile);
		} catch (UnsupportedAudioFileException | IOException e) {
			System.out.println("Error trying to initialize the audio input stream: " + e);
		}
        audioClip = null;
		try {
			audioClip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			System.out.println("Error trying to initialize the audio clip: " + e);
		}
        try {
			audioClip.open(audioInputStream);
		} catch (LineUnavailableException | IOException e) {
			System.out.println("Error trying to open the audio clip: " + e);
		}
	}

	/** Plays the clip with or without loop
	 * @param isLooping Defines if the clip has to loop
	 */
	public void playClip(boolean isLooping) {
		if (isLooping) {
			audioClip.loop(-1);
		} else {
			audioClip.start();
		}
    }

	/** Stops the played clip
	 */
	public void stopClip() {
		try {
			audioClip.stop();
		} catch (Exception e) {
			System.err.println("Error trying to stop playing this clip :" + e);
		}
	}
}

