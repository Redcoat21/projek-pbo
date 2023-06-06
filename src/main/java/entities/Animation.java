package entities;

import main.Main;
import processing.core.PImage;

import java.util.ArrayList;

public class Animation {
    private ArrayList<PImage> sprites;

    public Animation() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Add sprites into the sprites list.
     * @param sprite The image / sprite to be added.
     */
    public void addSprite(PImage sprite) {
        this.sprites.add(sprite);
    }

    /**
     * Add sprites into the sprites list with option to resize the image.
     * @param sprite The image / sprite to be added.
     * @param width The new width of the sprite.
     * @param height The new height of the sprite.
     */
    public void addSprite(PImage sprite, float width, float height) {
        PImage temp = sprite;
        temp.resize((int) width, (int) height);
        this.sprites.add(temp);
    }

    /**
     * Return the amount of sprites in this animation.
     * @return The sprites in this animation.
     */
    public int getSpriteCount() {
        return this.sprites.size();
    }

    public void play(Entities entities) {
        for(PImage image : this.sprites) {
            Main.processing.image(image, entities.getX(), entities.getY());
        }
    }
}