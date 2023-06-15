package entities.tiles;

import entities.Animateable;
import entities.animation.Animation;
import entities.Direction;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Represent every single tiles that will be on the map.
 */
public class Tiles implements Animateable {
    private PVector size;
    private PVector position;
    private Animation sprites;

    /**
     * Constructor for the Tiles class
     * @param x X position of this tile.
     * @param y Y position of this tile.
     */
    public Tiles(float x, float y) {
        this.position = new PVector(x, y);
        this.sprites = new Animation(60);
    }

    /**
     * Get the position of this tile.
     * @return The tile position in PVector format.
     */
    public PVector getPosition() {
        return this.position;
    }

    /**
     * Add a single image into the sprites list.
     * @param animationFor The direction for the animation.
     * @param sprite The image / sprite that will be loaded.
     */
    @Override
    public void addSprite(Direction animationFor, PImage sprite) {
        this.sprites.addSprite(animationFor, sprite, this.size);
    }
}
