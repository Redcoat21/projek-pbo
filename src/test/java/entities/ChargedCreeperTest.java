package entities;
import main.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChargedCreeperTest {
    private Entities chargedCreeper;
    private Main a = new Main();
    @BeforeEach
    public void createChargedCreeper() {
        Main app = new Main();
        app.initMain();
        ArcadeMode am = app.getAm();
        am.setChargedCreeper(new ChargedCreeper(20,20));
        chargedCreeper = am.getChargedCreeper();
    }
    @AfterEach
    public void cleanUp() {
        chargedCreeper = null;
    }
    @Test
    void PositionShouldBeTwentyAndTwentyTest() {
        assertEquals(20.0f, chargedCreeper.getX());
        assertEquals(20.0f, chargedCreeper.getY());
    }
    @Test
    void PlayerOutOfBoundTest(){
        ChargedCreeper you = (ChargedCreeper) chargedCreeper;
        assertEquals(20.0f,you.getY());
        you.moveTo(Direction.UP);
        you.moveFreely();
        assertEquals(80.0f,you.getY());
    }
}