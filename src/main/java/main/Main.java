package main;

import entities.Direction;
import processing.core.PApplet;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;
import java.util.Random;

public class Main extends PApplet{
    /*
        this variable mode is used for marking the condition of the game
        1 = load screen
        2 = choosing game mode
        3 = arcade mode
        4 = endless mode
        5 = exit
     */
    int mode;
    boolean bgmplaying;
    boolean[] isplaying = new boolean[3];
    public static PApplet processing;
//    Player player;
    private LoadingScreen ls;
    private ChoosingMenu cm;
    private ArcadeMode am;
    private EndlessMode em;
    private Random rand;
    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void setup() {
        background(100);
//        player = new Player(width/10, height/2);
        processing = this;
        ls = new LoadingScreen();
        cm = new ChoosingMenu();
        am = new ArcadeMode();
        em = new EndlessMode();
        rand = new Random();
        mode = 3;
        frameRate(60);
        bgmplaying=false;
        isplaying[0]=false;
        isplaying[1]=false;
        isplaying[2]=false;
    }

    @Override
    public void draw() {

        if(mode == 1) {

            background(0);
            ls.display();
            if(ls.isPressed()){
                ls.pressed();
                mode = 2;
            }
        }
        else if(mode == 2){

//            am = new ArcadeMode();
//            em = new EndlessMode();
            cm.render();
        }
        else if(mode == 3){
            am.render();

        }
        else if(mode == 4){
            em.render();
        }
        if(mode==2&&bgmplaying==true&&isplaying[0]!=true){
            stopMusic();
            playMusic(2);
            isplaying[0]=true;
            isplaying[1]=false;
            isplaying[2]=false;
        } else if (mode==3&&bgmplaying==true&&isplaying[1]!=true) {
            stopMusic();
            playMusic(3);
            isplaying[0]=false;
            isplaying[1]=true;
            isplaying[2]=false;
        } else if (mode==4&&bgmplaying==true&&isplaying[2]!=true) {
            stopMusic();
            playMusic(4);
            isplaying[0]=false;
            isplaying[1]=false;
            isplaying[2]=true;
        }else if(mode==2&&bgmplaying==false&&isplaying[0]!=true){
            playMusic(2);
            bgmplaying=true;
            isplaying[0]=true;
            isplaying[1]=false;
            isplaying[2]=false;
        } else if (mode==3&&bgmplaying==false&&isplaying[1]!=true) {
            playMusic(3);
            bgmplaying=true;
            isplaying[0]=false;
            isplaying[1]=true;
            isplaying[2]=false;
        } else if (mode==4&&bgmplaying==false&&isplaying[2]!=true) {
            playMusic(4);
            bgmplaying=true;
            isplaying[0]=false;
            isplaying[1]=false;
            isplaying[2]=true;
        }
//        if(mode==1&&bgmplaying==false||mode==2&&bgmplaying==false){
//            playMusic(1);
//            bgmplaying=true;
//        } else if (mode==3||mode==4) {
//            if(mode==3){
//                if(am.isEnemyDie()==true){
//                    if(bgmplaying==false){
//                        playMusic(1);
//                        bgmplaying=true;
//                    }else{
//                        if(needchange==true)
//                        stopMusic();
//
//                    }
//                }if(am.isEnemyDie()==false){
//                    if(bgmplaying==false){
//                        playMusic(4);
//                        bgmplaying=true;
//                    }else{
//                        if(needchange==true)
//                            stopMusic();
//
//                    }
//                }
//            } else if (mode==4) {
//                if(em.isEnemyDie()==true){
//                    playMusic(1);
//                    bgmplaying=true;
//                }else{
//                    playMusic(4);
//                    bgmplaying=true;
//                }
//            }
        }
    bgm song = new bgm();
    public void playMusic(int mode){
        bgm.setFile(mode);
        bgm.loop();
    }
    public void stopMusic(){
        bgm.stop();
    }
    @Override
    public void keyPressed(){
        if(mode == 1){
            ls.pressed();
        }
        else if(mode == 3 && am.isAlive()) {
//            am.getPlayer().setSpeed(am.getPlayer().getSpeed());
//            am.getPlayer().setSpeed(1);
            if (key == 'a') {
                am.getPlayer().moveTo(Direction.LEFT);
            }

            if (key == 'd') {
                am.getPlayer().moveTo(Direction.RIGHT);
            }

            if (key == 'w') {
                am.getPlayer().moveTo(Direction.UP);
            }

            if (key == 's') {
                am.getPlayer().moveTo(Direction.DOWN);
            }
        }
        else if(mode == 4 && em.isAlive()) {
            if (key == 'a') {
                em.getPlayer().moveTo(Direction.LEFT);
            }

            if (key == 'd') {
                em.getPlayer().moveTo(Direction.RIGHT);
            }

            if (key == 'w') {
                em.getPlayer().moveTo(Direction.UP);
            }

            if (key == 's') {
                em.getPlayer().moveTo(Direction.DOWN);
            }
        }
    }

    @Override
    public void keyReleased() {
        if(mode == 3) {
            if (key == 'a' || key == 'd' || key == 's' || key == 'w') {
                if(!keyPressed){
                    am.getPlayer().stop();
                    am.getPlayer().clearDirection();
                }
                else{
                    try {
                        if (key == 'a') {
                            am.getPlayer().keyReleasedDirection(Direction.LEFT);
                        }
                        if (key == 'd') {
                            am.getPlayer().keyReleasedDirection(Direction.RIGHT);
                        }
                        if (key == 'w') {
                            am.getPlayer().keyReleasedDirection(Direction.UP);
                        }
                        if (key == 's') {
                            am.getPlayer().keyReleasedDirection(Direction.DOWN);
                        }
                    }
                    catch (IndexOutOfBoundsException e){
                        System.out.println("lanjut");
                    }
                }
            }
        }
        else if(mode == 4){
            if (key == 'a' || key == 'd' || key == 's' || key == 'w') {
                if(!keyPressed){
                    em.getPlayer().stop();
                    em.getPlayer().clearDirection();
                }
                else{
                    try {
                        if (key == 'a') {
                            em.getPlayer().keyReleasedDirection(Direction.LEFT);
                        }
                        if (key == 'd') {
                            em.getPlayer().keyReleasedDirection(Direction.RIGHT);
                        }
                        if (key == 'w') {
                            em.getPlayer().keyReleasedDirection(Direction.UP);
                        }
                        if (key == 's') {
                            em.getPlayer().keyReleasedDirection(Direction.DOWN);
                        }
                    }
                    catch (IndexOutOfBoundsException e){
                        System.out.println("lanjut");
                    }
                }
            }
        }
    }
    @Override
    public void mouseClicked() {
        if(mode == 2){
            int click = cm.buttonPressed();
            if(click == 0){
                mode = 3;
            }
            else if(click == 1){
                mode = 4;
            }
            else if(click == 2){
                exit();
            }
        }
        else if(mode == 3 && !am.isAlive()) {
            mode = 2;
            am = new ArcadeMode();
        }
        else if(mode == 3 && am.isChoosing()){
            int click = am.buttonPressed();
            if(click == 0){
                am.getPlayer().heal();
                am.choosed();
                System.out.println("ke heal");
            }
            else if(click == 1){
                am.getPlayer().getWeapon().setLevel(1);
                am.choosed();
                System.out.println("senjata level up");
            }
            else if(click == 2){
                am.choosed();
            }
        }
        else if(mode == 3 && am.win){
            mode = 2;
        }
        else if(mode == 4 && !em.isAlive()) {
            mode = 2;
            em = new EndlessMode();
        }
    }
    public static void main(String[] args) {
        PApplet.main("main.Main");


    }
}

