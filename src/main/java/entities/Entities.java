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
    private HashMap<Direction, Animation> animationList;
    /**
     * Representing the Entity's position on the map.
     */
    private PVector position;
    /**
     * Representing the size in Width and Height
     */
    private final PVector size;
//    /**
//     * The hp of the entities.
//     */
//    private final int health;
   /**
     * The direction that the entities is currently moving toward.
     */
    private Direction direction;
//    /**
//     * The speed that the entity (if able to move) moves on.
//     */
//    private final float speed;
//    /**
//     * The list of what key is pressed
//     */
//    private ArrayList<Direction> savingDirection;

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
//        this.health = health;
//        direction = Direction.NONE;
//        this.speed = speed;
//        savingDirection = new ArrayList<>();
        this.animationList = new HashMap<>();

        for(Direction direction : Direction.values()) {
            this.animationList.put(direction, new Animation(5));
        }
    }

//    private boolean entitiesIntersect(Entities e1){
//        float combHalfWidth = (e1.getWidth()+getWidth())/2;
//        float combHalfHeight = (e1.getHeight()+getHeight())/2;
//        float distanceOnX = Math.abs(e1.getX()-getX());
//        float distanceOnY = Math.abs(e1.getY()-getY());
//
//        if(distanceOnX<combHalfWidth && distanceOnY<combHalfHeight){
//            return true;
//        }
//        return false;
//    }

//    public void clearDirection(){
//        savingDirection.clear();
//    }
//    public void addDirection(Direction direction){
//        boolean twin = false;
//        for(int i=0; i<savingDirection.size(); i++){
//            if(savingDirection.get(i).equals(direction)){
//                twin = true;
//            }
//        }
//        if(!twin) {
//            savingDirection.add(direction);
//        }
//    }
//
//    public void keyReleasedDirection(Direction direction){
//        for(int i=0; i<savingDirection.size(); i++){
////            System.out.println(i + ". " + savingDirection.get(i));
//            if(savingDirection.get(i).equals(direction)){
////                System.out.println("removed. " + savingDirection.get(i));
//                savingDirection.remove(i);
//            }
//        }
////        System.out.println("the last index is " + savingDirection.get(savingDirection.size()-1));
//        moveTo(savingDirection.get(savingDirection.size()-1));
//    }
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

//    /**
//     * Move the entity based on its direction, if It's NONE or not moving then stand still.
//     */
//    public void move() {
//        switch(direction) {
//            case UP -> this.position.add(0.0f, -(this.speed));
//            case RIGHT -> this.position.add(this.speed, 0.0f);
//            case DOWN -> this.position.add(0.0f, this.speed);
//            case LEFT -> this.position.add(-(this.speed), 0.0f);
//        }
//        boolean outOfBoundUp = this.position.y < 80.0f;
//        boolean outOfBoundRight = this.position.x > 1280.0f - this.size.x;
//        boolean outOfBoundDown = this.position.y > 720.0f - this.size.y;
//        boolean outOfBoundLeft = this.position.x < 0.0f;
//
//        if(outOfBoundUp) {
//            this.position.set(this.position.x, 80.0f);
//        }
//
//        if(outOfBoundRight) {
//            this.position.set(1280.0f - this.size.x, this.position.y);
//        }
//
//        if(outOfBoundDown) {
//            this.position.set(this.position.x, 720.0f - this.size.y);
//        }
//
//        if(outOfBoundLeft) {
//            this.position.set(0.0f, this.position.y);
//        }
//    }

//    /**
//     * Set the direction of the entity.
//     * @param direction The direction that the entity will move toward to.
//     */
//    public void moveTo(Direction direction) {
//        this.direction = direction;
//        addDirection(direction);
//    }

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

//    /**
//     * Stop the entity's movement
//     */
//    public void stop() {
//        direction = Direction.NONE;
//    }
//
//    /**
//     * Check if it's moving in the given direction.
//     * @param direction The direction to check.
//     * @return If the entity's current direction is equal to the given direction.
//     */
//    public boolean isMovingIn(Direction direction) {
//        return this.direction.equals(direction);
//    }


    protected PVector getSize() {
        return size;
    }

    public void addSprites(Direction addToWalkToAnimation, String spritePath) {
        Animation temp = this.animationList.get(addToWalkToAnimation);
        temp.addImage(this, spritePath);
    }

    public HashMap<Direction, Animation> getAnimationList() {
        return this.animationList;
    }

    public Direction getDirection() {
        return this.direction;
    }
}
