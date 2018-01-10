package Simulator;

public class Main implements Runnable{

    @Override
    public void run() {
        Forest forest = new Forest(75, 20); // first parameter is forest density (0-100) second is side length (so 20 is a 20 by 20 square)
        Graphics graphics = new Graphics(forest);

        while (forest.fires.size() > 0) { // main loop
            //forest.show();
            // if you uncomment forest.show() it has a text representation of the burning. This worked and had no memory leak. X meant tree, O meant grass, and a space was ash. F was fire.
            // I used that before JFrame.
            System.out.println(Graphics.frameNumber);
            try {
                Thread.sleep(1000);
            } catch (Exception e){
                System.out.println();
            }

            graphics.nextFrame();
        }
        //forest.show();
    }

    public static void main(String[] args) {
        new Thread(new Main()).start();
    }
}
