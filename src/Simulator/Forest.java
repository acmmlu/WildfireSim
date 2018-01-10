package Simulator;

import java.util.ArrayList;
import java.util.Random;

public class Forest {
    private Tile[][] forest;
    private int forestDensity;
    private int[] forestDimensions = new int[2];
    public ArrayList<Tile> fires;

    public Forest(){
        forestDensity = 75;
        forestDimensions[0] = 100;
        forestDimensions[1] = 100;
        forest = generateForest(forestDensity);
        fires = new ArrayList<>();
        placeFire();
    }

    public Forest(int d) {
        forestDensity = d;
        forestDimensions[0] = 100;
        forestDimensions[1] = 100;
        forest = generateForest(forestDensity);
        fires = new ArrayList<>();
        placeFire();
    }

    public Forest(int d, int x, int y) {
        forestDensity = d;
        forestDimensions[0] = x;
        forestDimensions[1] = y;
        forest = generateForest(forestDensity);
        fires = new ArrayList<>();
        placeFire();
    }

    private Tile[][] generateForest(int d){
        Random r = new Random();
        Tile[][] arr = new Tile[forestDimensions[0]][forestDimensions[1]];
        for (int i = 0; i < forestDimensions[0]; i++) {
            for (int j = 0; j < forestDimensions[1]; j++) {
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
        for (int i = 0; i < forestDimensions[0]; i++){
            for (int j = 0; j < forestDimensions[1]; j++){
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
        try {
            int x = forestDimensions[0] / 2;
            int y = forestDimensions[1] / 2;
            Tile fire = new Tile(Tiles.FIRE, x, y);
            forest[x][y] = fire;
            fires.add(fire);
        } catch (Exception e){
            System.out.println("ERR placing fire");
        }
    }

    public void updateFire(){
        int i = 0;
        while (i < fires.size()){
            fires.get(i).duration += 1;
            if (fires.get(i).duration <= 3 && fires.get(i).duration > 1) {
                burnAdjacent(fires.get(i));
                i++;
            } else if (fires.get(i).duration > 3){
                fires.get(i).setType(Tiles.ASH);
                fires.remove(i);
            } else {
                i++;
            }
        }
    }

    private void burnAdjacent(Tile t){
        int row = t.location[0];
        int col = t.location[1];
        Random r = new Random();

        if (row > 0){
            int val = r.nextInt(100) + 1;
            if (val <= forest[row-1][col].getBurnChance()) {
                Tile fire = new Tile(Tiles.FIRE, row-1, col);
                forest[row-1][col] = fire;
                fires.add(fire);
            }
        } if (row < forestDimensions[1]-1){
            int val = r.nextInt(100) + 1;
            if (val <= forest[row+1][col].getBurnChance()) {
                Tile fire = new Tile(Tiles.FIRE, row+1, col);
                forest[row+1][col] = fire;
                fires.add(fire);
            }
        } if (col > 0){
            int val = r.nextInt(100) + 1;
            if (val <= forest[row][col-1].getBurnChance()) {
                Tile fire = new Tile(Tiles.FIRE, row, col-1);
                forest[row][col-1] = fire;
                fires.add(fire);
            }
        } if (col < forestDimensions[0]-1){
            int val = r.nextInt(100) + 1;
            if (val <= forest[row][col+1].getBurnChance()) {
                Tile fire = new Tile(Tiles.FIRE, row, col+1);
                forest[row][col+1] = fire;
                fires.add(fire);
            }
        }
    }
}
