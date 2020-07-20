package elo.evopt;

import elo.Square;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ja
 */
public class Population {

    private List<Individual> pop;

    public Population(int number, Individual seed) {
        pop = Collections.synchronizedList(new ArrayList<>(number));       // Suports threads, supposedly
        Square[] ch = seed.getChromosome();
        for (int i = 0; i < number; i++) {
            Individual temp = new Individual(seed.getWidth(), ch);
            pop.add(temp);
        }
    }

    public int getNum() {
        return pop.size();
    }

    public void fillRandom(double density) {
        for (Individual i : pop) {
            i.fillRandom(density);
        }
    }

    public void fillRandom(double density, java.util.Random r) {
        for (Individual i : pop) {
            i.fillRandom(density, r);
        }
    }
    
    public void countFitness(int time){
        countFitness(Runtime.getRuntime().availableProcessors(), time);
    }

    public void countFitness(int threadsNum, int time) {
        int n = pop.size();
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

        FitnessThread[] threads = new FitnessThread[threadsNum];
        for (int i = 0; i < threadsNum; i++) {
            threads[i] = new FitnessThread(from[i], to[i], time);
            threads[i].start();
        }
        try {
            for (FitnessThread ct : threads) {
                ct.join();
            }
        } catch (InterruptedException e) {

        }
    }

    public void crossover(double probability, int divNum) {
        int threadsNum = Runtime.getRuntime().availableProcessors();
        crossover(probability, divNum, threadsNum, new java.util.Random());
    }

    public void crossover(double probability, int divNum, int threadsNum, java.util.Random r) {
        int n = pop.size();
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

        CrossoverThread[] threads = new CrossoverThread[threadsNum];
        for (int i = 0; i < threadsNum; i++) {
            threads[i] = new CrossoverThread(probability, divNum, n, from[i], to[i], r);
            threads[i].start();
        }
        try {
            for (CrossoverThread ct : threads) {
                ct.join();
            }
        } catch (InterruptedException e) {

        }
    }

    public void mutation(double probability, int divNum) {
        int threadsNum = Runtime.getRuntime().availableProcessors();
        mutation(probability, divNum, threadsNum, new java.util.Random());
    }

    public void mutation(double probability, int divNum, int threadsNum, java.util.Random r) {
        int n = pop.size();
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

        MutationThread[] threads = new MutationThread[threadsNum];
        for (int i = 0; i < threadsNum; i++) {
            threads[i] = new MutationThread(probability, divNum, n, from[i], to[i], r);
            threads[i].start();
        }
        try {
            for (MutationThread ct : threads) {
                ct.join();
            }
        } catch (InterruptedException e) {

        }
    }

    public void rouletteSelection(int survivors) {
        
    }

    public void tournamentSelection(int survivors, java.util.Random r) {
        if (survivors < pop.size()) {
            List<Individual> tempPop = Collections.synchronizedList(new ArrayList<>(survivors));
            int groupNum = pop.size() / survivors;
            int rest = pop.size() % survivors;

            for (int i = 0; i < rest; i++) {
                Individual max = pop.remove(r.nextInt(pop.size()));
                for (int j = 0; j < groupNum; j++) {
                    Individual temp = pop.remove(r.nextInt(pop.size()));
                    if (max.getFitness() < temp.getFitness()) {
                        max = temp;
                    }
                }
                tempPop.add(max);
            }
            for (int i = rest; i < survivors; i++) {
                Individual max = pop.remove(r.nextInt(pop.size()));
                for (int j = 0; j < groupNum - 1; j++) {
                    Individual temp = pop.remove(r.nextInt(pop.size()));
                    if (max.getFitness() < temp.getFitness()) {
                        max = temp;
                    }
                }
                tempPop.add(max);
            }            
            pop = tempPop;
        }
    }

    public Individual getBest() {
        if (pop.size() > 0) {
            Individual max = pop.get(0);
            for (Individual i : pop) {      // Check first twice, but looks better.
                if (max.getFitness() < i.getFitness()) {
                    max = i;
                }
            }
            return max;
        } else {
            return null;
        }
    }

    private class CrossoverThread extends Thread {

        private final double probability;
        private final int divNum;
        private final int size;
        private final int from;
        private final int to;
        private final java.util.Random r;

        public CrossoverThread(double probability, int divNum, int size, int from, int to, java.util.Random r) {
            this.probability = probability;
            this.divNum = divNum;
            this.size = size;
            this.from = from;
            this.to = to;
            this.r = r;
        }

        @Override
        public void run() {
            for (int i = from; i < to; i++) {
                for (int j = 0; j < size; j++) {
                    if (probability >= r.nextDouble()) {
                        Individual temp = pop.get(i).crossover(pop.get(j), divNum);
                        pop.add(temp);
                    }
                }
            }
        }
    }

    private class MutationThread extends Thread {

        private final double probability;
        private final int divNum;
        private final int size;
        private final int from;
        private final int to;
        private final java.util.Random r;

        public MutationThread(double probability, int divNum, int size, int from, int to, java.util.Random r) {
            this.probability = probability;
            this.divNum = divNum;
            this.size = size;
            this.from = from;
            this.to = to;
            this.r = r;
        }

        @Override
        public void run() {
            for (int i = from; i < to; i++) {
                if (probability >= r.nextDouble()) {
                    Individual temp =pop.get(i).mutation(divNum, r);
                    pop.add(temp);
                }
            }
        }
    }
    
    private class FitnessThread extends Thread {
        
        private final int from;
        private final int to;
        private final int time;
        
        public FitnessThread(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
        
        @Override
        public void run() {
            for (int i = from; i < to; i++) {
                pop.get(i).fitness(time);
            }
        }
    }
}
