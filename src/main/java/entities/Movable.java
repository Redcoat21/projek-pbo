package entities;

import entities.tiles.Hole;
import entities.tiles.Obstacles;

import java.util.ArrayList;

import entities.tiles.Wall;
import main.Main;
import main.Map;
import processing.core.PConstants;

public class Movable extends Entities{
    /**
     * The hp of the entities.
     */
    private int health;
    /**
     * The direction that the entities is currently moving toward.
     */
    private Direction direction;
    /**
     * The speed that the entity (if able to move) moves on.
     */
    private int speed;
    /**
     * The list of what key is pressed
     */
    private ArrayList<Direction> savingDirection;
    private Direction atkDirection;

    protected static Map map;
    protected float atkSpeed;

    public Map getMap() {
        return map;
    }

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param health The health that the entity have.
     * @param speed The speed that the entity is moving on.
     */

    //constuctor only for player
    public Movable(float x, float y, int width, int height, int health, int speed, int atkSpeed, Map map) {
        super(x, y, width, height);
        this.health = health;
        direction = Direction.NONE;
        this.speed = speed;
        savingDirection = new ArrayList<>();
        this.map = map;
        atkDirection = Direction.RIGHT;
        this.atkSpeed = atkSpeed;
    }

    //constructor for other movable except player
    public Movable(float x, float y, int width, int height, int health, int speed, int atkSpeed) {
        super(x, y, width, height);
        this.health = health;
        direction = Direction.NONE;
        this.speed = speed;
        this.atkSpeed = atkSpeed;
        savingDirection = new ArrayList<>();
        setMap(4);
    }

    public void setMap(int floor) {
        map = new Map(floor);
    }

    public void updateMap(Map map){
        this.map = map;
    }

    protected boolean entitiesCollisionChecker(){
        for(Obstacles[] obsTemp: map.getMap()){
            for(Obstacles obs: obsTemp){
                if(obs != null) {
                    if (entitiesIntersectWall(obs) && obs instanceof Wall) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private void entitiesCollisionWall(){
        for(Obstacles[] obsTemp: map.getMap()){
            for(Obstacles obs: obsTemp){
                if(obs != null) {
                    if (entitiesIntersectWall(obs) && obs instanceof Wall) {
                        if(direction.equals(Direction.UP)){
                            getPosition().add(0.0f, this.speed - gapCollisionOnY(obs));
                        }
                        else if(direction.equals(Direction.DOWN)){
                            getPosition().add(0.0f, -(this.speed) + gapCollisionOnY(obs));
                        }
                        else if(direction.equals(Direction.RIGHT)){
                            getPosition().add(-(this.speed) + gapCollisionOnX(obs), 0.0f);
                        }
                        else if(direction.equals(Direction.LEFT)){
                            getPosition().add(this.speed - gapCollisionOnX(obs), 0.0f);
                        }
                    }
                }
            }
        }
    }

    private float gapCollisionOnX (Obstacles e1){
        float combHalfWidth = (e1.getWidth()+getWidth())/2;
//        float distanceOnX = Math.abs((e1.getX() + e1.getWidth()/2)-(getX() + getWidth()/2));
        float distanceOnX = Math.abs(e1.getXFromCenter() - getXFromCenter());


        return distanceOnX+this.speed-combHalfWidth;
    }

    private float gapCollisionOnY (Obstacles e1){
        float combHalfHeight = (e1.getHeight()+getHeight())/2;
//        float distanceOnY = Math.abs((e1.getY() + e1.getHeight()/2)-(getY() + getHeight()/2));
        float distanceOnY = Math.abs(e1.getYFromCenter() - getYFromCenter());

        return distanceOnY+this.speed-combHalfHeight;
    }

    private void entitiesCollisionHole(){
        for(Obstacles[] obsTemp: map.getMap()){
            for(Obstacles obs: obsTemp){
                if(obs != null) {
                    if (entitiesIntersectHole(obs) && obs instanceof Hole) {
                        fallen();
                    }
                }
            }
        }
    }

    protected boolean entitiesIntersectWall(Obstacles e1){
        float combHalfWidth = (e1.getWidth()+getWidth())/2;
        float combHalfHeight = (e1.getHeight()+getHeight())/2;
//        float distanceOnX = Math.abs((e1.getX() + e1.getWidth()/2)-(getX() + getWidth()/2));
//        float distanceOnY = Math.abs((e1.getY() + e1.getHeight()/2)-(getY() + getHeight()/2));
        float distanceOnX = Math.abs(e1.getXFromCenter() - getXFromCenter());
        float distanceOnY = Math.abs(e1.getYFromCenter() - getYFromCenter());

        if(distanceOnX<combHalfWidth && distanceOnY<combHalfHeight){
            return true;
        }
        return false;
    }

    private boolean entitiesIntersectHole(Obstacles e1){
        float combHalfWidth = (e1.getWidth()+getWidth())/2;
        float combHalfHeight = (e1.getHeight()+getHeight())/2;
        float distanceOnX = Math.abs(e1.getX()-getX());
        float distanceOnY = Math.abs(e1.getY()-getY());


        if(distanceOnX+5<combHalfWidth && distanceOnY+5<combHalfHeight){
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

        entitiesCollisionWall();
        entitiesCollisionHole();
        facing();
    }

    /**
     * Set the direction of the entity.
     * @param direction The direction that the entity will move toward to.
     */
    public void moveTo(Direction direction) {
        this.direction = direction;
        addDirection(direction);
    }

    protected void facing(){
        if(!direction.equals(Direction.NONE)){
             atkDirection = direction;
        }
    }

    protected Direction getAtkDirection(){
        return atkDirection;
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

    public void addHealth(int health){
        this.health += health;
    }

    public void fallen(){
        this.health = 0;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void subHP(int hp){
        health -= hp;
    }
    protected void setHealth(int health){
        this.health = health;
    }

    public float getAtkSpeed(){
        return atkSpeed/5;
    }
}
