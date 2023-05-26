package entities;

import main.Main;
import processing.core.PImage;

import java.util.ArrayList;

public class Animation {
    /**
     * Please note that sprite images should be .png format.
     */
    private ArrayList<PImage> sprite;
    private int frame;
    private int imageCount;

    public Animation(int imageCount) {
        this.imageCount = imageCount;
        this.sprite = new ArrayList<>();
    }

    public void addImage(Entities entity, String pathToImage) {
        PImage image = Main.processing.loadImage(pathToImage);
        image.resize((int) entity.getWidth(), (int) entity.getHeight());
        this.sprite.add(image);
    }

    public void playAnimation(Entities entity) {
        for(PImage image : this.sprite) {
            System.out.println(image.width);
            Main.processing.image(image, entity.getX(), entity.getY());
        }
    }
}
