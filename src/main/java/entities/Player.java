package entities;

import processing.core.PImage;
import weapon.Weapon;
import main.Main;

public class Player extends Entities {
    private Weapon weapon;

    public Player(){
        super(40, 400, 20, 20, 20, 5);
    }

    public Player(float x, float y){
        super(x, y, 20, 20, 20, 5);
    }

    @Override
    public void render() {
        Main.processing.noStroke();
        this.addSprites(Direction.NONE, "./assets/Tileset/tile004.png");
        Main.processing.rect(getX(), getY(), getWidth(), getHeight());
        Animation temp = this.getAnimationList().get(this.getDirection());
        temp.playAnimation(this);
    }
}
