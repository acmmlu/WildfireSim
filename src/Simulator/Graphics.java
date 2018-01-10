package Simulator;
import javax.swing.*;
import java.awt.*;

public class Graphics {

    public static int frameNumber = 0;
    private Forest forest;
    private JFrame frame;

    private void initialize(){

        // JFrame stuff
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize((forest.getSideLength() + 1) * 10 + 5, (forest.getSideLength() + 2) * 10);
        frame.setLayout(null);
        frame.setBounds(0, 0, (forest.getSideLength()) * 10 + 5, (forest.getSideLength() + 2)* 10);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Our forest
        Tile[][] forestTiles = forest.getTiles();

        // Adds every tile in the forest to the JFrame. Thought this might be the cause of the leak but the leak happens with it commented out.
        for (int i = 0; i < forest.getSideLength(); i++){
            for (int j = 0; j < forest.getSideLength(); j++){
                frame.add(forestTiles[i][j]);
            }
        }
        frame.setVisible(true);
    }

    public Graphics(){
        forest = new Forest();
        initialize();
    }

    public Graphics(Forest f){
        forest = f;
        initialize();
    }

    public void nextFrame(){
        forest.updateFire();
        Tile[][] forestTiles = forest.getTiles();
        // Updates the fires. I thought this might cause the leak but it does not because the leak still happens with this loop commented out.
        for (int i = 0; i < forest.getSideLength(); i++){
            for (int j = 0; j < forest.getSideLength(); j++){
                forestTiles[i][j].repaint();
            }
        }
        frameNumber++;
    }
}
