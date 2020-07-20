package elo.picture;

/**
 *
 * @author Ja
 */
public class MirrorWaterContainer  {

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
            waterQuarte(map, x0, y0, r, -1, 1, amount);
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
                ix -= dix;
                dix = -dix;
            }
            if (iy + y0 < 0 || iy + y0 >= maxh) {
                iy -= diy;
                diy = -diy;
            }
            if (map.get(iy + y0, ix + x0) < 0) {
                int fx = 0;
                int fy = 0;
                if (ix - dix + x0 >= 0 && ix - dix + x0 < maxw && iy + y0 >= 0 && iy + y0 < maxh) {
                    if (map.get(iy + y0, ix - dix + x0) >= 0) {
                        fx = 1;
                    }
                }
                if (ix + x0 >= 0 && ix + x0 < maxw && iy - diy + y0 >= 0 && iy - diy + y0 < maxh) {
                    if (map.get(iy - diy + y0, ix + x0) >= 0) {
                        fy = 1;
                    }
                }

                if (fx == 1 && fy == 0) {
                    ix -= dix;
                    dix = -dix;
                } else if (fx == 0 && fy == 1) {
                    iy -= diy;
                    diy = -diy;
                } else if (ix - dix + x0 > 0 && ix - dix + x0 < maxw && iy - diy + y0 >= 0 && iy - diy + y0 < maxh) {
                    ix -= dix;
                    dix = -dix;
                    iy -= diy;
                    diy = -diy;
                }
            }
            map.add(iy + y0, ix + x0, amount);

            int x = dix;
            int dx = dix;
            for (int j = 1; (i + j) * (i + j) + i * i <= r2; j++) {
                if (ix + x + x0 < 0 || ix + x + x0 >= maxw) {
                    x -= dx;
                    dx = -dx;
                }
                if (ix + x + x0 > 0 && ix + x + x0 < maxw && iy + y0 >= 0 && iy + y0 < maxh) {
                    if (map.get(iy + y0, ix + x + x0) < 0) {
                        x -= dx;
                        dx = -dx;
                    }
                }
                if (ix + x + x0 < 0 || ix + x + x0 >= maxw) {
                    x -= dx;
                    dx = -dx;
                }
                map.add(iy + y0, ix + x + x0, amount);
                x += dx;
            }

            int y = diy;
            int dy = diy;
            for (int j = 1; (i + j) * (i + j) + i * i <= r2; j++) {
                if (iy + y + y0 < 0 || iy + y + y0 >= maxh) {
                    y -= dy;
                    dy = -dy;
                }
                if (ix + x0 > 0 && ix + x0 < maxw && iy + y + y0 >= 0 && iy + y + y0 < maxh) {
                    if (map.get(iy + y + y0, ix + x0) < 0) {
                        y -= dy;
                        dy = -dy;
                    }
                }
                if (iy + y + y0 < 0 || iy + y + y0 >= maxh) {
                    y -= dy;
                    dy = -dy;
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
                dx = -dx;
                x += dx;
            }
            if (map.get(y0, x + x0) < 0) {
                dx = -dx;
                x += dx;
            }
            if (x + x0 < 0 || x + x0 >= maxw) {
                dx = -dx;
                x += dx;
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
                dy = -dy;
                y += dy;
            }
            if (map.get(y + y0, x0) < 0) {
                dy = -dy;
                y += dy;
            }
            if (y + y0 < 0 || y + y0 >= maxh) {
                dy = -dy;
                y += dy;
            }
            map.add(y + y0, x0, -amount);
            y += dy;
        }
    }
}
