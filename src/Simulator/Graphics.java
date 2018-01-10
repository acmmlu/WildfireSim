package Simulator;
import javax.swing.*;
import java.awt.*;

public class Graphics {

    private int frameNumber = 0;
    private Forest forest;
    private JFrame frame;

    private void initialize(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(forest.getSideLength() * 10, forest.getSideLength() * 10);
        frame.setLayout(null);
        frame.setBounds(0, 0, forest.getSideLength() * 10, forest.getSideLength() * 10);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Tile[][] forestTiles = forest.getTiles();
        java.awt.Container cp = frame.getContentPane();

        for (int i = 0; i < forest.getSideLength(); i++){
            for (int j = 0; j < forest.getSideLength(); j++){
                frame.add(forestTiles[i][j]);
            }
        }
//        frame.add(new JPanel(){
//            @Override
//            public void paintComponent(java.awt.Graphics g){
//                super.paintComponent(g);
//                Graphics2D g2d = (Graphics2D) g;
//                g2d.fillRect(0, 0, 100, 100);
//            }
//        });
        //frame.add(new Tile(Tiles.FIRE, 10, 0));
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
//        Tile[][] forestTiles = forest.getTiles();
//        for (int i = 0; i < forest.getSideLength(); i++){
//            for (int j = 0; j < forest.getSideLength(); j++){
//                forestTiles.paintComponent();
//            }
//        }
        frameNumber++;
    }
}
