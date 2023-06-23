package entities;
import main.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EliteSkeletonsTest {
    private Entities eliteSkeletons;
    private Main a = new Main();
    @BeforeEach
    public void createEliteSkeletons() {
        Main app = new Main();
        app.initMain();
        ArcadeMode am = app.getAm();
        am.setEliteSkeletons(new EliteSkeletons(20,20));
        eliteSkeletons = am.getEliteSkeletons();
    }
    @AfterEach
    public void cleanUp() {
        eliteSkeletons = null;
    }
    @Test
    void PositionShouldBeTwentyAndTwentyTest() {
        assertEquals(20.0f, eliteSkeletons.getX());
        assertEquals(20.0f, eliteSkeletons.getY());
    }
    @Test
    void EliteSkeletonsOutOfBoundTest(){
        EliteSkeletons elskelly = (EliteSkeletons) eliteSkeletons;
        assertEquals(20.0f,elskelly.getY());
        elskelly.moveTo(Direction.UP);
        elskelly.moveFreely();
        assertEquals(80.0f,elskelly.getY());
    }
    @Test
    void resetPositionTest(){
        EliteSkeletons elskelly = (EliteSkeletons) eliteSkeletons;
        elskelly.setTo(200.0f,200.0f);
        assertEquals(200.0f,elskelly.getY());
        elskelly.resetPos();
        assertEquals(20.0f,elskelly.getY());
    }
    @Test
    void checkAgroAttackOfSkeletons(){
        EliteSkeletons elskelly = (EliteSkeletons) eliteSkeletons;
        elskelly.setTo(50.0f,50.0f);
        assertTrue(elskelly.checkAgro(250,250));
        assertFalse(elskelly.checkAgro(400,400));
    }
}