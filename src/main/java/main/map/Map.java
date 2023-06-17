package main.map;

import interface_package.Renderable;
import tiles.Tiles;
import processing.core.PVector;

class MapGenerator {
    // Probability of generating a wall
    private static final float WALL_THRESHOLD = 0.4f;
    public static Map generateMap(int row, int column) {
        Map generatedMap = new Map(row, column);
        return null;
    }
}

/**
 * Represent the battle map of the game.
 */
public class Map implements Renderable {
    private Tiles[][] tileMap;
    public Map(int row, int column) {
        this.tileMap = new Tiles[row][column];
    }

    /**
     * Assign tiles based on the given position.
     * @param position Position in PVector format (x, y)
     * @param tiles The tiles that will be assigned.
     */
    public void assignTiles(PVector position, Tiles tiles) {

    }

    /**
     * Assign tiles based on the given position.
     * @param x The x position of the will be assigned tiles.
     * @param y The y position of the will be assigned tiles.
     * @param tiles The tiles that will be assigned.
     */
    public void assignTiles(int x, int y, Tiles tiles) {

    }

    @Override
    public void render() {
        ;
    }
}
