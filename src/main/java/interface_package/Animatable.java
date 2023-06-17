package interface_package;

import entities.Direction;
import processing.core.PImage;

public interface Animatable {
    void addSprite(String forWhichAnimation, Direction animationFor, PImage sprite);
}
