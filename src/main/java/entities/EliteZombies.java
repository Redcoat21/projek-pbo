package entities;

import entities.tiles.Obstacles;
import entities.tiles.Wall;
import main.Main;
import main.Map;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EliteZombies extends Movable implements Pathfinding{
    private boolean agro;
    private int agroIdx;
    private int tickMove;
    private int indexDelay;
    private Player target;
    Obstacles[][] tiles;
    private ArrayList<Direction> pathList;
    private int pathIdx;
    private boolean gotPath;
    private boolean attack;
    private boolean eligible;

//    public EliteZombies(float x, float y) {
//        super(x, y,30,30,4,3, 4);
//        agro = false;
//        agroIdx=0;
//        tickMove=0;
//        indexDelay=0;
//        gotPath=false;
//    }

    /**
     * @param x the x-axis that the entity will be spawned
     * @param y the y-axis that the entity will be spawned
     */
    public EliteZombies(float x, float y) {
        super(x, y,30,30,22,2,4, 4);
        agro = false;
        agroIdx=0;
        tickMove=0;
        indexDelay=0;
        this.tiles = map.getMap();
        pathIdx=0;
        attack = false;
        eligible = false;
        startTime = 0;
        elapsedTime = 0;
        elapsedSecond = (int) (elapsedTime / 1000);
    }
    @Override
    public void render() {
        elapsedTime = System.currentTimeMillis() - startTime;
        elapsedSecond = (int) elapsedTime/1000;
        tickMove++;
//        Main.processing.text("HP "+getHealth() + "   X: "+getX()+"   Y: "+getY() + " Agro:   "+agroIdx+ " Status: "+agro,getX(),getY()+60);
        Main.processing.noStroke();
        Main.processing.fill(0,255,127);
        Main.processing.rect(getX(), getY(), getWidth(), getHeight());
//        Main.processing.text("Attack : "+attack+" face: "+getAtkDirection(),getX(),getY()+160);
//        j * 20, i * 20 + 80
//        Agro Mode
        if(agro){
            if(map==null){
                if(indexDelay<4)this.stop();
                this.stop();
                if(Math.abs(getX()-target.getX())>Math.abs(getY()- target.getY())&&!entitiesCollisionChecker()){
                    if(getX()> target.getX())this.moveTo(Direction.LEFT);
                    else this.moveTo(Direction.RIGHT);
                }else if(Math.abs(getX()-target.getX())>0){
                    if(getY()> target.getY())this.moveTo(Direction.UP);
                    else this.moveTo(Direction.DOWN);
                }else{
                    if(getX()> target.getX())this.moveTo(Direction.LEFT);
                    else this.moveTo(Direction.RIGHT);
                }
                Obstacles[][] init = new Obstacles[64][32];
                for(int i=0 ; i<32; i++){
                    for(int j=0; j<64; j++){
                        init[j][i] = null;
                    }
                }
            }else{
                if(pathList==null){
                    pathList=getNextDirection(new ArrayList<Direction>(),getObjectCoords()[0],getObjectCoords()[1],new Obstacles[64][32]);
                }else{
                    pathIdx+=getSpeed();
                    if(pathIdx<(pathList.size()*20)-1){
                        if(pathIdx!=(pathList.size()*20)-1){
                            this.moveTo(pathList.get(pathIdx/20));
                        }
//                        Main.processing.text("Direction : "+pathList.get(pathIdx/20)+" Idx : "+pathIdx,getX(),getY()+120);
                        if(pathList.get(pathIdx/20)!=Direction.NONE)eligible=false;
                    }else{
                        this.moveTo(Direction.NONE);
                        this.stop();
                        pathIdx=0;
                        pathList=null;
                        gotPath=false;
                        eligible=true;
                        if(target.getXFromCenter() < getXFromCenter() && target.getYFromCenter() > getY() && target.getYFromCenter() < getY()+getHeight()){
                            facingTo(Direction.LEFT);
                        }
                        else if(target.getXFromCenter() > getXFromCenter() && target.getYFromCenter() > getY() && target.getYFromCenter() < getY()+getHeight()){
                            facingTo(Direction.RIGHT);
                        }
                        else if(target.getYFromCenter() < getYFromCenter()){
                            facingTo(Direction.UP);
                        }
                        else if(target.getYFromCenter() > getYFromCenter()){
                            facingTo(Direction.DOWN);
                        }
                    }
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
            gotPath=false;
            pathIdx=0;
            indexDelay++;
            tickMove=0;
            idle();
            agroIdx++;
        }
    }

    /**
     * @param you the target that the entity has agro-ed upon
     */
    public void checkAgro(Player you){
        if(Math.abs(getX()-you.getX())<=200&&Math.abs(getY()-you.getY())<=200){
            target = you;
            this.agro=true;
            if(Math.abs(getX()-you.getX())<=200&&Math.abs(getY()-you.getY())<=200&&eligible){
                attack=true;
            }else attack =false;
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
    public ArrayList<Direction> getNextDirection(ArrayList<Direction> dlist, int x, int y, Obstacles[][] moved) {
        if(Math.abs(x-getTargetCoords()[0])<=1&&Math.abs(y-getTargetCoords()[1])<=1){
            dlist.add(Direction.NONE);
            gotPath=true;
            System.out.println("LELE");
            return dlist;
        } else{
            ArrayList<ValueTile> moves = new ArrayList<>();
            if(x-1>=0 && Math.abs((x-1)-getTargetCoords()[0])<=10&& !gotPath && Math.abs((x)*20- getTargetCoords()[0]*20)!=0)moves.add(new ValueTile(Math.abs((x-1)*20- getTargetCoords()[0]*20),x-1,y,Direction.LEFT));
            if(x+1<64 && Math.abs((x+1)-getTargetCoords()[0])<=10&& !gotPath && Math.abs((x)*20-getTargetCoords()[0]*20)!=0)moves.add(new ValueTile(Math.abs((x+1)*20-getTargetCoords()[0]*20),x+1,y,Direction.RIGHT));
            if(y-1>=0 && Math.abs((y-1)-getTargetCoords()[1])<=10&& !gotPath && Math.abs((y)*20+80-(getTargetCoords()[1]*20+80))!=0)moves.add(new ValueTile(Math.abs((y-1)*20+80-(getTargetCoords()[1]*20+80)),x,y-1,Direction.UP));
            if(y+1<32 && Math.abs((y+1)-getTargetCoords()[1])<=10 && !gotPath && Math.abs((y)*20+80- (getTargetCoords()[1]*20+80))!=0)moves.add(new ValueTile(Math.abs((y+1)*20+80- (getTargetCoords()[1]*20+80)),x,y+1,Direction.DOWN));
            if(moves!=null){
                Collections.sort(moves, new Comparator<ValueTile>() {
                    @Override
                    public int compare(ValueTile o1, ValueTile o2) {
                        return (int)(o1.getValue()-o2.getValue());
                    }
                });
//                for (ValueTile a:moves) System.out.print(a.getValue()+"  Direction : "+a.getMoved()+" ");
                System.out.println();
                for (ValueTile a : moves){
                    if(!gotPath){
                        if(tiles[a.getX()][a.getY()] instanceof Wall);
                        else if(moved[a.getX()][a.getY()]==null){
                            dlist.add(a.getMoved());
                            moved[a.getX()][a.getY()] = new Obstacles(a.getX(),a.getY());
                            getNextDirection(dlist,a.getX(),a.getY(),moved);
                        }
                    }
                }
            }
        }
        System.out.println("LALA");
        return dlist;

    }

    @Override
    public int[] getTargetCoords() {
        int[] coords = new int[2];
        coords[0] = (int) target.getX()/20;
        coords[1] = (int) ((target.getY()-80)/20);
        return coords;
    }

    @Override
    public int[] getObjectCoords() {
        int[] coords = new int[2];
        coords[0] = (int) getX()/20;
        coords[1] = (int) ((getY()-80)/20);
        return coords;
    }

    /**
     * @param target the target that the entity attacked
     */
    public void atk(Movable target){
        int atkX = (int) getXFromCenter();
        int atkY = (int) getYFromCenter();
        int WHArc = 80;
        Main.processing.noStroke();
        Main.processing.fill(255,0,0);
        if(elapsedSecond > coolDown && attack) {
            swingAtkCollision(atkX, atkY, WHArc / 2, target, getAtkDirection());
            if (getAtkDirection().equals(Direction.RIGHT)) {
                Main.processing.arc(atkX, atkY, WHArc, WHArc, -PConstants.HALF_PI, PConstants.HALF_PI);
            } else if (getAtkDirection().equals(Direction.DOWN)) {
                Main.processing.arc(atkX, atkY, WHArc, WHArc, 0, PConstants.PI);
            } else if (getAtkDirection().equals(Direction.LEFT)) {
                Main.processing.arc(atkX, atkY, WHArc, WHArc, PConstants.HALF_PI, PConstants.PI + PConstants.HALF_PI);
            } else if (getAtkDirection().equals(Direction.UP)) {
                Main.processing.arc(atkX, atkY, WHArc, WHArc, PConstants.PI, PConstants.TWO_PI);
            }
            startTime = System.currentTimeMillis();
        }
    }
    /**
     * @param atkX the x-point that the swing centered upon
     * @param atkY the y-point that the swing centered upon
     * @param radius the radius of the swing
     * @param musuh the enemy that collide with the swing
     * @param direction the direction of the swing
     */
    private void swingAtkCollision(int atkX, int atkY, int radius, Movable musuh, Direction direction){
        int pointOnRectX = 0;
        int pointOnRectY = 0;
        int XDistToRect = 0;
        int YDistToRect = 0;
        float dist = 0;
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
                musuh.subHP(1);
//                    return true;
            }
            else if(direction.equals(Direction.LEFT) && XDistToRect>=0){
//                    System.out.println("kena kiri");
                musuh.subHP(1);
//                    return true;
            }
            else if(direction.equals(Direction.UP) && YDistToRect>=0){
//                    System.out.println("kena atas");
                musuh.subHP(1);
//                    return true;
            }
            else if(direction.equals(Direction.DOWN) && YDistToRect<=0){
//                    System.out.println("kena bawah");
//                    System.out.println("dist: " + dist);
//                    System.out.println("arc: " + radius);
                musuh.subHP(1);
//                    return true;
            }

        }
//        return false;
    }
}
