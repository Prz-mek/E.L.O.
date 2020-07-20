package elo;

import java.awt.EventQueue;
import elo.gui.Menu;

/**
 *
 * @author Ja
 */
public class ELO {

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Menu();
            }
        });
    }
}