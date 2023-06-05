package main;

import processing.core.PConstants;
import processing.core.PFont;

public class ChoosingMenu {
    private int mode;

    public ChoosingMenu(){
        mode = 2;
    }

    public void render(){
        Main.processing.background(0);
        Main.processing.textAlign(PConstants.CENTER, PConstants.TOP);

        //title section
        Main.processing.fill(255);
        Main.processing.textSize(50);
        Main.processing.text("WELCOME", Main.processing.width/2, 100);
        Main.processing.textSize(30);
        Main.processing.text("TO", Main.processing.width/2, 155);
        Main.processing.textSize(60);
        Main.processing.text("ESCAPE THE DUNGEON", Main.processing.width/2, 180);

        //arcade mode btn section
        Main.processing.noStroke();
        Main.processing.fill(125);
        Main.processing.rect(Main.processing.width/2-200, Main.processing.height/2-60, 400, 100, 10);
        Main.processing.textAlign(PConstants.CENTER, PConstants.CENTER);
        Main.processing.textSize(40);
        Main.processing.fill(255);
        Main.processing.text("ARCADE MODE", Main.processing.width/2, Main.processing.height/2-15);

        //endless mode btn section
        Main.processing.noStroke();
        Main.processing.fill(125);
        Main.processing.rect(Main.processing.width/2-200, Main.processing.height/2+60, 400, 100, 10);
        Main.processing.textAlign(PConstants.CENTER, PConstants.TOP);
        Main.processing.textSize(40);
        Main.processing.fill(255);
        Main.processing.text("ENDLESS MODE", Main.processing.width/2, Main.processing.height/2+85);

        //exit btn section
        Main.processing.noStroke();
        Main.processing.fill(125);
        Main.processing.rect(Main.processing.width/2-200, Main.processing.height/2+180, 400, 100, 10);
        Main.processing.textAlign(PConstants.CENTER, PConstants.TOP);
        Main.processing.textSize(40);
        Main.processing.fill(255);
        Main.processing.text("EXIT", Main.processing.width/2, Main.processing.height/2+205);
    }
}
