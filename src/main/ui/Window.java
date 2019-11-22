package ui;

import model.exceptions.NoCorpusUploadedException;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener {
    private MenuBar menuBar;
    private JFileChooser fileChooser;
    private FileTools fileTools;

    public Window() {
        super("JPhonoCalc: Java Functional Load Calculator");
        int width = 960;
        int height = 600;

        fileTools = new FileTools();

        menuBar = new MenuBar(this);
        setJMenuBar(menuBar);

        fileChooser = new JFileChooser("data/");

        setSize(width, height);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuBar.loadCorpus) {
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == fileChooser.APPROVE_OPTION) {
                String path = fileChooser.getSelectedFile().getPath();

                try {
                    fileTools.handleUpload(path);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Action not supported.");
                }
            }
        } else if (e.getSource() == menuBar.viewPhonemes) {
            try {
                ArrayList<String> phonemes = fileTools.getPhonemes();
                JOptionPane.showMessageDialog(null, phonemes.get(0) + "\n" + phonemes.get(1));
            } catch (NoCorpusUploadedException ex) {
                JOptionPane.showMessageDialog(null, "No corpus uploaded.");
            }
        }

    }
}
