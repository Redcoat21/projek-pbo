package entities;

import processing.core.PImage;
import weapon.Weapon;
import main.Main;

public class Player extends Movable {
    int baseHp;
    private Weapon weapon;
    float baseX;
    float baseY;

    public Player(float x, float y, int map){
        super(x, y, 20, 20, 3, 3, map);
        baseHp = 3;
        baseX = x;
        baseY = y;
    }

    @Override
    public void render() {
        Main.processing.noStroke();
        Main.processing.rect(getX(), getY(), getWidth(), getHeight());
    }

    public void heal(){
        if(getHealth() < baseHp){
            addHealth(1);
        }
    }

    public boolean isDead(){
        if(getHealth() < 1){
            return true;
        }
        return false;
    }

    public void resetPos(){
        setTo(baseX, baseY);
    }
}
