package entities;

import main.Main;
import processing.core.PImage;

import java.util.ArrayList;

public class Animation {
    private int frameDuration;
    private int currentSpriteIndex;
    private ArrayList<PImage> sprites;

    public Animation(int frameDuration) {
        this.frameDuration = frameDuration;
        this.sprites = new ArrayList<>();
    }

    /**
     * Add sprites into the sprites list.
     * @param sprite The image / sprite to be added.
     */
    public void addSprite(PImage sprite) {
        this.sprites.add(sprite);
    }

    /**
     * Add sprites into the sprites list with option to resize the image.
     * @param sprite The image / sprite to be added.
     * @param width The new width of the sprite.
     * @param height The new height of the sprite.
     */
    public void addSprite(PImage sprite, float width, float height) {
        PImage temp = sprite;
        temp.resize((int) width, (int) height);
        this.sprites.add(temp);
    }

    /**
     * Return the amount of sprites in this animation.
     * @return The sprites in this animation.
     */
    public int getSpriteCount() {
        return this.sprites.size();
    }

    /**
     * Load the image(s) onto the screen.
     * @param entities The current entities that the sprite is displayed onto.
     */

    public void play(Entities entities) {
        int frameIndex = Main.processing.frameCount / this.frameDuration;
        currentSpriteIndex = frameIndex % sprites.size();
        Main.processing.image(sprites.get(currentSpriteIndex), entities.getX(), entities.getY());
    }
}