package elo.picture;

import elo.Square;
import java.awt.image.BufferedImage;

/**
 *
 * @author Ja
 */
public class Watering{

    private final double DOWN = 0.9;
    private final double UP = 3.0;


    private final SprinklersGroup sprinklers;
    private final HydroMap map;



    public Watering(Square[] result, int width, boolean mirror, int scale) {
        sprinklers = new SprinklersGroup(result, width, mirror, scale);
        map = new HydroMap(result, width, scale);
    }
    
    public BufferedImage getImage(int time) {
        HydroMap hydro = sprinklers.water(map, time);
        return createPicture(hydro, hydro.avg(), DOWN, UP);
    }


    public BufferedImage createPicture(HydroMap map, double avg, double min, double max) {
        int w = map.getWidth();
        int h = map.getHeight();
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int value = map.get(i, j);
                double diff = (value - avg) / avg;
                diff *= diff < 0 ? -1 : 1;
                int rgb;
                if (value < 0) {
                    rgb = 128;                      // red
                    rgb = (rgb << 8) + 128;         // green
                    rgb = (rgb << 8) + 128;         // blue
                } else if (value == 0) {
                    rgb = 255;                      // red
                    rgb <<= 16;
                } else if (value > avg) {
                    rgb = (int)(diff < DOWN ? 255 : (diff < UP ? 255 - diff * 255 / UP : 0));       //green
                    rgb = (rgb << 8) +(int)(diff < DOWN ? 255 * diff / DOWN : 255);                 // blue
                } else {    
                    rgb = (int)(diff < DOWN ? 255 * diff / DOWN : 255);                 // red
                    rgb = (rgb << 8) + (int)(diff < DOWN ? 255 : 255 - diff * 255);     // green
                    rgb <<= 8;
                }
                
                img.setRGB(j, i, rgb);
            }
        }

        return img;
    }
}
