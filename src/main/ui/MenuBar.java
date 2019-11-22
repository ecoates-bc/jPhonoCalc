package ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    JMenuItem loadStarter;
    JMenuItem loadCorpus;
    JMenuItem loadApi;
    JMenuItem saveCorpus;
    JMenuItem exit;
    JMenuItem viewPhonemes;
    JMenuItem calculateFLoad;

    public MenuBar(ActionListener l) {
        super();

        JMenu menuFile = new JMenu("File");

        loadStarter = new JMenuItem("Load corpus starter");
        loadStarter.addActionListener(l);
        menuFile.add(loadStarter);

        loadCorpus = new JMenuItem("Load corpus from file");
        loadCorpus.addActionListener(l);
        menuFile.add(loadCorpus);

        loadApi = new JMenuItem("Load corpus using API");
        loadApi.addActionListener(l);
        menuFile.add(loadApi);

        saveCorpus = new JMenuItem("Export...");
        saveCorpus.addActionListener(l);
        menuFile.add(saveCorpus);

        JSeparator fileSeparator = new JSeparator();
        menuFile.add(fileSeparator);

        exit = new JMenuItem("Exit");
        exit.addActionListener(l);
        menuFile.add(exit);

        add(menuFile);

        JMenu menuLanguage = new JMenu("Language");
        viewPhonemes = new JMenuItem("View phonemes");
        viewPhonemes.addActionListener(l);
        menuLanguage.add(viewPhonemes);

        add(menuLanguage);

        JMenu menuCorpus = new JMenu("Corpus");
        calculateFLoad = new JMenuItem("Functional load...");
        calculateFLoad.addActionListener(l);
        menuCorpus.add(calculateFLoad);

        add(menuCorpus);
    }
}
