package main;

import entities.Player;
import processing.core.PApplet;
public class Main extends PApplet{
    Player player;
    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void setup() {
        background(100);
        player = new Player(width/10, height/2);
        processing = this;
    }

    @Override
    public void draw() {
        background(100);
        player.render();
        player.move();
    }

    public void keyPressed(){
        if(key == 'a'){
            player.moveLeft();
        }

        if(key == 'd'){
            player.moveRight();
        }

        if(key == 'w'){
            player.moveUp();
        }

        if(key == 's'){
            player.moveDown();
        }
    }

    public void keyReleased(){
        if(key == 'a'){
            player.stopLeft();
        }

        if(key == 'd'){
            player.stopRight();
        }

        if(key == 'w'){
            player.stopUp();
        }

        if(key == 's'){
            player.stopDown();
        }
    }

    public static void main(String[] args) {
        PApplet.main("main.Main");
    }

    public static PApplet processing;
}

