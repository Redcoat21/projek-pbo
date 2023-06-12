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
     Map map;
    boolean alive;
    ArrayList<Movable> entities = new ArrayList<>();


    public ArcadeMode(){
        player = new Player(0,15.5f*20+80);
        startTime = System.currentTimeMillis();
        elapsedTime = System.currentTimeMillis() - startTime;
        elapsedSeconds = (int) (elapsedTime / 1000);
        secondsDisplay = elapsedSeconds % 60;
        elapsedMinutes = elapsedSeconds / 60;
        minutesDisplay = elapsedMinutes % 60;
        alive = true;
        floor = 4;
        map = new Map(floor);
//        entities.add(new Zombies(100,100,map));
//        entities.add(new Zombies(320,390,map));
//        entities.add(new Skeletons(800,300,map));
//        entities.add(new EliteZombies(100,150,map));
        entities.add(new ChargedCreeper(580, 410,map));
        entities.add(new ChargedCreeper(580, 430,map));
        entities.add(new ChargedCreeper(580, 450,map));
        entities.add(new ChargedCreeper(580, 410,map));
        entities.add(new ChargedCreeper(580, 430,map));
        entities.add(new ChargedCreeper(580, 450,map));
//        entities.add(new BigBoss(1000,300,map));
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
        if(alive) {
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

            map.printMap();
            player.render();

            for (Movable a:entities){
                if(a instanceof Zombies)a.render();
                else if(a instanceof Skeletons)a.render();
                else if(a instanceof EliteZombies)a.render();
                else if(a instanceof ChargedCreeper)a.render();
                else if(a instanceof BigBoss)a.render();
            }

            player.move();

            for (Movable a:entities){
                if(a instanceof Zombies){
                    ((Zombies) a).checkAgro(player);
                    a.move();
                }else if(a instanceof Skeletons){
                    ((Skeletons) a).checkAgro(player);
                    a.move();
                }else if(a instanceof  EliteZombies){
                    ((EliteZombies) a).checkAgro(player);
                    a.move();
                }else if(a instanceof ChargedCreeper){
                    ((ChargedCreeper)a).checkAgro(player);
                    a.move();
                    if(((ChargedCreeper) a).isSuicide()){
                        map = ((ChargedCreeper) a).getMapCreeper();
                        for (Movable b:entities){
                            b.setMap(map);
                        }
                    }
                }else if(a instanceof BigBoss){
                    ((BigBoss)a).checkAgro(player);
                    a.move();
                }
            }
            removeDead();

            if(player.getHealth() < 1){
                alive = false;
            }

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

    private static String byPaddingZeros(int value, int paddingLength) {
        return String.format("%0" + paddingLength + "d", value);
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isAlive() {
        return alive;
    }
}
