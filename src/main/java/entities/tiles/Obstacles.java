package entities.tiles;

import entities.Direction;
import entities.Entities;
import main.Main;
import processing.core.PImage;

public class Obstacles extends Entities{
    public Obstacles(float x, float y) {
        super(x, y, 20, 20);
        PImage temp = Main.processing.loadImage("src/main/resources/assets/Tileset/tile003.png");
        this.addSprites("idle", Direction.NONE, temp, this.getSize());
    }

    @Override
    public void render() {
        this.play("idle", Direction.NONE);
    }
}
