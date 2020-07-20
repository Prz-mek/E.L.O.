package elo.positions;

import elo.Square;
import elo.evopt.Individual;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Ja
 */
public class Position {     // And sprinkler

    private final Square type;
    private final int x;
    private final int y;

    Position(Square sprin, int x, int y) {
        this.type = sprin;
        this.x = x;
        this.y = y;
    }
    
    public Square getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public static List<Position> getPositionsList(Individual ind, int scale) {
        ArrayList<Position> list = new ArrayList<>();
        Square[] ch = ind.getChromosome();
        int width = ind.getWidth();
        int height = ind.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j ++) {
                if (ch[i * width + j] != Square.BAR && ch[i * width + j] != Square.GRASS) {
                    switch (ch[i * width + j]) {
                        case FULL:
                            list.add(new Position(Square.FULL, j * scale + scale / 2, i * scale + scale / 2));
                            break;
                        case THREE_QUARTES_1:
                            list.add(new Position(Square.THREE_QUARTES_1, j * scale, i * scale));
                            break;
                        case THREE_QUARTES_2:
                            list.add(new Position(Square.THREE_QUARTES_2, j * scale, i * scale + scale - 1));
                            break;
                        case THREE_QUARTES_3:
                            list.add(new Position(Square.THREE_QUARTES_3, j * scale + scale - 1, i * scale + scale - 1));
                            break;
                        case THREE_QUARTES_4:
                            list.add(new Position(Square.THREE_QUARTES_4, j * scale + scale - 1, i * scale));
                            break;
                        case HALF_1:
                            list.add(new Position(Square.HALF_1, j * scale, i * scale + scale / 2));
                            break;
                        case HALF_2:
                            list.add(new Position(Square.HALF_2, j * scale + scale / 2, i * scale + scale - 1));
                            break;
                        case HALF_3:
                            list.add(new Position(Square.HALF_3, j * scale + scale - 1, i * scale + scale / 2));
                            break;
                        case HALF_4:
                            list.add(new Position(Square.HALF_4, j * scale + scale / 2, i * scale));
                            break;
                        case QUARTE_1:
                            list.add(new Position(Square.QUARTE_1,j * scale, i * scale + scale - 1));
                            break;
                        case QUARTE_2:
                            list.add(new Position(Square.QUARTE_2, j * scale + scale - 1, i * scale + scale - 1));
                            break;
                        case QUARTE_3:
                            list.add(new Position(Square.QUARTE_3, j * scale + scale - 1, i * scale));
                            break;
                        case QUARTE_4:
                            list.add(new Position(Square.QUARTE_4, j * scale, i * scale));
                            break;
                    }
                }
            }
        }
        
        return list;
    }

    @Override
    public String toString() {
        return "( " + x + ", " + y + " )"; 
    }
}
