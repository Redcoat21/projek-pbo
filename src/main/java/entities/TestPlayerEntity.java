package entities;

import entities.animation.AnimationLoader;
import entities.animation.AnimationPlayer;
import main.Main;
import processing.core.PApplet;

public class TestPlayerEntity extends Entity {
    public TestPlayerEntity(float x, float y, int width, int height) {
        super(x, y, width, height);
        this.addSprite(Direction.NONE, AnimationLoader.loadImage("assets/Sprites/Skeleton/Skeleton.png"));
    }

    @Override
    public void render() {
        AnimationPlayer a = new AnimationPlayer();
        a.play(Direction.NONE, this);
    }

    public void move() {
        PApplet mainProgram = Main.getMainProgram();
        Direction movingToward = switch(mainProgram.key) {
            case 'a', 'A' -> Direction.LEFT;
            case 'd', 'D' -> Direction.RIGHT;
            case 's', 'S' -> Direction.DOWN;
            case 'w', 'W' -> Direction.UP;
            default -> Direction.NONE;
        };
        super.move(movingToward);
    }
}
