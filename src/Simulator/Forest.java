package Simulator;

import java.util.ArrayList;
import java.util.Random;

public class Forest {
    private Tile[][] forest;
    private int forestDensity;
    private int sideLength;
    public ArrayList<Tile> fires;

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

    public Forest(int d) {
        forestDensity = d;
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
        try {
            if (row > 0) {
                int val = r.nextInt(100) + 1;
                if (val <= forest[row - 1][col].getBurnChance()) {
                    int dur = forest[row - 1][col].getType() == Tiles.TREE ? 0 : 1;
                    Tile fire = new Tile(Tiles.FIRE, row - 1, col);
                    fire.duration = dur;
                    forest[row - 1][col] = fire;
                    fires.add(fire);
                }
            }
            if (row < sideLength - 1) {
                int val = r.nextInt(100) + 1;
                if (val <= forest[row + 1][col].getBurnChance()) {
                    int dur = forest[row - 1][col].getType() == Tiles.TREE ? 0 : 1;
                    Tile fire = new Tile(Tiles.FIRE, row + 1, col);
                    fire.duration = dur;
                    forest[row + 1][col] = fire;
                    fires.add(fire);
                }
            }
            if (col > 0) {
                int val = r.nextInt(100) + 1;
                if (val <= forest[row][col - 1].getBurnChance()) {
                    int dur = forest[row - 1][col].getType() == Tiles.TREE ? 0 : 1;
                    Tile fire = new Tile(Tiles.FIRE, row, col - 1);
                    fire.duration = dur;
                    forest[row][col - 1] = fire;
                    fires.add(fire);
                }
            }
            if (col < sideLength - 1) {
                int val = r.nextInt(100) + 1;
                if (val <= forest[row][col + 1].getBurnChance()) {
                    int dur = forest[row - 1][col].getType() == Tiles.TREE ? 0 : 1;
                    Tile fire = new Tile(Tiles.FIRE, row, col + 1);
                    fire.duration = dur;
                    forest[row][col + 1] = fire;
                    fires.add(fire);
                }
            }
        } catch (Exception e){
            System.out.printf("%d\t%d", row, col);
        }
    }
}
