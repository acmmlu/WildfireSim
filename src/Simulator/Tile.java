package Simulator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tile extends JPanel{

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
        this.setBounds(location[0] * 10, location[1] * 10, 10, 10);
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
                        c = new Color(255, 0, 0);
                        break;
                    case 1:
                        c = new Color(255, 40, 0);
                        break;
                    case 2:
                        c = new Color(255, 80, 0);
                        break;
                    case 3:
                        c = new Color(255, 120, 0);
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
        this.setBackground(c);
        //g2d.fillRect(location[0] * 10, location[1] * 10, 100, 100);
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
