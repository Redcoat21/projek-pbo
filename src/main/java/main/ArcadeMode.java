package main;

import entities.Player;
import processing.core.PConstants;

public class ArcadeMode {
    Player player;
    int x;
    int y;
    public ArcadeMode(int x, int y){
        player = new Player(x/10,y/2);
        this.x = x;
        this.y = y;
    }

    public void render() {
        Main.processing.noStroke();
        Main.processing.fill(102, 51, 0);
        Main.processing.rect(0,0,x,80);
        Main.processing.fill(255);
        Main.processing.textSize(14);
        Main.processing.textAlign(PConstants.RIGHT);
        Main.processing.text("fps " + (int) Main.processing.frameRate, x, 14);
        player.render();
        player.move();
    }

    public Player getPlayer() {
        return player;
    }
}
