package entities;

/**
 * Represents anything that can be collided and interacted with.
 */
public interface Collideable {
    boolean isColliding(Collideable other);
}
