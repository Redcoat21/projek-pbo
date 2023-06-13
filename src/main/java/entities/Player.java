package entities;

import main.Map;
import processing.core.PConstants;
import processing.core.PImage;
import weapon.*;
import main.Main;

import java.util.ArrayList;

public class Player extends Movable {
    int baseHp;
    private Weapon weapon;
    private SwordFactory swordFactory;
    private SpearFactory spearFactory;
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
        super(x, y, 20, 20, 3, 3, 3, map);
        baseHp = 3;
        baseX = x;
        baseY = y;
        swordFactory = new SwordFactory();
        spearFactory = new SpearFactory();
        weapon = swordFactory.createWeapon(SwordType.IRON_SWORD, 0);
//        weapon = spearFactory.createWeapon(SpearType.IRON_SPEAR, 0);
    }

    @Override
    public void render() {
        Main.processing.noStroke();
//        this.addSprites(Direction.NONE, "./assets/Tileset/tile004.png");
        Main.processing.rect(getX(), getY(), getWidth(), getHeight());
//        for (int i=0;i<32;i++){
//            for (int j=0;j<64;j++){
//                if(this.entitiesIntersectWall(new Obstacles(j*20,i*20+80))){
//                    Main.processing.text("X: "+j+"   Y: "+i,getX(),getY()+100);
//                }
//            }
//        }
//        Animation temp = this.getAnimationList().get(this.getDirection());
//        temp.playAnimation(this);
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

    public void atk(ArrayList<Movable> enemy){
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
    }

    private void stabAtkCollision(int atkX, int atkY, int width, int height, ArrayList<Movable> enemy, Direction direction){
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

    private void swingAtkCollision(int atkX, int atkY, int radius, ArrayList<Movable> enemy, Direction direction){
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
                    System.out.println("kena kanan");
                    musuh.subHP(weapon.calculateDamageDealt());
//                    return true;
                }
                else if(direction.equals(Direction.LEFT) && XDistToRect>=0){
                    System.out.println("kena kiri");
                    musuh.subHP(weapon.calculateDamageDealt());
//                    return true;
                }
                else if(direction.equals(Direction.UP) && YDistToRect>=0){
                    System.out.println("kena atas");
                    musuh.subHP(weapon.calculateDamageDealt());
//                    return true;
                }
                else if(direction.equals(Direction.DOWN) && YDistToRect<=0){
                    System.out.println("kena bawah");
//                    System.out.println("dist: " + dist);
//                    System.out.println("arc: " + radius);
                    musuh.subHP(weapon.calculateDamageDealt());
//                    return true;
                }
            }
        }
//        return false;
    }

    private int clamp(int min, int max, int value){
        if(min > value){
            return min;
        }
        else if(max < value){
            return max;
        }
        else{
            return value;
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public float getAtkSpeed() {
        return atkSpeed/5 * weapon.getWeight();
    }
}
