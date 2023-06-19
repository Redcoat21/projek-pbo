package main;

import processing.core.PImage;

public class LoadingScreen {
    private boolean pressed;
    private PImage background;

    public LoadingScreen() {
        pressed = false;
        background = Main.processing.loadImage("../assets/Background/loadingScreen.png");
    }

    public void render(){
        Main.processing.background(background);
    }

    public boolean isPressed() {
        return pressed;
    }

    public void pressed(){
        pressed = !pressed;
    }
}
