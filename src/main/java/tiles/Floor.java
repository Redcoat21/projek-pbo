package tiles;

import entities.Collideable;

/**
 * Represent a floor tile, a floor tile is always walkable.
 */
public class Floor extends Tiles implements Collideable {
    public Floor(float x, float y) {
        super(x, y, true);
    }

    @Override
    public boolean isColliding(Collideable other) {
        return false;
    }
}
