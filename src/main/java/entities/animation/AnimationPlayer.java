package entities.animation;

import entities.Direction;
import entities.Entities;
import entities.tiles.Tiles;
import main.Main;
import processing.core.PImage;

import java.util.ArrayList;

public class AnimationPlayer {
    public static void play(Direction animationFor, Entities entities) {

    }

    public static void play(Tiles tiles) {
        ArrayList<PImage> spriteAnimation = tiles.getSprites().getSpritesList(Direction.NONE);
        if(spriteAnimation == null) {
            throw new RuntimeException("Sprites for this animation hasn't been set yet");
        }

        for(PImage sprite : spriteAnimation) {
            Main.processing.image(sprite, tiles.getPosition().x, tiles.getPosition().y);
        }
    }
}
