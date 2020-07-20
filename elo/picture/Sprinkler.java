package elo.picture;

/**
 *
 * @author Ja
 */
public class Sprinkler {

    private int x;
    private int y;
    private int r;
    private int interval;
    private WaterFunction fun;

    public Sprinkler(int x, int y, int r, int interval, WaterFunction fun) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.interval = interval;
        this.fun = fun;
    }

    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void water(HydroMap map, int time) {
        fun.water(map, x, y, r, time / interval);
    }
}
