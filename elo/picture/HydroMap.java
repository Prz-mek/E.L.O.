package elo.picture;

import elo.Square;
import java.util.Arrays;

/**
 *
 * @author Ja
 */
public class HydroMap {

    private final int width;
    private final int height;
    private final int[][] field;

    public HydroMap(Square[] ch, int w, int scale) {
        this.width = w * scale;
        int h = ch.length / w;
        this.height = h * scale;
        field = new int[height][width];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (ch[i * w + j] == Square.BAR) {
                    for (int k = i * scale; k < (i + 1) * scale; k++) {
                        for (int l = j * scale; l < (j + 1) * scale; l++) {
                            field[k][l] = Integer.MIN_VALUE;
                        }
                    }
                }
            }
        }
    }
    
    private HydroMap(int w, int h, int[][] f) {
        this.width = w;
        this.height = h;
        this.field = f;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int get(int i, int j) {
        return field[i][j];
    }
    
    public void add(int i, int j, int value) {
        field[i][j] += value;
    }
    
    public double avg() {
        double total = 0;
        int blank = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (field[i][j] >= 0) {
                    total += field[i][j];
                    blank++;
                }
            }
        }
        
        return total / blank;
    }
    
    public HydroMap copyMap(){
        int[][] f = new int[height][];
        for (int i = 0; i < height; i++) {
            f[i] = Arrays.copyOf(field[i], width);
        }
        return new HydroMap(width, height, f);
    }

}
