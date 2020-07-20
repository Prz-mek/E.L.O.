package elo.evopt;

/**
 *
 * @author Ja
 */
public class EvolutionaryAlgorithm {

    private int iter;
    private int size;

    public EvolutionaryAlgorithm(int iter, int size) {
        this.iter = iter;
        this.size = size;
    }

    public void setIterations(int iter) {
        this.iter = iter;
    }

    public void setPopulation(int size) {
        this.size = size;
    }

    public Individual otimization(Individual seed, int time) {
        Population pop = new Population(size, seed);
        pop.fillRandom(0.05);
        pop.countFitness(time);

        for (int i = 0; i < iter; i++) {
            pop.crossover(0.7, 5);
            pop.mutation(0.01, 5);
            pop.countFitness(time);
            pop.tournamentSelection(size, new java.util.Random());
        }

        return pop.getBest();
    }
}
