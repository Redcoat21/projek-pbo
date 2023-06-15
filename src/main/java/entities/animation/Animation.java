package entities.animation;

import entities.Direction;
import entities.Entities;
import main.Main;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Hashtable;

public class Animation {
    private int frameDuration;
    private int currentSpriteIndex;
    private Hashtable<Direction, ArrayList<PImage>> sprites;

    /**
     * Constructor for the Animation class.
     * @param frameDuration How many in seconds that the sprite will be showed on.
     */
    public Animation(int frameDuration) {
        this.frameDuration = frameDuration;
        this.sprites = new Hashtable<>();
    }

    /**
     * Add the sprites based on the given direction
     * @param animationFor based on {@link entities.Direction} NONE for idle, LEFT, UP, RIGHT, DOWN
     * @param image 1 frame of image.
     * @param size the new size for the image.
     */
    public void addSprite(Direction animationFor, PImage image, PVector size) {
        PImage temp = image;

        // Meaning if the new size given is not (0,0)
        if(size.x != 0.0f && size.y != 0.0) {
            int newWidth = (int) size.x;
            int newHeight = (int) size.y;
            temp.resize(newWidth, newHeight);
        }

        // Meaning that the current direction for the sprites list is null, then assign a new list to it.
        this.sprites.computeIfAbsent(animationFor, k -> new ArrayList<>());
        this.sprites.get(animationFor).add(temp);
    }

    /**
     * Add the sprites based on the given direction
     * @param animationFor based on {@link entities.Direction} NONE for idle, LEFT, UP, RIGHT, DOWN
     * @param image 1 frame of image.
     */
    public void addSprite(Direction animationFor, PImage image) {
        PImage temp = image;
        // Meaning that the current direction for the sprites list is null, then assign a new list to it.
        this.sprites.computeIfAbsent(animationFor, k -> new ArrayList<>());
        this.sprites.get(animationFor).add(temp);
    }

    /**
     * Get the sprites list based on the given direction.
     * @param animationFor Which sprites list should be returned.
     * @return The sprites list.
     */
    public ArrayList<PImage> getSpritesList(Direction animationFor) {
        return this.sprites.get(animationFor);
    }

    /**
     * Load the image(s) onto the screen.
     * @param animationFor Which animation should it played.
     * @param entities The current entities that the sprite is displayed onto.
     */
    public void play(Direction animationFor, Entities entities) {
        int frameIndex = Main.processing.frameCount / this.frameDuration;
        currentSpriteIndex = frameIndex % sprites.size();
        Main.processing.image(this.sprites.get(animationFor).get(currentSpriteIndex), entities.getX(), entities.getY());
    }
}