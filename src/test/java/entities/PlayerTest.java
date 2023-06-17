package entities;

import main.Main;
import main.Map;
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
    private Main a = new Main();
    @BeforeEach
    public void createPlayer() {
        player = new Player(20.0f, 20.0f, null);
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

}