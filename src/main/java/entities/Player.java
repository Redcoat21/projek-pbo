package entities;

import processing.core.PImage;
import weapon.Weapon;
import main.Main;

public class Player extends Movable {
    int baseHp;
    private Weapon weapon;

    public Player(float x, float y){
        super(x, y, 20, 20, 3, 3);
        baseHp = 3;
    }

    @Override
    public void render() {
        Main.processing.noStroke();
//        this.addSprites(Direction.NONE, "./assets/Tileset/tile004.png");
        Main.processing.rect(getX(), getY(), getWidth(), getHeight());
//        Animation temp = this.getAnimationList().get(this.getDirection());
//        temp.playAnimation(this);
    }

    public void heal(){
        if(getHealth() < baseHp){
            addHealth(1);
        }
    }
}
