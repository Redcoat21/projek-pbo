package entities.animation;

import entities.Direction;
import entities.Entity;
import entities.tiles.Tiles;

import main.Main;
import processing.core.PApplet;
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
     * @param entity The current entities that the animation will be played on.
     */
    public void play(Direction animationFor, Entity entity) {
        this.playAnimation(animationFor, entity.getSprites(), entity.getPosition());
    }

    private void playAnimation(Direction directionFor, Animation sprites, PVector position) {
        PApplet mainProgram = Main.getMainProgram();
        ArrayList<PImage> spriteAnimation = sprites.getSpritesList(directionFor);
        int frameDuration = sprites.getFrameDuration();

        if(spriteAnimation == null) {
            throw new RuntimeException("Sprites for this animation hasn't been set yet");
        }

        int frameIndex = mainProgram.frameCount / frameDuration;
        currentSpriteIndex = frameIndex % spriteAnimation.size();

        mainProgram.image(spriteAnimation.get(currentSpriteIndex), position.x, position.y);
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