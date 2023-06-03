package entities;
import main.Main;
import processing.core.PImage;
import processing.core.PVector;
import java.util.ArrayList;
import java.util.HashMap;

import main.Map;

/**
 * Representing Any object in the map that have the following criteria : able to move or have collision.
 */
public abstract class Entities {
    /**
     * Representing the Entity's position on the map.
     */
    private PVector position;
    /**
     * Representing the size in Width and Height
     */
    private final PVector size;
   /**
     * The direction that the entities is currently moving toward.
     */
    private Direction direction;

    /**
     * Constructor for the Entity class and its child.
     * @param x The x position of the entity in the map.
     * @param y The y position of the entity in the map.
     * @param width The width of the entity.
     * @param height The height of the entity.
     */

    public Entities(float x, float y, int width, int height) {
        this.position = new PVector(x, y);
        this.size = new PVector(width, height);
    }
    /**
     * Get the current position (x,y) of the entity in Vector2.
     * @return Vector2 position of the entity.
     */
    public PVector getPosition() {
        return this.position;
    }

    /**
     * Get the x position of the entity.
     * @return The x position of the entity in the map.
     */
    public float getX() {
        return this.position.x;
    }

    /**
     * Get the y position of the entity
     * @return The y position of the entity in the map.
     */
    public float getY() {
        return this.position.y;
    }

    /**
     * Get the width of the entity.
     * @return The width of the entity.
     */
    public float getWidth() {
        return this.size.x;
    }

    /**
     * Get the height of the entity.
     * @return The height of the entity.
     */
    public float getHeight() {
        return this.size.y;
    }

//    /**
//     * Get the current health of the entity.
//     * @return The health of the entity.
//     */
//    public int getHealth() {
//        return health;
//    }

    /**
     * Render the entity in the map.
     */
    public void render(){
        Main.processing.noStroke();
        //Main.processing.rect(this.position.x, this.position.y, this.size.x, this.size.y);
    }

    /**
     * Set the entity's position on the given (x,y) value.
     * @param x The new x position of the entity.
     * @param y The new y position of the entity.
     */
    public void setTo(float x, float y) {
        this.position.set(x, y);
    }

    /**
     * Set the entity's position on the given (x,y) value but with a Vector2 parameter.
     * @param position The new (x,y) value of the entity.
     */
    public void setTo(PVector position) {
        this.position = position;
    }

    protected PVector getSize() {
        return size;
    }

    public Direction getDirection() {
        return this.direction;
    }
}
