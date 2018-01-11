package Simulator;

import javax.swing.*;
import java.awt.*;

public class Main implements Runnable{

    Forest forest;
    Graphics graphics;
    Timer timer;
    JSlider forestSize, forestDensity, treeBC, grassBC, ashBC, speed;
    JPanel panel;
    ForestParams forestParams;
    static final int SLIDER_MIN = 0;
    static final int SLIDER_MAX = 100;
    static final String SPEED_FAST = "Fast";
    static final String SPEED_SLOW = "Slow";

    JFrame menu;
    @Override
    public void run() {
        menu = new JFrame();
        JButton start = new JButton("Start");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();

        forestSize = new JSlider();
        forestSize.setLabelTable(forestSize.createStandardLabels(100));
        forestSize.setPaintLabels(true);
        forestDensity = new JSlider(SLIDER_MIN, SLIDER_MAX, 75);
        treeBC = new JSlider(SLIDER_MIN, SLIDER_MAX, 60);
        grassBC = new JSlider(SLIDER_MIN, SLIDER_MAX, 20);
        ashBC = new JSlider(SLIDER_MIN, SLIDER_MAX, 0);
        speed = new JSlider();
        start.addActionListener(e -> beginSimulation(forestSize.getValue(),
                                                    forestDensity.getValue(),
                                                    treeBC.getValue(),
                                                    grassBC.getValue(),
                                                    ashBC.getValue(),
                                                    1000));
        panel.add(forestSize);
        panel.add(forestDensity);
        panel.add(treeBC);
        panel.add(grassBC);
        panel.add(ashBC);
        panel.add(speed);
        panel.add(start);
        panel.setPreferredSize(new Dimension(450, 100));
        menu.add(panel);
        menu.pack();
        menu.setVisible(true);
        menu.setResizable(true);
        //beginSimulation(50, 75, 0, 0, 0, 100);
    }

    public void beginSimulation(int forestSize, int forestDensity, int treeBC, int grassBC, int ashBC, int speed){

        forest = new Forest(forestDensity, forestSize);
        forestParams = new ForestParams(forestSize, forestDensity, treeBC, grassBC, ashBC);
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
