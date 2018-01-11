package Simulator;

import javax.swing.*;

public class Main implements Runnable{
    Forest forest;
    Graphics graphics;
    Timer timer;

    JFrame menu;
    @Override
    public void run() {
        menu = new JFrame();
        JButton start = new JButton("Start");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.add(start);
        menu.pack();
        menu.setVisible(true);
        menu.setResizable(true);
        start.addActionListener(e -> beginSimulation(20, 75, 0, 0, 0, 1000));
        //beginSimulation(50, 75, 0, 0, 0, 100);
    }

    public void beginSimulation(int forestSize, int forestDensity, int treeBC, int grassBC, int ashBC, int speed){

        forest = new Forest(forestDensity, forestSize);
        graphics = new Graphics( forest/*new Forest(forestDensity, forestSize)*/);

        graphics.setVisible(true);

        timer = new Timer(speed, e -> simulationLoop());
        timer.start();
    }

    public void simulationLoop(){
        graphics.nextFrame();
    }

    public static void main(String[] args) {
        new Thread(new Main()).start();
    }
}


/* game loop
while (forest.fires.size() > 0) { // main loop
                    //forest.show();
                    // if you uncomment forest.show() it has a text representation of the burning. This worked and had no memory leak. X meant tree, O meant grass, and a space was ash. F was fire.
                    // I used that before JFrame.
                    System.out.println(Graphics.frameNumber);
                    try {
                        Thread.sleep(speed); // Time between frames
                    } catch (Exception e) {
                        System.out.println();
                    }

                    this.nextFrame();
                }
 */
