package Simulator;

import javax.swing.*;
import java.awt.*;

public class Main implements Runnable{

    private Forest forest;
    private Graphics graphics;
    private Timer timer;
    private JSlider forestSize, forestDensity, treeBC, grassBC, ashBC, speed;
    private JLabel fsize, fdensity, tbc, gbc, abc, s; // labels to put above the sliders
    private JPanel panel;
    private ForestParams forestParams;
    private static final int SLIDER_MIN = 0;
    private static final int SLIDER_MAX = 100;
    private JFrame menu;

    @Override
    public void run() {
        menu = new JFrame("Wildfire Simulator");
        JButton start = new JButton("Start");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLocation(500, 300);
        panel = new JPanel();

        forestSize = new JSlider(20, SLIDER_MAX);
        fsize = new JLabel("Forest Size");
        fsize.setPreferredSize(new Dimension(200, 10));
        forestDensity = new JSlider(SLIDER_MIN, SLIDER_MAX, 75);
        fdensity = new JLabel("Forest Density");
        fdensity.setPreferredSize(new Dimension(200, 10));
        treeBC = new JSlider(SLIDER_MIN, SLIDER_MAX, 60);
        tbc = new JLabel("Tree Burn Chance");
        tbc.setPreferredSize(new Dimension(200, 10));
        grassBC = new JSlider(SLIDER_MIN, SLIDER_MAX, 20);
        gbc = new JLabel("Grass Burn Chance");
        gbc.setPreferredSize(new Dimension(200, 10));
        ashBC = new JSlider(SLIDER_MIN, SLIDER_MAX, 0);
        abc = new JLabel("Ash Burn Chance");
        abc.setPreferredSize(new Dimension(200, 10));
        speed = new JSlider();
        s = new JLabel("Burn Speed");
        s.setPreferredSize(new Dimension(200, 10));

        start.addActionListener(e -> beginSimulation(forestSize.getValue(),
                                                    forestDensity.getValue(),
                                                    treeBC.getValue(),
                                                    grassBC.getValue(),
                                                    ashBC.getValue(),
                                                    (100 - speed.getValue() + 1) * 10));
        panel.add(fsize);
        panel.add(fdensity);
        panel.add(forestSize);
        panel.add(forestDensity);
        panel.add(tbc);
        panel.add(gbc);
        panel.add(treeBC);
        panel.add(grassBC);
        panel.add(abc);
        panel.add(s);
        panel.add(ashBC);
        panel.add(speed);
        panel.add(start);
        panel.setPreferredSize(new Dimension(450, 155));
        menu.add(panel);
        menu.pack();
        menu.setVisible(true);
        menu.setResizable(true);
    }

    private void beginSimulation(int forestSize, int forestDensity, int treeBC, int grassBC, int ashBC, int speed) {

        forestParams = new ForestParams(forestSize, forestDensity, treeBC, grassBC, ashBC);
        forest = new Forest(forestParams);
        graphics = new Graphics(forest/*new Forest(forestDensity, forestSize)*/);

        graphics.setVisible(true);
        graphics.startTimer();
    }

    public static void main(String[] args) {
        new Thread(new Main()).start();
    }
}