package main;

import entities.Movable;
import entities.Player;
import entities.Skeletons;
import entities.Zombies;
import processing.core.PConstants;

import java.util.ArrayList;

public class ArcadeMode {
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
    int wave;
    Map map;
    boolean alive;
    boolean battle;
    boolean done;
    boolean win;
    ArrayList<Movable> entities = new ArrayList<>();

    public ArcadeMode(){
        floor = 5;
        player = new Player(0,15.5f*20+80, floor);
        startTime = System.currentTimeMillis();
        elapsedTime = System.currentTimeMillis() - startTime;
        elapsedSeconds = (int) (elapsedTime / 1000);
        secondsDisplay = elapsedSeconds % 60;
        elapsedMinutes = elapsedSeconds / 60;
        minutesDisplay = elapsedMinutes % 60;
        startTimeText = 0;
        elapsedTimeText = 0;
        elapsedSecondsText = (int) (elapsedTimeText / 1000);
        alive = true;
        wave = 0;
        map = new Map(floor);
        battle = false;
        done = false;
        win = false;
    }
    public void removeDead(){
        for (int i=0;i< entities.size();i++){
            Movable a = entities.get(i);
            if(a.getHealth()==0){
                entities.remove(i);
            }
        }
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
                gantiWave();
            }
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

            for (Movable a:entities){
                if(a instanceof Zombies)a.render();
                else if(a instanceof Skeletons)a.render();
            }

            for (Movable a:entities){
                if(a instanceof Zombies){
                    ((Zombies) a).checkAgro(player);
                    a.move();
                }else if(a instanceof Skeletons){
                    ((Skeletons) a).checkAgro(player);
                    a.move();
                }
            }
            removeDead();

            if(wave<3 && isEnemyDie()){
                gantiWave();
            }
            else if(isEnemyDie()){
                battleDone();
            }

            if(player.isDead()){
                alive = false;
            }
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

            map.printMap();
            player.render();
            player.move();

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
        if(entities.size()<1){
            return true;
        }
        return false;
    }
    private void battleDone(){
        battle = false;
        done = true;
        map.battleDone();
        player.updateMap(map);
//        System.out.println("SUDAH SELESAI");
    }
    private void gantiWave(){
        wave++;
        if(wave < 4){
            entities.add(new Zombies(320,390, floor));
//            entities.add(new Skeletons(800,300));
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
}
