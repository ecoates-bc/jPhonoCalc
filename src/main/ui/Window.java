package ui;

import model.exceptions.NoCorpusUploadedException;
import model.exceptions.NoStarterUploadedException;
import model.exceptions.OverwritingStarterException;
import model.exceptions.UnexpectedCharacterException;

import javax.swing.*;
import java.awt.*;
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

        getContentPane().setBackground(new Color(0xBFA9A4));
        JLabel title = new JLabel("Welcome to JPhonoCalc");
        title.setBounds(64, 150, width, 200);
        title.setFont(new Font("Sans", Font.ITALIC, 68));
        title.setForeground(new Color(0xE7D2D2));
        add(title);

        fileTools = new FileTools();

        menuBar = new MenuBar(this);
        setJMenuBar(menuBar);

        fileChooser = new JFileChooser("data/");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLayout(null);
        setVisible(true);
    }

    // EFFECTS: checks for all actions
    @Override
    public void actionPerformed(ActionEvent e) {
        corpusStarterAction(e);
        loadCorpusActions(e);
        phonoActions(e);
        apiLoadActions(e);
        saveChartActions(e);
    }

    // EFFECTS: handles loading starter file and any exceptions
    private void corpusStarterAction(ActionEvent e) {
        if (e.getSource() == menuBar.loadStarter) {
            String path = optionDialog();
            try {
                fileTools.handleUpload(path);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Action not supported.");
            } catch (OverwritingStarterException ex) {
                int response = JOptionPane.showConfirmDialog(null, "Overwriting corpus starter. Are you sure?");
                if (response == JOptionPane.OK_OPTION) {
                    fileTools.overwriteReader();
                    try {
                        fileTools.handleUpload(path);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Action not supported.");
                    }
                }
            }
        }
    }

    // EFFECTS: handles loading corpus from file and any exceptions
    private void loadCorpusActions(ActionEvent e) {
        if (e.getSource() == menuBar.loadCorpus) {
            String path = optionDialog();
            try {
                fileTools.handleRead(path);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Action not supported.");
            } catch (NoStarterUploadedException ex) {
                JOptionPane.showMessageDialog(null, "No starter file loaded.");
            }
        }
    }

    // EFFECTS: handles viewing phonemes, functional load, probability, and any exceptions
    private void phonoActions(ActionEvent e) {
        if (e.getSource() == menuBar.viewPhonemes) {
            try {
                ArrayList<String> phonemes = fileTools.viewPhonemesAsString();
                JOptionPane.showMessageDialog(null, phonemes.get(0) + "\n" + phonemes.get(1));
            } catch (NoStarterUploadedException ex) {
                JOptionPane.showMessageDialog(null, "No starter file uploaded.");
            }
        } else if (e.getSource() == menuBar.calculateFLoad) {
            new FloadDialog();
        } else if (e.getSource() == menuBar.calculateProbability) {
            new ProbDialog();
        } else if (e.getSource() == menuBar.exit) {
            dispose();
        }
    }

    // EFFECTS: handles loading corpus from API and any exceptions
    private void apiLoadActions(ActionEvent e) {
        if (e.getSource() == menuBar.loadApi) {
            String path = optionDialog();
            try {
                fileTools.getApiValues(path);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Action not supported.");
            } catch (NoStarterUploadedException ex) {
                JOptionPane.showMessageDialog(null, "No starter file uploaded.");
            }
        }
    }

    // EFFECTS: handles saving fLoad matrix to csv file
    private void saveChartActions(ActionEvent e) {
        if (e.getSource() == menuBar.floadMatrix) {
            new MatrixDialog();
        }
    }

    // EFFECTS: handles uploading files
    private String optionDialog() {
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getPath();
        } else {
            return "";
        }
    }

    // EFFECTS: handles UI for selecting phonemes for fLoad calculation; exceptions
    private class FloadDialog {
        FloadDialog() {
            JPanel panel = new JPanel();
            JComboBox<String> first = new JComboBox<>();
            JComboBox<String> second = new JComboBox<>();

            try {
                addPhonemes(first);
                addPhonemes(second);

                panel.add(first);
                panel.add(second);

                JOptionPane.showMessageDialog(null, panel);

                String a = first.getItemAt(first.getSelectedIndex());
                String b = second.getItemAt(second.getSelectedIndex());

                try {
                    String fload = fileTools.getFLoadAsString(a, b);
                    JOptionPane.showMessageDialog(null, fload);
                } catch (UnexpectedCharacterException e) {
                    JOptionPane.showMessageDialog(null, "Unexpected character.");
                } catch (NoCorpusUploadedException e) {
                    JOptionPane.showMessageDialog(null, "No corpus uploaded.");
                }

            } catch (NoStarterUploadedException e) {
                JOptionPane.showMessageDialog(null, "No starter file uploaded.");
            }
        }

        private void addPhonemes(JComboBox<String> jbox) throws NoStarterUploadedException {
            for (String s : fileTools.getInventory()) {
                jbox.addItem(s);
            }
        }
    }

    // EFFECTS: handles UI for selecting the phoneme for probability calculation; exceptions
    private class ProbDialog {
        ProbDialog() {
            JPanel panel = new JPanel();
            JComboBox<String> p = new JComboBox<>();

            try {
                addPhonemes(p);
                panel.add(p);
                JOptionPane.showMessageDialog(null, panel);

                String s = p.getItemAt(p.getSelectedIndex());

                try {
                    String prob = fileTools.getProbabilityAsString(s);
                    JOptionPane.showMessageDialog(null, prob);
                } catch (UnexpectedCharacterException e) {
                    JOptionPane.showMessageDialog(null, "Unexpected character.");
                } catch (NoCorpusUploadedException e) {
                    JOptionPane.showMessageDialog(null, "No corpus uploaded.");
                }

            } catch (NoStarterUploadedException e) {
                JOptionPane.showMessageDialog(null, "No starter file uploaded.");
            }
        }

        private void addPhonemes(JComboBox<String> jbox) throws NoStarterUploadedException {
            for (String s : fileTools.getInventory()) {
                jbox.addItem(s);
            }
        }
    }

    // EFFECTS: handles UI for choosing types of matrix
    private class MatrixDialog {
        MatrixDialog() {
            JPanel panel = new JPanel();
            JComboBox<String> p = new JComboBox<>();
            p.addItem("Vowels");
            p.addItem("Consonants");
            p.addItem("All");
            panel.add(p);

            JOptionPane.showMessageDialog(null, panel, "Types:",
                    JOptionPane.INFORMATION_MESSAGE);

            String response = p.getItemAt(p.getSelectedIndex());

            try {
                int selection = fileChooser.showSaveDialog(null);

                if (selection == JFileChooser.APPROVE_OPTION) {
                    fileTools.getFLoadMatrix(response, fileChooser.getSelectedFile().getAbsolutePath());
                }
            } catch (NoStarterUploadedException e) {
                JOptionPane.showMessageDialog(null, "No starter file uploaded.");
            } catch (NoCorpusUploadedException e) {
                JOptionPane.showMessageDialog(null, "No corpus uploaded.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Action not supported.");
            }
        }
    }
}
