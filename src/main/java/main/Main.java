package main;

import entities.Direction;
import entities.Player;
import processing.core.PApplet;
public class Main extends PApplet{
    /*
        this variable mode is used for marking the condition of the game
        1 = load screen
        2 = choosing game mode
        3 = arcade mode
        4 = endless mode
        5 = exit
     */
    int mode;
    public static PApplet processing;
//    Player player;
    private LoadingScreen ls;
    private ArcadeMode am;
    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void setup() {
        background(100);
//        player = new Player(width/10, height/2);
        processing = this;
        ls = new LoadingScreen(width, height);
        am = new ArcadeMode(width, height);
        mode = 1;
    }

    @Override
    public void draw() {
//        background(100);
//        player.render();
//        player.move();
        if(mode == 1) {
            background(0);
            ls.display();
            if(ls.isPressed()){
                mode = 3;
            }
        }
        else if(mode == 3){
            background(204,102,0);
            am.render();
        }
    }

    @Override
    public void keyPressed(){
        if(mode == 1){
            ls.pressed();
        }
        else if(mode == 3) {
            if (key == 'a') {
                am.getPlayer().moveTo(Direction.LEFT);
            }

            if (key == 'd') {
                am.getPlayer().moveTo(Direction.RIGHT);
            }

            if (key == 'w') {
                am.getPlayer().moveTo(Direction.UP);
            }

            if (key == 's') {
                am.getPlayer().moveTo(Direction.DOWN);
            }
        }
    }



    //TODO, If key is released then check the next direction.
    @Override
    public void keyReleased(){
        if(mode == 3) {
            if (key == 'a' || key == 'd' || key == 's' || key == 'w') {
                if(!keyPressed){
                    am.getPlayer().stop();
                }
                else{
                    if(key == 'a'){
                        am.getPlayer().keyReleasedDirection(Direction.LEFT);
                    }
                    if(key == 'd'){
                        am.getPlayer().keyReleasedDirection(Direction.RIGHT);
                    }
                    if(key == 'w'){
                        am.getPlayer().keyReleasedDirection(Direction.UP);
                    }
                    if(key == 's'){
                        am.getPlayer().keyReleasedDirection(Direction.DOWN);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("main.Main");
    }
}

