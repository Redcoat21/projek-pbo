package entities;
import main.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SkeletonsTest {
    private Entities skeleton;
    private Main a = new Main();
    @BeforeEach
    public void createPlayer() {
        Main app = new Main();
        app.initMain();
        ArcadeMode am = app.getAm();
        am.setZombies(new Zombies(20,20));
        skeleton = am.getZombies();
    }
    @AfterEach
    public void cleanUp() {
        skeleton = null;
    }
    @Test
    void PositionShouldBeTwentyAndTwentyTest() {
        assertEquals(20.0f, skeleton.getX());
        assertEquals(20.0f, skeleton.getY());
    }

}