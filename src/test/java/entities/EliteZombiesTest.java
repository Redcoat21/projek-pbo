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
    @Test
    void EliteZombiesDirectionTest(){
        EliteZombies elzom = (EliteZombies) eliteZombies;
        /**
         * initial Elite Zombies spawn point is 200,200
         */
        elzom.setTo(200.0f,200.0f);
        Direction[] dirs = {Direction.UP,Direction.DOWN,Direction.LEFT,Direction.RIGHT};
        for(int i=0;i<=80;i++){
            Direction current=Direction.NONE;
            if(i<80)current = dirs[i/20];
            else current = dirs[i/20-1];
            if(i/20==0){
                elzom.moveTo(current);
            }else if(i/20==1){
                if(i%20 ==0){
                    /**
                     * After Elite Zombies moves UP for 20 ticks (20*2)<- 2 is the Elite Zombies speed - 200 (starting position) = 160
                     */
                    elzom.stop();
                    assertEquals(160.0f,elzom.getY());
                }
                elzom.moveTo(current);
            }else if(i/20 == 2){
                if(i%20 ==0){
                    /**
                     * After Elite Zombies moves DOWN for 20 ticks (20*2)<- 2 is the Elite Zombies speed + 160 (starting position) = 200
                     */
                    elzom.stop();
                    assertEquals(200.0f,elzom.getY());
                }
                elzom.moveTo(current);
            }else if(i/20 == 3) {
                /**
                 * After Elite Zombies moves LEFT for 20 ticks (20*2)<- 2 is the Elite Zombies speed - 200 (starting position) = 160
                 */
                if(i%20 ==0){
                    elzom.stop();
                    assertEquals(160.0f,elzom.getX());
                }
                else elzom.moveTo(current);
            }
            elzom.moveFreely();
        }
        /**
         * End point, Elite Zombies should be back to where it's original Place
         */
        elzom.stop();
        assertEquals(200.0f, elzom.getX());
    }
}