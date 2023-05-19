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
        if(movingLeft){
            x-=speed;
        }
        if(movingRight){
            x+=speed;
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
}
