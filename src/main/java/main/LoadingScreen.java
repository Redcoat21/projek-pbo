package main;

import processing.core.PConstants;

public class LoadingScreen {
    private final int xText;
    private final int yText;
    private boolean pressed;

    public LoadingScreen(int xText, int yText) {
        this.xText = xText;
        this.yText = yText;
        pressed = false;
    }

    public void display(){
        Main.processing.textSize(40);
        Main.processing.textAlign(PConstants.CENTER);
        Main.processing.text("Press Any Key To Continue", xText/2, yText/2);
//        Main.processing.text(Main.processing.frameRate, xText/2, yText/2);

    }

    public boolean isPressed() {
        return pressed;
    }

    public void pressed(){
        pressed = true;
    }
}
