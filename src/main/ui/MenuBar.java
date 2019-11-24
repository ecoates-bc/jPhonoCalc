package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    JMenuItem loadStarter;
    JMenuItem loadCorpus;
    JMenuItem loadApi;
    JMenuItem saveCorpus;
    JMenuItem exit;
    JMenuItem viewPhonemes;
    JMenuItem calculateProbability;
    JMenuItem calculateFLoad;

    public MenuBar(ActionListener l) {
        super();

        JMenu menuFile = new JMenu("File");

        loadStarter = addMenuItem("Load starter from file...", l, menuFile);
        loadCorpus = addMenuItem("Load corpus from file...", l, menuFile);
        loadApi = addMenuItem("Load API corpus from file...", l, menuFile);
        saveCorpus = addMenuItem("Save corpus...", l, menuFile);

        JSeparator fileSeparator = new JSeparator();
        menuFile.add(fileSeparator);

        exit = addMenuItem("Exit", l, menuFile);

        add(menuFile);

        JMenu menuLanguage = new JMenu("Language");
        viewPhonemes = addMenuItem("Phonemes", l, menuLanguage);
        menuLanguage.add(viewPhonemes);

        add(menuLanguage);

        JMenu menuCorpus = new JMenu("Corpus");
        calculateProbability = addMenuItem("Probability...", l, menuCorpus);
        calculateFLoad = addMenuItem("Functional load...", l, menuCorpus);
        add(menuCorpus);

    }

    public JMenuItem addMenuItem(String title, ActionListener l, JMenu menu) {
        JMenuItem j = new JMenuItem(title);
        j.addActionListener(l);
        menu.add(j);
        return j;
    }
}
