package entities.animation;

import main.Main;
import processing.core.PImage;

public class AnimationLoader {
    /**
     * Load an image based on the given path.
     * Note that the path will be based on src/main/resources root.
     * @param relativePath Path to the image.
     * @return The loaded image.
     */
    public static PImage loadImage(String relativePath) {
        String fullPath = "./src/main/resources/" + relativePath;
        return Main.processing.loadImage(fullPath);
    }
}
