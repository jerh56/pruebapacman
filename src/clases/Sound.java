package clases;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public enum Sound {
    PACMAN_MUNCH("sounds/pacman_munch.wav"),
    PACMAN_BEGINNING("sounds/pacman_beginning.wav"),
    PACMAN_DEATH("sounds/pacman_death.wav"),
    SIREN("sounds/siren.wav"),
    GHOST_SCARED("sounds/ghosts_ambient_scared2.wav"),
    GHOST_EATEN("sounds/ghost_eaten.wav");

    private Clip clip;

    Sound(String soundFileName) {
        try {
            URL url = this.getClass().getResource(soundFileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void play(Boolean loop) {
        if(!clip.isRunning()) {
            clip.setFramePosition(0);
            clip.start();
            if (loop)
                clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        clip.stop();
        clip.setFramePosition(0);
    }
}

//public class Sound {
//    public static final AudioClip PACMAN_MUNCH = Applet.newAudioClip(Sound.class.getResource("sounds/pacman_munch.wav"));
//    public static final AudioClip PACMAN_BEGINNING = Applet.newAudioClip(Sound.class.getResource("sounds/pacman_beginning.wav"));
//    public static final AudioClip PACMAN_DEATH = Applet.newAudioClip(Sound.class.getResource("sounds/pacman_death.wav"));
//    public static final AudioClip SIREN = Applet.newAudioClip(Sound.class.getResource("sounds/siren.wav"));
//    public static final AudioClip GHOST_SCARED = Applet.newAudioClip(Sound.class.getResource("sounds/ghosts_ambient_scared2.wav"));
//    public static final AudioClip GHOST_EATEN = Applet.newAudioClip(Sound.class.getResource("sounds/ghost_eaten.wav"));
//}