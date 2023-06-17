package entities.enemies;

import entities.Entity;
public abstract class Enemy extends Entity {
    private State state;
    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * Determine whether this enemy is attacking a player.
     * @return True if this enemy is attacking a player, false otherwise.
     */
    public boolean isAttacking() {
        return this.state == State.ATTACKING;
    }

    /**
     * Determine whether this enemy is in a specific state.
     * @param state The state to check.
     * @return True if this enemy is in the specified state, false otherwise.
     */
    public boolean isState(State state) {
        return this.state == state;
    }
}
