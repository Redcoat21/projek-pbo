package main;

import entities.Direction;
import entities.Player;
import processing.core.PApplet;
import processing.core.PImage;

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
    boolean[] isplaying;
    public static PApplet processing;
    private LoadingScreen ls;
    private ChoosingMenu cm;
    private ArcadeMode am;
    private EndlessMode em;
    private Random rand;
    private Bgm song;
    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void setup() {
        background(100);
        processing = this;
        ls = new LoadingScreen();
        cm = new ChoosingMenu();
        am = new ArcadeMode();
        em = new EndlessMode();
        rand = new Random();
        song = new Bgm();
        isplaying = new boolean[3];
        mode = 1;
        frameRate(60);
        bgmplaying=false;
        isplaying[0]=false;
        isplaying[1]=false;
        isplaying[2]=false;
    }

    @Override
    public void draw() {

        if(mode == 1) {
            ls.render();
            if(ls.isPressed()){
                ls.pressed();
                mode = 2;
            }
        }
        else if(mode == 2){
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
    }
    public void initMain(){
        am = new ArcadeMode(new Player());
    }

    public void playMusic(int mode){
        Bgm.setFile(mode);
        Bgm.loop();
    }
    public void stopMusic(){
        Bgm.stop();
    }
    @Override
    public void keyPressed(){
        if(mode == 1){
            ls.pressed();
        }
        else if(mode == 3 && am.isAlive()) {
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
            am = new ArcadeMode();
            mode = 2;
        }
        else if(mode == 3 && am.isChoosing()){
            int click = am.buttonPressed();
            if(click == 0){
                am.getPlayer().heal();
                am.choosed();
            }
            else if(click == 1){
                am.getPlayer().getWeapon().increaseLevel(1);
                am.choosed();
            }
            else if(click == 2){
                am.choosed();
                am.getPlayer().switchWeapon();
            }
        }
        else if(mode == 3 && am.win){
            mode = 2;
        }
        else if(mode == 4 && em.isChoosing()){
            int click = em.buttonPressed();
            if(click == 0){
                em.getPlayer().heal();
                em.choosed();
                System.out.println("keheal");
            }
            else if(click == 1){
                em.getPlayer().getWeapon().increaseLevel(1);
                em.choosed();
                System.out.println("level up");
            }
            else if(click == 2){
                em.choosed();
                em.getPlayer().switchWeapon();
                System.out.println("ganti senjata");
            }
        }
        else if(mode == 4 && !em.isAlive()) {
            em = new EndlessMode();
            mode = 2;
        }
    }
    public static void main(String[] args) {
        PApplet.main("main.Main");
    }

    public ArcadeMode getAm() {
        return am;
    }
}

