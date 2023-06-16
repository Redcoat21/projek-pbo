package tiles;

import entities.Animatable;
import animation.Animation;
import entities.Direction;
import animation.AnimationPlayer;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Represent every single tiles that will be on the map.
 */
public abstract class Tiles implements Animatable {
    private AnimationPlayer animationPlayer;
    private PVector size;
    private PVector position;
    private Animation sprites;
    private boolean walkAble;

    /**
     * Constructor for the Tiles class
     * @param x X position of this tile.
     * @param y Y position of this tile.
     */
    public Tiles(float x, float y) {
        this.position = new PVector(x, y);
        this.sprites = new Animation(60);
        this.walkAble = false;
    }

    /**
     * Constructor for the Tiles class
     * @param x X position of this tile.
     * @param y Y position of this tile.
     * @param walkAble Determine whether a tile is walkable or not (Walls or floors).
     */
    public Tiles(float x, float y, boolean walkAble) {
        this(x, y);
        this.walkAble = walkAble;
    }

    /**
     * Get the position of this tile.
     * @return The tile position in PVector format.
     */
    public PVector getPosition() {
        return this.position;
    }

    /**
     * Determine whether a tile is walkable or not.
     * @return The walk-ability status of the tile.
     */
    public boolean isWalkable() {
        return this.walkAble;
    }

    /**
     * Get the sprites list.
     * @return The sprites list.
     */
    public Animation getSprites() {
        return sprites;
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

    /**
     * Play the tile animation.
     */
    public void playAnimation(Direction animationFor) {
        this.animationPlayer.play(this);
    }
}
