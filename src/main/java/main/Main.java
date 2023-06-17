package main;

import entities.Player;
import processing.core.PApplet;

/**
 * Main entry to the program.
 */
public class Main extends PApplet {
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
        background(100);
        processing = this;
        this.frameRate(60);
        this.player = new Player(100, 100, 100, 100);
    }

    @Override
    public void draw() {
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
