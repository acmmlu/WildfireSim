package Simulator;
import javax.swing.*;

public class Graphics extends JFrame{

    public static int frameNumber = 0;
    private Forest forest;
    private int speed;
    private Timer timer;

    private void initialize(){

        // JFrame stuff
//        frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
//        frame.setSize((forest.getSideLength() + 1) * 10 + 5, (forest.getSideLength() + 2) * 10);
//        frame.setLayout(null);
//        frame.setBounds(0, 0, (forest.getSideLength()) * 10 + 5, (forest.getSideLength() + 2)* 10);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize((forest.getSideLength() + 1) * 10 + 5, (forest.getSideLength() + 2) * 10);
        this.setLayout(null);
        this.setBounds(0, 0, (forest.getSideLength()) * 10 + 5, (forest.getSideLength() + 2)* 10);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon img = new ImageIcon("FireIcon.png");
        this.setIconImage(img.getImage());

        // Our forest
        Tile[][] forestTiles = forest.getTiles();

        // Adds every tile in the forest to the JFrame. Thought this might be the cause of the leak but the leak happens with it commented out.
        for (int i = 0; i < forest.getSideLength(); i++){
            for (int j = 0; j < forest.getSideLength(); j++){
                this.add(forestTiles[i][j]);
            }
        }
        setVisible(true);
    }

    public Graphics(){
        forest = new Forest();
        initialize();
    }

    public Graphics(Forest f){
        forest = f;
        //this.speed = speed;
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

    public void startTimer(){
        timer = new Timer(speed, e -> nextFrame());
        timer.start();
    }
}
