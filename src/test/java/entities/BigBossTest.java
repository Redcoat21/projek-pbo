package entities;
import main.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class BigBossTest {
    private Entities BigBoss;
    private Main a = new Main();
    @BeforeEach
    public void createSkeletons() {
        Main app = new Main();
        app.initMain();
        ArcadeMode am = app.getAm();
        am.setBigBoss(new BigBoss(20,20));
        BigBoss = am.getBigBoss();
    }
    @AfterEach
    public void cleanUp() {
        BigBoss = null;
    }
    @Test
    void PositionShouldBeTwentyAndTwentyTest() {
        assertEquals(20.0f, BigBoss.getX());
        assertEquals(20.0f, BigBoss.getY());
    }
}