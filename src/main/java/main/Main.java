package main;

import entities.Direction;
import processing.core.PApplet;

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
    public static PApplet processing;
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
        mode = 4;
        frameRate(60);
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
            cm.render();
        }
        else if(mode == 3){
            am.render();
        }
        else if(mode == 4){
            em.render();
        }
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
            mode = 2;
            em = new EndlessMode();
        }
    }
    public static void main(String[] args) {
        PApplet.main("main.Main");
    }
}

