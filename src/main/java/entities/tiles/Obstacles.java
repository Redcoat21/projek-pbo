package entities.tiles;

import entities.Direction;
import entities.Entities;
import main.Main;
import processing.core.PImage;

public class Obstacles extends Entities{
    public Obstacles(float x, float y) {
        super(x, y, 20, 20);
    }
    @Override
    public void render() {
        this.play(Direction.NONE);
    }
}
