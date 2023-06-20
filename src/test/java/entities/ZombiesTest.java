package entities;
import main.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ZombiesTest {
    private Entities zombies;
    private Main a = new Main();
    @BeforeEach
    public void createPlayer() {
        Main app = new Main();
        app.initMain();
        ArcadeMode am = app.getAm();
        am.setZombies(new Zombies(20,20));
        zombies = am.getZombies();
    }
    @AfterEach
    public void cleanUp() {
        zombies = null;
    }
    @Test
    void PositionShouldBeTwentyAndTwentyTest() {
        assertEquals(20.0f, zombies.getX());
        assertEquals(20.0f, zombies.getY());
    }

}