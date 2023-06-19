package main;

import processing.core.PConstants;

public class LoadingScreen {
    private boolean pressed;

    public LoadingScreen() {
        pressed = false;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void pressed(){
        pressed = !pressed;
    }
}
