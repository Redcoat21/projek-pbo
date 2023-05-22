package ultilities;

public class Vector2 {
    private int x;
    private int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x position.
     * @return The x position.
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y position/
     * @return The y position.
     */

    public int getY() {
        return y;
    }

    /**
     * Teleport to the given (x,y) location.
     * @param x X position to teleport to.
     * @param y Y position to teleport to.
     */
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Move by the given (x,y) unit.
     * @param x Move by x unit.
     * @param y Move by y unit.
     */

    public void moveBy(int x, int y) {
        this.x += x;
        this.y += y;
    }
}
