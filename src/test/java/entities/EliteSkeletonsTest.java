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
    void PlayerOutOfBoundTest(){
        EliteSkeletons you = (EliteSkeletons) eliteSkeletons;
        assertEquals(20.0f,you.getY());
        you.moveTo(Direction.UP);
        you.moveFreely();
        assertEquals(80.0f,you.getY());
    }

}