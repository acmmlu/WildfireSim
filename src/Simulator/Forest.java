package Simulator;

import java.util.ArrayList;
import java.util.Random;

public class Forest {
    private Tile[][] forest; // Array of Array of tiles that represents every tile in the forest.
    private int forestDensity; // 0-100, 100 being all trees, 0 being no trees.
    private int sideLength; // Dimensions of tile array.
    public ArrayList<Tile> fires; // List of all the fires.

    private void initialize(){
        forest = generateForest(forestDensity);
        fires = new ArrayList<>();
        placeFire();
    }

    public Forest(){
        forestDensity = 75;
        sideLength = 100;
        initialize();
    }

    public Forest(int density) {
        forestDensity = density;
        sideLength = 100;
        initialize();
    }

    public Forest(int d, int s) {
        forestDensity = d;
        sideLength = s;
        initialize();
    }

    public int getSideLength(){
        return sideLength;
    }

    public Tile[][] getTiles(){
        return forest;
    }

    private Tile[][] generateForest(int d){
        // Populates Tile[][] forest with trees and grass.
        Random r = new Random();
        Tile[][] arr = new Tile[sideLength][sideLength];
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                int val = r.nextInt(100) + 1;
                Tiles type = val <= d ? Tiles.TREE : Tiles.GRASS;
                arr[i][j] = new Tile(type, i, j);
            }
        }
        return arr;
    }

    @Override
    public String toString(){
        String s = "";
        for (int i = 0; i < sideLength; i++){
            for (int j = 0; j < sideLength; j++){
                switch (this.forest[i][j].getType()){
                    case TREE:
                        s += "X";
                        break;
                    case ASH:
                        s += " ";
                        break;
                    case GRASS:
                        s += "O";
                        break;
                    case FIRE:
                        s += "F";
                        break;
                }
            }
            s += "\n";
        }
        return s;
    }

    public void show(){
        System.out.println(this.toString());
    }

    private void placeFire(){
        // changes a tile in the middle to fire.
        try {
            int mid = sideLength / 2;
            Tile fire = new Tile(Tiles.FIRE, mid, mid);
            forest[mid][mid] = fire;
            fires.add(fire);
        } catch (Exception e){
            System.out.println("ERR placing fire");
        }
    }

    public void updateFire(){
        int i = 0;
        while (i < fires.size()){
            fires.get(i).duration += 1;
            if (fires.get(i).duration <= 3 && fires.get(i).duration > 1) { // fire tries to spread if it hasn't been burning too long
                burnAdjacent(fires.get(i));
                i++;
            } else if (fires.get(i).duration > 3){ // fire has been burning too long so it is turned to ash.
                fires.get(i).setType(Tiles.ASH);
                fires.remove(i);
            } else {
                i++;
            }
        }
    }

    private void burnAdjacent(Tile t){
        // starts fires and avoids out of bounds exceptions
        int row = t.location[0];
        int col = t.location[1];
        Random r = new Random();
        //try {
            if (row > 0) { // makes sure fire is not along the bottom edge
                int val = r.nextInt(100) + 1;
                if (val <= forest[row - 1][col].getBurnChance()) { // Lights fire based on burn chance
                    if (forest[row-1][col].getType() != Tiles.FIRE) { // Make sure the tile isn't already burning
                        int dur = forest[row - 1][col].getType() == Tiles.TREE ? 0 : 1; // fire burns longer if a tree ignites vs a bush
                        forest[row - 1][col].setType(Tiles.FIRE);
                        forest[row - 1][col].duration = dur;
                        fires.add(forest[row - 1][col]);
                    }
                }
            }
            if (row < sideLength - 1) { // makes sure fire isn't on top edge
                int val = r.nextInt(100) + 1;
                if (val <= forest[row + 1][col].getBurnChance()) { // Lights fire based on burn chance
                    if (forest[row+1][col].getType() != Tiles.FIRE) {
                        int dur = forest[row + 1][col].getType() == Tiles.TREE ? 0 : 1; // fire burns longer if a tree ignites vs a bush
                        forest[row + 1][col].setType(Tiles.FIRE);
                        forest[row + 1][col].duration = dur;
                        fires.add(forest[row + 1][col]);
                    }
                }
            }
            if (col > 0) { // makes sure fire isn't on left edge
                int val = r.nextInt(100) + 1;
                if (val <= forest[row][col - 1].getBurnChance()) { // Lights fire based on burn chance
                    if (forest[row][col-1].getType() != Tiles.FIRE) {
                        int dur = forest[row][col - 1].getType() == Tiles.TREE ? 0 : 1; // fire burns longer if a tree ignites vs a bush
                        forest[row][col - 1].setType(Tiles.FIRE);
                        forest[row][col - 1].duration = dur;
                        fires.add(forest[row][col - 1]);
                    }
                }
            }
            if (col < sideLength - 1) { // makes sure fire isn't on right edge
                int val = r.nextInt(100) + 1;
                if (val <= forest[row][col + 1].getBurnChance()) { // Lights fire based on burn chance
                    if (forest[row][col+1].getType() != Tiles.FIRE) {
                        int dur = forest[row][col + 1].getType() == Tiles.TREE ? 0 : 1; // fire burns longer if a tree ignites vs a bush
                        forest[row][col + 1].setType(Tiles.FIRE);
                        forest[row][col + 1].duration = dur;
                        fires.add(forest[row][col + 1]);
                    }
                }
            }
//        } catch (Exception e){
//            System.out.printf("%d\t%d", row, col);
//        }
    }
}
