package entities;

import weapon.Weapon;
import main.Main;

public class Player extends Entities {
    private Weapon senjata;

    public Player(){
        super(40, 400, 20, 20, 20, false, false, false, false, 5);
    }

    public Player(int x, int y){
        super(x, y, 20, 20, 20, false, false, false, false, 5);
    }
}
