package clases;

import java.awt.*;

public class Animation {
    // Declarando atributos
    // Delay y looping cuentan con valores predeterminados en caso de que no sean asignados por el constructor
    private Image[] images;
    private int delay = 1;
    private boolean looping = true;
    private boolean finished = false;

    private int currentFrame = 0;
    private int count = delay;
    private int direction = 1;

    public Animation(Image[] images, int delay, boolean looping) {
        this.images = images;
        this.delay = delay;
        this.looping = looping;
    }

    public Animation(Image[] images, int delay) {
        this.images = images;
        this.delay = delay;
    }

    public Animation(AnimationEnum animation) {
        this.images = animation.getImages();
        this.delay = animation.getDelay();
        this.looping = animation.isLooping();
    }

    public void update() {
        if(images.length == 1 || finished) return;
        count--;

        if (count <= 0) {
            count = delay;
            currentFrame = currentFrame + direction;
            if(!looping  && currentFrame == (images.length - 1)) finished = true;

            if ((currentFrame == (images.length - 1) || currentFrame == 0) && looping) {
                direction = -direction;
            }
        }
    }

    public void setImages(Image[] images) {
        this.images = images;
    }
    public void setCurrentFrame(int frame) {
        this.currentFrame = frame;
    }

    public Image[] getImages() {
        return this.images;
    }
    public int getCurrentFrame() {
        return this.currentFrame;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
