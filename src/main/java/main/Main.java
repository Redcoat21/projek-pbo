package main;

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

    public void keyPressed(){
        if(mode == 1){
            ls.pressed();
        }
        else if(mode == 3) {
            if (key == 'a') {
                am.getPlayer().moveLeft();
            }

            if (key == 'd') {
                am.getPlayer().moveRight();
            }

            if (key == 'w') {
                am.getPlayer().moveUp();
            }

            if (key == 's') {
                am.getPlayer().moveDown();
            }
        }
    }

    public void keyReleased(){
        if(mode == 3) {
            if (key == 'a') {
                am.getPlayer().stopLeft();
            }

            if (key == 'd') {
                am.getPlayer().stopRight();
            }

            if (key == 'w') {
                am.getPlayer().stopUp();
            }

            if (key == 's') {
                am.getPlayer().stopDown();
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("main.Main");
    }

    public static PApplet processing;
}

