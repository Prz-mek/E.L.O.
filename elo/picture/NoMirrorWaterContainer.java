package elo.picture;

/**
 *
 * @author Ja
 */
public class NoMirrorWaterContainer {

    static public class Full implements WaterFunction {

        @Override
        public void water(HydroMap map, int x0, int y0, int r, int amount) {
            waterQuarte(map, x0, y0, r, 1, -1, amount);
            waterQuarte(map, x0, y0, r, -1, -1, amount);
            waterQuarte(map, x0, y0, r, -1, 1, amount);
            waterQuarte(map, x0, y0, r, 1, 1, amount);
            removeBarsX(map, r, x0, y0, 1, amount);
            removeBarsX(map, r, x0, y0, -1, amount);
            removeBarsY(map, r, x0, y0, 1, amount);
            removeBarsY(map, r - 1, x0, y0 - 1, -1, amount);
        }
    }

    static public class ThreeQuartes1 implements WaterFunction {

        @Override
        public void water(HydroMap map, int x0, int y0, int r, int amount) {
            waterQuarte(map, x0, y0, r, 1, -1, amount);
            waterQuarte(map, x0, y0, r, 1, 1, amount);
            waterQuarte(map, x0, y0, r, -1, 1, amount);
            removeBarsX(map, r, x0, y0, 1, amount);
            removeBarsY(map, r, x0, y0, 1, amount);
        }
    }

    static public class ThreeQuartes2 implements WaterFunction {

        @Override
        public void water(HydroMap map, int x0, int y0, int r, int amount) {
            waterQuarte(map, x0, y0, r, -1, -1, amount);
            waterQuarte(map, x0, y0, r, 1, -1, amount);
            waterQuarte(map, x0, y0, r, 1, 1, amount);
            removeBarsX(map, r, x0, y0, 1, amount);
            removeBarsY(map, r, x0, y0, -1, amount);
        }
    }

    static public class ThreeQuartes3 implements WaterFunction {

        @Override
        public void water(HydroMap map, int x0, int y0, int r, int amount) {
            waterQuarte(map, x0, y0, r, -1, 1, amount);
            waterQuarte(map, x0, y0, r, -1, -1, amount);
            waterQuarte(map, x0, y0, r, 1, -1, amount);
            removeBarsX(map, r, x0, y0, -1, amount);
            removeBarsY(map, r, x0, y0, -1, amount);
        }
    }

    static public class ThreeQuartes4 implements WaterFunction {

        @Override
        public void water(HydroMap map, int x0, int y0, int r, int amount) {
            waterQuarte(map, x0, y0, r, 1, 1, amount);
            waterQuarte(map, x0, y0, r, -1, 1, amount);
            waterQuarte(map, x0, y0, r, -1, -1, amount);
            removeBarsX(map, r, x0, y0, -1, amount);
            removeBarsY(map, r, x0, y0, 1, amount);
        }
    }

    static public class Half1 implements WaterFunction {

        @Override
        public void water(HydroMap map, int x0, int y0, int r, int amount) {
            waterQuarte(map, x0, y0, r, 1, -1, amount);
            waterQuarte(map, x0, y0, r, 1, 1, amount);
            removeBarsX(map, r, x0, y0, 1, amount);
        }
    }

    static public class Half2 implements WaterFunction {

        @Override
        public void water(HydroMap map, int x0, int y0, int r, int amount) {
            waterQuarte(map, x0, y0, r, -1, -1, amount);
            waterQuarte(map, x0, y0, r, 1, -1, amount);
            removeBarsY(map, r, x0, y0, -1, amount);
        }
    }

    static public class Half3 implements WaterFunction {

        @Override
        public void water(HydroMap map, int x0, int y0, int r, int amount) {
            waterQuarte(map, x0, y0, r, -1, 1, amount);
            waterQuarte(map, x0, y0, r, -1, -1, amount);
            removeBarsX(map, r, x0, y0, -1, amount);
        }
    }

    static public class Half4 implements WaterFunction {

        @Override
        public void water(HydroMap map, int x0, int y0, int r, int amount) {
            waterQuarte(map, x0, y0, r, 1, 1, amount);
            waterQuarte(map, x0, y0, r, -1, 1, amount);
            removeBarsY(map, r, x0, y0, 1, amount);
        }
    }

    static public class Quarte1 implements WaterFunction {

        @Override
        public void water(HydroMap map, int x0, int y0, int r, int amount) {
            waterQuarte(map, x0, y0, r, 1, -1, amount);
        }
    }

    static public class Quarte2 implements WaterFunction {

        @Override
        public void water(HydroMap map, int x0, int y0, int r, int amount) {
            waterQuarte(map, x0, y0, r, -1, -1, amount);
        }
    }

    static public class Quarte3 implements WaterFunction {

        @Override
        public void water(HydroMap map, int x0, int y0, int r, int amount) {
            waterQuarte(map, x0, y0, r, 1, -1, amount);
        }
    }

    static public class Quarte4 implements WaterFunction {

        @Override
        public void water(HydroMap map, int x0, int y0, int r, int amount) {
            waterQuarte(map, x0, y0, r, 1, -1, amount);
        }
    }

    static void waterQuarte(HydroMap map, int x0, int y0, int r, int dix, int diy, int amount) {
        int maxh = map.getHeight();
        int maxw = map.getWidth();
        int r2 = r * r;
        int ix = 0;
        int iy = 0;
        for (int i = 0; 2 * i * i <= r2; i++) {
            if (ix + x0 < 0 || ix + x0 >= maxw) {
                break;
            }
            if (iy + y0 < 0 || iy + y0 >= maxh) {
                break;
            }
            if (map.get(iy + y0, ix + x0) < 0) {
                break;
            }
            map.add(iy + y0, ix + x0, amount);

            int x = dix;
            int dx = dix;
            for (int j = 1; (i + j) * (i + j) + i * i <= r2; j++) {
                if (ix + x + x0 < 0 || ix + x + x0 >= maxw) {
                    break;
                }
                if (ix + x + x0 > 0 && ix + x + x0 < maxw && iy + y0 >= 0 && iy + y0 < maxh) {
                    if (map.get(iy + y0, ix + x + x0) < 0) {
                        break;
                    }
                }
                if (ix + x + x0 < 0 || ix + x + x0 >= maxw) {
                    break;
                }
                map.add(iy + y0, ix + x + x0, amount);
                x += dx;
            }

            int y = diy;
            int dy = diy;
            for (int j = 1; (i + j) * (i + j) + i * i <= r2; j++) {
                if (iy + y + y0 < 0 || iy + y + y0 >= maxh) {
                    break;
                }
                if (ix + x0 > 0 && ix + x0 < maxw && iy + y + y0 >= 0 && iy + y + y0 < maxh) {
                    if (map.get(iy + y + y0, ix + x0) < 0) {
                        break;
                    }
                }
                if (iy + y + y0 < 0 || iy + y + y0 >= maxh) {
                    break;
                }
                map.add(iy + y + y0, ix + x0, amount);
                y += dy;
            }

            ix += dix;
            iy += diy;
        }
    }

    static void removeBarsX(HydroMap map, int r, int x0, int y0, int dest, int amount) {
        int x = 0;
        int dx = dest;
        int maxw = map.getWidth();
        for (int j = 0; j <= r; j++) {
            if (x + x0 < 0 || x + x0 >= maxw) {
                break;
            }
            if (map.get(y0, x + x0) < 0) {
                break;
            }

            map.add(y0, x + x0, -amount);
            x += dx;
        }
    }

    static void removeBarsY(HydroMap map, int r, int x0, int y0, int dest, int amount) {
        int maxh = map.getHeight();
        int y = 0;
        int dy = dest;
        for (int j = 0; j <= r; j++) {
            if (y + y0 < 0 || y + y0 >= maxh) {
                break;
            }
            if (map.get(y + y0, x0) < 0) {
                break;
            }

            map.add(y + y0, x0, -amount);
            y += dy;
        }
    }
}
