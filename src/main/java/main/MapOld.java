//package main;
//
//import entities.tiles.Hole;
//import entities.tiles.Obstacles;
//import entities.tiles.Wall;
//
//public class MapOld {
//    Obstacles[][] map;
//    int floor;
//
//    public MapOld(int floor){
//        this.floor = floor;
//        map = new Obstacles[64][32];
//        clearMap();
//        setWall();
//    }
//
//    private void clearMap(){
//        for(int i=0 ; i<32; i++){
//            for(int j=0; j<64; j++){
//                map[j][i] = null;
//            }
//        }
//    }
//
//    public void battleStart(){
//        for(int i=0; i<32; i++) {
//            for (int j = 0; j < 64; j++) {
//                if (j == 0 && (i > 13 && i < 18)) {
//                    map[j][i] = new Wall(j * 20, i * 20 + 80);
//                }
//                else if (j == 56 && (i > 13 && i < 18)) {
//                    map[j][i] = new Wall(j * 20, i * 20 + 80);
//                }
//                else if (j == 63 && (i > 13 && i < 18)) {
//                    map[j][i] = new Wall(j * 20, i * 20 + 80);
//                }
//            }
//        }
//    }
//
//    public void battleDone(){
//        for(int i=0; i<32; i++) {
//            for (int j = 0; j < 64; j++) {
//                if (j == 56 && (i > 13 && i < 18)) {
//                    map[j][i] = null;
//                }
//                else if (j == 63 && (i > 13 && i < 18)) {
//                    map[j][i] = null;
//                }
//            }
//        }
//    }
//
//
//
//    private void setWall(){
//            for(int i=0; i<32; i++){
//                for(int j=0; j<64; j++){
//                    if (j == 56 && i < 14) {
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if (j == 56 && i > 17) {
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(i==0 || i==31){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if (j == 0 && i < 14) {
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if (j == 0 && i > 17) {
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if (j == 63 && i < 14) {
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if (j == 63 && i > 17) {
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                }
//            }
//
//        if(floor == 2) {
//            for (int i = 0; i < 32; i++) {
//                for (int j = 0; j < 64; j++) {
//                    if (j > 13 && j < 16 && (i > 4 && i < 26)) {
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(i > 4 && i < 7 && (j>14 && j<26)){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(i > 23 && i < 26 && (j>14 && j<26)){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if (j > 41 && j < 44 && (i > 4 && i < 26)) {
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(i > 4 && i < 7 && (j>31 && j<44)){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(i > 23 && i < 26 && (j>31 && j<44)){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                }
//            }
//        }
//        else if(floor == 3){
//            for (int i = 0; i < 32; i++) {
//                for (int j = 0; j < 64; j++) {
//                    if(j > 9 && j < 12 && (i > 4 && i < 11)){
//                        map[j][i] = new Hole(j * 20, i * 20 + 80);
//                    }
//                    else if(j > 9 && j < 12 && (i > 20 && i < 27)){
//                        map[j][i] = new Hole(j * 20, i * 20 + 80);
//                    }
//                    else if(j > 18 && j < 21 && (i > 12 && i < 19)){
//                        map[j][i] = new Hole(j * 20, i * 20 + 80);
//                    }
//                    else if(j > 27 && j < 30 && (i > 4 && i < 11)){
//                        map[j][i] = new Hole(j * 20, i * 20 + 80);
//                    }
//                    else if(j > 27 && j < 30 && (i > 20 && i < 27)){
//                        map[j][i] = new Hole(j * 20, i * 20 + 80);
//                    }
//                    else if(j > 36 && j < 39 && (i > 12 && i < 19)) {
//                        map[j][i] = new Hole(j * 20, i * 20 + 80);
//                    }
//                    else if(j > 45 && j < 48 && (i > 4 && i < 11)){
//                        map[j][i] = new Hole(j * 20, i * 20 + 80);
//                    }
//                    else if(j > 45 && j < 48 && (i > 20 && i < 27)){
//                        map[j][i] = new Hole(j * 20, i * 20 + 80);
//                    }
//                }
//            }
//        }
//        else if(floor == 4){
//            for(int i=0; i<32; i++){
//                for(int j=0; j<64; j++){
//                    if((j > 8 && j < 11) && (i > 15 && i < 21)){
//                        map[j][i] = new Hole(j * 20, i * 20 + 80);
//                    }
//                    else if((j > 8 && j < 11) && i > 8){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(i > 8 && i < 11 && j > 10 && j < 18){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if((j > 24 && j < 27) && i > 2 && i < 7){
//                        map[j][i] = new Hole(j * 20, i * 20 + 80);
//                    }
//                    else if((j > 24 && j < 27) && i < 27){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(i > 24 && i < 27 && j > 20 && j < 27){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(j > 39 && j < 42 && i < 7){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(j > 39 && j < 42 && i > 19 && i < 25){
//                        map[j][i] = new Hole(j * 20, i * 20 + 80);
//                    }
//                    else if(j > 39 && j < 42 && i > 12){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(i > 4 && i < 7 && j > 30 && j < 40){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(i > 12 && i < 15 && j > 36 && j < 50){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                }
//            }
//        }
//        else if(floor == 5){
//            for(int i=0; i<32; i++) {
//                for (int j = 0; j < 64; j++) {
//                    if(j == 9 && i > 3 && i < 10){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(i == 9 && j > 3 && j < 9){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(j == 9 && i > 21 && i < 28){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(i == 22 && j > 3 && j < 9){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(j == 47 && i > 3 && i < 10){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(i == 9 && j > 46 && j < 53){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(j == 47 && i > 21 && i < 28){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(i == 22 && j > 46 && j < 53){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(i == 24 && j > 17 && j < 24){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(i == 24 && j > 32 && j < 39){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(i == 7 && j > 17 && j < 24){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(i == 7 && j > 32 && j < 39){
//                        map[j][i] = new Wall(j * 20, i * 20 + 80);
//                    }
//                    else if(j == 14 && i > 13 && i < 18){
//                        map[j][i] = new Hole(j * 20, i * 20 + 80);
//                    }
//                    else if(j == 42 && i > 13 && i < 18){
//                        map[j][i] = new Hole(j * 20, i * 20 + 80);
//                    }
//                }
//            }
//        }
//    }
//
//    public void printMap(){
//        for(int i=0 ; i<32; i++) {
//            for (int j = 0; j < 64; j++) {
//                if(map[j][i] != null){
//                    map[j][i].render();
//                }
//                else{
//                    if(j == 63 && i > 13 && i < 18){
//                        Main.processing.noStroke();
//                        Main.processing.fill(0,255,0);
//                        Main.processing.rect(j*20, i*20+80, 20, 20);
//                    }
//                }
//            }
//        }
//    }
//    public Obstacles[][] getMap() {
//        return map;
//    }
//    public void removeTile(int x,int y){
//        if(map[x][y] !=null ){
//            map[x][y] = null;
//        }
//    }
//
//    public int getFloor() {
//        return floor;
//    }
//}
