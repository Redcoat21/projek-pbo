package main;

import entities.Entity;
import entities.TestPlayerEntity;

public abstract class GameMode {
    private Entity player;

    public GameMode() {
        this.player = new TestPlayerEntity(100, 100, 20, 20);
    }
    public TestPlayerEntity getPlayer() {
        return (TestPlayerEntity) this.player;
    }
}
