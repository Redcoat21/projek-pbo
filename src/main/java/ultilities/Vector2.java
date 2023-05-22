package ultilities;

public class Vector2 {
    private float x;
    private float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x position.
     * @return The x position.
     */
    public float getX() {
        return x;
    }

    /**
     * Get the y position/
     * @return The y position.
     */

    public float getY() {
        return y;
    }

    /**
     * Teleport to the given (x,y) location.
     * @param x X position to teleport to.
     * @param y Y position to teleport to.
     */
    public void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Move by the given (x,y) unit.
     * @param x Move by x unit.
     * @param y Move by y unit.
     */

    public void moveBy(float x, float y) {
        this.x += x;
        this.y += y;
    }
}
