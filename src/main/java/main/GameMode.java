package main;

import entities.Entity;
import entities.Player;

public abstract class GameMode {
    private Entity player;

    public GameMode() {
        this.player = new Player(100, 100, 20, 20);
    }

    /**
     * Get the references to the player on this game mode.
     * @return The references to the player.
     */
    public Player getPlayer() {
        return (Player) this.player;
    }

}
