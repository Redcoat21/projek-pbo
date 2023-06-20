package main;
import static org.junit.jupiter.api.Assertions.*;

import entities.*;
import org.junit.Test;
public class EndlessModeTest {

    @Test
    public void testIsEnemyDie_NoEnemiesAlive_ReturnsTrue(){
        EndlessMode yourClass = new EndlessMode(1);
        Movable[] enemy = new Movable[100];
        enemy[0] = new Zombies();
        enemy[1] = new Skeletons();
        enemy[2] = new EliteZombies();
        enemy[3] = new EliteSkeletons();
        for (int i = 0; i < 4; i++) {
            assertTrue(enemy[i].isDead());
        }


        boolean result = yourClass.isEnemyDie();
        assertTrue(result);
    }
}