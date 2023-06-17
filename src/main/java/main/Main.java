package main;

import entities.Player;
import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * Main entry to the program.
 */
public class Main extends PApplet {
    private PGraphics temp;
    enum Mode {
        ARCADE,
        ENDLESS
    }
    private GameMode gameMode;
    private static PApplet processing;
    private Player player;
    @Override
    public void settings() {
        size(1280, 720);
    }
    @Override
    public void setup() {
        temp = this.createGraphics(this.displayWidth, this.displayHeight);
        temp.beginDraw();
        temp.noFill();
        temp.stroke(0, 0, 255);
        temp.ellipse(100, 100, 180, 180);
        temp.ellipse(70, 70, 10, 10);
        temp.ellipse(130, 70, 10, 10);
        temp.ellipse(100, 100, 10, 10);
        temp.line(70, 130, 130, 130);
        temp.endDraw();
        processing = this;
        this.frameRate(60);
        this.player = new Player(100, 100, 100, 100);
    }

    @Override
    public void draw() {
        background(100);
        this.player.render();
    }

    @Override
    public void keyPressed() {
        this.player.move();
    }

    /**
     * Get the current reference of this program to be used in other class.
     * @return The reference to this program.
     */
    public static PApplet getMainProgram() {
        return processing;
    }

    public static void main(String[] args) {
        PApplet.main("main.Main");
    }
}
