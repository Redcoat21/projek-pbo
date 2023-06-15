package entities;

import entities.tiles.Obstacles;
import entities.tiles.Wall;
import main.Main;

public class BulletOld extends MovableOld {
    private float baseX;
    private float baseY;
    private int damage;
    private boolean firing;
    public BulletOld(float x, float y, int radius, int health, int speed, int damage, Direction facing) {
        super(x, y, radius, radius, health, speed, facing);
        this.damage = damage;
        firing = false;
    }

    public BulletOld(){
        //the x and y from bullet is already from center
        super(-10, -10, 10, 1, 10, 1, Direction.RIGHT);
        this.baseX = -10;
        this.baseY = -10;
        this.damage = 0;
    }

    public boolean getHit(){
        return false;
    }

    @Override
    public void render() {
        Main.processing.noStroke();
        Main.processing.fill(255,0,0);
        Main.processing.circle(getX(), getY(), getWidth());
    }

    private void entitiesCollisionWall(){
        for(Obstacles[] obsTemp: mapOld.getMap()){
            for(Obstacles obs: obsTemp){
                if(obs != null) {
                    if (entitiesIntersectWall(obs) && obs instanceof Wall) {
                        fallen();
                    }
                }
            }
        }
    }

    @Override
    public void move() {
        if(firing){
            moving();
        }
        if(isEntitiesCollisionWall()){
            firing = false;
            setSpeed(0);
            setTo(-10, -10);
        }
    }

    public void fired(float x, float y, int speed, int damage, Direction facing){
        firing = true;
        float baseX = x;
        float baseY = y;
        setTo(x, y);
        this.damage = damage;
        setSpeed(speed);
        moveTo(facing);
    }

    public boolean isFired(){
        return firing;
    }

    public void hit(){
        firing = false;
    }
}
