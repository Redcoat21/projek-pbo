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
    public void createSkeletons() {
        Main app = new Main();
        app.initMain();
        ArcadeMode am = app.getAm();
        am.setSkeletons(new Skeletons(20,20));
        skeleton = am.getSkeletons();
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
    @Test
    void SkeletonsOutOfBoundTest(){
        Skeletons skelly = (Skeletons) skeleton;
        assertEquals(20.0f,skelly.getY());
        skelly.moveTo(Direction.UP);
        skelly.moveFreely();
        assertEquals(80.0f,skelly.getY());
    }
    @Test
    void resetPositionTest(){
        Skeletons skelly = (Skeletons) skeleton;
        skeleton.setTo(200.0f,200.0f);
        assertEquals(200.0f,skelly.getY());
        skelly.resetPos();
        assertEquals(20.0f,skelly.getY());
    }
    @Test
    void checkAgroAttackOfSkeletons(){
        Skeletons skelly = (Skeletons) skeleton;
        skeleton.setTo(50.0f,50.0f);
        assertTrue(skelly.checkAgro(250,250));
        assertFalse(skelly.checkAgro(400,400));
    }
    @Test
    void SkeletonsHPTest(){
        Skeletons skelly = (Skeletons) skeleton;
        skelly.setHealth(12);
        assertEquals(12,skelly.getHealth());
        skelly.subHP(9);
        assertEquals(3,skelly.getHealth());
        skelly.addHealth(4);
        assertEquals(7,skelly.getHealth());
        skelly.fallen();
        assertEquals(0,skelly.getHealth());
    }
}