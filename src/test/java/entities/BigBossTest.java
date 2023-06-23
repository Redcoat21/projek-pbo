package entities;
import main.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class BigBossTest {
    private Entities bigBoss;
    private Main a = new Main();
    @BeforeEach
    public void createBigBoss() {
        Main app = new Main();
        app.initMain();
        ArcadeMode am = app.getAm();
        am.setBigBoss(new BigBoss(20,20));
        bigBoss = am.getBigBoss();
    }
    @AfterEach
    public void cleanUp() {
        bigBoss = null;
    }
    @Test
    void PositionShouldBeTwentyAndTwentyTest() {
        assertEquals(20.0f, bigBoss.getX());
        assertEquals(20.0f, bigBoss.getY());
    }
    @Test
    void BigBossOutOfBoundTest(){
        BigBoss bb = (BigBoss) bigBoss;
        assertEquals(20.0f,bb.getY());
        bb.moveTo(Direction.UP);
        bb.moveFreely();
        assertEquals(80.0f,bb.getY());
    }
    @Test
    void resetPositionTest(){
        BigBoss bb = (BigBoss) bigBoss;
        bb.setTo(200.0f,200.0f);
        assertEquals(200.0f,bb.getY());
        bb.resetPos();
        assertEquals(20.0f,bb.getY());
    }
}