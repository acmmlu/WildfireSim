package Simulator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tile extends JPanel{

    private Tiles type; // Ash, grass, fire, or tree
    public int duration; // only really used for fires (they are extinguished once duration reaches 3. Duration goes up by one every frame.
    public int[] location = new int[2]; // Where in the forest is the tile
    private int burnChance; // how likely is the tile to catch on fire (0-100 with 0 being no chance and 100 being guaranteed)
    private ForestParams forestParams;

    public Tile(Tiles t, int row, int col, ForestParams forestParams){ // Creates a tile with its type and location
        type = t;
        duration = 0;
        location[0] = row;
        location[1] = col;
        this.forestParams = forestParams;
        setBurnChance(t);
        this.setBounds(location[0] * 10, location[1] * 10, 10, 10); // This is because every tile is drawn to the Jframe and its background is filled with the appropriate color
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color c = new Color(0, 0, 0);
        switch (type){
            case FIRE:
                switch (duration){
                    case 0:
                        c = new Color(255, 0, 0); // fire is brightest when it is first lit
                        break;
                    case 1:
                        c = new Color(255, 40, 0);
                        break;
                    case 2:
                        c = new Color(255, 80, 0);
                        break;
                    case 3:
                        c = new Color(255, 120, 0); // and fades as time passes
                        break;
                }
                break;
            case GRASS:
                c = new Color(0, 255, 20);
                break;
            case ASH:
                c = new Color(175, 175, 175);
                break;
            case TREE:
                c = new Color(0, 102, 0);
                break;
        }
        g2d.setColor(c);
        this.setBackground(c); // draws the tiles
    }

    public Tiles getType(){
        return type;
    }

    public int getBurnChance(){
        return burnChance;
    }

    public void setType(Tiles t){ // change the type of a tile (mostly for fires igniting and being extinguished)
        type = t;
        duration = 0;
        setBurnChance(t);
    }

    public void setBurnChance(Tiles t){
        switch (t){
            case TREE:
                burnChance = forestParams.treeBC; // Trees are the most likely to ignite.
                break;
            case ASH:
                burnChance = forestParams.ashBC; // ash can't catch on fire because the fuel is already exhausted
                break;
            case FIRE:
                burnChance = -1; // fire can't catch on fire because it's already burning
                break;
            case GRASS:
                burnChance = forestParams.grassBC; // grass can light but not very likely
                break;
        }
    }
}
