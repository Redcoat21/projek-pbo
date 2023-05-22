package entities;
//TODO change x and y into Vector2 object.

//TODO May change the class into a builder pattern.
import main.Main;
import ultilities.Vector2;

public abstract class Entities{
    private Vector2 position;
    private Vector2 size;
    private int hp;
    private Direction direction;
    private float speed;

    public Entities(int x, int y, int w, int h, int hp, int speed) {
        this.position = new Vector2(x, y);
        this.size = new Vector2(w, h);
        this.hp = hp;
        direction = Direction.NONE;
        this.speed = speed;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public float getX() {
        return this.position.getX();
    }

    public float getY() {
        return this.position.getY();
    }

    public float getW() {
        return this.size.getX();
    }

    public float getH() {
        return this.size.getY();
    }

    public int getHp() {
        return hp;
    }

    public void render(){
        Main.processing.noStroke();
        Main.processing.rect(this.position.getX(),this.position.getY(),this.size.getX(),this.size.getY());
    }

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
//    public void move(){
//        if(movingLeft){
//            this.position.addBy(-(this.speed), 0);
//        }
//
//        if(movingRight){
//            this.position.addBy(this.speed, 0.0f);
//        }
//
//        if(movingUp){
//            this.position.addBy(0.0f, -(this.speed));
//        }
//
//        if(movingDown){
//            this.position.addBy(0.0f, this.speed);
//        }
//
//        if(this.position.getX() < 0.0f){
//            this.position.setTo(0.0f, 0.0f);
//        }
//
//        if(this.position.getX() > 1280.0 - this.size.getX()){
//            this.position.setTo(1280.0f - this.size.getX(), 0.0f);
//        }
//
//        if(this.position.getY() < 80.0f){
//            this.position.setTo(0.0f, 80.0f);
//        }
//        if(this.position.getY() > 720.0f - this.size.getY()) {
//            this.position.setTo(0.0f, 720.0f - this.size.getY());
//        }
//    }

    public void moveUp(){
        direction = Direction.UP;
    }

    public void moveDown(){
        direction = Direction.DOWN;
    }

    public void moveLeft(){
        direction = Direction.LEFT;
    }

    public void moveRight(){
        direction = Direction.RIGHT;
    }

    public void stopUp(){
        movingUp = false;
    }

    public void stopDown(){
        movingDown = false;
    }

    public void stopLeft(){
        movingLeft = false;
    }

    public void stopRight(){
        movingRight = false;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public void setX(float x) {
        this.position.addBy(x, 0.0f);
    }
}
