package ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    JMenuItem loadCorpus;
    JMenuItem saveCorpus;
    JMenuItem viewPhonemes;

    public MenuBar(ActionListener l) {
        super();

        JMenu menuFile = new JMenu("File");

        loadCorpus = new JMenuItem("Load new corpus");
        loadCorpus.addActionListener(l);
        menuFile.add(loadCorpus);

        saveCorpus = new JMenuItem("Export...");
        saveCorpus.addActionListener(l);
        menuFile.add(saveCorpus);

        add(menuFile);

        JMenu menuLanguage = new JMenu("Language");
        viewPhonemes = new JMenuItem("View phonemes");
        viewPhonemes.addActionListener(l);
        menuLanguage.add(viewPhonemes);

        add(menuLanguage);
    }
}
