package interface_package;

/**
 * Represent any object that can be rendered and showed onto the screen.
 */
@FunctionalInterface
public interface Renderable {
    /**
     * Display the object onto the screen.
     */
    void render();
}
