package entities;

import main.Main;

public class Zombies extends Movable{
    private boolean agro;
    private int agroIdx;
    private int tickMove;
    private int indexDelay;
    private Player target;

    public Zombies(float x, float y) {
        super(x, y,20,20,2,1);
        agro = false;
        agroIdx=0;
        tickMove=0;
        indexDelay=0;
    }
    @Override
    public void render() {
        tickMove++;
        Main.processing.text("HP "+getHealth() + "   X: "+getX()+"   Y: "+getY() + " Agro:   "+agroIdx+ " Status: "+agro,getX(),getY()+60);
        Main.processing.noStroke();
        Main.processing.fill(0,255,127);
        Main.processing.rect(getX(), getY(), getWidth(), getHeight());

//        Agro Mode
        if(agro){
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
        if(Math.abs(getX()-you.getX())<=200&&Math.abs(getY()-you.getY())<=200){
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
}
