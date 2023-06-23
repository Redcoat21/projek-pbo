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
    void checkAgroAttackOfEliteSkeletons(){
        EliteSkeletons elskelly = (EliteSkeletons) eliteSkeletons;
        elskelly.setTo(50.0f,50.0f);
        assertTrue(elskelly.checkAgro(250,250));
        assertFalse(elskelly.checkAgro(400,400));
    }
    @Test
    void EliteSkeletonsHPTest(){
        EliteSkeletons elskelly = (EliteSkeletons) eliteSkeletons;
        elskelly.setHealth(15);
        assertEquals(15,elskelly.getHealth());
        elskelly.subHP(8);
        assertEquals(7,elskelly.getHealth());
        elskelly.addHealth(4);
        assertEquals(11,elskelly.getHealth());
        elskelly.fallen();
        assertEquals(0,elskelly.getHealth());
    }
    @Test
    void EliteSkeletonsDirectionTest(){
        EliteSkeletons elskelly = (EliteSkeletons) eliteSkeletons;
        /**
         * initial Elite Skeletons spawn point is 200,200
         */
        elskelly.setTo(200.0f,200.0f);
        Direction[] dirs = {Direction.UP,Direction.DOWN,Direction.LEFT,Direction.RIGHT};
        for(int i=0;i<=80;i++){
            Direction current=Direction.NONE;
            if(i<80)current = dirs[i/20];
            else current = dirs[i/20-1];
            if(i/20==0){
                elskelly.moveTo(current);
            }else if(i/20==1){
                if(i%20 ==0){
                    /**
                     * After Elite Skeletons moves UP for 20 ticks (20*3)<- 3 is the Elite Skeletons speed - 200 (starting position) = 140
                     */
                    elskelly.stop();
                    assertEquals(140.0f,elskelly.getY());
                }
                elskelly.moveTo(current);
            }else if(i/20 == 2){
                if(i%20 ==0){
                    /**
                     * After Elite Skeletons moves DOWN for 20 ticks (20*3)<- 3 is the Elite Skeletons speed + 140 (starting position) = 200
                     */
                    elskelly.stop();
                    assertEquals(200.0f,elskelly.getY());
                }
                elskelly.moveTo(current);
            }else if(i/20 == 3) {
                /**
                 * After Elite Skeletons moves LEFT for 20 ticks (20*3)<- 3 is the Elite Skeletons speed - 200 (starting position) = 140
                 */
                if(i%20 ==0){
                    elskelly.stop();
                    assertEquals(140.0f,elskelly.getX());
                }
                else elskelly.moveTo(current);
            }
            elskelly.moveFreely();
        }
        /**
         * End point, Elite Skeletons should be back to where it's original Place
         */
        elskelly.stop();
        assertEquals(200.0f, elskelly.getX());
    }
}