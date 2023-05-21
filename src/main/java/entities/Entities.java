package entities;

import processing.core.PApplet;
import main.Main;
public abstract class Entities{
    private int x;
    //posisi x
    private int y;
    //posisi y
    private int w;
    //ukuran besar width sprite
    private int h;
    //ukuran besar height sprite
    private int hp;
    //health
    private boolean movingLeft;
    private boolean movingRight;
    private boolean movingUp;
    private boolean movingDown;
    private int speed;

    public Entities(int x, int y, int w, int h, int hp, boolean movingLeft, boolean movingRight, boolean movingUp, boolean movingDown, int speed) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.hp = hp;
        this.movingLeft = movingLeft;
        this.movingRight = movingRight;
        this.movingUp = movingUp;
        this.movingDown = movingDown;
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getHp() {
        return hp;
    }

    public void render(){
        Main.processing.noStroke();
        Main.processing.rect(x,y,w,h);
    }

    public void move(){
        if(movingLeft){
            x -= speed;
        }
        if(movingRight){
            x += speed;
        }
        if(movingUp){
            y-=speed;
        }
        if(movingDown){
            y+=speed;
        }
        if(x<0){
            x = 0;
        }
        if(x>1280-w){
            x = 1280-w;
        }
        if(y<80){
            y = 80;
        }
        if(y>720-h){
            y = 720-h;
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

    public void setX(int x) {
        this.x += x;
    }
}
