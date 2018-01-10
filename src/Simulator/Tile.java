package Simulator;

import java.util.ArrayList;

public class Tile {

    private Tiles type;
    public int duration;
    public int[] location = new int[2];
    private int burnChance;

    public enum Sides{
        TOP, BOTTOM, LEFT, RIGHT, NONE
    }

    public Tile(Tiles t, int row, int col){
        type = t;
        duration = 0;
        location[0] = row;
        location[1] = col;
        switch(type){
            case TREE:
                burnChance = 50;
                break;
            case ASH:
                burnChance = 0;
                break;
            case FIRE:
                burnChance = 0;
                break;
            case GRASS:
                burnChance = 20;
                break;
        }
    }

    public Tiles getType(){
        return type;
    }

    public int getBurnChance(){
        return burnChance;
    }

    public void setType(Tiles t){
        type = t;
        duration = 0;
    }
}
