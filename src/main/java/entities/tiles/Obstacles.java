package entities.tiles;

import entities.Entities;
import main.Main;

public class Obstacles extends Entities{
    public Obstacles(float x, float y) {
        super(x, y, 20, 20);
        this.addSprites(
                Main.processing.loadImage("src/main/resources/assets/Tileset/tile003.png")
                , this.getSize()
        );
    }

    @Override
    public void render() {
        this.getSprites().play(this);
    }
}
