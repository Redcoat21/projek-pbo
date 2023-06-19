package main;

import processing.core.PConstants;
import processing.core.PFont;

import java.util.ArrayList;

public class ChoosingMenu {
    private int mode;
    private int[] x;
    private int[] y;
    private int[] w;
    private int[] h;

    public ChoosingMenu(){
        mode = 2;
        x = new int[3];
        y = new int[3];
        w = new int[3];
        h = new int[3];
        x[0] = Main.processing.width/2-160;
        x[1] = Main.processing.width/2-160;
        x[2] = Main.processing.width/2-160;
        y[0] = Main.processing.height/2-150;
        y[1] = Main.processing.height/2+30;
        y[2] = Main.processing.height/2+205;
        w[0] = 320;
        w[1] = 320;
        w[2] = 320;
        h[0] = 120;
        h[1] = 120;
        h[2] = 120;
    }

//    public void render(){
////        Main.processing.background(0);
//        Main.processing.textAlign(PConstants.CENTER, PConstants.TOP);
//
//        //arcade mode btn section
//        Main.processing.noStroke();
//        Main.processing.fill(125);
////        Main.processing.rect(Main.processing.width/2-200, Main.processing.height/2-60, 400, 100, 10);
////        Main.processing.rect(x[0], y[0], w[0], h[0], 10);
////        Main.processing.textAlign(PConstants.CENTER, PConstants.CENTER);
////        Main.processing.textSize(40);
////        Main.processing.fill(255);
////        Main.processing.text("ARCADE MODE", Main.processing.width/2, Main.processing.height/2-15);
////
////        //endless mode btn section
////        Main.processing.noStroke();
////        Main.processing.fill(125);
//////        Main.processing.rect(Main.processing.width/2-200, Main.processing.height/2+60, 400, 100, 10);
////        Main.processing.rect(x[1], y[1], w[1], h[1], 10);
////        Main.processing.textAlign(PConstants.CENTER, PConstants.TOP);
////        Main.processing.textSize(40);
////        Main.processing.fill(255);
////        Main.processing.text("ENDLESS MODE", Main.processing.width/2, Main.processing.height/2+85);
////
////        //exit btn section
////        Main.processing.noStroke();
////        Main.processing.fill(125);
//////        Main.processing.rect(Main.processing.width/2-200, Main.processing.height/2+180, 400, 100, 10);
////        Main.processing.rect(x[2], y[2], w[2], h[2], 10);
////        Main.processing.textAlign(PConstants.CENTER, PConstants.TOP);
////        Main.processing.textSize(40);
////        Main.processing.fill(255);
////        Main.processing.text("EXIT", Main.processing.width/2, Main.processing.height/2+205);
//    }

    public int buttonPressed(){
        for(int i = 0; i<x.length; i++){
            if(Main.processing.mouseX > x[i] && Main.processing.mouseX < x[i]+w[i] &&
                    Main.processing.mouseY > y[i] && Main.processing.mouseY < y[i]+h[i]){
                return i;
            }
        }
        return -1;
    }


}
