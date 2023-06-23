package entities;
import main.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SkeletonsTest {
    private Entities skeletons;
    private Main a = new Main();
    @BeforeEach
    public void createSkeletons() {
        Main app = new Main();
        app.initMain();
        ArcadeMode am = app.getAm();
        am.setSkeletons(new Skeletons(20,20));
        skeletons = am.getSkeletons();
    }
    @AfterEach
    public void cleanUp() {
        skeletons = null;
    }
    @Test
    void PositionShouldBeTwentyAndTwentyTest() {
        assertEquals(20.0f, skeletons.getX());
        assertEquals(20.0f, skeletons.getY());
    }
    @Test
    void SkeletonsOutOfBoundTest(){
        Skeletons skelly = (Skeletons) skeletons;
        assertEquals(20.0f,skelly.getY());
        skelly.moveTo(Direction.UP);
        skelly.moveFreely();
        assertEquals(80.0f,skelly.getY());
    }
    @Test
    void resetPositionTest(){
        Skeletons skelly = (Skeletons) skeletons;
        skeletons.setTo(200.0f,200.0f);
        assertEquals(200.0f,skelly.getY());
        skelly.resetPos();
        assertEquals(20.0f,skelly.getY());
    }
    @Test
    void checkAgroAttackOfSkeletons(){
        Skeletons skelly = (Skeletons) skeletons;
        skeletons.setTo(50.0f,50.0f);
        assertTrue(skelly.checkAgro(250,250));
        assertFalse(skelly.checkAgro(400,400));
    }
    @Test
    void SkeletonsHPTest(){
        Skeletons skelly = (Skeletons) skeletons;
        skelly.setHealth(12);
        assertEquals(12,skelly.getHealth());
        skelly.subHP(9);
        assertEquals(3,skelly.getHealth());
        skelly.addHealth(4);
        assertEquals(7,skelly.getHealth());
        skelly.fallen();
        assertEquals(0,skelly.getHealth());
    }
    @Test
    void SkeletonsDirectionTest(){
        Skeletons skelly = (Skeletons) skeletons;
        /**
         * initial Skeletons spawn point is 200,200
         */
        skelly.setTo(200.0f,200.0f);
        Direction[] dirs = {Direction.UP,Direction.DOWN,Direction.LEFT,Direction.RIGHT};
        for(int i=0;i<=80;i++){
            Direction current=Direction.NONE;
            if(i<80)current = dirs[i/20];
            else current = dirs[i/20-1];
            if(i/20==0){
                skelly.moveTo(current);
            }else if(i/20==1){
                if(i%20 ==0){
                    /**
                     * After Skeletons moves UP for 20 ticks (20*3)<- 3 is the Skeletons speed - 200 (starting position) = 140
                     */
                    skelly.stop();
                    assertEquals(140.0f,skelly.getY());
                }
                skelly.moveTo(current);
            }else if(i/20 == 2){
                if(i%20 ==0){
                    /**
                     * After Skeletons moves DOWN for 20 ticks (20*3)<- 3 is the Skeletons speed + 140 (starting position) = 200
                     */
                    skelly.stop();
                    assertEquals(200.0f,skelly.getY());
                }
                skelly.moveTo(current);
            }else if(i/20 == 3) {
                /**
                 * After Skeletons moves LEFT for 20 ticks (20*3)<- 3 is the Skeletons speed - 200 (starting position) = 140
                 */
                if(i%20 ==0){
                    skelly.stop();
                    assertEquals(140.0f,skelly.getX());
                }
                else skelly.moveTo(current);
            }
            skelly.moveFreely();
        }
        /**
         * End point, Skeletons should be back to where it's original Place
         */
        skelly.stop();
        assertEquals(200.0f, skelly.getX());
    }
}