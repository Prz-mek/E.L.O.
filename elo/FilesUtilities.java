package elo;

import elo.evopt.Individual;
import elo.positions.Position;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author Ja
 */
public class FilesUtilities {

    public static Individual readFromFile(File file) throws IOException, IllegalArgumentException {

        final int MAX_WIDTH = 80;
        final int MAX_HEIGHT = 40;
        final char GRASS_SIGN = '*';
        final char BAR_SIGN = '-';

        BufferedReader buffer = null;

        try {
            buffer = new BufferedReader(new FileReader(file));
            char[][] temp = new char[MAX_HEIGHT][MAX_WIDTH];

            String line;
            int minLength = MAX_WIDTH;
            int lineCounter = 0;
            while ((line = buffer.readLine()) != null && lineCounter < MAX_HEIGHT) {
                int signCounter = 0;
                int l = line.length();
                for (int i = 0; i < l && i < MAX_WIDTH; i++) {
                    char c = line.charAt(i);
                    if (c == '\n') {
                        break;
                    } else if (Character.isWhitespace(c)) {
                        continue;
                    } else if (c == GRASS_SIGN) {
                        temp[lineCounter][signCounter] = c;
                    } else if (c == BAR_SIGN) {
                        temp[lineCounter][signCounter] = c;
                    } else {
                        throw new IllegalArgumentException();
                    }
                    signCounter++;
                }
                if (signCounter < minLength) {
                    minLength = signCounter;
                }
                lineCounter++;
            }

            Square[] ch = new Square[lineCounter * minLength];
            if (ch.length == 0) {
                throw new IllegalArgumentException();
            }

            for (int i = 0; i < lineCounter; i++) {
                for (int j = 0; j < minLength; j++) {
                    if (temp[i][j] == GRASS_SIGN) {
                        ch[i * minLength + j] = Square.GRASS;
                    } else {
                        ch[i * minLength + j] = Square.BAR;
                    }
                }
            }

            return new Individual(minLength, ch);

        } catch (IOException | IllegalArgumentException ex) {
            throw ex;
        } finally {
            if (buffer != null) {
                buffer.close();
            }
        }
    }

    public static void writeImage(BufferedImage img, String format, File out) throws IOException {
        ImageIO.write(img, format, out);
    }

    public static void writePositions(List<Position> list, File file) throws IOException {

        final int MAX_IN_LINE = 5;

        BufferedWriter buffer = null;
        try {
            buffer = new BufferedWriter(new FileWriter(file));
            int i = 0;
            buffer.write("360\u00B0:\n");
            for (Position p : list) {
                if (p.getType() == Square.FULL) {
                    buffer.write(String.format("%-20s", p));
                    if (i == MAX_IN_LINE) {
                        buffer.write("\n");
                        i = 0;
                    } else {
                        i++;
                    }
                }
            }
            buffer.write("\n");
            
            i = 0;
            buffer.write("270\u00B0:\n");
            for (Position p : list) {
                if (p.getType() == Square.THREE_QUARTES_1 || p.getType() == Square.THREE_QUARTES_2 
                        || p.getType() == Square.THREE_QUARTES_3 ||p.getType() == Square.THREE_QUARTES_4) {
                    buffer.write(String.format("%-20s", p));
                    if (i == MAX_IN_LINE) {
                        buffer.write("\n");
                        i = 0;
                    } else {
                        i++;
                    }
                }
            }
            buffer.write("\n");
            
            i = 0;
            buffer.write("180\u00B0:\n");
            for (Position p : list) {
                if (p.getType() == Square.HALF_1 || p.getType() == Square.HALF_2 
                        || p.getType() == Square.HALF_3 ||p.getType() == Square.HALF_4) {
                    buffer.write(String.format("%-20s", p));
                    if (i == MAX_IN_LINE) {
                        buffer.write("\n");
                        i = 0;
                    } else {
                        i++;
                    }
                }
            }
            buffer.write("\n");
            
            i = 0;
            buffer.write("90\u00B0:\n");
            for (Position p : list) {
                if (p.getType() == Square.QUARTE_1 || p.getType() == Square.QUARTE_2 
                        || p.getType() == Square.QUARTE_3 ||p.getType() == Square.QUARTE_4) {
                    buffer.write(String.format("%-20s", p));
                    if (i == MAX_IN_LINE) {
                        buffer.write("\n");
                        i = 0;
                    } else {
                        i++;
                    }
                }
            }

        } catch (IOException ex) {
            throw ex;
        } finally {
            if (buffer != null) {
                buffer.close();
            }
        }
    }
}
