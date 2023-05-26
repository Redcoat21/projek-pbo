package entities;

import entities.tiles.Obstacles;

import java.util.ArrayList;

import main.Main;
import main.Map;

public class Movable extends Entities{
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

    private Map map;

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param health The health that the entity have.
     * @param speed The speed that the entity is moving on.
     */
    public Movable(float x, float y, int width, int height, int health, int speed) {
        super(x, y, width, height);
        this.health = health;
        direction = Direction.NONE;
        this.speed = speed;
        savingDirection = new ArrayList<>();
        setMap(1);
    }

    public void setMap(int floor) {
        map = new Map(floor);
    }

    private void entitiesCollision(){
        for(Obstacles[] obsTemp: map.getMap()){
            for(Obstacles obs: obsTemp){
                if(obs != null) {
                    if (entitiesIntersect(obs)) {
                        System.out.println("masuk");
                    }
                }
            }
        }
    }

    private boolean entitiesIntersect(Obstacles e1){
        float combHalfWidth = (e1.getWidth()+getWidth())/2;
        float combHalfHeight = (e1.getHeight()+getHeight())/2;
        float distanceOnX = Math.abs(e1.getX()-getX());
        float distanceOnY = Math.abs(e1.getY()-getY());

        if(distanceOnX<combHalfWidth && distanceOnY<combHalfHeight){
            return true;
        }
        return false;
    }

    /**
     * to clear all the object in the list
     */
    public void clearDirection(){
        savingDirection.clear();
    }

    /**
     * to add a new direction when a new key is pressed
     * @param direction
     */
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
//            System.out.println(i + ". " + savingDirection.get(i));
            if(savingDirection.get(i).equals(direction)){
//                System.out.println("removed. " + savingDirection.get(i));
                savingDirection.remove(i);
            }
        }
//        System.out.println("the last index is " + savingDirection.get(savingDirection.size()-1));
        moveTo(savingDirection.get(savingDirection.size()-1));
    }

    /**
     * Get the current health of the entity.
     * @return The health of the entity.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Move the moveable entity based on its direction, if It's NONE or not moving then stand still.
     */
    public void move() {
        switch(direction) {
            case UP -> getPosition().add(0.0f, -(this.speed));
            case RIGHT -> getPosition().add(this.speed, 0.0f);
            case DOWN -> getPosition().add(0.0f, this.speed);
            case LEFT -> getPosition().add(-(this.speed), 0.0f);
        }
        boolean outOfBoundUp = getPosition().y < 80.0f;
        boolean outOfBoundRight = getPosition().x > 1280.0f - getSize().x;
        boolean outOfBoundDown = getPosition().y > 720.0f - getSize().y;
        boolean outOfBoundLeft = getPosition().x < 0.0f;

        if(outOfBoundUp) {
            getPosition().set(getPosition().x, 80.0f);
        }

        if(outOfBoundRight) {
            getPosition().set(1280.0f - getSize().x, getPosition().y);
        }

        if(outOfBoundDown) {
            getPosition().set(getPosition().x, 720.0f - getSize().y);
        }

        if(outOfBoundLeft) {
            getPosition().set(0.0f, getPosition().y);
        }

        entitiesCollision();
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
