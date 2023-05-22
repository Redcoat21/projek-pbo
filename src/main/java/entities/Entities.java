package entities;
//TODO change x and y into Vector2 object.

//TODO May change the class into a builder pattern.
import processing.core.PApplet;
import main.Main;
import ultilities.Vector2;

public abstract class Entities{
    private Vector2 position;
    private Vector2 size;
    //ukuran besar height sprite
    private int hp;
    //health
    private boolean movingLeft;
    private boolean movingRight;
    private boolean movingUp;
    private boolean movingDown;
    private float speed;

    public Entities(int x, int y, int w, int h, int hp, int speed) {
        this.position = new Vector2(x, y);
        this.size = new Vector2(w, h);
        this.hp = hp;
        this.movingLeft = false;
        this.movingRight = false;
        this.movingUp = false;
        this.movingDown = false;
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

    public void move(){
        if(movingLeft){
            this.position.moveBy(-(this.speed), 0);
        }

        if(movingRight){
            this.position.moveBy(this.speed, 0.0f);
        }

        if(movingUp){
            this.position.moveBy(0.0f, -(this.speed));
        }

        if(movingDown){
            this.position.moveBy(0.0f, this.speed);
        }

        if(this.position.getX() < 0.0f){
            this.position.moveTo(0.0f, this.position.getY());
        }

        if(this.position.getX() > 1280.0 - this.size.getX()){
            this.position.moveTo(1280.0f - this.size.getX(), this.position.getY());
        }

        if(this.position.getY() < 80.0f){
            this.position.moveTo(this.position.getX(), 80.0f);
        }
        if(this.position.getY() > 720.0f - this.size.getY()) {
            this.position.moveTo(this.position.getX(), 720.0f - this.size.getY());
        }
    }

    public void moveUp(){
        movingUp = true;
    }

    public void moveDown(){
        movingDown = true;
    }

    public void moveLeft(){
        movingLeft = true;
    }

    public void moveRight(){
        movingRight = true;
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
        this.position.moveBy(x, 0.0f);
    }
}
