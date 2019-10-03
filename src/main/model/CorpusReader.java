package model;

import io.LoaderSaver;
import phonology.Phoneme;
import phonology.SoundInventory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CorpusReader implements LanguageTool, LoaderSaver {
    public List<String> words;
    public SoundInventory inventory;

    public CorpusReader(SoundInventory inventory) {
        this.inventory = inventory;
        words = new ArrayList<>();
    }

    // REQUIRES: Valid pathway
    // MODIFIES: this.words
    // EFFECTS: Adds a list of words from an imported corpus

    @Override
    public void read(String path) throws IOException {
        List<String> data = Files.readAllLines(Paths.get(path));
        String[] lines = data.toArray(new String[0]);

        for (String s: lines) {
            for (String s1: s.split(" ")) {
                words.add(s1);
            }
        }
    }

    // REQUIRES: No special characters
    // MODIFIES: this.inventory
    // EFFECTS: Analyzes each word in the reader's word list

    @Override
    public void analyze() {
        for (String w: words) {
            analyzeWord(w);
        }
    }

    // EFFECTS: Saves a file containing a list of phonemes from the inventory, named filename

    @Override
    public void save(String filename) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(filename + ".txt", "UTF-8");
        writer.println(inventory.getInventory());
        writer.close();
    }

    // REQUIRES: No special characters
    // MODIFIES: this.inventory
    // EFFECTS: Creates new phonemes for each new sound/character not found in the sound inventory
    public void analyzeWord(String word) {
        outerLoop:
        for (char c: word.toCharArray()) {
            for (Phoneme p: inventory.sounds) {
                if (p.checkSimilar(c)) {
                    continue outerLoop;
                }
            }
            Phoneme newSound = new Phoneme(c);
            inventory.sounds.add(newSound);
        }
    }

    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

}
