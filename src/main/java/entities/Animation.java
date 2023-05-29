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

    /**
     * Added an image into the animation list.
     * @param entity
     * @param pathToImage
     */
    public void addImage(Entities entity, String pathToImage) {
        PImage image = Main.processing.loadImage(pathToImage);
        image.resize((int) entity.getWidth(), (int) entity.getHeight());
        this.sprite.add(image);
    }

    /**
     * Play the animation list.
     * @param entity The entity that will be assigned to.
     */
    public void playAnimation(Entities entity) {
        for(PImage image : this.sprite) {
            System.out.println(image.width);
            Main.processing.image(image, entity.getX(), entity.getY());
        }
    }
}
