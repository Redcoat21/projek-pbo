package entities;

import entities.tiles.Obstacles;
import entities.tiles.Wall;
import main.Main;
import processing.core.PImage;

public class Bullet extends Movable{
    private float baseX;
    private float baseY;
    private int damage;
    private boolean firing;
    public Bullet(){
        //the x and y from bullet is already from center
        super(-10, -10, 60, 40, 10, 1, Direction.RIGHT);
        // loadImage();
        this.baseX = -10;
        this.baseY = -10;
        this.damage = 1;
    }

    public boolean getHit(){
        return false;
    }

    @Override
    public void render() {
        Main.processing.noStroke();
        Main.processing.fill(255,0,0);
        Main.processing.circle(getX(), getY(), getWidth());
        Main.processing.fill(255);
//        this.play("walk", this.getAtkDirection());
    }

    private void entitiesCollisionWall(){
        for(Obstacles[] obsTemp: map.getMap()){
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

    /**
     * @param x the x-axis that indicate where the bullet fired
     * @param y the y-axis that indicate where the bullet fired
     * @param speed the speed which the bullet travels
     * @param damage the damage that the bullet does
     * @param facing the direction that the bullet is facing
     */
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

    public int getDamage(){
        return damage;
    }

//    private void loadImage() {
//        String root = "src/main/resources/assets/";
//
//        Direction[] arrOfDirection = { Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN };
//
//        for(Direction d : arrOfDirection) {
//            String direction = switch(d) {
//                case DOWN -> "down";
//                case LEFT -> "left";
//                case RIGHT -> "right";
//                case UP -> "up";
//                default -> null;
//            };
//
//            PImage temp = Main.processing.loadImage(root + String.format("Sprites/Arrow/arrow_%s.png", direction));
//            this.addSprites("walk", d, temp, this.getSize());
//        }
//
////        PImage temp = Main.processing.loadImage(root + String.format("Sprites/Arrow/arrow_down.png"));
////        this.addSprites("walk", Direction.RIGHT, temp, this.getSize());
//    }
}
