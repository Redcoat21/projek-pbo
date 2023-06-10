package entities.tiles;

import main.Main;

public class Hole extends Obstacles{
    public Hole(float x, float y) {
        super(x, y);
    }

    @Override
    public void render() {
        Main.processing.noStroke();
        Main.processing.fill(0);
        Main.processing.rect(getX(),getY(),getWidth(),getHeight());
    }
}
