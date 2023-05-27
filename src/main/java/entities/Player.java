package entities;

import weapon.Weapon;
import main.Main;

public class Player extends Movable {
    int baseHp;
    private Weapon weapon;

    public Player(){
        super(40, 400, 20, 20, 3, 5);
        baseHp = 3;
    }

    public Player(float x, float y){
        super(x, y, 20, 20, 3, 5);
        baseHp = 3;
    }

    @Override
    public void render() {
        Main.processing.noStroke();
        Main.processing.fill(255);
        Main.processing.rect(getX(), getY(), getWidth(), getHeight());
    }

    public void heal(){
        if(getHealth() < baseHp){
            addHealth(1);
        }
    }
}
