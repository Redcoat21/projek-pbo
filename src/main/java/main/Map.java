package main;

import entities.tiles.Hole;
import entities.tiles.Obstacles;
import entities.tiles.Wall;

public class Map {
    Obstacles[][] map;
    int floor;

    public Map(int floor){
        this.floor = floor;
        map = new Obstacles[64][32];
        for(int i=0 ; i<32; i++){
            for(int j=0; j<64; j++){
                map[j][i] = null;
            }
        }
        setWall();
    }

    private void setWall(){
        if(floor == 1){
            for(int i=0; i<32; i++){
                for(int j=0; j<64; j++){
                    if (j == 56 && (i > -1 && i < 12)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if (j == 56 && (i > 18 && i < 32)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if(i==0 || i==31){
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if (j == 0 && (i > -1 && i < 14)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if (j == 0 && (i > 16 && i < 32)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if (j == 63 && (i > -1 && i < 14)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if (j == 63 && (i > 16 && i < 32)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                }
            }
        }
        else if(floor == 2) {
            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < 64; j++) {
                    if (j == 16 && (i > 4 && i < 26)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if(i==5 && (j>16 && j<29)){
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if(i==25 && (j>16 && j<29)){
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if (j == 46 && (i > 4 && i < 26)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if(i==5 && (j>33 && j<46)){
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if(i==25 && (j>33 && j<46)){
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if (j == 56 && (i > -1 && i < 12)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if (j == 56 && (i > 18 && i < 32)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if(i==0 || i==31){
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if (j == 0 && (i > -1 && i < 14)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if (j == 0 && (i > 16 && i < 32)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if (j == 63 && (i > -1 && i < 14)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if (j == 63 && (i > 16 && i < 32)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                }
            }
        }
        else if(floor == 3){
            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < 64; j++) {
                    if (j == 56 && (i > -1 && i < 12)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if (j == 56 && (i > 18 && i < 32)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if(i==0 || i==31){
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if (j == 0 && (i > -1 && i < 14)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if (j == 0 && (i > 16 && i < 32)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if (j == 63 && (i > -1 && i < 14)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if (j == 63 && (i > 16 && i < 32)) {
                        map[j][i] = new Wall(j * 20, i * 20 + 80);
                    }
                    else if(j == 12 && (i > 7 && i < 25)){
                        map[j][i] = new Hole(j * 20, i * 20 + 80);
                    }
                    else if(j == 22 && (i > 7 && i < 25)){
                        map[j][i] = new Hole(j * 20, i * 20 + 80);
                    }
                    else if(j == 32 && (i > 7 && i < 25)){
                        map[j][i] = new Hole(j * 20, i * 20 + 80);
                    }
                    else if(j == 42 && (i > 7 && i < 25)) {
                        map[j][i] = new Hole(j * 20, i * 20 + 80);
                    }
                }
            }
        }
    }

    public void printMap(){
        for(int i=0 ; i<32; i++) {
            for (int j = 0; j < 64; j++) {
                if(map[j][i] != null){
                    map[j][i].render();
                }
            }
        }
    }

    public Obstacles[][] getMap() {
        return map;
    }
}
