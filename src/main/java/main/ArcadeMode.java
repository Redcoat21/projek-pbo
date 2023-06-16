package main;

import entities.*;
import processing.core.PConstants;

import java.util.ArrayList;

public class ArcadeMode {
    Player player;
    int floor;
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
    Map map;
    boolean alive;
    boolean battle;
    boolean done;
    boolean reward;
    boolean choosing;
    boolean win;
    private int[] x;
    private int[] y;
    private int[] r;
    private Movable[] enemy;


    public ArcadeMode(){
        floor = 4;
        player = new Player(0,15.5f*20+80, new Map(floor));
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
        floor =1;
        wave = 0;
        map = new Map(floor);
        battle = false;
        done = false;
        reward = false;
        choosing = false;
        win = false;
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
        enemy = new Movable[91];
        for(int i=0; i<35; i++){
            enemy[i] = new Zombies(-100, -100);
        }
        for(int i=35; i<70; i++){
            enemy[i] = new Skeletons(-100, -100);
        }
        for(int i=70; i<80; i++){
            enemy[i] = new EliteZombies(-100, -100);
        }
        for(int i=80; i<90; i++){
            enemy[i] = new EliteSkeletons(-100, -100);
        }
        enemy[90] = new BigBoss(-100,-100);
    }

    public void render() {
        if(alive && !battle && !done && !win){
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
//            elapsedTime = System.currentTimeMillis() - startTime;
//            elapsedSeconds = (int) (elapsedTime / 1000);
//            secondsDisplay = elapsedSeconds % 60;
//            elapsedMinutes = elapsedSeconds / 60;
//            minutesDisplay = elapsedMinutes % 60;
            Main.processing.textAlign(PConstants.CENTER, PConstants.CENTER);
            Main.processing.textSize(40);
            Main.processing.fill(0);
            Main.processing.text(byPaddingZeros(0, 2) + ":" + byPaddingZeros(0, 2), Main.processing.width / 2, 40);

            //floor section
            Main.processing.textAlign(PConstants.CENTER, PConstants.TOP);
            Main.processing.textSize(24);
            Main.processing.text("FLOOR " + floor, Main.processing.width / 2, 0);

            map.printMap();
            player.render();
            player.move();

            startBattle();
            if(battle) {
//                System.out.println("masuk battle");
                gantiWave();
                startTimeAtk = System.currentTimeMillis();
//                System.out.println("selesai battle");
            }
        }
        else if(alive && battle) {
//            System.out.println("masuk suasana battle");
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
            Main.processing.text("FLOOR " + floor, Main.processing.width / 2, 0);

            //wave text section
            elapsedTimeText = System.currentTimeMillis() - startTimeText;
            elapsedSecondsText = (int) (elapsedTimeText / 1000);
            if(elapsedSecondsText<3){
                printWave();
            }

            map.printMap();
            player.render();
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
                    }
                }
            }

            if(wave<3 && isEnemyDie()){
                gantiWave();
            }
            else if(isEnemyDie()){
                battleDone();
                player.clearBullet();
            }

            if(player.isDead()){
                alive = false;
            }
//            System.out.println("selesai suasana battle");
        }
        else if(alive && done){
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
//            elapsedTime = System.currentTimeMillis() - startTime;
//            elapsedSeconds = (int) (elapsedTime / 1000);
//            secondsDisplay = elapsedSeconds % 60;
//            elapsedMinutes = elapsedSeconds / 60;
//            minutesDisplay = elapsedMinutes % 60;
            Main.processing.textAlign(PConstants.CENTER, PConstants.CENTER);
            Main.processing.textSize(40);
            Main.processing.fill(0);
            Main.processing.text(byPaddingZeros(minutesDisplay, 2) + ":" + byPaddingZeros(secondsDisplay, 2), Main.processing.width / 2, 40);

            //floor section
            Main.processing.textAlign(PConstants.CENTER, PConstants.TOP);
            Main.processing.textSize(24);
            Main.processing.text("FLOOR " + floor, Main.processing.width / 2, 0);

            if(player.getX() >= 57*20 && !reward){
                choosing = true;
            }
            if(choosing){
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
            }
            else {
                map.printMap();
                player.render();
                player.move();
            }

            if(player.isDead()){
                alive = false;
            }

            //for go to the next floor
            if(player.getX()>=1260 && floor < 5){
                changingMap();
            }
            else if(player.getX()>=1260){
                win = true;
                battle = false;
                done = false;
            }
        }
        else if(win){
            Main.processing.background(0);
            Main.processing.fill(255,215,0);
            Main.processing.textAlign(PConstants.CENTER, PConstants.CENTER);
            Main.processing.textSize(50);
            Main.processing.text("YOU WIN!", Main.processing.width/2, Main.processing.height/2-90);
            Main.processing.text("CONGRATULATIONS!", Main.processing.width/2, Main.processing.height/2-40);
            Main.processing.textSize(40);
            Main.processing.text("Click the Screen to Continue", Main.processing.width/2, Main.processing.height/2+10);
        }
        else{
            Main.processing.background(0);
            Main.processing.fill(255,0,0);
            Main.processing.textAlign(PConstants.CENTER, PConstants.CENTER);
            Main.processing.textSize(50);
            Main.processing.text("YOU DIED!", Main.processing.width/2, Main.processing.height/2-40);
            Main.processing.textSize(40);
            Main.processing.text("Click the Screen to Continue", Main.processing.width/2, Main.processing.height/2+10);
        }
    }

    private void changingMap(){
        floor++;
        wave = 0;
        map = new Map(floor);
        player.resetPos();
        player.updateMap(map);
//        for()
        battle = false;
        done = false;
//        System.out.println("masuk");
    }
    private boolean isEnemyDie(){
//        if(entities.size()<1){
//            return true;
//        }
//        return false;
        
        for(int i=0; i<enemy.length; i++){
            if(!enemy[i].isDead()){
                return false;
            }
        }
        return true;
    }
    private void battleDone(){
        battle = false;
        done = true;
        map.battleDone();
        player.updateMap(map);
//        System.out.println("SUDAH SELESAI");
    }
    private void gantiWave(){
//        System.out.println("masuk");
        wave++;
        if(wave < 4){
//            entities.add(new Zombies(320,390));
//            entities.add(new Skeletons(800,300));
//            entities.add(new Skeletons(700,250));
//            entities.add(new Skeletons(600,200));
//            entities.add(new EliteZombies(100,150));
//            entities.add(new EliteSkeletons(200, 150));
//            entities.add(new EliteSkeletons(400,120));
//            entities.add(new ChargedCreeper(150, 150));
//            entities.add(new ChargedCreeper(170, 150));
//            entities.add(new ChargedCreeper(190, 150));
//            entities.add(new BigBoss(1000,300));
            int countZ = 0;
            int countS = 0;
            int countEZ = 0;
            int countES = 0;
            int countBB = 0;
            if(floor == 1){
                if(wave == 1){
                    countZ = 1;
                }
                else if(wave == 2){
                    countZ = 3;
                }
                else{
                    countZ = 4;
//                    countES = 2;
                }
            }
            else if(floor == 2){
                if(wave == 1){
                    countZ = 2;
                    countS = 3;
                }
                else if(wave == 2){
                    countZ = 3;
                    countS = 6;
                }
                else{
                    countZ = 3;
                    countS = 8;
                }
            }
            else if(floor == 3){
                if(wave == 1) {
                    countZ = 5;
                    countS = 4;
                    countES = 1;
                }
                else{
                    countZ = 5;
                    countS = 5;
                    countES = 1;
                }
            }
            else if(floor == 4){
                if(wave == 1) {
                    countZ = 5;
                    countS = 4;
                    countEZ = 2;
                    countES = 2;
                }
                else{
                    countZ = 6;
                    countS = 3;
                    countEZ = 3;
                    countES = 2;
                }
            }
            else if(floor == 5){
                countZ = 5;
                countS = 5;
                countEZ = 2;
                countES = 2;
                countBB = 1;
            }
            countS+=35;
            countEZ+=70;
            countES+=80;
            for(int i=0; i<countZ; i++){
                enemy[i].summoned(floor);
            }

            for(int i=35; i<countS; i++){
                enemy[i].summoned(floor);
            }

            for(int i=70; i<countEZ; i++){
                enemy[i].summoned(floor);
            }

            for(int i=80; i<countES; i++){
                enemy[i].summoned(floor);
            }
            if(countBB > 0){
                enemy[90].summoned(30, 15, floor);
            }
        }
        player.updateMap(map);

//        System.out.println("selesai");
        startTimeText = System.currentTimeMillis();
        elapsedTimeText = System.currentTimeMillis() - startTimeText;
        elapsedSecondsText = (int) (elapsedTimeText / 1000);
        printWave();
    }

    private void printWave(){
        Main.processing.textAlign(PConstants.CENTER, PConstants.CENTER);
        Main.processing.textSize(30);
        if(floor == 5){
            Main.processing.text("BOSS LEVEL!", Main.processing.width / 2, 120);
        }
        else {
            Main.processing.text("WAVE " + wave, Main.processing.width / 2, 120);
        }
    }
    private void startBattle(){
        if(player.getX()>20 && !done){
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

    public boolean isWin(){
        return win;
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
