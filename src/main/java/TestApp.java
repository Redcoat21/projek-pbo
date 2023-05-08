import processing.core.PApplet;

public class TestApp extends PApplet {

    @Override
    public void settings() {
        size(1980, 1020);
    }

    @Override
    public void setup() {
        background(255);
    }

    @Override
    public void draw() {
        // Draw a circle in the center of the window
        ellipse(width/2, height/2, 50, 50);
    }

    public static void main(String[] args) {
        PApplet.main("TestApp");
    }
}
