package Simulator;

public class Main implements Runnable{

    @Override
    public void run() {
        Forest forest = new Forest(75, 10);
        Graphics graphics = new Graphics(forest);

        while (forest.fires.size() > 0) {
            System.out.println(forest.fires.size());
            forest.show();
            try {
                Thread.sleep(1000);
            } catch (Exception e){
                System.out.println();
            }

            graphics.nextFrame();
        }
        forest.show();
    }

    public static void main(String[] args) {
        new Thread(new Main()).start();
    }
}
