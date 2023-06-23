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
    void ChargedCreeperOutOfBoundTest(){
        ChargedCreeper cc = (ChargedCreeper) chargedCreeper;
        assertEquals(20.0f,cc.getY());
        cc.moveTo(Direction.UP);
        cc.moveFreely();
        assertEquals(80.0f,cc.getY());
    }
    @Test
    void resetPositionTest(){
        ChargedCreeper cc = (ChargedCreeper) chargedCreeper;
        cc.setTo(200.0f,200.0f);
        assertEquals(200.0f,cc.getY());
        cc.resetPos();
        assertEquals(20.0f,cc.getY());
    }
    @Test
    void checkAgroAttackOfChargedCreeper(){
        ChargedCreeper cc = (ChargedCreeper) chargedCreeper;
        cc.setTo(50.0f,50.0f);
        assertTrue(cc.checkAgro(100,100));
        assertFalse(cc.checkAgro(400,400));
    }
    @Test
    void ChargedCreeperHPTest(){
        ChargedCreeper cc = (ChargedCreeper) chargedCreeper;
        cc.setHealth(4);
        assertEquals(4,cc.getHealth());
        cc.subHP(3);
        assertEquals(1,cc.getHealth());
        cc.addHealth(1);
        assertEquals(2,cc.getHealth());
        cc.fallen();
        assertEquals(0,cc.getHealth());
    }
}