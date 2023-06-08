package entities;

import processing.core.PConstants;
import processing.core.PImage;
import weapon.*;
import main.Main;

public class Player extends Movable {
    int baseHp;
    private Weapon weapon;
    private SwordFactory swordFactory;
    private SpearFactory spearFactory;
    float baseX;
    float baseY;

    public Player(float x, float y, int map){
        super(x, y, 20, 20, 3, 3, map);
        baseHp = 3;
        baseX = x;
        baseY = y;
        swordFactory = new SwordFactory();
        spearFactory = new SpearFactory();
        weapon = swordFactory.createWeapon(SwordType.GREATSWORD, 0);
//        weapon = spearFactory.createWeapon(SpearType.GLAIVE, 0);
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

    public void atk(){
        int atkX = (int) (getX()+(getWidth()/2));
        int atkY = (int) (getY()+(getHeight()/2));
        int WHArc = 0;
        Main.processing.noStroke();
        Main.processing.fill(255,0,0);
        if(weapon instanceof Sword && !weapon.getWeaponName().equals("Great Sword")){
            WHArc = 60;
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


    }

    public Weapon getWeapon() {
        return weapon;
    }
}
