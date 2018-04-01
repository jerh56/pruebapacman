package clases;

import javax.swing.*;
import java.awt.*;

public enum AnimationEnum {
    PACMAN_IDLE(new Image[]{new ImageIcon("images/pacman.png").getImage()}, 1, true),

    PACMAN_NORMAL_UP(new Image[]{
            new ImageIcon("images/pacman.png").getImage(),
            new ImageIcon("images/up1.png").getImage(),
            new ImageIcon("images/up2.png").getImage(),
            new ImageIcon("images/up3.png").getImage()}, 1, true),

    PACMAN_NORMAL_RIGHT(new Image[]{
            new ImageIcon("images/pacman.png").getImage(),
            new ImageIcon("images/right1.png").getImage(),
            new ImageIcon("images/right2.png").getImage(),
            new ImageIcon("images/right3.png").getImage()}, 1, true),

    PACMAN_NORMAL_DOWN(new Image[]{
            new ImageIcon("images/pacman.png").getImage(),
            new ImageIcon("images/down1.png").getImage(),
            new ImageIcon("images/down2.png").getImage(),
            new ImageIcon("images/down3.png").getImage()}, 1, true),

    PACMAN_NORMAL_LEFT(new Image[]{
            new ImageIcon("images/pacman.png").getImage(),
            new ImageIcon("images/left1.png").getImage(),
            new ImageIcon("images/left2.png").getImage(),
            new ImageIcon("images/left3.png").getImage()}, 1, true),

    PACMAN_DIE(new Image[]{
            new ImageIcon("images/leftdie1.png").getImage(),
            new ImageIcon("images/leftdie2.png").getImage(),
            new ImageIcon("images/leftdie3.png").getImage(),
            new ImageIcon("images/leftdie4.png").getImage(),
            new ImageIcon("images/leftdie5.png").getImage(),
            new ImageIcon("images/leftdie6.png").getImage()}, 1, false),

    CLYDE_NORMAL_LEFT(new Image[]{
            new ImageIcon("images/clyde v3.png").getImage(),
            new ImageIcon("images/clyde v4.png").getImage()}, 4, true),
    CLYDE_NORMAL_RIGHT(new Image[]{
            new ImageIcon("images/clyde v1.png").getImage(),
            new ImageIcon("images/clyde v2.png").getImage()}, 4, true),

    BLINKY_NORMAL_LEFT(new Image[]{
            new ImageIcon("images/blinky v3.png").getImage(),
            new ImageIcon("images/blinky v4.png").getImage()}, 4, true),
    BLINKY_NORMAL_RIGHT(new Image[]{
            new ImageIcon("images/blinky v1.png").getImage(),
            new ImageIcon("images/blinky v2.png").getImage()}, 4, true),

    INKY_NORMAL_LEFT(new Image[]{
            new ImageIcon("images/inky v3.png").getImage(),
            new ImageIcon("images/inky v4.png").getImage()}, 4, true),
    INKY_NORMAL_RIGHT(new Image[]{
            new ImageIcon("images/inky v1.png").getImage(),
            new ImageIcon("images/inky v2.png").getImage()}, 4, true),

    PINKY_NORMAL_LEFT(new Image[]{
            new ImageIcon("images/pinky v3.png").getImage(),
            new ImageIcon("images/pinky v4.png").getImage()}, 4, true),
    PINKY_NORMAL_RIGHT(new Image[]{
            new ImageIcon("images/pinky v1.png").getImage(),
            new ImageIcon("images/pinky v2.png").getImage()}, 4, true);

    private Image[] images;
    private final int delay;
    private final boolean looping;

    AnimationEnum(Image[] images, int delay, boolean looping) {
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
