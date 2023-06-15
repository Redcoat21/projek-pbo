package entities.animation;

import entities.Animatable;
import entities.Direction;
import entities.Entities;
import entities.tiles.Tiles;
import main.Main;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * A static class that is used to play the animation.
 */
public class AnimationPlayer {
    private int currentSpriteIndex;

    /**
     * Play animation based on a given direction parameter.
     * @param animationFor Which animation should be played.
     * @param entities The current entities that the animation will be played on.
     */
    public void play(Direction animationFor, Entities entities) {
        this.playAnimation(animationFor, entities.getSprites(), entities.getPosition());
    }

    private void playAnimation(Direction directionFor, Animation sprites, PVector position) {
        ArrayList<PImage> spriteAnimation = sprites.getSpritesList(directionFor);
        int frameDuration = sprites.getFrameDuration();

        if(spriteAnimation == null) {
            throw new RuntimeException("Sprites for this animation hasn't been set yet");
        }

        int frameIndex = Main.processing.frameCount / frameDuration;
        currentSpriteIndex = frameIndex % spriteAnimation.size();

        Main.processing.image(spriteAnimation.get(currentSpriteIndex), position.x, position.y);
    }

    /**
     * Play animation, note that {@link Tiles} object always only have 1 direction, so {@link  Direction} parameter is unnecessary.
     * @param tiles The tiles that the animation will be played on.
     */

    public void play(Tiles tiles) {
        ArrayList<PImage> spriteAnimation = tiles.getSprites().getSpritesList(Direction.NONE);
        this.playAnimation(Direction.NONE, tiles.getSprites(), tiles.getPosition());
    }
}
