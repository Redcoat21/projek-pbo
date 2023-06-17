package interface_package;

import entities.Entity;

/**
 * Represent any object that can attack.
 * Note to register damage properly, the attacked object must have a collision. see {@link Collideable}
 */
@FunctionalInterface
public interface Combatant {
    /**
     * Attack method for every combatant object.
     * @param target The target that will be attacked. Note that it must have collision.
     */
    void attack(Collideable target);
}
