package entities;

import processing.core.PApplet;
public abstract class Entities extends PApplet{
    protected int x;
    //posisi x
    protected int y;
    //posisi y
    protected int w;
    //ukuran besar width sprite
    protected int h;
    //ukuran besar height sprite
    protected int hp;
    //health
    protected boolean movingLeft;
    protected boolean movingRight;
    protected boolean movingUp;
    protected boolean movingDown;
    protected int speed;

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
        rect(x,y,w,h);
    }

    public void move(){
        System.out.println("masuk");
        if(movingLeft){
            System.out.println("sebelum x = " + x);
            x -= speed;
            System.out.println("masuk left");
            System.out.println("sesudah x = " + x);
        }
        if(movingRight){
            System.out.println("sebelum x = " + x);
            x += speed;
            System.out.println("masuk right");
            System.out.println("sesudah x = " + x);
        }
        if(movingUp){
            y-=speed;
        }
        if(movingDown){
            y+=speed;
        }
    }

    public void jalanAtas(){
        movingUp = true;
    }

    public void jalanBawah(){
        movingDown = true;
    }

    public void jalanKiri(){
        movingLeft = true;
    }

    public void jalanKanan(){
        movingRight = true;
    }

    public void hentiAtas(){
        movingUp = false;
    }

    public void hentiBawah(){
        movingDown = false;
    }

    public void hentiKiri(){
        movingLeft = false;
    }

    public void hentiKanan(){
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
