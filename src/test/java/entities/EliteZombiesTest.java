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
    @Test
    void EliteZombiesOutOfBoundTest(){
        EliteZombies elzom = (EliteZombies) eliteZombies;
        assertEquals(20.0f,elzom.getY());
        elzom.moveTo(Direction.UP);
        elzom.moveFreely();
        assertEquals(80.0f,elzom.getY());
    }
    @Test
    void resetPositionTest(){
        EliteZombies elzom = (EliteZombies) eliteZombies;
        elzom.setTo(200.0f,200.0f);
        assertEquals(200.0f,elzom.getY());
        elzom.resetPos();
        assertEquals(20.0f,elzom.getY());
    }
    @Test
    void checkAgroAttackOfEliteZombies(){
        EliteZombies elzom = (EliteZombies) eliteZombies;
        elzom.setTo(50.0f,50.0f);
        assertTrue(elzom.checkAgro(250,250));
        assertFalse(elzom.checkAgro(400,400));
    }
    @Test
    void EliteZombiesHPTest(){
        EliteZombies elzom = (EliteZombies) eliteZombies;
        elzom.setHealth(22);
        assertEquals(22,elzom.getHealth());
        elzom.subHP(10);
        assertEquals(12,elzom.getHealth());
        elzom.addHealth(7);
        assertEquals(19,elzom.getHealth());
        elzom.fallen();
        assertEquals(0,elzom.getHealth());
    }
}