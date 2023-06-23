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
    @Test
    void checkAgroAttackOfZombies(){
        Zombies zom = (Zombies) zombies;
        zom.setTo(50.0f,50.0f);
        assertTrue(zom.checkAgro(250,250));
        assertFalse(zom.checkAgro(400,400));
    }
    @Test
    void ZombiesHPTest(){
        Zombies zom = (Zombies) zombies;
        zom.setHealth(18);
        assertEquals(18,zom.getHealth());
        zom.subHP(5);
        assertEquals(13,zom.getHealth());
        zom.addHealth(2);
        assertEquals(15,zom.getHealth());
        zom.fallen();
        assertEquals(0,zom.getHealth());
    }
    @Test
    void ZombiesDirectionTest(){
        Zombies zom = (Zombies) zombies;
        /**
         * initial Zombies spawn point is 200,200
         */
        zom.setTo(200.0f,200.0f);
        Direction[] dirs = {Direction.UP,Direction.DOWN,Direction.LEFT,Direction.RIGHT};
        for(int i=0;i<=80;i++){
            Direction current=Direction.NONE;
            if(i<80)current = dirs[i/20];
            else current = dirs[i/20-1];
            if(i/20==0){
                zom.moveTo(current);
            }else if(i/20==1){
                if(i%20 ==0){
                    /**
                     * After Zombies moves UP for 20 ticks (20*3)<- 3 is the Zombies speed - 200 (starting position) = 140
                     */
                    zom.stop();
                    assertEquals(140.0f,zom.getY());
                }
                zom.moveTo(current);
            }else if(i/20 == 2){
                if(i%20 ==0){
                    /**
                     * After Zombies moves DOWN for 20 ticks (20*3)<- 3 is the Zombies speed + 140 (starting position) = 200
                     */
                    zom.stop();
                    assertEquals(200.0f,zom.getY());
                }
                zom.moveTo(current);
            }else if(i/20 == 3) {
                /**
                 * After Zombies moves LEFT for 20 ticks (20*3)<- 3 is the Zombies speed - 200 (starting position) = 140
                 */
                if(i%20 ==0){
                    zom.stop();
                    assertEquals(140.0f,zom.getX());
                }
                else zom.moveTo(current);
            }
            zom.moveFreely();
        }
        /**
         * End point, Zombies should be back to where it's original Place
         */
        zom.stop();
        assertEquals(200.0f, zom.getX());
    }
}