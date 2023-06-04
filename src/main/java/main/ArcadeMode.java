package main;

import entities.Player;
import processing.core.PConstants;

public class ArcadeMode {
    Player player;
    int x;
    int y;
    int floor;
    Map map;

    public ArcadeMode(int x, int y){
        player = new Player(0,15.5f*20+80);
        player.addSprite(
                Main.processing.loadImage("data/tile007.png")
        );
        this.x = x;
        this.y = y;
        floor = 5;
        map = new Map(floor);
    }

    public void render() {
        Main.processing.noStroke();
        Main.processing.fill(102, 51, 0);
        Main.processing.rect(0,0,x,80);
        Main.processing.fill(255);
        Main.processing.textSize(14);
        Main.processing.textAlign(PConstants.RIGHT);
        Main.processing.text("fps " + (int) Main.processing.frameRate, x, 14);
        Main.processing.textAlign(PConstants.LEFT);
        Main.processing.text("Heart " + player.getHealth(), 10, 14);
        map.printMap();
        player.render();
        player.move();
    }

    public Player getPlayer() {
        return player;
    }
}
