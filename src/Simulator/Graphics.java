package Simulator;


public class Graphics {

    private int frameNumber = 0;
    private Forest forest;

    public Graphics(){
        forest = new Forest();
    }

    public Graphics(Forest f){
        forest = f;
    }

    public void nextFrame(){
        forest.updateFire();
    }
}
