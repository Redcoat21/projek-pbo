import processing.core.PApplet;
import java.util.ArrayList;

public class TestApp extends PApplet {

    Player player;
    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void setup() {
        background(100);
        player = new Player(width/10, height/2);
    }

    @Override
    public void draw() {
        // Draw a circle in the center of the window
//        ellipse(width/2, height/2, 50, 50);
//        rect(player.getX(), player.getY(), player.getW(), player.getH());
        player.render();

        System.out.println(player.getX());
        System.out.println(player.isMovingLeft());
        System.out.println(player.isMovingRight());
        player.setX(10);
        player.move();
    }

    public void keyPressed(){
        if(key == 'a'){
            player.jalanKiri();
        }

        if(key == 'd'){
            player.jalanKanan();
        }
    }

    public void keyReleased(){
        if(key == 'a'){
            player.hentiKiri();
        }

        if(key == 'd'){
            player.hentiKanan();
        }
    }

    public static void main(String[] args) {
        PApplet.main("TestApp");
    }
}
