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
    public void createZombies() {
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
    @Test
    void ZombiesOutOfBoundTest(){
        Zombies zom = (Zombies) zombies;
        assertEquals(20.0f,zom.getY());
        zom.moveTo(Direction.UP);
        zom.moveFreely();
        assertEquals(80.0f,zom.getY());
    }
    @Test
    void resetPositionTest(){
        Zombies zom = (Zombies) zombies;
        zombies.setTo(200.0f,200.0f);
        assertEquals(200.0f,zom.getY());
        zom.resetPos();
        assertEquals(20.0f,zom.getY());
    }

}