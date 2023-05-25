package Tiles;

import main.Main;

public class Wall extends Obstacles {
    public Wall(float x, float y) {
        super(x, y);
    }

    @Override
    public void render() {
        Main.processing.stroke(0);
        Main.processing.fill(152);
        Main.processing.rect(getX(),getY(),getWidth(),getHeight());
    }
}
