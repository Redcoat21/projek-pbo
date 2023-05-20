import processing.core.PApplet;
public class Player extends Entities {
    private Weapon senjata;

    public Player(){
        x = 40;
        y = 400;
        w = 20;
        h = 20;
        movingLeft = false;
        movingRight = false;
        movingUp = false;
        movingDown = false;
        speed = 50;
    }

    public Player(int x, int y){
        this.x = x;
        this.y = y;
        w = 20;
        h = 20;
    }

//    public void render(){
//        rect(x,y,w,h);
//    }
}
