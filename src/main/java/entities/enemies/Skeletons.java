//package entities.enemies;
//
//import entities.*;
//import entities.tiles.Wall;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//
//public class Skeletons extends MovableOld implements Pathfinding {
//    private boolean agro;
//    private boolean primed;
//    private int shootTick;
//    private int shootCounter;
//    private int agroIdx;
//    private int tickMove;
//    private int indexDelay;
//    private Player target;
//    Obstacles[][] tiles;
//    private ArrayList<Direction> pathList;
//    private int pathIdx;
//    private boolean gotPath;
//
////    public Skeletons(float x, float y) {
////        super(x, y,20,20,2,1, 5);
////        agro = false;
////        agroIdx=0;
////        tickMove=0;
////        indexDelay=0;
////        primed=false;
//////        Throwaway variable just for checking if delay is working
////        shootCounter=0;
////        shootTick=0;
////    }
//    public Skeletons(float x, float y) {
//        super(x, y,20,20,2,1,5);
//        agro = false;
//        agroIdx=0;
//        tickMove=0;
//        indexDelay=0;
////        this.map=map;
//        this.tiles = mapOld.getMap();
//        pathIdx=0;
//    }
//    @Override
//    public void render() {
//        tickMove++;
//        Main.processing.text("HP "+getHealth() + "   X: "+getX()+"   Y: "+getY() + " Agro:   "+agroIdx+ " Status: "+agro,getX(),getY()+60);
//        Main.processing.text("Shoot Idx "+shootTick+"   Shoot Counter  "+shootCounter,getX(),getY()-60);
//        Main.processing.noStroke();
//        Main.processing.fill(0,255,127);
//        Main.processing.rect(getX(), getY(), getWidth(), getHeight());
////        j * 20, i * 20 + 80
////        Agro Mode
//        if(agro){
//            if(primed){
//                shootTick++;
//                if(shootTick>=90){
//                    shootTick=0;
//                    shootCounter++;
//                }
//            }
//            if(mapOld !=null){
//                if(indexDelay<4)this.stop();
//                this.stop();
//                if(Math.abs(getX()-target.getX())>Math.abs(getY()- target.getY())&&!entitiesCollisionChecker()){
//                    if(getX()> target.getX())this.moveTo(Direction.LEFT);
//                    else this.moveTo(Direction.RIGHT);
//                }else if(Math.abs(getX()-target.getX())>0){
//                    if(getY()> target.getY())this.moveTo(Direction.UP);
//                    else this.moveTo(Direction.DOWN);
//                }else{
//                    if(getX()> target.getX())this.moveTo(Direction.LEFT);
//                    else this.moveTo(Direction.RIGHT);
//                }
//                Obstacles[][] init = new Obstacles[64][32];
//                for(int i=0 ; i<32; i++){
//                    for(int j=0; j<64; j++){
//                        init[j][i] = null;
//                    }
//                }
//            }else{
//                if(pathList==null){
//                    pathList=getNextDirection(new ArrayList<Direction>(),getObjectCoords()[0],getObjectCoords()[1],new Obstacles[64][32]);
//                }else{
//                    pathIdx++;
//                    if(pathIdx<pathList.size()*20-1){
//                        if(pathIdx!=pathList.size()*20-2){
//                            this.moveTo(pathList.get(pathIdx/20));
//                        }
//                        Main.processing.text("Direction : "+pathList.get(pathIdx/20)+" Idx : "+pathIdx,getX(),getY()+120);
//                    }else{
//                        this.moveTo(Direction.NONE);
//                        this.stop();
//                        pathIdx=0;
//                        pathList=null;
//                        gotPath=false;
//                    }
//                }
//
//            }
//
//        }
//        if(!agro && indexDelay>1 && indexDelay<4 && tickMove>35){
//            tickMove=0;
//            indexDelay++;
//            this.stop();
//        }else if(!agro &&indexDelay>=4&&tickMove>35){
//            indexDelay=0;
//        }
//        if(!agro&&tickMove>35){
//            indexDelay++;
//            tickMove=0;
//            idle();
//            agroIdx++;
//        }
//}
//    public void checkAgro(Player you){
//        if(Math.abs(getX()-you.getX())<=300&&Math.abs(getY()-you.getY())<=300){
//            target = you;
//            this.agro=true;
//            if(Math.abs(getX()-you.getX())<=100&&Math.abs(getY()-you.getY())<=100)this.primed = true;
//            else primed=false;
//        }else{
//            target=null;
//            this.agro=false;
//        }
//    }
//    public void idle(){
//        if(agroIdx>3){
//            agroIdx=0;
//        }
//        this.stop();
//        if(agroIdx>=0&&agroIdx<1)this.moveTo(Direction.DOWN);
//        else if(agroIdx>=1&&agroIdx<2)this.moveTo(Direction.RIGHT);
//        else if(agroIdx>=2&&agroIdx<3)this.moveTo(Direction.UP);
//        else if(agroIdx>=3&&agroIdx<4)this.moveTo(Direction.LEFT);
//    }
//
//    @Override
//    public ArrayList<Direction> getNextDirection(ArrayList<Direction> dlist, int x, int y, Obstacles[][] moved) {
//        if(Math.abs(x-getTargetCoords()[0])<=6&&Math.abs(y-getTargetCoords()[1])<=6){
//            dlist.add(Direction.NONE);
//            gotPath=true;
//            System.out.println("LELE");
//            return dlist;
//        } else{
//            ArrayList<ValueTile> moves = new ArrayList<>();
//            if(x-1>=0 && Math.abs((x-1)-getTargetCoords()[0])<=20&& !gotPath && Math.abs((x)*20- getTargetCoords()[0]*20)!=0)moves.add(new ValueTile(Math.abs((x-1)*20- getTargetCoords()[0]*20),x-1,y,Direction.LEFT));
//            if(x+1<64 && Math.abs((x+1)-getTargetCoords()[0])<=20&& !gotPath && Math.abs((x)*20-getTargetCoords()[0]*20)!=0)moves.add(new ValueTile(Math.abs((x+1)*20-getTargetCoords()[0]*20),x+1,y,Direction.RIGHT));
//            if(y-1>=0 && Math.abs((y-1)-getTargetCoords()[1])<=20&& !gotPath && Math.abs((y)*20+80-(getTargetCoords()[1]*20+80))!=0)moves.add(new ValueTile(Math.abs((y-1)*20+80-(getTargetCoords()[1]*20+80)),x,y-1,Direction.UP));
//            if(y+1<32 && Math.abs((y+1)-getTargetCoords()[1])<=20 && !gotPath && Math.abs((y)*20+80- (getTargetCoords()[1]*20+80))!=0)moves.add(new ValueTile(Math.abs((y+1)*20+80- (getTargetCoords()[1]*20+80)),x,y+1,Direction.DOWN));
//            if(moves!=null){
//                Collections.sort(moves, new Comparator<ValueTile>() {
//                    @Override
//                    public int compare(ValueTile o1, ValueTile o2) {
//                        return (int)(o1.getValue()-o2.getValue());
//                    }
//                });
//                for (ValueTile a:moves) System.out.print(a.getValue()+"  Direction : "+a.getMoved()+" ");
//                System.out.println();
//                for (ValueTile a : moves){
//                    if(!gotPath){
//                        if(tiles[a.getX()][a.getY()] instanceof Wall);
//                        else if(moved[a.getX()][a.getY()]==null){
//                            dlist.add(a.getMoved());
//                            moved[a.getX()][a.getY()] = new Obstacles(a.getX(),a.getY());
//                            getNextDirection(dlist,a.getX(),a.getY(),moved);
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println("LALA");
//        return dlist;
//
//    }
//
//    @Override
//    public int[] getTargetCoords() {
//        int[] coords = new int[2];
//        coords[0] = (int) target.getX()/20;
//        coords[1] = (int) ((target.getY()-80)/20);
//        return coords;
//    }
//
//    @Override
//    public int[] getObjectCoords() {
//        int[] coords = new int[2];
//        coords[0] = (int) getX()/20;
//        coords[1] = (int) ((getY()-80)/20);
//        return coords;
//    }
//}