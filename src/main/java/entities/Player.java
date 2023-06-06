package entities;

import entities.tiles.Obstacles;
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
        for (int i=0;i<32;i++){
            for (int j=0;j<64;j++){
                if(this.entitiesIntersectWall(new Obstacles(j*20,i*20+80))){
                    Main.processing.text("X: "+j+"   Y: "+i,getX(),getY()+100);
                }
            }
        }
//        Animation temp = this.getAnimationList().get(this.getDirection());
//        temp.playAnimation(this);
    }

    public void heal(){
        if(getHealth() < baseHp){
            addHealth(1);
        }
    }
}
