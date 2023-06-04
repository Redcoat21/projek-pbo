package entities;

import main.Main;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

public class Animation {
    /**
     * List of image that will form the Animation.
     */
    private ArrayList<PImage> spriteList;

    public Animation() {
        this.spriteList = new ArrayList<>();
    }

    public void addSprite(PImage sprite) {
        this.spriteList.add(sprite);
    }

    /**
     * Play the animation.
     * @param position The position of the current entity, or the position that the animation will be played on.
     */
    public void play(PVector position) {
        for(PImage sprite : this.spriteList) {
            Main.processing.image(sprite, position.x, position.y);
        }
    }

}
