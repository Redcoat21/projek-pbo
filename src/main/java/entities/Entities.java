//package entities;
//import main.Main;
//import processing.core.PImage;
//import processing.core.PVector;
//
///**
// * Representing Any object in the map that have the following criteria : have collision.
// */
//public abstract class Entities {
//    private Animation idleSprites;
//    private Animation walkingSprites;
//    /**
//     * Representing the Entity's position on the map. from left-top
//     */
//    private PVector position;
//    /**
//     * Representing the size in Width and Height
//     */
//    private final PVector size;
//   /**
//     * The direction that the entities is currently moving toward.
//     */
//    private Direction direction;
//    /**
//     * Constructor for the Entity class and its child.
//     * @param x The x position of the entity in the map.
//     * @param y The y position of the entity in the map.
//     * @param width The width of the entity.
//     * @param height The height of the entity.
//     */
//
//    public Entities(float x, float y, int width, int height) {
//        this.position = new PVector(x, y);
//        this.size = new PVector(width, height);
//        this.idleSprites = new Animation(30);
//        this.walkingSprites = new Animation(30);
//    }
//    /**
//     * Get the current position (x,y) of the entity in Vector2.
//     * @return Vector2 position of the entity.
//     */
//    public PVector getPosition() {
//        return this.position;
//    }
//
//    /**
//     * Get the x position of the entity.
//     * @return The x position of the entity in the map.
//     */
//    public float getX() {
//        return this.position.x;
//    }
//
//    /**
//     * Get the y position of the entity
//     * @return The y position of the entity in the map.
//     */
//    public float getY() {
//        return this.position.y;
//    }
//
//    public float getXFromCenter(){
//        return this.position.x + this.size.x/2;
//    }
//
//    public float getYFromCenter(){
//        return this.position.y + this.size.y/2;
//    }
//
//    /**
//     * Get the width of the entity.
//     * @return The width of the entity.
//     */
//    public float getWidth() {
//        return this.size.x;
//    }
//
//    /**
//     * Get the height of the entity.
//     * @return The height of the entity.
//     */
//    public float getHeight() {
//        return this.size.y;
//    }
//
//    /**
//     * Render the entity in the map.
//     */
//    public void render(){
//        Main.processing.noStroke();
//        //Main.processing.rect(this.position.x, this.position.y, this.size.x, this.size.y);
//    }
//
//    /**
//     * Set the entity's position on the given (x,y) value.
//     * @param x The new x position of the entity.
//     * @param y The new y position of the entity.
//     */
//    public void setTo(float x, float y) {
//        this.position.set(x, y);
//    }
//
//    /**
//     * Set the entity's position on the given (x,y) value but with a PVector parameter.
//     * @param position The new (x,y) value of the entity.
//     */
//    public void setTo(PVector position) {
//        this.position = position;
//    }
//
//    /**
//     * Get the size of the entity in (width, height) format.
//     * @return The size of the entity in PVector (width, height)
//     */
//    protected PVector getSize() {
//        return size;
//    }
//
//    /**
//     * Get the current direction that the entity is moving toward.
//     * @return The direction that the entitiy is moving toward.
//     */
//    public Direction getDirection() {
//        return this.direction;
//    }
//
//    /**
//     * Add idleSprites into the idleSprites list.
//     * @param animationFor Direction for the sprite.
//     * @param sprite The sprite to be added.
//     * @param resizedSize New size for the image if it want to be resized, put (0, 0) if not want to be resized.
//     */
//    public void addSprites(String which, Direction animationFor, PImage sprite, PVector resizedSize) {
//        if(which.equalsIgnoreCase("idle")) {
//            this.idleSprites.addSprite(animationFor, sprite, resizedSize);
//        }
//        else {
//            this.walkingSprites.addSprite(animationFor, sprite, resizedSize);
//        }
//    }
//
//    /**
//     * Load the idleSprites onto the screen.
//     * @param animationDirection Which animation should it played.
//     */
//    protected void play(String which, Direction animationDirection) {
//        if(animationDirection != null) {
//            if (which.equalsIgnoreCase("idle")) {
//                this.idleSprites.play(animationDirection, this);
//            } else {
//                this.walkingSprites.play(animationDirection, this);
//            }
//        }
//    }
//
//    public Animation getSprites(String which) {
//        if(which.equalsIgnoreCase("idle")) {
//            return idleSprites;
//        }
//
//        return walkingSprites;
//    }
//}
