package tiles;

import entities.Entities;
import main.Main;

public class Obstacles extends Entities{
    public Obstacles(float x, float y) {
        super(x, y, 20, 20, 9999, 0);
    }

    @Override
    public void render() {
        Main.processing.stroke(0);
        Main.processing.rect(getX(),getY(),getWidth(),getHeight());
    }
}
