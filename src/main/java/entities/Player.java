package entities;

import weapon.Weapon;
import main.Main;

public class Player extends Movable {
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
        Main.processing.fill(255);
        Main.processing.rect(getX(), getY(), getWidth(), getHeight());
    }
}
