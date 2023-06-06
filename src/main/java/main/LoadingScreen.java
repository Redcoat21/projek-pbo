package main;

import processing.core.PConstants;

public class LoadingScreen {
    private boolean pressed;

    public LoadingScreen() {
        pressed = false;
    }

    public void display(){
        Main.processing.fill(255);
        Main.processing.textSize(40);
        Main.processing.textAlign(PConstants.CENTER);
        Main.processing.text("Press Any Key To Continue", Main.processing.width/2, Main.processing.height/2);
//        Main.processing.text(Main.processing.frameRate, xText/2, yText/2);

    }

    public boolean isPressed() {
        return pressed;
    }

    public void pressed(){
        pressed = !pressed;
    }
}
