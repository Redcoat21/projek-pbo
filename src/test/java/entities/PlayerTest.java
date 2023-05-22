package entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Entities player;
    @BeforeEach
    public void createPlayer() {
        player = new Player(20.0f, 20.0f);
    }

    @AfterEach
    public void cleanUp() {
        player = null;
    }

    @Test
    void PositionShouldBeTwentyAndTwenty() {
        assertEquals(20.0f, player.getX());
        assertEquals(20.0f, player.getY());
    }

}