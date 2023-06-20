package main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import entities.*;
public class EndlessModeTest {
    private Movable[] enemy;
    @Test
    public void testIsEnemyDie_NoEnemiesAlive_ReturnsTrue() throws NullPointerException{
        // Create an instance of your class
        EndlessMode yourClass = new EndlessMode();

        // Set up the test case with no enemies alive

        enemy = new Movable[100];
        enemy[0] = new Zombies(-100, -100);
        for(int i=0; i<40; i++){
            enemy[i] = new Zombies(-100, -100);
            assertTrue(enemy[i].isDead());
        }



        // Invoke the method under test
        boolean result = yourClass.isEnemyDie();

        // Assert the result
        assertTrue(result);
    }

}