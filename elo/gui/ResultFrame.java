package elo.gui;

import elo.FilesUtilities;
import elo.positions.Position;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Ja
 */
public class ResultFrame extends JFrame {

    private final int MAX_WIDTH = 800;
    private final int MAX_HEIGHT = 600;

    private BufferedImage img;
    private List<Position> list;

    public ResultFrame(BufferedImage img, List<Position> list) {

        this.img = img;
        this.list = list;

        setLayout(new BorderLayout());

        JButton saveImageButton = new JButton("Zapisz Obraz");
        saveImageButton.addActionListener(new ImageSaveListener());
        
        JButton saveTextButton = new JButton("Zapisz Pozycje");
        saveTextButton.addActionListener(new PositionsSaveListener());
        
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.add(saveTextButton);
        southPanel.add(saveImageButton);

        add(new JLabel(scaleImage(img, MAX_WIDTH, MAX_HEIGHT)), BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        pack();
        setTitle("Result");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setVisible(true);
    }

    private class ImageSaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("user.dir"));
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    FilesUtilities.writeImage(img, "png", file);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Problem z zapisem obrazu do pliku.");
                }
            }
        }
    }
    
    private class PositionsSaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("user.dir"));
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    FilesUtilities.writePositions(list, file);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Problem z zapisem pozycji do pliku.");
                }
            }
        }
    }

    private ImageIcon scaleImage(BufferedImage image, int w, int h) {

        int width = image.getWidth();
        int height = image.getHeight();

        if (width > w) {
            height = w * height / width;
            width = w;
        }
        if (height > h) {
            width = h * width / height;
            height = h;
        }

        return new ImageIcon(image.getScaledInstance(width, height, BufferedImage.SCALE_DEFAULT));
    }
}
