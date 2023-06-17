package tiles;

import interface_package.Collideable;

/**
 * Represent a floor tile, a floor tile is always walkable.
 */
public class Floor extends Tiles {
    public Floor(float x, float y) {
        super(x, y, true);
    }

}
