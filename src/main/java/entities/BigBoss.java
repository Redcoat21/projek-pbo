package entities;

import entities.tiles.Obstacles;
import entities.tiles.Wall;
import main.Main;
import main.Map;

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
    private ArrayList<Direction> pathList;
    private boolean attack;
    private int attack_Idx_Counter;

//    public BigBoss(float x, float y) {
//        super(x, y,30,30,4,3, 3);
//        agro = false;
//        agroIdx=0;
//        tickMove=0;
//        indexDelay=0;
//        gotPath=false;
//    }

    public BigBoss(float x, float y, Map map) {
        super(x, y,50,50,200,2,5, 10);
        agro = false;
        agroIdx=0;
        tickMove=0;
        indexDelay=0;
        this.map=map;
        this.tiles = map.getMap();
        attack=false;
    }

    public BigBoss(float x, float y) {
        super(x, y,50,50,200,2,5, 10);
        agro = false;
        agroIdx=0;
        tickMove=0;
        indexDelay=0;
//        this.map=map;
        this.tiles = map.getMap();
        attack=false;
        attack_Idx_Counter=0;
    }
    @Override
    public void render() {
        tickMove++;
        Main.processing.text("HP "+getHealth() + "   X: "+getX()+"   Y: "+getY() + " Agro:   "+agroIdx+ " Status: "+agro+" Attack : "+attack,getX(),getY()+60);
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
            pathList=null;
            indexDelay++;
            tickMove=0;
            idle();
            agroIdx++;
        }
    }
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
}
