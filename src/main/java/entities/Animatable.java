package entities;

import processing.core.PImage;

public interface Animatable {
    void addSprite(Direction animationFor, PImage sprite);
    void playAnimation(Direction animationFor);
}
