package clases;

import pruebapacman.Principal;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public enum AnimationEnum {
    PACMAN_IDLE(1, true, "images/pacman.png"),

    PACMAN_NORMAL_UP(1, true,
            "/images/pacman.png",
            "/images/up1.png",
            "/images/up2.png",
            "/images/up3.png"),

    PACMAN_NORMAL_RIGHT(1, true,
            "/images/pacman.png",
            "/images/right1.png",
            "/images/right2.png",
            "/images/right3.png"),

    PACMAN_NORMAL_DOWN(1, true,
            "images/pacman.png",
            "images/down1.png",
            "images/down2.png",
            "images/down3.png"),

    PACMAN_NORMAL_LEFT(1, true,
            "images/pacman.png",
            "images/left1.png",
            "images/left2.png",
            "images/left3.png"),

    PACMAN_DIE(1, false,
            "images/leftdie1.png",
            "images/leftdie2.png",
            "images/leftdie3.png",
            "images/leftdie4.png",
            "images/leftdie5.png",
            "images/leftdie6.png"),

    CLYDE_NORMAL_LEFT(4, true,
            "images/clyde v3.png",
            "images/clyde v4.png"),
    CLYDE_NORMAL_RIGHT(4, true,
            "images/clyde v1.png",
            "images/clyde v2.png"),

    BLINKY_NORMAL_LEFT(4, true,
            "images/blinky v3.png",
            "images/blinky v4.png"),
    BLINKY_NORMAL_RIGHT(4, true,
            "images/blinky v1.png",
            "images/blinky v2.png"),

    INKY_NORMAL_LEFT(4, true,
            "images/inky v3.png",
            "images/inky v4.png"),
    INKY_NORMAL_RIGHT(4, true,
            "images/inky_v1.png",
            "images/inky_v2.png"),

    PINKY_NORMAL_LEFT(4, true,
            "images/pinky v3.png",
            "images/pinky v4.png"),
    PINKY_NORMAL_RIGHT(4, true,
            "images/pinky v1.png",
            "images/pinky v2.png"),

    GHOST_SCARED(1, false, "images/ghost scared.png"),

    GHOST_EATEN(1, false, "images/ghost eyes.png"),

    CEREZA(1, false, "images/cereza.png");

    private Image[] images;
    private final int delay;
    private final boolean looping;

    AnimationEnum(int delay, boolean looping, String... imageURLs) {
        Image[] images = new Image[imageURLs.length];

        for(int i = 0; i < imageURLs.length; i++) {
            URL url = this.getClass().getResource(imageURLs[i]);
            images[i] = new ImageIcon(url).getImage();
        }

        this.images = images;
        this.delay = delay;
        this.looping = looping;
    }
    public Image[] getImages() {
        return images;
    }

    public int getDelay() {
        return delay;
    }

    public boolean isLooping() {
        return looping;
    }
}
