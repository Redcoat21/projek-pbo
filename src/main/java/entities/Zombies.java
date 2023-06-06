package entities;

import entities.tiles.Obstacles;
import entities.tiles.Wall;
import main.Main;
import main.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Zombies extends Movable implements Pathfinding{
    private boolean agro;
    private int agroIdx;
    private int tickMove;
    private int indexDelay;
    private Player target;
    private Map map;
    Obstacles[][] tiles;
    private ArrayList<Direction> pathList;
    private int pathIdx;

    public Zombies(float x, float y) {
        super(x, y,20,20,2,1);
        agro = false;
        agroIdx=0;
        tickMove=0;
        indexDelay=0;
    }
    public Zombies(float x, float y, Map map) {
        super(x, y,20,20,2,1);
        agro = false;
        agroIdx=0;
        tickMove=0;
        indexDelay=0;
        this.map=map;
        this.tiles = map.getMap();
        pathIdx=0;
    }
    @Override
    public void render() {
        tickMove++;
        Main.processing.text("HP "+getHealth() + "   X: "+getX()+"   Y: "+getY() + " Agro:   "+agroIdx+ " Status: "+agro,getX(),getY()+60);
        Main.processing.noStroke();
        Main.processing.fill(0,255,127);
        Main.processing.rect(getX(), getY(), getWidth(), getHeight());
//        j * 20, i * 20 + 80
        for (int i=0;i<32;i++){
            for (int j=0;j<64;j++){
                if(this.entitiesIntersectWall(new Obstacles(j*20,i*20+80))){
                    Main.processing.text("X: "+j+"   Y: "+i,getX(),getY()+100);
                }
            }
        }

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
                    if(pathIdx<=pathList.size()*4-1){
                        this.moveTo(pathList.get(pathIdx/4));
                        Main.processing.text("Direction : "+pathList.get(pathIdx/4),getX(),getY()+120);
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
            indexDelay++;
            tickMove=0;
            idle();
            agroIdx++;
        }
    }
    public void checkAgro(Player you){
        if(Math.abs(getX()-you.getX())<=50&&Math.abs(getY()-you.getY())<=50){
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
    public ArrayList<Direction> getNextDirection(ArrayList<Direction> dlist, int x, int y, Obstacles[][] moved) {
        if(target.entitiesIntersectWall(new Wall(x,y))){
            dlist.add(Direction.NONE);
            System.out.println("LELE");
            return dlist;
        } else{
            ArrayList<ValueTile> moves = new ArrayList<>();
            if(x-1>=0 && Math.abs((x-1)-getTargetCoords()[0])<=10)moves.add(new ValueTile(Math.abs((x-1)-getTargetCoords()[0]),x-1,y,Direction.LEFT));
            if(x+1<64 && Math.abs((x+1)-getTargetCoords()[0])<=10)moves.add(new ValueTile(Math.abs((x+1)-getTargetCoords()[0]),x+1,y,Direction.RIGHT));
            if(y-1>=0 && Math.abs((y-1)-getTargetCoords()[1])<=10)moves.add(new ValueTile(Math.abs((y-1)-getTargetCoords()[1]),x,y-1,Direction.UP));
            if(y+1<32 && Math.abs((y+1)-getTargetCoords()[1])<=10)moves.add(new ValueTile(Math.abs((y+1)-getTargetCoords()[1]),x,y+1,Direction.DOWN));
            if(moves!=null){
                Collections.sort(moves, new Comparator<ValueTile>() {
                    @Override
                    public int compare(ValueTile o1, ValueTile o2) {
                        return o1.getValue()-o2.getValue();
                    }
                });
                for (ValueTile a:moves) System.out.print(a.getValue()+" ");
                System.out.println();
                for (ValueTile a : moves){
                    if(a.getValue()==0)return dlist;
                    if(tiles[a.getX()][a.getY()] instanceof Wall);
                    else if(moved[a.getX()][a.getY()]==null){
                        dlist.add(a.getMoved());
                        moved[a.getX()][a.getY()] = new Obstacles(a.getX(),a.getY());
                        getNextDirection(dlist,a.getX(),a.getY(),moved);
                    }
                }
            }
//            if(x+1<64){
//                if(tiles[x+1][y] instanceof Wall);
//                else if(moved[x+1][y]==null){
//                    dlist.add(Direction.RIGHT);
//                    moved[x+1][y] = new Obstacles(x+1,y);
//                    getNextDirection(dlist,x+1,y,moved);
//                }
//            }
//            if(x-1>=0){
//                if(tiles[x-1][y] instanceof Wall);
//                else if(moved[x-1][y]==null){
//                    dlist.add(Direction.LEFT);
//                    moved[x-1][y] = new Obstacles(x-1,y);
//                    getNextDirection(dlist,x-1,y,moved);
//                }
//            }
//            if(y+1<32){
//                if(tiles[x][y+1]instanceof Wall);
//                else if(moved[x][y+1]==null){
//                    dlist.add(Direction.DOWN);
//                    moved[x][y+1] = new Obstacles(x,y+1);
//                    getNextDirection(dlist,x,y+1,moved);
//                }
//            }
//            if(y-1>=0){
//                if(tiles[x][y-1]instanceof Wall);
//                else if(moved[x][y-1]==null){
//                    dlist.add(Direction.UP);
//                    moved[x][y-1] = new Obstacles(x,y-1);
//                    getNextDirection(dlist,x,y-1,moved);
//                }
//            }
        }
        System.out.println("LALA");
        return null;

    }

    @Override
    public int[] getTargetCoords() {
        int[] coords = new int[2];
        for (int i=0;i<32;i++){
            for (int j=0;j<64;j++){
                if(target.entitiesIntersectWall(new Obstacles(j*20,i*20+80))){
                    coords[0] = j;coords[1]=i;
                }
            }
        }
        return coords;
    }

    @Override
    public int[] getObjectCoords() {
        int[] coords = new int[2];
        for (int i=0;i<32;i++){
            for (int j=0;j<64;j++){
                if(entitiesIntersectWall(new Obstacles(j*20,i*20+80))){
                    coords[0] = j;coords[1]=i;
                }
            }
        }
        return coords;
    }
}
