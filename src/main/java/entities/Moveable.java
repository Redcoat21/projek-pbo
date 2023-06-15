package entities;

/**
 * Represent any object that can be moved.
 */
public interface Moveable {
    /**
     * Move method for every movable object.
     * @param movingToward The direction that the object will be moved onto.
     */
    void move(Direction movingToward);
}
