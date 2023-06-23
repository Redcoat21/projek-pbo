package entities;

import entities.tiles.Obstacles;
import entities.tiles.Wall;
import main.Main;
import main.Map;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BigBoss extends Movable{
    private boolean agro;
    private int agroIdx;
    private int tickMove;
    private int indexDelay;
    private Player target;
    Obstacles[][] tiles;
    private boolean attack;
    private int attack_Idx_Counter;
    private long startTimeCharging;
    private long elapsedTimeCharging;
    private int elapsedSecondCharging;
    private int chargingTime;
    private boolean charged;
    private int chargingX;
    private int chargingY;
    private int chargingArc;
    private int baseX;
    private int baseY;
    public BigBoss(){
        super(0, 0, 20, 20, 0, 3, 3);
    }
    public BigBoss(int x,int y){
        super(x, y, 20, 20, 0, 3, 3);
        baseX = x;
        baseY = y;
    }
    public void resetPos(){
        setTo(baseX, baseY);
    }
    /**
     *
     * @param x x-axis that the entity will spawn in
     * @param y y-axis that the entity will spawn in
     */
    public BigBoss(float x, float y) {
        super(x, y,50,50,200,2,5, 3);
        agro = false;
        agroIdx=0;
        tickMove=0;
        indexDelay=0;
        this.tiles = map.getMap();
        attack=false;
        attack_Idx_Counter=0;
        startTime = 0;
        elapsedTime = 0;
        elapsedSecond = (int) (elapsedTime / 1000);
        startTimeCharging = 0;
        elapsedTimeCharging = 0;
        elapsedSecondCharging = (int) (elapsedTime / 1000);
        chargingTime = 2;
        charged = false;
        chargingArc = 140;
    }
    @Override
    public void render() {
        elapsedTime = System.currentTimeMillis() - startTime;
        elapsedSecond = (int) elapsedTime/1000;
        tickMove++;
//        Main.processing.text("HP "+getHealth() + "   X: "+getX()+"   Y: "+getY() + " Agro:   "+agroIdx+ " Status: "+agro+" Attack : "+attack,getX(),getY()+60);
        Main.processing.noStroke();
        Main.processing.fill(0,255,127);
        Main.processing.rect(getX(), getY(), getWidth(), getHeight());
//        j * 20, i * 20 + 80
//        Agro Mode
        if(agro){
                if(indexDelay<4)this.stop();
                this.stop();
                if(Math.abs(getXFromCenter()-target.getXFromCenter())<=280&&Math.abs(getYFromCenter()-target.getYFromCenter())<=280){
                    this.stop();
                    attack_Idx_Counter++;
                        attack=true;
                    if(attack_Idx_Counter>300) {
                        if (attack_Idx_Counter > 350) {
                            attack_Idx_Counter = 0;
                        }
                    }

                }else if(Math.abs(getX()-target.getX())>Math.abs(getY()- target.getY())&&!entitiesCollisionChecker()){
                    if(getX()> target.getX())this.moveTo(Direction.LEFT);
                    else this.moveTo(Direction.RIGHT);
                    attack=false;
                }else if(Math.abs(getX()-target.getX())>0){
                    if(getY()> target.getY())this.moveTo(Direction.UP);
                    else this.moveTo(Direction.DOWN);
                    attack=false;
                }else{
                    if(getX()> target.getX())this.moveTo(Direction.LEFT);
                    else this.moveTo(Direction.RIGHT);
                    attack=false;
                }

                Obstacles[][] init = new Obstacles[64][32];
                for(int i=0 ; i<32; i++){
                    for(int j=0; j<64; j++){
                        init[j][i] = null;
                    }
                }

        }


//        Idle Mode
        if(!agro && indexDelay>1 && indexDelay<4 && tickMove>35){
            tickMove=0;
            indexDelay++;
            this.stop();
        }else if(!agro &&indexDelay>=4&&tickMove>35){
            indexDelay=0;
        }
        if(!agro&&tickMove>35){
            indexDelay++;
            tickMove=0;
            idle();
            agroIdx++;
        }
    }

    /**
     * @param you it points to the player that the entity has agro-ed into
     */
    public void checkAgro(Player you){
        if(Math.abs(getX()-you.getX())<=800&&Math.abs(getY()-you.getY())<=800){
            target = you;
            this.agro=true;
        }else{
            target=null;
            this.agro=false;
        }
    }
    public void idle(){
        if(agroIdx>7){
            agroIdx=0;
        }
        this.stop();
        if(agroIdx>=0&&agroIdx<2)this.moveTo(Direction.DOWN);
        else if(agroIdx>=2&&agroIdx<4)this.moveTo(Direction.RIGHT);
        else if(agroIdx>=4&&agroIdx<6)this.moveTo(Direction.UP);
        else if(agroIdx>=6&&agroIdx<8)this.moveTo(Direction.LEFT);
    }

    @Override
    protected boolean entitiesIntersectHole(Obstacles e1) {
        return false;
    }

    private void charging(){
        Main.processing.fill(255,0,0,100);
        Main.processing.circle(chargingX, chargingY, chargingArc);
        Main.processing.fill(255,0,0);
        elapsedTimeCharging = System.currentTimeMillis() - startTimeCharging;
        elapsedSecondCharging = (int) elapsedTimeCharging/1000;
    }

    /**
     * @param target the entity that can be attacked by this entity
     */
    public void atk(Movable target){
        Main.processing.noStroke();
        Main.processing.fill(255,0,0);
        if(elapsedSecond > coolDown && attack && !charged) {
            charged = true;
            chargingX = (int) target.getXFromCenter();
            chargingY = (int) target.getYFromCenter();
            startTime = System.currentTimeMillis();
            startTimeCharging = System.currentTimeMillis();
            elapsedTimeCharging = System.currentTimeMillis() - startTimeCharging;
            elapsedSecondCharging = (int) elapsedTimeCharging;
        }
        else if(charged){
            charging();
        }

        if(elapsedSecondCharging > chargingTime && charged){
            thunderCollision(chargingX, chargingY, chargingArc/2, target);
            Main.processing.circle(chargingX, chargingY, chargingArc);
            System.out.println("dor");
            charged = false;
        }
    }

    /**
     * @param atkX the x point that determine the radius of the attack
     * @param atkY the y point that determine the radius of the attack
     * @param radius the surface that indicate the attack
     * @param musuh the target that will be attack if there is any
     */
    private void thunderCollision(int atkX, int atkY, int radius, Movable musuh){
        int pointOnRectX = 0;
        int pointOnRectY = 0;
        int XDistToRect = 0;
        int YDistToRect = 0;
        float dist = 0;
        pointOnRectX = clamp((int) musuh.getX(), (int) (musuh.getX()+musuh.getWidth()), atkX);
        pointOnRectY = clamp((int) musuh.getY(), (int) (musuh.getY()+musuh.getHeight()), atkY);
        XDistToRect = atkX - pointOnRectX;
        YDistToRect = atkY - pointOnRectY;

        dist = (float) Math.sqrt((XDistToRect*XDistToRect) + (YDistToRect*YDistToRect));
        if(dist < radius){
            musuh.subHP(1);
        }
    }
}
