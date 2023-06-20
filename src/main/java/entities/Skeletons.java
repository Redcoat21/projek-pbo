package entities;

import entities.tiles.Obstacles;
import entities.tiles.Wall;
import main.Main;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Skeletons extends Movable implements Pathfinding{
    private boolean agro;
    private int agroIdx;
    private int tickMove;
    private int indexDelay;
    private Player target;
    Obstacles[][] tiles;
    private ArrayList<Direction> pathList;
    private int pathIdx;
    private boolean gotPath;
    private Bullet bullet;

    public Skeletons(){
        super(0, 0, 20, 20, 0, 3, 3);
    }

    /**
     * @param x the x-axis that the entity will spawn into
     * @param y the y-axis that the enitty will spawn into
     */
    public Skeletons(float x, float y) {
        super(x, y,50,50,12,1,5, 2);
        loadImage();
        agro = false;
        agroIdx=0;
        tickMove=0;
        indexDelay=0;
        this.tiles = map.getMap();
        pathIdx=0;
        bullet = new Bullet();
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
//        Main.processing.noStroke();
//        Main.processing.fill(0,255,127);
//        Main.processing.rect(getX(), getY(), getWidth(), getHeight());
//        j * 20, i * 20 + 80
            this.play("walk", this.getAtkDirection());
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
                    pathIdx++;
                    if(pathIdx<pathList.size()*20-1){
                        if(pathIdx!=pathList.size()*20-2){
                            this.moveTo(pathList.get(pathIdx/20));
                        }
//                        Main.processing.text("Direction : "+pathList.get(pathIdx/20)+" Idx : "+pathIdx + "atk : " + getAtkDirection(),getX(),getY()+120);
                    }else{
                        this.moveTo(Direction.NONE);
                        this.stop();
                        pathIdx=0;
                        pathList=null;
                        gotPath=false;
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

                        if(!bullet.isFired() && elapsedSecond > coolDown){
                            bullet.fired(getXFromCenter(), getYFromCenter(), 4, 1, getAtkDirection());
                            startTime = System.currentTimeMillis();
                        }
                    }
                }

            }

        }
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
        if(Math.abs(getX()-you.getX())<=300&&Math.abs(getY()-you.getY())<=300){
            target = you;
            this.agro=true;
        }else{
            target=null;
            this.agro=false;
        }
    }
    public void idle(){
        if(agroIdx>3){
            agroIdx=0;
        }
        this.stop();
        if(agroIdx == 0)this.moveTo(Direction.DOWN);
        else if(agroIdx == 1)this.moveTo(Direction.RIGHT);
        else if(agroIdx == 2)this.moveTo(Direction.UP);
        else if(agroIdx == 3)this.moveTo(Direction.LEFT);
    }

    @Override
    public ArrayList<Direction> getNextDirection(ArrayList<Direction> dlist, int x, int y, Obstacles[][] moved) {
        if((Math.abs(x-getTargetCoords()[0])<=12&&Math.abs(y-getTargetCoords()[1])==0)||Math.abs(x-getTargetCoords()[0])==0&&Math.abs(y-getTargetCoords()[1])<=12){
            dlist.add(Direction.NONE);
            gotPath=true;
            System.out.println("LELE");
            return dlist;
        } else{
            ArrayList<ValueTile> moves = new ArrayList<>();
            if(x-1>=0 && Math.abs((x-1)-getTargetCoords()[0])<=20&& !gotPath && Math.abs((x)*20- getTargetCoords()[0]*20)!=0)moves.add(new ValueTile(Math.abs((x-1)*20- getTargetCoords()[0]*20),x-1,y,Direction.LEFT));
            if(x+1<64 && Math.abs((x+1)-getTargetCoords()[0])<=20&& !gotPath && Math.abs((x)*20-getTargetCoords()[0]*20)!=0)moves.add(new ValueTile(Math.abs((x+1)*20-getTargetCoords()[0]*20),x+1,y,Direction.RIGHT));
            if(y-1>=0 && Math.abs((y-1)-getTargetCoords()[1])<=20&& !gotPath && Math.abs((y)*20+80-(getTargetCoords()[1]*20+80))!=0)moves.add(new ValueTile(Math.abs((y-1)*20+80-(getTargetCoords()[1]*20+80)),x,y-1,Direction.UP));
            if(y+1<32 && Math.abs((y+1)-getTargetCoords()[1])<=20 && !gotPath && Math.abs((y)*20+80- (getTargetCoords()[1]*20+80))!=0)moves.add(new ValueTile(Math.abs((y+1)*20+80- (getTargetCoords()[1]*20+80)),x,y+1,Direction.DOWN));
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

    @Override
    public void move() {
        super.move();

        if(bullet.isFired()){
            bullet.move();
            bullet.render();
        }
    }

    /**
     * @param target if the bullet collision with the player/target
     */
    public void bulletAtkCollision(Player target){
        int pointOnRectX = 0;
        int pointOnRectY = 0;
        int XDistToRect = 0;
        int YDistToRect = 0;
        float dist = 0;

        pointOnRectX = clamp((int) target.getX(), (int) (target.getX()+target.getWidth()), (int) bullet.getX());
        pointOnRectY = clamp((int) target.getY(), (int) (target.getY()+target.getHeight()), (int) bullet.getY());
        XDistToRect = (int) bullet.getX() - pointOnRectX;
        YDistToRect = (int) bullet.getY() - pointOnRectY;
        dist = (float) Math.sqrt((XDistToRect*XDistToRect) + (YDistToRect*YDistToRect));
        if(dist < bullet.getWidth() && bullet.isFired()){
            target.subHP(bullet.getDamage());
            bullet.hit();
        }
    }

    protected int clamp(int min, int max, int value){
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

    private void loadImage() {
        String root = "src/main/resources/assets/Sprites/Skeleton_Archer/";
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

                PImage temp2 = Main.processing.loadImage(root + String.format("walk%s%d.png", directionString, i + 1));
                this.addSprites("walk", d, temp2, this.getSize());
            }
        }
    }
}
