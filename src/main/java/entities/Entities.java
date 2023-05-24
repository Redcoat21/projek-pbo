package entities;
import main.Main;
import ultilities.Vector2;
import java.util.ArrayList;

/**
 * Representing Any object in the map that have the following criteria : able to move or have collision.
 */
public abstract class Entities {
    /**
     * Representing the Entity's position on the map.
     */
    private Vector2 position;
    /**
     * Representing the size in Width and Height
     */
    private final Vector2 size;
    /**
     * The hp of the entities.
     */
    private final int health;
    /**
     * The direction that the entities is currently moving toward.
     */
    private Direction direction;
    /**
     * The speed that the entity (if able to move) moves on.
     */
    private final float speed;
    /**
     * The list of what key is pressed
     */
    private ArrayList<Direction> savingDirection;
    /**
     * Constructor for the Entity class and its child.
     * @param x The x position of the entity in the map.
     * @param y The y position of the entity in the map.
     * @param width The width of the entity.
     * @param height The height of the entity.
     * @param health The health that the entity have.
     * @param speed The speed that the entity is moving on.
     */

    public Entities(float x, float y, int width, int height, int health, int speed) {
        this.position = new Vector2(x, y);
        this.size = new Vector2(width, height);
        this.health = health;
        direction = Direction.NONE;
        this.speed = speed;
        savingDirection = new ArrayList<>();
    }

    public void addDirection(Direction direction){
        boolean twin = false;
        for(int i=0; i<savingDirection.size(); i++){
            if(savingDirection.get(i).equals(direction)){
                twin = true;
            }
        }
        if(!twin) {
            savingDirection.add(direction);
        }
    }

    public void keyReleasedDirection(Direction direction){
        for(int i=0; i<savingDirection.size(); i++){
            System.out.println(i + ". " + savingDirection.get(i));
            if(savingDirection.get(i).equals(direction)){
                System.out.println("removed. " + savingDirection.get(i));
                savingDirection.remove(i);
            }
        }
        System.out.println("the last index is " + savingDirection.get(savingDirection.size()-1));
        moveTo(savingDirection.get(savingDirection.size()-1));
    }

    /**
     * Get the current position (x,y) of the entity in Vector2.
     * @return Vector2 position of the entity.
     */
    public Vector2 getPosition() {
        return this.position;
    }

    /**
     * Get the x position of the entity.
     * @return The x position of the entity in the map.
     */
    public float getX() {
        return this.position.getX();
    }

    /**
     * Get the y position of the entity
     * @return The y position of the entity in the map.
     */
    public float getY() {
        return this.position.getY();
    }

    /**
     * Get the width of the entity.
     * @return The width of the entity.
     */
    public float getWidth() {
        return this.size.getX();
    }

    /**
     * Get the height of the entity.
     * @return The height of the entity.
     */
    public float getHeight() {
        return this.size.getY();
    }

    /**
     * Get the current health of the entity.
     * @return The health of the entity.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Render the entity in the map.
     */
    public void render(){
        Main.processing.noStroke();
        Main.processing.rect(this.position.getX(),this.position.getY(),this.size.getX(),this.size.getY());
    }

    /**
     * Move the entity based on its direction, if It's NONE or not moving then stand still.
     */
    public void move() {
        switch(direction) {
            case UP -> this.position.addBy(0.0f, -(this.speed));
            case RIGHT -> this.position.addBy(this.speed, 0.0f);
            case DOWN -> this.position.addBy(0.0f, this.speed);
            case LEFT -> this.position.addBy(-(this.speed), 0.0f);
        }
        boolean outOfBoundUp = this.position.getY() < 80.0f;
        boolean outOfBoundRight = this.position.getX() > 1280.0f - this.size.getX();
        boolean outOfBoundDown = this.position.getY() > 720.0f - this.size.getY();
        boolean outOfBoundLeft = this.position.getX() < 0.0f;

        if(outOfBoundUp) {
            this.position.setTo(this.size.getX(), 80.0f);
        }

        if(outOfBoundRight) {
            this.position.setTo(1280.0f - this.size.getX(), this.position.getY());
        }

        if(outOfBoundDown) {
            this.position.setTo(this.position.getX(), 720.0f - this.size.getY());
        }

        if(outOfBoundLeft) {
            this.position.setTo(0.0f, this.position.getY());
        }
    }

    /**
     * Set the direction of the entity.
     * @param direction The direction that the entity will move toward to.
     */
    public void moveTo(Direction direction) {
        this.direction = direction;
        addDirection(direction);
    }

    /**
     * Set the entity's position on the given (x,y) value.
     * @param x The new x position of the entity.
     * @param y The new y position of the entity.
     */
    public void setTo(float x, float y) {
        this.position.setTo(x, y);
    }

    /**
     * Set the entity's position on the given (x,y) value but with a Vector2 parameter.
     * @param position The new (x,y) value of the entity.
     */
    public void setTo(Vector2 position) {
        this.position = position;
    }

    /**
     * Stop the entity's movement
     */
    public void stop() {
        direction = Direction.NONE;
    }

    /**
     * Check if it's moving in the given direction.
     * @param direction The direction to check.
     * @return If the entity's current direction is equal to the given direction.
     */
    public boolean isMovingIn(Direction direction) {
        return this.direction.equals(direction);
    }
}
