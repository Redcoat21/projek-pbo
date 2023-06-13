package entities;

public class Bullet extends Movable{
    private int damage;
    public Bullet(float x, float y, int width, int height, int health, int speed, int damage, Direction facing) {
        super(x, y, width, height, health, speed, facing);
        this.damage = damage;
    }


}
