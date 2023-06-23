package main;
import static org.junit.jupiter.api.Assertions.*;

import entities.*;
import org.junit.Test;
public class EndlessModeTest {
    private Entities player;
    @Test
    public void testIsEnemyDie_NoEnemiesAlive_ReturnsTrue(){
        EndlessMode em = new EndlessMode(1);
        Movable[] enemy = new Movable[100];
        enemy[0] = new Zombies();
        enemy[1] = new Skeletons();
        enemy[2] = new EliteZombies();
        enemy[3] = new EliteSkeletons();
        enemy[4] = new ChargedCreeper();
        enemy[5] = new BigBoss();
        for (int i = 0; i < 6; i++) {
            assertTrue(enemy[i].isDead());
        }
        boolean result = em.isEnemyDie();
        assertTrue(result);
    }
    @Test
    public void testIsAllEnemyDead(){
        EndlessMode em = new EndlessMode(1);
        Movable[] enemy = new Movable[100];
        enemy[0] = new Zombies();
        enemy[1] = new Skeletons();
        enemy[2] = new EliteZombies();
        enemy[3] = new EliteSkeletons();
        enemy[4] = new ChargedCreeper();
        enemy[5] = new BigBoss();
        for (int i = 0; i < 6; i++) {
            enemy[i].setHealth(10);
            assertFalse(enemy[i].isDead());
            enemy[i].subHP(10);
            assertTrue(enemy[i].isDead());
        }
    }
    @Test
    public void testEndlessModeGameOver(){
        Main app = new Main();
        app.initMain2();
        EndlessMode em = app.getEm();
        em.setPlayer(new Player(20,20));
        player = em.getPlayer();
        Player you = (Player)player;
        assertFalse(you.isDead());
        you.subHP(4);
        assertEquals(1,you.getHealth());
        you.heal();
        assertEquals(2,you.getHealth());
        you.fallen();
        assertTrue(you.isDead());
    }
    @Test
    public void testAddEndlessEntities(){
        EndlessMode em = new EndlessMode(1);
        Movable[] enemy = new Movable[100];
        enemy = new Movable[100];
        for(int i=0; i<40; i++){
            enemy[i] = new Zombies(-100, -100);
        }
        for(int i=40; i<80; i++){
            enemy[i] = new Skeletons(-100, -100);
        }
        for(int i=80; i<90; i++){
            enemy[i] = new EliteZombies(-100, -100);
        }
        for(int i=90; i<100; i++){
            enemy[i] = new EliteSkeletons(-100, -100);
        }
        assertEquals(100,enemy.length);
    }
}