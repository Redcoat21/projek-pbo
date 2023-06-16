package animation;

import main.Main;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * A static class that is used to load image based on the given path (Relative or Absolute).
 */
public class AnimationLoader {
    /**
     * Load an image based on the given path.
     * Note that the path will be based on src/main/resources root.
     * @param relativePath Path to the image.
     * @return The loaded image.
     */
    public static PImage loadImage(String relativePath) {
        PApplet mainProgram = Main.getMainProgram();
        String fullPath = "./src/main/resources/" + relativePath;
        return mainProgram.loadImage(fullPath);
    }
}
