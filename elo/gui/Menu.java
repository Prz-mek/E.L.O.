package elo.gui;

import elo.FilesUtilities;
import elo.evopt.Individual;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Ja
 */
public class Menu extends JFrame {

    private final int DEFAULT_WIDTH = 500;
    private final int DEFAULT_HEIGHT = 400;

    private Individual seed;
    private int iter;
    private int populationSize;
    private int time;
    private boolean collisions;

    private final JLabel fileName;

    public Menu() {
        setLayout(new GridBagLayout());

        this.seed = null;
        this.iter = 30;
        this.populationSize = 70;
        this.time = 10;
        this.collisions = true;

        fileName = new JLabel("Brak pliku");

        // Reading file
        JButton fileButton = new JButton("Dodaj");
        fileButton.addActionListener(new FileReaderListener());

        // Precision menu
        JComboBox<String> combo = new JComboBox<>();
        combo.addItem("Mała");
        combo.addItem("Średnia");
        combo.addItem("Duża");
        combo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String str = combo.getItemAt(combo.getSelectedIndex());
                switch (str) {
                    case "Mała":
                        iter = 30;
                        populationSize = 70;
                        break;
                    case "Średnia":
                        iter = 50;
                        populationSize = 100;
                        break;
                    case "Duża":
                        iter = 60;
                        populationSize = 120;
                        break;
                    default:
                        break;
                }
            }
        });
        
        // set time
        JTextField timeField = new JTextField("10");
        timeField.setPreferredSize(new Dimension(100, 24));
        JButton timeButton = new JButton("Zatwierdź");
        timeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                time = Integer.parseInt(timeField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Nie właściwy format danych.");
                }
            }
            
        });

        // collision check
        JCheckBox collisionBox = new JCheckBox("Odbijanie");
        collisionBox.setSelected(true);
        collisionBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                collisions = collisionBox.isSelected();
            }
        });

        // run button
        JButton runButton = new JButton("URUCHOM");
        runButton.setFont(new java.awt.Font("Tahoma", 0, 20));
        runButton.setPreferredSize(new Dimension(200, 50));
        runButton.addActionListener(new RunListener());

        // Adding to grid
        add(new JLabel("Plik:"), new GBC(0, 1).setAnchor(GBC.EAST).setInsets(10, 5, 10, 5));
        add(fileName, new GBC(1, 1).setInsets(10, 5, 10, 5));
        add(fileButton, new GBC(2, 1).setInsets(10, 5, 10, 5));

        add(new JLabel("Dokładność:"), new GBC(0, 0).setAnchor(GBC.EAST).setInsets(10, 5, 10, 5));
        add(combo, new GBC(1, 0).setInsets(10, 5, 10, 5));
        
        add(new JLabel("Czas:"), new GBC(0, 2).setAnchor(GBC.EAST).setInsets(10, 5, 10, 5));
        add(timeField, new GBC(1, 2).setInsets(10, 5, 10, 5));
        add(timeButton, new GBC(2, 2).setInsets(10, 5, 10, 5));

        add(collisionBox, new GBC(1, 3).setInsets(10, 5, 10, 5));

        add(runButton, new GBC(1, 4).setInsets(30, 5, 10, 5));

        setTitle("E.L.O. - Evolutionary Lawn Optimization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setResizable(false);
        //setLocationByPlatform(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class FileReaderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("user.dir"));
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    seed = FilesUtilities.readFromFile(file);
                    fileName.setText(file.getName());
                } catch (IOException ex1) {
                    JOptionPane.showMessageDialog(null, "Problem z czytaniem pliku.");
                } catch (IllegalArgumentException ex2) {
                    JOptionPane.showMessageDialog(null, "W pliku znajdują się niedozwolone symbole.");
                }
            }
        }
    }

    private class RunListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (seed != null) {
                CountThread thread = new CountThread(iter, populationSize, seed, time, collisions);
                thread.setDaemon(true);
                thread.start();
            } else {
                JOptionPane.showMessageDialog(null, "Brak wczytanego pliku.");
            }
        }
    }
}
