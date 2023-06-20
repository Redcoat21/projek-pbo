package entities;
import main.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EliteZombiesTest {
    private Entities eliteZombies;
    private Main a = new Main();
    @BeforeEach
    public void createEliteZombies() {
        Main app = new Main();
        app.initMain();
        ArcadeMode am = app.getAm();
        am.setEliteZombies(new EliteZombies(20,20));
        eliteZombies = am.getEliteZombies();
    }
    @AfterEach
    public void cleanUp() {
        eliteZombies = null;
    }
    @Test
    void PositionShouldBeTwentyAndTwentyTest() {
        assertEquals(20.0f, eliteZombies.getX());
        assertEquals(20.0f, eliteZombies.getY());
    }

}