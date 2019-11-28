package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    JMenuItem loadStarter;
    JMenuItem loadCorpus;
    JMenuItem loadApi;
    JMenuItem exit;
    JMenuItem viewPhonemes;
    JMenuItem addFeatures;
    JMenuItem calculateProbability;
    JMenuItem calculateFLoad;
    JMenuItem floadMatrix;

    public MenuBar(ActionListener l) {
        super();

        JMenu menuFile = new JMenu("File");

        loadStarter = addMenuItem("Load starter from file...", l, menuFile);
        loadCorpus = addMenuItem("Load corpus from file...", l, menuFile);
        loadApi = addMenuItem("Load API corpus from file...", l, menuFile);

        JSeparator fileSeparator = new JSeparator();
        menuFile.add(fileSeparator);

        exit = addMenuItem("Exit", l, menuFile);

        add(menuFile);

        JMenu menuLanguage = new JMenu("Language");
        viewPhonemes = addMenuItem("Phonemes", l, menuLanguage);
        addFeatures = addMenuItem("Features...", l, menuLanguage);

        add(menuLanguage);

        JMenu menuCorpus = new JMenu("Corpus");
        calculateProbability = addMenuItem("Probability...", l, menuCorpus);
        calculateFLoad = addMenuItem("Functional load...", l, menuCorpus);
        JSeparator corpSeparator = new JSeparator();
        menuCorpus.add(corpSeparator);
        floadMatrix = addMenuItem("Functional load matrix...", l, menuCorpus);
        add(menuCorpus);

    }

    // EFFECTS: Makes adding menu items smoother
    public JMenuItem addMenuItem(String title, ActionListener l, JMenu menu) {
        JMenuItem j = new JMenuItem(title);
        j.addActionListener(l);
        menu.add(j);
        return j;
    }
}
