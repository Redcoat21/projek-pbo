package interface_package;

import entities.Direction;

/**
 * Represent any object that can be moved.
 */
@FunctionalInterface
public interface Moveable {
    /**
     * Move method for every movable object.
     * @param movingToward The direction that the object will be moved onto.
     */
    void move(Direction movingToward);
}
