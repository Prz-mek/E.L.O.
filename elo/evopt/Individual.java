package elo.evopt;

import elo.Square;

/**
 *
 * @author Ja
 */
public class Individual implements Comparable<Individual> {

    private final int width;
    private final Square[] chromosome;
    private double fit = 0.0;

    public Individual(int width, Square[] ch) {
        this.width = width;
        chromosome = java.util.Arrays.copyOf(ch, ch.length);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return chromosome.length / width;
    }

    public int getLength() {
        return chromosome.length;
    }

    public double getFitness() {
        return fit;
    }

    public void fitness(int time) {

        int[] map = new int[chromosome.length];
        int w = width;
        int h = chromosome.length / width;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (chromosome[i * w + j] == Square.BAR) {
                    chromosome[i * w + j].water(map, width, j, i, time);
                }
            }
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                chromosome[i * w + j].water(map, width, j, i, time);
            }
        }
        double value = 0.0;
        int count = 0;
        int blank = 0;
        double Sx = 0.0;
        double Sx2 = 0.0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] >= 0) {
                Sx += map[i];
                Sx2 += map[i] * map[i];
                if (map [i] == 0) {
                    blank++;
                }
                count++;
            }
        }
        double Ex = Sx / count;
        double Vx = Sx2 / count - Ex * Ex;

        fit = 100000.0 - Vx - 10 * blank;
    }

    public Square[] getChromosome() {
        return java.util.Arrays.copyOf(chromosome, chromosome.length);
    }

    public void fillRandom(double density) {
        fillRandom(density, new java.util.Random());
    }

    public void fillRandom(double density, java.util.Random r) {
        int n = getLength();
        int q = (int) (getLength() * density);
        for (int i = 0; i < q; i++) {
            int p;
            do {
                p = r.nextInt(n);
            } while (chromosome[p] == Square.BAR);
            chromosome[p] = Square.numToSquare(r.nextInt(13) + 2);      // Atention!
        }
    }

    public Individual mutation(double density) {
        return mutation(density, new java.util.Random());
    }

    public Individual mutation(double density, java.util.Random r) {
        Square[] ch = getChromosome();
        int n = getLength();
        int q = (int) (getLength() * density);
        for (int i = 0; i < q; i++) {
            int p;
            do {
                p = r.nextInt(n);
            } while (ch[p] == Square.BAR);

            Square s;
            do {
                s = Square.numToSquare(r.nextInt(13) + 2);
            } while (s == ch[p]);
            ch[p] = s;
        }
        Individual heir = new Individual(width, ch);
        return heir;

    }

    public Individual crossover(Individual partner, int divNum) {
        java.util.Random r = new java.util.Random();
        int[] divisions = new int[divNum];
        for (int i = 0; i < divNum; i++) {
            divisions[i] = r.nextInt(getLength() - 1) + 1;
        }
        java.util.Arrays.sort(divisions);

        return crossover(partner, divisions);
    }

    public Individual crossover(Individual partner, int[] divisions) {
        if (partner != null && partner.getLength() == getLength()) {
            Square[] ch1 = java.util.Arrays.copyOf(chromosome, getLength());
            Square[] ch2 = partner.getChromosome();
            int privious = 0;
            int witch = 0;
            for (int d : divisions) {
                if ((witch & 1) == 1) {
                    System.arraycopy(ch2, privious, ch1, privious, d - privious);
                }
                privious = d;
                witch++;
            }
            if ((witch & 1) == 1) {
                System.arraycopy(ch2, privious, ch1, privious, ch1.length - privious);
            }

            Individual heir = new Individual(width, ch1);
            return heir;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        String str = "chromosome = [ ";
        for (Square s : chromosome) {
            str += s + " ";
        }
        str += "]";
        return str;
    }

    @Override
    public int compareTo(Individual t) {
        if (fit > t.getFitness()) {
            return 1;
        } else if (fit < t.getFitness()) {
            return -1;
        } else {
            return 0;
        }
    }

}
