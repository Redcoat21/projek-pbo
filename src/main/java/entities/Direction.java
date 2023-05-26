package entities;

import processing.core.PVector;

public enum Direction {
    UP(0.0f, -1.0f),
    RIGHT(1.0f, 0.0f),
    DOWN(0.0f, 1.0f),
    LEFT(-1.0f, 0.0f),
    NONE(0.0f, 0.0f);
    private final PVector direction;

    Direction(float x, float y) {
        this.direction = new PVector(x, y);
    }

    public PVector getDirection() {
        return direction;
    }
}
