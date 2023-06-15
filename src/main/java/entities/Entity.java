package entities;

import entities.animation.Animation;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Base class for any object that : Have sprites, can attack and can move.
 */
public abstract class Entity implements Animatable, Combatant, Moveable {
    private Animation sprites;
    private PVector position;
    private final PVector size;
    private Direction facingToward;
    private Direction direction;

    /**
     * Constructor for the Entity class and its child.
     * @param x The x position of the entity in the map.
     * @param y The y position of the entity in the map.
     * @param width The width of the entity.
     * @param height The height of the entity.
     */

    public Entity(float x, float y, int width, int height) {
        this.position = new PVector(x, y);
        this.size = new PVector(width, height);
        this.sprites = new Animation(30);
    }
    /**
     * Get the current position (x,y) of the entity in PVector.
     * @return Vector2 position of the entity.
     */
    public PVector getPosition() {
        return this.position;
    }

    /**
     * Get the size of the entity in PVector.
     * @return PVector format of the size (width, height).
     */
    public PVector getSize() {
        return this.size;
    }

    /**
     * Get the sprites list of the entity.
     * @return Spriteslist in {@link Animation}.
     */
    public Animation getSprites() {
        return sprites;
    }

    /**
     * Render the entity in the map.
     */
    public abstract void render();

    /**
     * Set the entity's position on the given (x,y) value.
     * @param x The new x position of the entity.
     * @param y The new y position of the entity.
     */
    public void setTo(float x, float y) {
        this.position.set(x, y);
    }

    /**
     * Set the entity's position on the given (x,y) value but with a PVector parameter.
     * @param position The new (x,y) value of the entity.
     */
    public void setTo(PVector position) {
        this.position = position;
    }

    /**
     * Get the current direction that the entity is moving toward.
     * @return The direction that the entity is moving toward.
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Get the current direction that the entity is facing to.
     * @return The direction that the entity is facing to.
     */
    public Direction getFacingToward() {
        return facingToward;
    }

    /**
     * Add sprites into the sprites list.
     * @param animationFor Direction for the sprite.
     * @param sprite The sprite to be added.
     */
    @Override
    public void addSprite(Direction animationFor, PImage sprite) {
        this.sprites.addSprite(animationFor, sprite, this.size);
    }

    @Override
    public void attack(Entity target) {
        System.out.println("Attacking!");
    }

    @Override
    public final void move(Direction movingToward) {
        this.position = this.position.add(movingToward.getDirection());
        this.facingToward = movingToward;
    }
}
