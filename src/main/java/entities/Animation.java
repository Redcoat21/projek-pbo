package entities;

import main.Main;
import processing.core.PImage;

public class Animation {
    /**
     * Please note that sprite images should be .png format.
     */
    private PImage[] sprite;
    private int frame;
    private int imageCount;

    public Animation(String imageName, int imageCount) {
        this.imageCount = imageCount;
        this.sprite = new PImage[imageCount];

        for(int i = 0; i < imageCount; i++) {
            String fileName = String.format("./assets/%s.png", imageName);
            this.sprite[i] = Main.processing.loadImage(fileName);
        }
    }
}
