package entities;

import main.Map;
import org.w3c.dom.ranges.Range;
import processing.core.PConstants;
import processing.core.PImage;
import weapon.*;
import main.Main;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player extends Movable {
    int baseHp;
    private Weapon weapon;
    private Weapon nextWeapon;
    private SwordFactory swordFactory;
    private SpearFactory spearFactory;
    private RangedFactory rangedFactory;
    private Bullet[] bullets;
    float baseX;
    float baseY;

//    public Player(float x, float y, int map){
//        super(x, y, 20, 20, 3, 3, 4, new Map(map));
//        baseHp = 3;
//        baseX = x;
//        baseY = y;
//        swordFactory = new SwordFactory();
//        spearFactory = new SpearFactory();
//        weapon = swordFactory.createWeapon(SwordType.IRON_SWORD, 0);
////        weapon = spearFactory.createWeapon(SpearType.GLAIVE, 0);
//    }

    public Player(float x, float y, Map map){
        super(x, y, 20, 20, 5, 3, 3, map);
        loadImage();
        baseHp = getHealth();
        baseX = x;
        baseY = y;
        swordFactory = new SwordFactory();
        spearFactory = new SpearFactory();
        rangedFactory = new RangedFactory();
        bullets = new Bullet[10];
        int temp = 1;
        for(int i=0; i<bullets.length; i++){
            bullets[i] = new Bullet();
        }
        weapon = swordFactory.createWeapon(SwordType.IRON_SWORD, 0);
//        weapon = spearFactory.createWeapon(SpearType.IRON_SPEAR, 0);
//        weapon = rangedFactory.createWeapon(RangedType.IRON_BOW, 0);
        nextWeapon = null;
    }

    @Override
    public void render() {
//        Main.processing.noStroke();
        this.play("walk", this.getAtkDirection());
//        this.addSprites(Direction.NONE, "./assets/Tileset/tile004.png");
//        Main.processing.rect(getX(), getY(), getWidth(), getHeight());
    }

    public void heal(){
        if(getHealth() < baseHp){
            addHealth(1);
        }
    }



    public void resetPos(){
        setTo(baseX, baseY);
    }

//    public void atk(ArrayList<Movable> enemy){
//        int atkX = (int) getXFromCenter();
//        int atkY = (int) getYFromCenter();
//        int WHArc = 0;
//        int Wstab = 0;
//        int Hstab = 0;
//        Main.processing.noStroke();
//        Main.processing.fill(255,0,0);
//        if(weapon instanceof Sword && !weapon.getWeaponName().equals("Great Sword")){
//            WHArc = 60;
////            System.out.println(swingAtkCollision(atkX, atkY, WHArc, enemy, getAtkDirection()));
//            swingAtkCollision(atkX, atkY, WHArc/2, enemy, getAtkDirection());
//            if(getAtkDirection().equals(Direction.RIGHT)){
//                Main.processing.arc(atkX, atkY, WHArc, WHArc, -PConstants.HALF_PI, PConstants.HALF_PI);
//            }
//            else if(getAtkDirection().equals(Direction.DOWN)){
//                Main.processing.arc(atkX, atkY, WHArc, WHArc, 0, PConstants.PI);
//            }
//            else if(getAtkDirection().equals(Direction.LEFT)){
//                Main.processing.arc(atkX, atkY, WHArc, WHArc, PConstants.HALF_PI, PConstants.PI+PConstants.HALF_PI);
//            }
//            else if(getAtkDirection().equals(Direction.UP)){
//                Main.processing.arc(atkX, atkY, WHArc, WHArc, PConstants.PI, PConstants.TWO_PI);
//            }
//        }
//        else if(weapon instanceof Sword && weapon.getWeaponName().equals("Great Sword")){
//            WHArc = 80;
//            swingAtkCollision(atkX, atkY, WHArc/2, enemy, getAtkDirection());
//            if(getAtkDirection().equals(Direction.RIGHT)){
//                Main.processing.arc(atkX, atkY, WHArc, WHArc, -PConstants.HALF_PI, PConstants.HALF_PI);
//            }
//            else if(getAtkDirection().equals(Direction.DOWN)){
//                Main.processing.arc(atkX, atkY, WHArc, WHArc, 0, PConstants.PI);
//            }
//            else if(getAtkDirection().equals(Direction.LEFT)){
//                Main.processing.arc(atkX, atkY, WHArc, WHArc, PConstants.HALF_PI, PConstants.PI+PConstants.HALF_PI);
//            }
//            else if(getAtkDirection().equals(Direction.UP)){
//                Main.processing.arc(atkX, atkY, WHArc, WHArc, PConstants.PI, PConstants.TWO_PI);
//            }
//        }
//        else if(weapon instanceof Spear && weapon.getWeaponName().equals("Glaive")){
//            WHArc = 100;
//            swingAtkCollision(atkX, atkY, WHArc/2, enemy, getAtkDirection());
//            if(getAtkDirection().equals(Direction.RIGHT)){
//                Main.processing.arc(atkX, atkY, WHArc, WHArc, -PConstants.HALF_PI, PConstants.HALF_PI);
//            }
//            else if(getAtkDirection().equals(Direction.DOWN)){
//                Main.processing.arc(atkX, atkY, WHArc, WHArc, 0, PConstants.PI);
//            }
//            else if(getAtkDirection().equals(Direction.LEFT)){
//                Main.processing.arc(atkX, atkY, WHArc, WHArc, PConstants.HALF_PI, PConstants.PI+PConstants.HALF_PI);
//            }
//            else if(getAtkDirection().equals(Direction.UP)){
//                Main.processing.arc(atkX, atkY, WHArc, WHArc, PConstants.PI, PConstants.TWO_PI);
//            }
//        }
//        else if(weapon instanceof Spear && weapon.getWeaponName().equals("Iron Spear")){
//            Hstab = 20;
//            Wstab = 50;
//            stabAtkCollision(atkX, atkY, Wstab, Hstab, enemy, getAtkDirection());
//            if(getAtkDirection().equals(Direction.RIGHT)){
//                Main.processing.quad(atkX, atkY - Hstab/2, atkX, atkY + Hstab/2,atkX + Wstab, atkY + Hstab/2, atkX + Wstab, atkY - Hstab/2);
//            }
//            else if(getAtkDirection().equals(Direction.DOWN)){
//                Main.processing.quad(atkX - Hstab/2, atkY, atkX + Hstab/2, atkY,atkX + Hstab/2, atkY + Wstab, atkX - Hstab/2, atkY + Wstab);
//            }
//            else if(getAtkDirection().equals(Direction.LEFT)){
//                Main.processing.quad(atkX, atkY - Hstab/2, atkX, atkY + Hstab/2,atkX - Wstab, atkY + Hstab/2, atkX - Wstab, atkY - Hstab/2);
//            }
//            else if(getAtkDirection().equals(Direction.UP)){
//                Main.processing.quad(atkX - Hstab/2, atkY, atkX + Hstab/2, atkY,atkX + Hstab/2, atkY - Wstab, atkX - Hstab/2, atkY - Wstab);
//            }
//        }
//        else if(weapon instanceof Spear && weapon.getWeaponName().equals("Spear of The Lord")){
//            Hstab = 30;
//            Wstab = 70;
//            stabAtkCollision(atkX, atkY, Wstab, Hstab, enemy, getAtkDirection());
//            if(getAtkDirection().equals(Direction.RIGHT)){
//                Main.processing.quad(atkX, atkY - Hstab/2, atkX, atkY + Hstab/2,atkX + Wstab, atkY + Hstab/2, atkX + Wstab, atkY - Hstab/2);
//            }
//            else if(getAtkDirection().equals(Direction.DOWN)){
//                Main.processing.quad(atkX - Hstab/2, atkY, atkX + Hstab/2, atkY,atkX + Hstab/2, atkY + Wstab, atkX - Hstab/2, atkY + Wstab);
//            }
//            else if(getAtkDirection().equals(Direction.LEFT)){
//                Main.processing.quad(atkX, atkY - Hstab/2, atkX, atkY + Hstab/2,atkX - Wstab, atkY + Hstab/2, atkX - Wstab, atkY - Hstab/2);
//            }
//            else if(getAtkDirection().equals(Direction.UP)){
//                Main.processing.quad(atkX - Hstab/2, atkY, atkX + Hstab/2, atkY,atkX + Hstab/2, atkY - Wstab, atkX - Hstab/2, atkY - Wstab);
//            }
//        }
//        else if(weapon instanceof Ranged){
//            for(int i=0; i< bullets.length; i++){
//                if(!bullets[i].isFired()){
//                    bullets[i].fired(atkX, atkY, ((Ranged) weapon).getSpeed(), ((Ranged) weapon).calculateDamageDealt(), getAtkDirection());
//                    break;
//                }
//            }
//        }
//    }

    /**
     * @param enemy the enemy that the entity will attack
     */
    public void atk(Movable[] enemy){
        int atkX = (int) getXFromCenter();
        int atkY = (int) getYFromCenter();
        int WHArc = 0;
        int Wstab = 0;
        int Hstab = 0;
        Main.processing.noStroke();
        Main.processing.fill(255,0,0);
        if(weapon instanceof Sword && !weapon.getWeaponName().equals("Great Sword")){
            WHArc = 60;
//            System.out.println(swingAtkCollision(atkX, atkY, WHArc, enemy, getAtkDirection()));
            swingAtkCollision(atkX, atkY, WHArc/2, enemy, getAtkDirection());
            if(getAtkDirection().equals(Direction.RIGHT)){
                Main.processing.arc(atkX, atkY, WHArc, WHArc, -PConstants.HALF_PI, PConstants.HALF_PI);
            }
            else if(getAtkDirection().equals(Direction.DOWN)){
                Main.processing.arc(atkX, atkY, WHArc, WHArc, 0, PConstants.PI);
            }
            else if(getAtkDirection().equals(Direction.LEFT)){
                Main.processing.arc(atkX, atkY, WHArc, WHArc, PConstants.HALF_PI, PConstants.PI+PConstants.HALF_PI);
            }
            else if(getAtkDirection().equals(Direction.UP)){
                Main.processing.arc(atkX, atkY, WHArc, WHArc, PConstants.PI, PConstants.TWO_PI);
            }
        }
        else if(weapon instanceof Sword && weapon.getWeaponName().equals("Great Sword")){
            WHArc = 80;
            swingAtkCollision(atkX, atkY, WHArc/2, enemy, getAtkDirection());
            if(getAtkDirection().equals(Direction.RIGHT)){
                Main.processing.arc(atkX, atkY, WHArc, WHArc, -PConstants.HALF_PI, PConstants.HALF_PI);
            }
            else if(getAtkDirection().equals(Direction.DOWN)){
                Main.processing.arc(atkX, atkY, WHArc, WHArc, 0, PConstants.PI);
            }
            else if(getAtkDirection().equals(Direction.LEFT)){
                Main.processing.arc(atkX, atkY, WHArc, WHArc, PConstants.HALF_PI, PConstants.PI+PConstants.HALF_PI);
            }
            else if(getAtkDirection().equals(Direction.UP)){
                Main.processing.arc(atkX, atkY, WHArc, WHArc, PConstants.PI, PConstants.TWO_PI);
            }
        }
        else if(weapon instanceof Spear && weapon.getWeaponName().equals("Glaive")){
            WHArc = 100;
            swingAtkCollision(atkX, atkY, WHArc/2, enemy, getAtkDirection());
            if(getAtkDirection().equals(Direction.RIGHT)){
                Main.processing.arc(atkX, atkY, WHArc, WHArc, -PConstants.HALF_PI, PConstants.HALF_PI);
            }
            else if(getAtkDirection().equals(Direction.DOWN)){
                Main.processing.arc(atkX, atkY, WHArc, WHArc, 0, PConstants.PI);
            }
            else if(getAtkDirection().equals(Direction.LEFT)){
                Main.processing.arc(atkX, atkY, WHArc, WHArc, PConstants.HALF_PI, PConstants.PI+PConstants.HALF_PI);
            }
            else if(getAtkDirection().equals(Direction.UP)){
                Main.processing.arc(atkX, atkY, WHArc, WHArc, PConstants.PI, PConstants.TWO_PI);
            }
        }
        else if(weapon instanceof Spear && weapon.getWeaponName().equals("Iron Spear")){
            Hstab = 20;
            Wstab = 50;
            stabAtkCollision(atkX, atkY, Wstab, Hstab, enemy, getAtkDirection());
            if(getAtkDirection().equals(Direction.RIGHT)){
                Main.processing.quad(atkX, atkY - Hstab/2, atkX, atkY + Hstab/2,atkX + Wstab, atkY + Hstab/2, atkX + Wstab, atkY - Hstab/2);
            }
            else if(getAtkDirection().equals(Direction.DOWN)){
                Main.processing.quad(atkX - Hstab/2, atkY, atkX + Hstab/2, atkY,atkX + Hstab/2, atkY + Wstab, atkX - Hstab/2, atkY + Wstab);
            }
            else if(getAtkDirection().equals(Direction.LEFT)){
                Main.processing.quad(atkX, atkY - Hstab/2, atkX, atkY + Hstab/2,atkX - Wstab, atkY + Hstab/2, atkX - Wstab, atkY - Hstab/2);
            }
            else if(getAtkDirection().equals(Direction.UP)){
                Main.processing.quad(atkX - Hstab/2, atkY, atkX + Hstab/2, atkY,atkX + Hstab/2, atkY - Wstab, atkX - Hstab/2, atkY - Wstab);
            }
        }
        else if(weapon instanceof Spear && weapon.getWeaponName().equals("Spear of The Lord")){
            Hstab = 30;
            Wstab = 70;
            stabAtkCollision(atkX, atkY, Wstab, Hstab, enemy, getAtkDirection());
            if(getAtkDirection().equals(Direction.RIGHT)){
                Main.processing.quad(atkX, atkY - Hstab/2, atkX, atkY + Hstab/2,atkX + Wstab, atkY + Hstab/2, atkX + Wstab, atkY - Hstab/2);
            }
            else if(getAtkDirection().equals(Direction.DOWN)){
                Main.processing.quad(atkX - Hstab/2, atkY, atkX + Hstab/2, atkY,atkX + Hstab/2, atkY + Wstab, atkX - Hstab/2, atkY + Wstab);
            }
            else if(getAtkDirection().equals(Direction.LEFT)){
                Main.processing.quad(atkX, atkY - Hstab/2, atkX, atkY + Hstab/2,atkX - Wstab, atkY + Hstab/2, atkX - Wstab, atkY - Hstab/2);
            }
            else if(getAtkDirection().equals(Direction.UP)){
                Main.processing.quad(atkX - Hstab/2, atkY, atkX + Hstab/2, atkY,atkX + Hstab/2, atkY - Wstab, atkX - Hstab/2, atkY - Wstab);
            }
        }
        else if(weapon instanceof Ranged){
            for(int i=0; i< bullets.length; i++){
                if(!bullets[i].isFired()){
                    bullets[i].fired(atkX, atkY, ((Ranged) weapon).getSpeed(), ((Ranged) weapon).calculateDamageDealt(), getAtkDirection());
                    break;
                }
            }
        }
    }

    /**
     * @param enemy the target that the player targets
     */
    public void bulletAtkCollision(ArrayList<Movable> enemy){
        int pointOnRectX = 0;
        int pointOnRectY = 0;
        int XDistToRect = 0;
        int YDistToRect = 0;
        float dist = 0;

        for(int i=0; i<bullets.length; i++){
            if(bullets[i].isFired()){
                for(Movable musuh: enemy){
                    pointOnRectX = clamp((int) musuh.getX(), (int) (musuh.getX()+musuh.getWidth()), (int) bullets[i].getX());
                    pointOnRectY = clamp((int) musuh.getY(), (int) (musuh.getY()+musuh.getHeight()), (int) bullets[i].getY());
                    XDistToRect = (int) bullets[i].getX() - pointOnRectX;
                    YDistToRect = (int) bullets[i].getY() - pointOnRectY;
                    dist = (float) Math.sqrt((XDistToRect*XDistToRect) + (YDistToRect*YDistToRect));
                    if(dist < bullets[i].getWidth() && bullets[i].isFired()){
                        musuh.subHP(weapon.calculateDamageDealt());
                        bullets[i].hit();
                    }
                }
            }
        }
    }

    /**
     * @param enemy if the bullet collision with the target
     */
    public void bulletAtkCollision(Movable[] enemy){
        int pointOnRectX = 0;
        int pointOnRectY = 0;
        int XDistToRect = 0;
        int YDistToRect = 0;
        float dist = 0;

        for(int i=0; i<bullets.length; i++){
            if(bullets[i].isFired()){
                for(Movable musuh: enemy){
                    pointOnRectX = clamp((int) musuh.getX(), (int) (musuh.getX()+musuh.getWidth()), (int) bullets[i].getX());
                    pointOnRectY = clamp((int) musuh.getY(), (int) (musuh.getY()+musuh.getHeight()), (int) bullets[i].getY());
                    XDistToRect = (int) bullets[i].getX() - pointOnRectX;
                    YDistToRect = (int) bullets[i].getY() - pointOnRectY;
                    dist = (float) Math.sqrt((XDistToRect*XDistToRect) + (YDistToRect*YDistToRect));
                    if(dist < bullets[i].getWidth() && bullets[i].isFired()){
                        musuh.subHP(weapon.calculateDamageDealt());
                        bullets[i].hit();
                    }
                }
            }
        }
    }

    /**
     * @param atkX the x-point that indicate the center of the attack radius
     * @param atkY the y-point that indicate the center of the attack radius
     * @param width the width of the attack radius
     * @param height the height of the attack radius
     * @param enemy the enemy that's in the radius
     * @param direction the direction that the attack is facing
     */
    private void stabAtkCollision(int atkX, int atkY, int width, int height, Movable[] enemy, Direction direction){
        int Xcenter = atkX;
        int Ycenter = atkY;
        int aWidth = width;
        int aHeight = height;
        float distX = 0;
        float distY = 0;
        float combWidth = 0;
        float combHeight = 0;

        if(direction.equals(Direction.RIGHT)){
            Xcenter = atkX + width/2;
        }
        else if(direction.equals(Direction.DOWN)){
            Ycenter = atkY + width/2;
            aWidth = height;
            aHeight = width;
        }
        else if(direction.equals(Direction.LEFT)){
            Xcenter = atkX - width/2;
        }
        else if(direction.equals(Direction.UP)){
            Ycenter = atkY - width/2;
            aWidth = height;
            aHeight = width;
        }

        for(Movable musuh: enemy){
            combWidth = (aWidth+musuh.getWidth())/2;
            combHeight = (aHeight+musuh.getHeight())/2;
            distX = Math.abs(Xcenter - musuh.getXFromCenter());
            distY = Math.abs(Ycenter - musuh.getYFromCenter());

            if(distX <= combWidth && distY <=combHeight){
                musuh.subHP(weapon.calculateDamageDealt());
            }
        }
    }

    /**
     * @param atkX the x-point that the swing centered upon
     * @param atkY the y-point that the swing centered upon
     * @param radius the radius of the swing
     * @param enemy the enemy that collide with the swing
     * @param direction the direction of the swing
     */
    private void swingAtkCollision(int atkX, int atkY, int radius, Movable[] enemy, Direction direction){
        int pointOnRectX = 0;
        int pointOnRectY = 0;
        int XDistToRect = 0;
        int YDistToRect = 0;
        float dist = 0;
        for(Movable musuh: enemy){
            pointOnRectX = clamp((int) musuh.getX(), (int) (musuh.getX()+musuh.getWidth()), atkX);
            pointOnRectY = clamp((int) musuh.getY(), (int) (musuh.getY()+musuh.getHeight()), atkY);
//            System.out.println(pointOnRectX + ", " + pointOnRectY);
            XDistToRect = atkX - pointOnRectX;
            YDistToRect = atkY - pointOnRectY;
//            System.out.println(XDistToRect + ", " + YDistToRect);
            dist = (float) Math.sqrt((XDistToRect*XDistToRect) + (YDistToRect*YDistToRect));
//            System.out.println("dist gak kena: " + dist);
//            System.out.println("arcnya: " + radius);
            if(dist < radius){
                if(direction.equals(Direction.RIGHT) && XDistToRect<=0){
//                    System.out.println("kena kanan");
                    musuh.subHP(weapon.calculateDamageDealt());
//                    return true;
                }
                else if(direction.equals(Direction.LEFT) && XDistToRect>=0){
//                    System.out.println("kena kiri");
                    musuh.subHP(weapon.calculateDamageDealt());
//                    return true;
                }
                else if(direction.equals(Direction.UP) && YDistToRect>=0){
//                    System.out.println("kena atas");
                    musuh.subHP(weapon.calculateDamageDealt());
//                    return true;
                }
                else if(direction.equals(Direction.DOWN) && YDistToRect<=0){
//                    System.out.println("kena bawah");
//                    System.out.println("dist: " + dist);
//                    System.out.println("arc: " + radius);
                    musuh.subHP(weapon.calculateDamageDealt());
//                    return true;
                }
            }
        }
//        return false;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public float getAtkSpeed() {
        return atkSpeed/5 * weapon.getWeight();
    }

    @Override
    public void move() {
        super.move();
        for(int i=0; i < bullets.length; i++){
            if(bullets[i].isFired()) {
//                System.out.println("bullet " + i + " gerak");
                bullets[i].move();
                bullets[i].render();
            }
        }
    }

    public void clearBullet(){
        for(int i=0; i< bullets.length; i++){
            bullets[i].hit();
        }
    }

    public void generateNextWeapon(int phase){
        int type = rand.nextInt(3);
        int rarity = rand.nextInt(30);

        /*
        if type = 1
        rarity from 0-19 = common
        rarity from 20-25 = epic
        rarity from 26-29 = unique

        if type = 2
        rarity from 0-19 = common
        rarity from 20-25 = epic
        rarity from 26-29 = legendary

        if type = 3
        rarity from 0-25 = common
        rarity from 26-29 = epic
         */

        if(type == 1){
            if(rarity < 20){
                nextWeapon = swordFactory.createWeapon(SwordType.IRON_SWORD, phase);
            }
            else if(rarity < 26){
                nextWeapon = swordFactory.createWeapon(SwordType.GOLDEN_SWORD, phase);
            }
            else{
                nextWeapon = swordFactory.createWeapon(SwordType.GREATSWORD, phase);
            }
        }
        else if(type == 2){
            if(rarity < 20){
                nextWeapon = spearFactory.createWeapon(SpearType.IRON_SPEAR, phase);
            }
            else if(rarity < 26){
                nextWeapon = spearFactory.createWeapon(SpearType.GLAIVE, phase);
            }
            else{
                nextWeapon = spearFactory.createWeapon(SpearType.SPEAR_OF_THE_LORD, phase);
            }
        }
        else{
            if(rarity < 26){
                nextWeapon = rangedFactory.createWeapon(RangedType.WOOD_BOW, phase);
            }
            else{
                nextWeapon = rangedFactory.createWeapon(RangedType.IRON_BOW, phase);
            }
        }
    }

    public void switchWeapon(){
        weapon = nextWeapon;
        nextWeapon = null;
    }

    public String getNextWeaponName() {
        return nextWeapon.getWeaponName();
    }

    private void loadImage() {
        String root = "src/main/resources/assets/Sprites/Player/";
        Direction[] temp = new Direction[4];

        temp[0] = Direction.RIGHT;
        temp[1] = Direction.LEFT;
        temp[2] = Direction.UP;
        temp[3] = Direction.DOWN;

        for(Direction d : temp) {
            for (int i = 0; i < 4; i++) {
                String directionString = switch(d) {
                    case UP -> "up";
                    case DOWN -> "down";
                    case RIGHT -> "right";
                    case LEFT -> "left";
                    default -> null;
                };

                PImage temp2 = Main.processing.loadImage(root + String.format("player_walk_%s%d.png", directionString, i + 1));
                this.addSprites("walk", d, temp2, this.getSize());
            }
        }
    }
}
