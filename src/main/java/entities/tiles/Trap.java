package entities.tiles;

import entities.Collideable;
import entities.CollisionObserver;
import entities.Entities;

/**
 * Represent a floor tile that can damage the player, a Trap is always walkable but can damage anything that walk on it.
 */
public class Trap extends Floor implements CollisionObserver<Entities>, Collideable {
    private int damageDealt;
    public Trap(float x, float y, int damageDealt) {
        super(x, y);
        this.damageDealt = damageDealt;
    }

    @Override
    public void notifyCollision(Entities entities) {

    }


}
