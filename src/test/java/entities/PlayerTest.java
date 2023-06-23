package entities;

import main.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weapon.Ranged;
import weapon.RangedType;
import weapon.SpearType;
import weapon.SwordType;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Entities player;
    @BeforeEach
    public void createPlayer() {
        Main app = new Main();
        app.initMain();
        ArcadeMode am = app.getAm();
        am.setPlayer(new Player(20,20));
        player = am.getPlayer();
    }

    @AfterEach
    public void cleanUp() {
        player = null;
    }

    @Test
    public void weaponCreationTest() {
        Player temp = (Player) player;
        temp.setWeapon(temp.createWeapon("sword", SwordType.IRON_SWORD, 1));

        assertEquals(temp.createWeapon("sword", SwordType.IRON_SWORD, 1), temp.getWeapon());
        assertNotEquals(temp.createWeapon("spear", SpearType.SPEAR_OF_THE_LORD, 1), temp.getWeapon());
        assertNotEquals(temp.createWeapon("ranged", RangedType.WOOD_BOW, 1), temp.getWeapon());

        assertThrows(IllegalArgumentException.class, () -> temp.createWeapon("abcdef", SwordType.IRON_SWORD, 1));
        assertThrows(IllegalArgumentException.class, () -> temp.createWeapon("spear", RangedType.WOOD_BOW, 1));
    }
    @Test
    void PositionShouldBeTwentyAndTwentyTest() {
        assertEquals(20.0f, player.getX());
        assertEquals(20.0f, player.getY());
    }

    @Test
    void PlayerOutOfBoundTest(){
        Player you = (Player)player;
        assertEquals(20.0f,you.getY());
        you.moveTo(Direction.UP);
        you.moveFreely();
        assertEquals(80.0f,you.getY());
    }
    @Test
    void PlayerDirectionTest(){
        Player you = (Player)player;
        /**
         * initial point is 200,200
         */
        you.setTo(200.0f,200.0f);
        Direction[] dirs = {Direction.UP,Direction.DOWN,Direction.LEFT,Direction.RIGHT};
        for(int i=0;i<=80;i++){
            Direction current=Direction.NONE;
            if(i<80)current = dirs[i/20];
            else current = dirs[i/20-1];
            if(i/20==0){
                you.moveTo(current);
            }else if(i/20==1){
                if(i%20 ==0){
                    /**
                     * After player moves UP for 20 ticks (20*3)<- 3 is the player speed - 200 (starting position) = 140
                     */
                    you.stop();
                    assertEquals(140.0f,you.getY());
                }
                you.moveTo(current);
            }else if(i/20 == 2){
                if(i%20 ==0){
                    /**
                     * After player moves DOWN for 20 ticks (20*3)<- 3 is the player speed + 140 (starting position) = 200
                     */
                    you.stop();
                    assertEquals(200.0f,you.getY());
                }
                you.moveTo(current);
            }else if(i/20 == 3) {
                /**
                 * After player moves LEFT for 20 ticks (20*3)<- 3 is the player speed - 200 (starting position) = 140
                 */
                if(i%20 ==0){
                    you.stop();
                    assertEquals(140.0f,you.getX());
                }
                else you.moveTo(current);
            }
            you.moveFreely();
        }
        /**
         * End point, Player should be back to where it's original Place
         */
        you.stop();
        assertEquals(200.0f, you.getX());
    }
    @Test
    void HPTest(){
        Player you = (Player) player;
        assertEquals(5,you.getHealth());
        you.subHP(1);
        assertEquals(4,you.getHealth());
        you.subHP(4);
        assertEquals(0,you.getHealth());
        you.heal();
        assertEquals(1,you.getHealth());

    }
    @Test
    void resetPositionTest(){
        Player you = (Player) player;
        player.setTo(200.0f,200.0f);
        assertEquals(200.0f,you.getY());
        you.resetPos();
        assertEquals(20.0f,you.getY());
    }
    @Test
    void switchWeaponTest(){
        Player you = (Player) player;
        you.setWeapon(you.createWeapon("sword", SwordType.IRON_SWORD, 1));
        assertEquals("Iron Sword",you.getWeapon().getWeaponName());
        you.setNextWeapon(you.createWeapon("sword",SwordType.GOLDEN_SWORD,1));
        assertEquals("Golden Sword",you.getNextWeaponName());
        you.switchWeapon();
        assertEquals("Golden Sword",you.getWeapon().getWeaponName());
    }

}