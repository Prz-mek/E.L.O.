package elo.gui;

import elo.evopt.EvolutionaryAlgorithm;
import elo.evopt.Individual;
import elo.picture.Watering;
import elo.positions.Position;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author Ja
 */
public class CountThread extends Thread {

    private final int SCALE = 100;

    private final int iter;
    private final int popSize;
    private final Individual seed;
    private final int time;
    private final boolean collisions;

    public CountThread(int iter, int popSize, Individual seed, int time, boolean collisions) {
        this.iter = iter;
        this.popSize = popSize;
        this.seed = seed;
        this.time = time;
        this.collisions = collisions;
    }

    @Override
    public void run() {
        EvolutionaryAlgorithm algo = new EvolutionaryAlgorithm(iter, popSize);
        Individual result = algo.otimization(seed, time);
        Watering water = new Watering(result.getChromosome(), result.getWidth(), collisions, SCALE);
        BufferedImage img = water.getImage(time);
        List<Position> list = Position.getPositionsList(result, SCALE);
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                new ResultFrame(img, list);
            }          
        }); 
    }
}
