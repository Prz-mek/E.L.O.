package elo;

/**
 *
 * @author Ja
 */
public enum Square {
    GRASS(0.0, 0) {
        @Override
        public void water(int[] map, int width, int x, int y, int time) {
        }
    },
    BAR(0.0, 0) {
        @Override
        public void water(int[] map, int width, int x, int y, int time) {
            map[y * width + x] += Integer.MIN_VALUE;
        }
    },
    FULL(1.0, 4) {
        @Override
        public void water(int[] map, int width, int x, int y, int time) {
            int interval = 4;
            map[y * width + x] += time / interval;
        }
    },
    THREE_QUARTES_1(3.0, 3) {
        @Override
        public void water(int[] map, int width, int x, int y, int time) {
            int interval = 3;
            crossWater(map, width, x, y, time, interval);
            int maxw = width;
            int maxh = map.length / width;
            if (y - 1 < 0 || x + 1 >= maxw) {
                map[y * width + x] += time / interval;
            } else if (map[(y - 1) * width + x + 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y - 1) * width + x + 1] += time / interval;
            }

            if (x + 1 >= maxw || y + 1 >= maxh) {
                map[y * width + x] += time / interval;
            } else if (map[(y + 1) * width + x + 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y + 1) * width + x + 1] += time / interval;
            }

            if (y + 1 >= maxh || x - 1 < 0) {
                map[y * width + x] += time / interval;
            } else if (map[(y + 1) * width + x - 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y + 1) * width + x - 1] += time / interval;
            }
        }
    },
    THREE_QUARTES_2(3.0, 3) {
        @Override
        public void water(int[] map, int width, int x, int y, int time) {
            int interval = 3;
            crossWater(map, width, x, y, time, interval);
            int maxw = width;
            int maxh = map.length / width;
            if (y - 1 < 0 || x - 1 < 0) {
                map[y * width + x] += time / interval;
            } else if (map[(y - 1) * width + x - 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y - 1) * width + x - 1] += time / interval;
            }

            if (y - 1 < 0 || x + 1 >= maxw) {
                map[y * width + x] += time / interval;
            } else if (map[(y - 1) * width + x + 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y - 1) * width + x + 1] += time / interval;
            }

            if (x + 1 >= maxw || y + 1 >= maxh) {
                map[y * width + x] += time / interval;
            } else if (map[(y + 1) * width + x + 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y + 1) * width + x + 1] += time / interval;
            }
        }
    },
    THREE_QUARTES_3(3.0, 3) {
        @Override
        public void water(int[] map, int width, int x, int y, int time) {
            int interval = 3;
            crossWater(map, width, x, y, time, interval);
            int maxw = width;
            int maxh = map.length / width;
            if (y + 1 >= maxh || x - 1 < 0) {
                map[y * width + x] += time / interval;
            } else if (map[(y + 1) * width + x - 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y + 1) * width + x - 1] += time / interval;
            }

            if (y - 1 < 0 || x - 1 < 0) {
                map[y * width + x] += time / interval;
            } else if (map[(y - 1) * width + x - 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y - 1) * width + x - 1] += time / interval;
            }

            if (y - 1 < 0 || x + 1 >= maxw) {
                map[y * width + x] += time / interval;
            } else if (map[(y - 1) * width + x + 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y - 1) * width + x + 1] += time / interval;
            }
        }
    },
    THREE_QUARTES_4(3.0, 3) {
        @Override
        public void water(int[] map, int width, int x, int y, int time) {
            int interval = 3;
            crossWater(map, width, x, y, time, interval);
            int maxw = width;
            int maxh = map.length / width;
            if (y + 1 >= maxh || x + 1 >= maxh) {
                map[y * width + x] += time / interval;
            } else if (map[(y + 1) * width + x + 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y + 1) * width + x + 1] += time / interval;
            }

            if (y + 1 >= maxh || x - 1 < 0) {
                map[y * width + x] += time / interval;
            } else if (map[(y + 1) * width + x - 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y + 1) * width + x - 1] += time / interval;
            }

            if (y - 1 < 0 || x - 1 < 0) {
                map[y * width + x] += time / interval;
            } else if (map[(y - 1) * width + x - 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y - 1) * width + x - 1] += time / interval;
            }
        }
    },
    HALF_1(2.0, 2) {
        @Override
        public void water(int[] map, int width, int x, int y, int time) {
            int interval = 2;
            int maxw = width;
            int maxh = map.length / width;
            map[y * width + x] += time / interval;
            if (y - 1 < 0) {
                map[y * width + x] += time / interval;
            } else if (map[(y - 1) * width + x] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y - 1) * width + x] += time / interval;
            }

            if (x + 1 >= maxw) {
                map[y * width + x] += time / interval;
            } else if (map[y * width + x + 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[y * width + x + 1] += time / interval;
            }

            if (y + 1 >= maxh) {
                map[y * width + x] += time / interval;
            } else if (map[(y + 1) * width + x] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y + 1) * width + x] += time / interval;
            }
        }
    },
    HALF_2(2.0, 2) {
        @Override
        public void water(int[] map, int width, int x, int y, int time) {
            int interval = 2;
            int maxw = width;
            map[y * width + x] += time / interval;
            if (x - 1 < 0) {
                map[y * width + x] += time / interval;
            } else if (map[y * width + x - 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[y * width + x - 1] += time / interval;
            }

            if (y - 1 < 0) {
                map[y * width + x] += time / interval;
            } else if (map[(y - 1) * width + x] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y - 1) * width + x] += time / interval;
            }

            if (x + 1 >= maxw) {
                map[y * width + x] += time / interval;
            } else if (map[y * width + x + 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[y * width + x + 1] += time / interval;
            }
        }
    },
    HALF_3(2.0, 2) {
        @Override
        public void water(int[] map, int width, int x, int y, int time) {
            int interval = 2;
            int maxw = width;
            int maxh = map.length / width;
            map[y * width + x] += time / interval;
            if (y - 1 < 0) {
                map[y * width + x] += time / interval;
            } else if (map[(y - 1) * width + x] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y - 1) * width + x] += time / interval;
            }

            if (x - 1 < 0) {
                map[y * width + x] += time / interval;
            } else if (map[y * width + x - 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[y * width + x - 1] += time / interval;
            }

            if (y + 1 >= maxh) {
                map[y * width + x] += time / interval;
            } else if (map[(y + 1) * width + x] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y + 1) * width + x] += time / interval;
            }
        }
    },
    HALF_4(2.0, 2) {
        @Override
        public void water(int[] map, int width, int x, int y, int time) {
            int interval = 2;
            int maxw = width;
            int maxh = map.length / width;
            map[y * width + x] += time / interval;
            if (x - 1 < 0) {
                map[y * width + x] += time / interval;
            } else if (map[y * width + x - 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[y * width + x - 1] += time / interval;
            }

            if (y + 1 >= maxh) {
                map[y * width + x] += time / interval;
            } else if (map[(y + 1) * width + x] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y + 1) * width + x] += time / interval;
            }

            if (x + 1 >= maxw) {
                map[y * width + x] += time / interval;
            } else if (map[y * width + x + 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[y * width + x + 1] += time / interval;
            }
        }
    },
    QUARTE_1(4.0, 1) {
        @Override
        public void water(int[] map, int width, int x, int y, int time) {
            int interval = 1;
            armsWater(map, width, x, y, 1, -1, 4, time, interval);

            int maxw = width;
            if (x + 1 >= maxw || y - 1 < 0) {
                map[y * width + x] += 3 * time / interval;
            } else if (map[(y - 1) * width + x + 1] < 0) {
                map[y * width + x] += 3 * time / interval;
            } else {
                map[(y - 1) * width + x + 1] += time / interval;
                if (x + 2 >= maxw) {
                    map[(y - 1) * width + x + 1] += time / interval;
                } else if (map[(y - 1) * width + x + 2] < 0) {
                    map[(y - 1) * width + x + 1] += time / interval;
                } else {
                    map[(y - 1) * width + x + 2] += time / interval;
                }
                
                if (y - 2 < 0) {
                    map[(y - 1) * width + x + 1] += time / interval;
                } else if (map[(y - 2) * width + x + 1] < 0) {
                    map[(y - 1) * width + x + 1] += time / interval;
                } else {
                    map[(y - 2) * width + x + 1] += time / interval;
                }
            }
        }
    },
    QUARTE_2(4.0, 1) {
        @Override
        public void water(int[] map, int width, int x, int y, int time) {
            int interval = 1;
            armsWater(map, width, x, y, -1, -1, 4, time, interval);
           
            if (x - 1 < 0 || y - 1 < 0) {
                map[y * width + x] += 3 * time / interval;
            } else if (map[(y - 1) * width + x - 1] < 0) {
                map[y * width + x] += 3 * time / interval;
            } else {
                map[(y - 1) * width + x - 1] += time / interval;
                if (x - 2 < 0) {
                    map[(y - 1) * width + x - 1] += time / interval;
                } else if (map[(y - 1) * width + x - 2] < 0) {
                    map[(y - 1) * width + x - 1] += time / interval;
                } else {
                    map[(y - 1) * width + x - 2] += time / interval;
                }
                
                if (y - 2 < 0) {
                    map[(y - 1) * width + x - 1] += time / interval;
                } else if (map[(y - 2) * width + x - 1] < 0) {
                    map[(y - 1) * width + x - 1] += time / interval;
                } else {
                    map[(y - 2) * width + x - 1] += time / interval;
                }
            }
        }
    },
    QUARTE_3(4.0, 1) {
        @Override
        public void water(int[] map, int width, int x, int y, int time) {
            int interval = 1;
            armsWater(map, width, x, y, -1, 1, 4, time, interval);
            
            int maxh = map.length / width;
            if (x - 1 < 0 || y + 1 >= maxh) {
                map[y * width + x] += 3 * time / interval;
            } else if (map[(y + 1) * width + x - 1] < 0) {
                map[y * width + x] += 3 * time / interval;
            } else {
                map[(y + 1) * width + x - 1] += time / interval;
                if (x - 2 < 0) {
                    map[(y + 1) * width + x - 1] += time / interval;
                } else if (map[(y + 1) * width + x - 2] < 0) {
                    map[(y + 1) * width + x - 1] += time / interval;
                } else {
                    map[(y + 1) * width + x - 2] += time / interval;
                }
                
                if (y + 2 >= maxh) {
                    map[(y + 1) * width + x - 1] += time / interval;
                } else if (map[(y + 2) * width + x] < 0) {
                    map[(y + 1) * width + x - 1] += time / interval;
                } else {
                    map[(y + 2) * width + x - 1] += time / interval;
                }
            }
        }
    },
    QUARTE_4(4.0, 1) {
        @Override
        public void water(int[] map, int width, int x, int y, int time) {
            int interval = 1;
            armsWater(map, width, x, y, 1, 1, 4, time, interval);
            
            int maxw = width;
            int maxh = map.length / width;
            if (x + 1 >= maxw || y + 1 >= maxh) {
                map[y * width + x] += 3 * time / interval;
            } else if (map[(y + 1) * width + x + 1] < 0) {
                map[y * width + x] += 3 * time / interval;
            } else {
                map[(y + 1) * width + x + 1] += time / interval;
                if (x + 2 >= maxw) {
                    map[(y + 1) * width + x + 1] += time / interval;
                } else if (map[(y + 1) * width + x + 2] < 0) {
                    map[(y + 1) * width + x + 1] += time / interval;
                } else {
                    map[(y + 1) * width + x + 2] += time / interval;
                }
                
                if (y + 2 >= maxh) {
                    map[(y + 1) * width + x + 1] += time / interval;
                } else if (map[(y + 2) * width + x] < 0) {
                    map[(y + 1) * width + x + 1] += time / interval;
                } else {
                    map[(y + 2) * width + x + 1] += time / interval;
                }
            }
        }
    };

    private final double radius;
    private final int interval;

    private Square(double r, int t) {
        radius = r;
        interval = t;
    }

    public double getRadius() {
        return radius;
    }

    public int getInterval() {
        return interval;
    }

    public static Square numToSquare(int n) {
        switch (n) {
            case 0:
                return BAR;
            case 1:
                return GRASS;
            case 2:
                return FULL;
            case 3:
                return THREE_QUARTES_1;
            case 4:
                return THREE_QUARTES_2;
            case 5:
                return THREE_QUARTES_3;
            case 6:
                return THREE_QUARTES_4;
            case 7:
                return HALF_1;
            case 8:
                return HALF_2;
            case 9:
                return HALF_3;
            case 10:
                return HALF_4;
            case 11:
                return QUARTE_1;
            case 12:
                return QUARTE_2;
            case 13:
                return QUARTE_3;
            case 14:
                return QUARTE_4;
        }
        return null;
    }

    public abstract void water(int[] map, int width, int x, int y, int time);

    private static void crossWater(int[] map, int width, int x, int y, int time, int interval) {
        int maxw = width;
        int maxh = map.length / width;
        map[y * width + x] += time / interval;
        if (x - 1 < 0) {
            map[y * width + x] += time / interval;
            if (x + 1 >= maxw) {
                map[y * width + x] += time / interval;
            } else if (map[y * width + x + 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[y * width + x + 1] += time / interval;
            }
        } else if (map[y * width + x - 1] < 0) {
            map[y * width + x] += time / interval;
            if (x + 1 >= maxw) {
                map[y * width + x] += time / interval;
            } else if (map[y * width + x + 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[y * width + x + 1] += time / interval;
            }
        } else {
            map[y * width + x - 1] += time / interval;
            if (x - 2 < 0) {
                map[y * width + x - 1] += time / interval;
            } else if (map[y * width + x - 2] < 0) {
                map[y * width + x - 1] += time / interval;
            } else {
                map[y * width + x - 2] += time / interval;
            }
        }

        if (x + 1 >= maxw) {
            map[y * width + x] += time / interval;
            if (x - 1 < 0) {
                map[y * width + x] += time / interval;
            } else if (map[y * width + x - 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[y * width + x - 1] += time / interval;
            }
        } else if (map[y * width + x + 1] < 0) {
            map[y * width + x] += time / interval;
            if (x - 1 < 0) {
                map[y * width + x] += time / interval;
            } else if (map[y * width + x - 1] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[y * width + x - 1] += time / interval;
            }
        } else {
            map[y * width + x + 1] += time / interval;
            if (x + 2 >= maxw) {
                map[y * width + x + 1] += time / interval;
            } else if (map[y * width + x + 2] < 0) {
                map[y * width + x + 1] += time / interval;
            } else {
                map[y * width + x + 2] += time / interval;
            }
        }

        if (y - 1 < 0) {
            map[y * width + x] += time / interval;
            if (y + 1 >= maxh) {
                map[y * width + x] += time / interval;
            } else if (map[(y + 1) * width + x] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y + 1) * width + x] += time / interval;
            }
        } else if (map[(y - 1) * width + x] < 0) {
            map[y * width + x] += time / interval;
            if (y + 1 >= maxh) {
                map[y * width + x] += time / interval;
            } else if (map[(y + 1) * width + x] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y + 1) * width + x] += time / interval;
            }
        } else {
            map[(y - 1) * width + x] += time / interval;
            if (y - 2 < 0) {
                map[(y - 1) * width + x] += time / interval;
            } else if (map[(y - 2) * width + x] < 0) {
                map[(y - 1) * width + x] += time / interval;
            } else {
                map[(y - 2) * width + x] += time / interval;
            }
        }

        if (y + 1 >= maxh) {
            map[y * width + x] += time / interval;
            if (y - 1 < 0) {
                map[y * width + x] += time / interval;
            } else if (map[(y - 1) * width + x] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y - 1) * width + x] += time / interval;
            }
        } else if (map[(y + 1) * width + x] < 0) {
            map[y * width + x] += time / interval;
            if (y - 1 < 0) {
                map[y * width + x] += time / interval;
            } else if (map[(y - 1) * width + x] < 0) {
                map[y * width + x] += time / interval;
            } else {
                map[(y - 1) * width + x] += time / interval;
            }
        } else {
            map[(y + 1) * width + x] += time / interval;
            if (y + 2 >= maxh) {
                map[(y + 1) * width + x] += time / interval;
            } else if (map[(y + 2) * width + x] < 0) {
                map[(y + 1) * width + x] += time / interval;
            } else {
                map[(y + 2) * width + x] += time / interval;
            }
        }
    }

    private static void armsWater(int[] map, int width, int x0, int y0, int desX, int desY, int r, int time, int interval) {
        int maxw = width;
        int maxh = map.length / width;
        map[y0 * width + x0] += time / interval;

        int x = desX;
        int dx = desX;
        for (int i = 1; i < r; i++) {
            if (x + x0 < 0 || x + x0 >= maxw) {
                dx = -dx;
                x += dx;
            }
            if (map[y0 * width + x + x0] < 0) {
                dx = -dx;
                x += dx;
            }
            if (x + x0 < 0 || x + x0 >= maxw) {
                dx = -dx;
                x += dx;
            }
            map[y0 * width + x + x0] += time / interval;
            x += dx;

        }

        int y = desY;
        int dy = desY;
        for (int i = 1; i < r; i++) {
            if (y + y0 < 0 || y + y0 >= maxh) {
                dy = -dy;
                y += dy;
            }
            if (map[(y + y0) * width + x0] < 0) {
                dy = -dy;
                y += dy;
            }
            if (y + y0 < 0 || y + y0 >= maxh) {
                dy = -dy;
                y += dy;
            }
            map[(y + y0) * width + x0] += time / interval;
            y += dy;
        }
    }
}
