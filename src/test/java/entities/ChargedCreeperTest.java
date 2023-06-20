package entities;
import main.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChargedCreeperTest {
    private Entities ChargedCreeper;
    private Main a = new Main();
    @BeforeEach
    public void createSkeletons() {
        Main app = new Main();
        app.initMain();
        ArcadeMode am = app.getAm();
        am.setChargedCreeper(new ChargedCreeper(20,20));
        ChargedCreeper = am.getChargedCreeper();
    }
    @AfterEach
    public void cleanUp() {
        ChargedCreeper = null;
    }
    @Test
    void PositionShouldBeTwentyAndTwentyTest() {
        assertEquals(20.0f, ChargedCreeper.getX());
        assertEquals(20.0f, ChargedCreeper.getY());
    }

}