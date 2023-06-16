package main;

import entities.*;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.Random;

public class EndlessMode {
    int floor;
    Player player;
    long startTime;
    long elapsedTime;
    int elapsedSeconds;
    int secondsDisplay;
    int elapsedMinutes;
    int minutesDisplay;
    long startTimeText;
    long elapsedTimeText;
    int elapsedSecondsText;
    long startTimeAtk;
    long elapsedTimeAtk;
    float elapsedSecondsAtk;
    int wave;
    int phase;
    Map map;
    boolean alive;
    boolean battle;
    private int[] x;
    private int[] y;
    private int[] r;
    private Movable[] enemy;
    boolean choosing;
    boolean reward;
    private Random rand;

    public EndlessMode(){
        floor = 4;
        startTime = System.currentTimeMillis();
        elapsedTime = System.currentTimeMillis() - startTime;
        elapsedSeconds = (int) (elapsedTime / 1000);
        secondsDisplay = elapsedSeconds % 60;
        elapsedMinutes = elapsedSeconds / 60;
        minutesDisplay = elapsedMinutes % 60;
        startTimeText = 0;
        elapsedTimeText = 0;
        elapsedSecondsText = (int) (elapsedTimeText / 1000);
        startTimeAtk = 0;
        elapsedTimeAtk = 0;
        elapsedSecondsAtk = (float) (elapsedTimeAtk / 1000);
        alive = true;
        wave = 0;
        phase = 1;
        map = new Map(floor);
        player = new Player(0,15.5f*20+80, map);
        battle = false;
        choosing = false;
        reward = true;
        x = new int[3];
        y = new int[3];
        r = new int[3];
        x[0] = Main.processing.width/2-200;
        x[1] = Main.processing.width/2;
        x[2] = Main.processing.width/2+200;
        y[0] = Main.processing.height/2-30;
        y[1] = Main.processing.height/2-30;
        y[2] = Main.processing.height/2-30;
        r[0] = 50;
        r[1] = 50;
        r[2] = 50;
        rand = new Random();
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
    }

    public void render() {
        if(alive && !battle){
//            System.out.println("AWAL AWAL");
            Main.processing.background(204,102,0);
            Main.processing.noStroke();

            //header section
            Main.processing.fill(102, 51, 0);
            Main.processing.rect(0, 0, Main.processing.width, 80);

            //fps section
            Main.processing.fill(255);
            Main.processing.textSize(14);
            Main.processing.textAlign(PConstants.RIGHT);
            Main.processing.text("fps " + (int) Main.processing.frameRate, Main.processing.width, 14);

            //health section
            Main.processing.textSize(14);
            Main.processing.textAlign(PConstants.LEFT);
            Main.processing.text("Heart " + player.getHealth(), 10, 14);

            //timer section
            Main.processing.textAlign(PConstants.CENTER, PConstants.CENTER);
            Main.processing.textSize(40);
            Main.processing.fill(0);
            Main.processing.text(byPaddingZeros(0, 2) + ":" + byPaddingZeros(0, 2), Main.processing.width / 2, 40);

            //floor section
            Main.processing.textAlign(PConstants.CENTER, PConstants.TOP);
            Main.processing.textSize(24);
            Main.processing.text("WAVE " + wave, Main.processing.width / 2, 0);

            map.printMap();
            player.render();
            player.move();

            startBattle();
            if(battle) {
                gantiWave();
                startTimeAtk = System.currentTimeMillis();
            }
        }
        else if(alive && choosing && !reward){
            Main.processing.background(0);

            //first choice
            Main.processing.noStroke();
            Main.processing.fill(125);
            Main.processing.rect(x[0], y[0], r[0], r[0], 5);
            Main.processing.textAlign(PConstants.CENTER, PConstants.CENTER);
            Main.processing.textSize(20);
            Main.processing.fill(255);
            Main.processing.text("heal", x[0]  + r[0]/2, y[0] + 60);

            //second choice
            Main.processing.noStroke();
            Main.processing.fill(125);
            Main.processing.rect(x[1], y[1], r[1], r[1], 5);
            Main.processing.textAlign(PConstants.CENTER, PConstants.CENTER);
            Main.processing.textSize(20);
            Main.processing.fill(255);
            Main.processing.text("level up", x[1]  + r[1]/2, y[1] + 60);

            //third choice
            Main.processing.noStroke();
            Main.processing.fill(125);
            Main.processing.rect(x[2], y[2], r[2], r[2], 5);
            Main.processing.textAlign(PConstants.CENTER, PConstants.CENTER);
            Main.processing.textSize(20);
            Main.processing.fill(255);
            Main.processing.text("change weapon", x[2]  + r[2]/2, y[2] + 60);
            Main.processing.text(player.getNextWeaponName(), x[2] + r[2]/2, y[2] + 80);
        }
        else if(alive && battle) {
            Main.processing.background(204,102,0);
            Main.processing.noStroke();

            //header section
            Main.processing.fill(102, 51, 0);
            Main.processing.rect(0, 0, Main.processing.width, 80);

            //fps section
            Main.processing.fill(255);
            Main.processing.textSize(14);
            Main.processing.textAlign(PConstants.RIGHT);
            Main.processing.text("fps " + (int) Main.processing.frameRate, Main.processing.width, 14);

            //health section
            Main.processing.textSize(14);
            Main.processing.textAlign(PConstants.LEFT);
            Main.processing.text("Heart " + player.getHealth(), 10, 14);

            //timer section
            elapsedTime = System.currentTimeMillis() - startTime;
            elapsedSeconds = (int) (elapsedTime / 1000);
            secondsDisplay = elapsedSeconds % 60;
            elapsedMinutes = elapsedSeconds / 60;
            minutesDisplay = elapsedMinutes % 60;
            Main.processing.textAlign(PConstants.CENTER, PConstants.CENTER);
            Main.processing.textSize(40);
            Main.processing.fill(0);
            Main.processing.text(byPaddingZeros(minutesDisplay, 2) + ":" + byPaddingZeros(secondsDisplay, 2), Main.processing.width / 2, 40);

            //floor section
            Main.processing.textAlign(PConstants.CENTER, PConstants.TOP);
            Main.processing.textSize(24);
            Main.processing.text("WAVE " + wave, Main.processing.width / 2, 0);

            //wave text section
            elapsedTimeText = System.currentTimeMillis() - startTimeText;
            elapsedSecondsText = (int) (elapsedTimeText / 1000);
            if(elapsedSecondsText<3){
                printWave();
            }

            map.printMap();
            player.move();

            player.bulletAtkCollision(enemy);

            //atk section
            elapsedTimeAtk = System.currentTimeMillis() - startTimeAtk;
            elapsedSecondsAtk = (float) elapsedTimeAtk/1000;
            if(elapsedSecondsAtk>=player.getAtkSpeed()) {
                player.atk(enemy);
//                System.out.println("waktu: " + elapsedSecondsAtk);
//                System.out.println("speed: " + player.getAtkSpeed());
//                System.out.println("masuk");
                startTimeAtk = System.currentTimeMillis();
            }

            player.render();

            for (Movable a:enemy){
                if(!a.isDead()) {
                    if (a instanceof Zombies) a.render();
                    else if (a instanceof Skeletons) a.render();
                    else if (a instanceof EliteZombies) a.render();
                    else if (a instanceof EliteSkeletons) a.render();
                    else if (a instanceof ChargedCreeper) a.render();
                    else if (a instanceof BigBoss) a.render();
                }
            }

            for (Movable a:enemy){
                if(!a.isDead()) {
                    if (a instanceof Zombies) {
                        ((Zombies) a).checkAgro(player);
                        a.move();
                        ((Zombies) a).atk(player);
                    } else if (a instanceof Skeletons) {
                        ((Skeletons) a).checkAgro(player);
                        a.move();
                        ((Skeletons) a).bulletAtkCollision(player);
                    } else if (a instanceof EliteZombies) {
                        ((EliteZombies) a).checkAgro(player);
                        a.move();
                        ((EliteZombies) a).atk(player);
                    } else if (a instanceof EliteSkeletons) {
                        ((EliteSkeletons) a).checkAgro(player);
                        a.move();
                        ((EliteSkeletons) a).bulletAtkCollision(player);
                    } else if (a instanceof ChargedCreeper) {
                        ((ChargedCreeper) a).checkAgro(player);
                        a.move();
                    } else if (a instanceof BigBoss) {
                        ((BigBoss) a).checkAgro(player);
                        a.move();
                        ((BigBoss) a).atk(player);
                    }
                }
            }

            if(wave % 3 == 0 && wave!= 0 && !reward){
                choosing = true;
                player.generateNextWeapon(phase);
            }
            else if(wave % 3 !=0){
                reward = false;
            }

            if(isEnemyDie() && !choosing){
                gantiWave();
            }

            if(player.isDead()){
                alive = false;
            }
        }
        else{
            Main.processing.background(0);
            Main.processing.fill(255,0,0);
            Main.processing.textAlign(PConstants.CENTER, PConstants.CENTER);
            Main.processing.textSize(50);
            Main.processing.text("YOU DIED!", Main.processing.width/2, Main.processing.height/2-80);
            Main.processing.textSize(40);
            Main.processing.text("You survived for " + wave + " waves and " + minutesDisplay + " minutes " + secondsDisplay + " seconds", Main.processing.width/2, Main.processing.height/2-32);
            Main.processing.text("Click the Screen to Continue", Main.processing.width/2, Main.processing.height/2+10);
        }
    }

    private boolean isEnemyDie(){
        for(int i=0; i<enemy.length; i++){
            if(!enemy[i].isDead()){
                return false;
            }
        }
        return true;
    }

    private void gantiWave(){
        wave++;
        int countZ = 0;
        int countS = 0;
        int countEZ = 0;
        int countES = 0;

        if(wave%9 == 0 && wave != 0){
            phase++;
            player.getWeapon().switchPhase();
        }

        countZ = rand.nextInt(phase*(wave/2)+1);
        countS = rand.nextInt(phase*(wave/2)+1);
        if(wave > 9) {
            countEZ = rand.nextInt(wave/2);
            countES = rand.nextInt(wave/2);
            countEZ = Math.max(countEZ, 1);
            countES = Math.max(countES, 1);
        }

        countZ = Math.max(countZ, 1);
        countS = Math.max(countS, 1);

        countZ = Math.min(countZ, 40);
        countS = Math.min(countS, 40);
        countEZ = Math.min(countEZ, 10);
        countES = Math.min(countES, 10);

        countS+=40;
        countEZ+=80;
        countES+=90;
        for(int i=0; i<countZ; i++){
            enemy[i].summoned(floor);
        }

        for(int i=40; i<countS; i++){
            enemy[i].summoned(floor);
        }

        for(int i=80; i<countEZ; i++){
            enemy[i].summoned(floor);
        }

        for(int i=90; i<countES; i++){
            enemy[i].summoned(floor);
        }


        startTimeText = System.currentTimeMillis();
        elapsedTimeText = System.currentTimeMillis() - startTimeText;
        elapsedSecondsText = (int) (elapsedTimeText / 1000);
        printWave();
    }

    private void printWave(){
        Main.processing.textAlign(PConstants.CENTER, PConstants.CENTER);
        Main.processing.textSize(30);
        Main.processing.text("WAVE " + wave, Main.processing.width/2, 120);
    }
    private void startBattle(){
        if(player.getX()>20){
            battle = true;
            map.battleStart();
            player.updateMap(map);
            startTime = System.currentTimeMillis();
        }
    }

    private static String byPaddingZeros(int value, int paddingLength) {
        return String.format("%0" + paddingLength + "d", value);
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isChoosing() {
        return choosing;
    }

    public int buttonPressed(){
        for(int i = 0; i<x.length; i++){
            if(Main.processing.mouseX > x[i] && Main.processing.mouseX < x[i]+r[i] &&
                    Main.processing.mouseY > y[i] && Main.processing.mouseY < y[i]+r[i]){
                return i;
            }
        }
        return -1;
    }

    public void choosed(){
        choosing = false;
        reward = true;
    }
}
