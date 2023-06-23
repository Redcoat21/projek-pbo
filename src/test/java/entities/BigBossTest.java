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
    @Test
    void checkAgroAttackOfBigBoss(){
        BigBoss bb = (BigBoss) bigBoss;
        bb.setTo(50.0f,50.0f);
        assertTrue(bb.checkAgro(100,100));
        assertFalse(bb.checkAgro(1000,1000));
    }
    @Test
    void BigBossHPTest(){
        BigBoss bb = (BigBoss) bigBoss;
        bb.setHealth(200);
        assertEquals(200,bb.getHealth());
        bb.subHP(139);
        assertEquals(61,bb.getHealth());
        bb.addHealth(45);
        assertEquals(106,bb.getHealth());
        bb.fallen();
        assertEquals(0,bb.getHealth());
    }
    @Test
    void BigBossDirectionTest(){
        BigBoss bb = (BigBoss) bigBoss;
        /**
         * initial Big Boss spawn point is 200,200
         */
        bb.setTo(200.0f,200.0f);
        Direction[] dirs = {Direction.UP,Direction.DOWN,Direction.LEFT,Direction.RIGHT};
        for(int i=0;i<=80;i++){
            Direction current=Direction.NONE;
            if(i<80)current = dirs[i/20];
            else current = dirs[i/20-1];
            if(i/20==0){
                bb.moveTo(current);
            }else if(i/20==1){
                if(i%20 ==0){
                    /**
                     * After Big Boss moves UP for 20 ticks (20*2)<- 2 is the Big Boss speed - 200 (starting position) = 160
                     */
                    bb.stop();
                    assertEquals(160.0f,bb.getY());
                }
                bb.moveTo(current);
            }else if(i/20 == 2){
                if(i%20 ==0){
                    /**
                     * After Big Boss moves DOWN for 20 ticks (20*2)<- 2 is the Big Boss speed + 160 (starting position) = 200
                     */
                    bb.stop();
                    assertEquals(200.0f,bb.getY());
                }
                bb.moveTo(current);
            }else if(i/20 == 3) {
                /**
                 * After Big Boss moves LEFT for 20 ticks (20*2)<- 2 is the Big Boss speed - 200 (starting position) = 160
                 */
                if(i%20 ==0){
                    bb.stop();
                    assertEquals(160.0f,bb.getX());
                }
                else bb.moveTo(current);
            }
            bb.moveFreely();
        }
        /**
         * End point, Big Boss should be back to where it's original Place
         */
        bb.stop();
        assertEquals(200.0f, bb.getX());
    }
}