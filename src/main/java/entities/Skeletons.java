package entities;

import main.Main;

public class Skeletons extends Movable{
    private boolean agro;
    private boolean primed;
    private int shootTick;
    private int shootCounter;
    private int agroIdx;
    private int tickMove;
    private int indexDelay;
    private Player target;

    public Skeletons(float x, float y) {
        super(x, y,20,20,2,1);
        agro = false;
        agroIdx=0;
        tickMove=0;
        indexDelay=0;
        primed=false;
//        Throwaway variable just for checking if delay is working
        shootCounter=0;
        shootTick=0;
    }
    @Override
    public void render() {
        tickMove++;
        Main.processing.text("HP "+getHealth() + "   X: "+getX()+"   Y: "+getY() + " Agro:   "+agroIdx+ " Status: "+agro,getX(),getY()+60);
        Main.processing.text("Shoot Tick : "+shootTick+"   Shoot Counter : "+shootCounter+"   Primed : "+primed,getX(),getY()+100);
        Main.processing.noStroke();
        Main.processing.fill(0,100);
        Main.processing.rect(getX(), getY(), getWidth(), getHeight());

//        Agro Mode
        if(primed){
            if(indexDelay<4)this.stop();
            this.stop();
            if(shootTick>100){
                shootTick=0; shootCounter++;
                indexDelay=0;
            }
            shootTick++;

        }
        if(agro && !primed){
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
        if(!primed&&!agro && indexDelay>1 && indexDelay<8 && tickMove>35){
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
            if(Math.abs(getX()-you.getX())<=100&&Math.abs(getY()-you.getY())<=100)this.primed = true;
            else primed=false;
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
        if(agroIdx>=0&&agroIdx<1)this.moveTo(Direction.DOWN);
        else if(agroIdx>=1&&agroIdx<2)this.moveTo(Direction.RIGHT);
        else if(agroIdx>=2&&agroIdx<3)this.moveTo(Direction.UP);
        else if(agroIdx>=3&&agroIdx<4)this.moveTo(Direction.LEFT);
    }
}
