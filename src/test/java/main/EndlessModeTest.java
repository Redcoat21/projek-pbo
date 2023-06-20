package main;
import static org.junit.jupiter.api.Assertions.*;
import entities.Zombies;
import entities.Movable;
import org.junit.Test;
public class EndlessModeTest {

    @Test
    public void testIsEnemyDie_NoEnemiesAlive_ReturnsTrue(){
        EndlessMode em = new EndlessMode(1);
        Movable[] enemy = new Movable[100];
        enemy[0] = new Zombies();
        assertTrue(enemy[0].isDead());
        boolean hsl = em.isEnemyDie();
        assertTrue(hsl);
    }
}