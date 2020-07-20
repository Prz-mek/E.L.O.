package elo.picture;

import elo.Square;
import java.util.ArrayList;

/**
 *
 * @author Ja
 */
public class SprinklersGroup {
    
    ArrayList<Sprinkler> group;

    
    public SprinklersGroup(Square[] result, int width, boolean mirror, int scale) {
        group = new ArrayList<>();
        int height = result.length / width;
        if (mirror) {
            fillSprinklersBaltching(result, height, width, scale);
        } else {
            fillSprinklersNoBaltching(result, height, width, scale);
        }       
    }
    
    public HydroMap water(HydroMap m, int time) {
        return water(m, time, Runtime.getRuntime().availableProcessors());
    }
    
    public HydroMap water(HydroMap m, int time, int threadsNum) {
        HydroMap map = m.copyMap();
        int n = group.size();
        int from[] = new int[threadsNum];
        int to[] = new int[threadsNum];
        int perThread = n / threadsNum;
        int rest = n % threadsNum;

        from[0] = 0;
        for (int i = 1; i < rest; i++) {
            from[i] = from[i - 1] + perThread + 1;
            to[i - 1] = from[i];
        }
        if (rest != 0) {
            for (int i = rest; i < threadsNum; i++) {
                from[i] = from[i - 1] + perThread;
                to[i - 1] = from[i];
            }
        }
        to[threadsNum - 1] = n;

        WaterThread[] threads = new WaterThread[threadsNum];
        for (int i = 0; i < threadsNum; i++) {
            threads[i] = new WaterThread(map, from[i], to[i], time);
            threads[i].start();
        }
        try {
            for (WaterThread ct : threads) {
                ct.join();
            }
        } catch (InterruptedException e) {

        }

        return map;
    }
    
    private class WaterThread extends Thread {

        private final HydroMap map;
        private final int from;
        private final int to;
        private final int time;

        public WaterThread(HydroMap map, int from, int to, int time) {
            this.map = map;
            this.from = from;
            this.to = to;
            this.time = time;
        }

        @Override
        public void run() {
            for (int i = from; i < to; i++) {
                group.get(i).water(map, time);
            }
        }
    }
    
    private void fillSprinklersBaltching(Square[] result, int height, int width, int scale) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (result[i * width + j] != Square.BAR && result[i * width + j] != Square.GRASS) {
                    switch (result[i * width + j]) {
                        case FULL:
                            group.add(new Sprinkler(j * scale + scale / 2, i * scale + scale / 2, (int) Square.FULL.getRadius() * scale,
                                    Square.FULL.getInterval(),  new MirrorWaterContainer.Full()));
                            break;
                        case THREE_QUARTES_1:
                            group.add(new Sprinkler(j * scale, i * scale, (int) Square.THREE_QUARTES_1.getRadius() * scale,
                                    Square.THREE_QUARTES_2.getInterval(), new MirrorWaterContainer.ThreeQuartes1()));
                            break;
                        case THREE_QUARTES_2:
                            group.add(new Sprinkler(j * scale, i * scale + scale - 1, (int) Square.THREE_QUARTES_2.getRadius() * scale,
                                    Square.THREE_QUARTES_2.getInterval(), new MirrorWaterContainer.ThreeQuartes2()));
                            break;
                        case THREE_QUARTES_3:
                            group.add(new Sprinkler(j * scale + scale - 1, i * scale + scale - 1, (int) Square.THREE_QUARTES_3.getRadius() * scale,
                                    Square.THREE_QUARTES_3.getInterval(), new MirrorWaterContainer.ThreeQuartes3()));
                            break;
                        case THREE_QUARTES_4:
                            group.add(new Sprinkler(j * scale + scale - 1, i * scale, (int) Square.THREE_QUARTES_4.getRadius() * scale,
                                    Square.THREE_QUARTES_4.getInterval(), new MirrorWaterContainer.ThreeQuartes4()));
                            break;
                        case HALF_1:
                            group.add(new Sprinkler(j * scale, i * scale + scale / 2, (int) Square.HALF_1.getRadius() * scale,
                                    Square.HALF_1.getInterval(), new MirrorWaterContainer.Half1()));
                            break;
                        case HALF_2:
                            group.add(new Sprinkler(j * scale + scale / 2, i * scale + scale - 1, (int) Square.HALF_2.getRadius() * scale,
                                    Square.HALF_2.getInterval(), new MirrorWaterContainer.Half2()));
                            break;
                        case HALF_3:
                            group.add(new Sprinkler(j * scale + scale - 1, i * scale + scale / 2, (int) Square.HALF_3.getRadius() * scale,
                                    Square.HALF_3.getInterval(), new MirrorWaterContainer.Half3()));
                            break;
                        case HALF_4:
                            group.add(new Sprinkler(j * scale + scale / 2, i * scale, (int) Square.HALF_4.getRadius() * scale,
                                    Square.HALF_4.getInterval(), new MirrorWaterContainer.Half4()));
                            break;
                        case QUARTE_1:
                            group.add(new Sprinkler(j * scale, i * scale + scale - 1, (int) Square.QUARTE_1.getRadius() * scale,
                                    Square.QUARTE_1.getInterval(), new MirrorWaterContainer.Quarte1()));
                            break;
                        case QUARTE_2:
                            group.add(new Sprinkler(j * scale + scale - 1, i * scale + scale - 1, (int) Square.QUARTE_2.getRadius() * scale,
                                    Square.QUARTE_2.getInterval(), new MirrorWaterContainer.Quarte2()));
                            break;
                        case QUARTE_3:
                            group.add(new Sprinkler(j * scale + scale - 1, i * scale, (int) Square.QUARTE_3.getRadius() * scale,
                                    Square.QUARTE_3.getInterval(), new MirrorWaterContainer.Quarte3()));
                            break;
                        case QUARTE_4:
                            group.add(new Sprinkler(j * scale, i * scale, (int) Square.QUARTE_4.getRadius() * scale,
                                    Square.QUARTE_4.getInterval(), new MirrorWaterContainer.Quarte4()));
                            break;
                    }
                }
            }
        }
    }
    
    private void fillSprinklersNoBaltching(Square[] result, int height, int width, int scale) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (result[i * width + j] != Square.BAR && result[i * width + j] != Square.GRASS) {
                    switch (result[i * width + j]) {
                        case FULL:
                            group.add(new Sprinkler(j * scale + scale / 2, i * scale + scale / 2, (int) Square.FULL.getRadius() * scale,
                                    Square.FULL.getInterval(),  new NoMirrorWaterContainer.Full()));
                            break;
                        case THREE_QUARTES_1:
                            group.add(new Sprinkler(j * scale, i * scale, (int) Square.THREE_QUARTES_1.getRadius() * scale,
                                    Square.THREE_QUARTES_2.getInterval(), new NoMirrorWaterContainer.ThreeQuartes1()));
                            break;
                        case THREE_QUARTES_2:
                            group.add(new Sprinkler(j * scale, i * scale + scale - 1, (int) Square.THREE_QUARTES_2.getRadius() * scale,
                                    Square.THREE_QUARTES_2.getInterval(), new NoMirrorWaterContainer.ThreeQuartes2()));
                            break;
                        case THREE_QUARTES_3:
                            group.add(new Sprinkler(j * scale + scale - 1, i * scale + scale - 1, (int) Square.THREE_QUARTES_3.getRadius() * scale,
                                    Square.THREE_QUARTES_3.getInterval(), new NoMirrorWaterContainer.ThreeQuartes3()));
                            break;
                        case THREE_QUARTES_4:
                            group.add(new Sprinkler(j * scale + scale - 1, i * scale, (int) Square.THREE_QUARTES_4.getRadius() * scale,
                                    Square.THREE_QUARTES_4.getInterval(), new NoMirrorWaterContainer.ThreeQuartes4()));
                            break;
                        case HALF_1:
                            group.add(new Sprinkler(j * scale, i * scale + scale / 2, (int) Square.HALF_1.getRadius() * scale,
                                    Square.HALF_1.getInterval(), new NoMirrorWaterContainer.Half1()));
                            break;
                        case HALF_2:
                            group.add(new Sprinkler(j * scale + scale / 2, i * scale + scale - 1, (int) Square.HALF_2.getRadius() * scale,
                                    Square.HALF_2.getInterval(), new NoMirrorWaterContainer.Half2()));
                            break;
                        case HALF_3:
                            group.add(new Sprinkler(j * scale + scale - 1, i * scale + scale / 2, (int) Square.HALF_3.getRadius() * scale,
                                    Square.HALF_3.getInterval(), new NoMirrorWaterContainer.Half3()));
                            break;
                        case HALF_4:
                            group.add(new Sprinkler(j * scale + scale / 2, i * scale, (int) Square.HALF_4.getRadius() * scale,
                                    Square.HALF_4.getInterval(), new NoMirrorWaterContainer.Half4()));
                            break;
                        case QUARTE_1:
                            group.add(new Sprinkler(j * scale, i * scale + scale - 1, (int) Square.QUARTE_1.getRadius() * scale,
                                    Square.QUARTE_1.getInterval(), new NoMirrorWaterContainer.Quarte1()));
                            break;
                        case QUARTE_2:
                            group.add(new Sprinkler(j * scale + scale - 1, i * scale + scale - 1, (int) Square.QUARTE_2.getRadius() * scale,
                                    Square.QUARTE_2.getInterval(), new NoMirrorWaterContainer.Quarte2()));
                            break;
                        case QUARTE_3:
                            group.add(new Sprinkler(j * scale + scale - 1, i * scale, (int) Square.QUARTE_3.getRadius() * scale,
                                    Square.QUARTE_3.getInterval(), new NoMirrorWaterContainer.Quarte3()));
                            break;
                        case QUARTE_4:
                            group.add(new Sprinkler(j * scale, i * scale, (int) Square.QUARTE_4.getRadius() * scale,
                                    Square.QUARTE_4.getInterval(), new NoMirrorWaterContainer.Quarte4()));
                            break;
                    }
                }
            }
        }
    }
}
