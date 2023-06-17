package tiles;

import interface_package.Collideable;
import interface_package.CollisionObserver;
import entities.Entity;

/**
 * Represent a floor tile that can damage the player, a Trap is always walkable but can damage anything that walk on it.
 */
public class Trap extends Floor implements CollisionObserver<Entity>, Collideable {
    private int damageDealt;
    public Trap(float x, float y, int damageDealt) {
        super(x, y);
        this.damageDealt = damageDealt;
    }

    @Override
    public void notifyCollision(Entity entity) {

    }

    @Override
    public boolean isColliding(Collideable other) {
        return false;
    }
}
