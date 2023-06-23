package main;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.Assert.*;

public class ArcadeModeTest {
    private Entities player;
    private Entities zombies;
    private Entities skeletons;
    private Entities eliteZombies;
    private Entities eliteSkeletons;
    private Entities chargedCreeper;
    private Entities bigBoss;
    @Test
    void addEntitiesTest() {
        Main app = new Main();
        app.initMain();
        ArcadeMode am = app.getAm();
        am.setPlayer(new Player(20,20));
        player = am.getPlayer();
        am.setZombies(new Zombies(20,20));
        zombies = am.getZombies();
        am.setSkeletons(new Skeletons(20,20));
        skeletons = am.getSkeletons();
        am.setEliteZombies(new EliteZombies(20,20));
        eliteZombies = am.getEliteZombies();
        am.setEliteSkeletons(new EliteSkeletons(20,20));
        eliteSkeletons = am.getEliteSkeletons();
        am.setChargedCreeper(new ChargedCreeper(20,20));
        chargedCreeper = am.getChargedCreeper();
        am.setBigBoss(new BigBoss(20,20));
        bigBoss = am.getBigBoss();
        Player player = new Player();
        Zombies zom = new Zombies();
        Skeletons skelly = new Skeletons();
        EliteZombies elzom = new EliteZombies();
        EliteSkeletons elskelly = new EliteSkeletons();
        ChargedCreeper cc = new ChargedCreeper();
        BigBoss bb = new BigBoss();
        am.addEntities(player);
        am.addEntities(zom);
        am.addEntities(skelly);
        am.addEntities(elzom);
        am.addEntities(elskelly);
        am.addEntities(cc);
        am.addEntities(bb);
        assertNotNull(am.getPlayer());
        assertNotNull(am.getZombies());
        assertNotNull(am.getSkeletons());
        assertNotNull(am.getEliteZombies());
        assertNotNull(am.getEliteSkeletons());
        assertNotNull(am.getChargedCreeper());
        assertNotNull(am.getBigBoss());
        assertEquals(player, am.getPlayer());
        assertEquals(zom, am.getZombies());
        assertEquals(skelly, am.getSkeletons());
        assertEquals(elzom, am.getEliteZombies());
        assertEquals(elskelly, am.getEliteSkeletons());
        assertEquals(cc, am.getChargedCreeper());
        assertEquals(bb, am.getBigBoss());
    }
    @Test
    void testWave(){
        Main app = new Main();
        app.initMain();
        ArcadeMode am = app.getAm();
        am.setWave(0);
        assertEquals(0,am.getWave());
        am.newWave(1);
        assertEquals(1,am.getWave());

    }
    @Test
    void testFloor(){
        Main app = new Main();
        app.initMain();
        ArcadeMode am = app.getAm();
        am.setFloor(1);
        assertEquals(1,am.getFloor());
        am.newFloor(1);
        assertEquals(2,am.getFloor());
    }
}