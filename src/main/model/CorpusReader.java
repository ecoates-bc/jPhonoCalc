package model;

import ui.io.LoaderSaver;
import model.phonology.Consonant;
import model.phonology.Language;
import model.phonology.Phoneme;
import model.phonology.Vowel;

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
    public Language language;

    public CorpusReader(Language language, String path) throws IOException {
        this.language = language;
        words = new ArrayList<>();

        List<String> sounds = Files.readAllLines(Paths.get(path));

        for (Character c: sounds.get(0).toCharArray()) {
            Consonant newSound = new Consonant(c);
            language.addToInventory(newSound);
        }

        for (Character c: sounds.get(1).toCharArray()) {
            Vowel newSound = new Vowel(c);
            language.addToInventory(newSound);
        }
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
    // MODIFIES: this.language
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
        PrintWriter writer = new PrintWriter("data/" + filename + ".txt", "UTF-8");
        for (Phoneme p: language.inventory) {
            String acc = "";
            writer.println(p.sound);
            for (Phoneme q: p.pre) {
                acc += q.sound;
            }
            writer.println("Pre: " + acc);
            acc = "";
            for (Phoneme r: p.post) {
                acc += r.sound;
            }
            writer.println("Post: " + acc);
            writer.println(" ");
        }
        writer.close();
    }

    // REQUIRES: No special characters
    // MODIFIES: this.language
    // EFFECTS: adds distribution info to each phoneme in the inventory
    public void analyzeWord(String word) {
        Phoneme before = new Consonant('#');

        for (Character c: word.toCharArray()) {
            for (Phoneme p: language.inventory) {
                if (p.isEqual(c)) {
                    p.pre.add(before);
                    before.post.add(p);
                    before = p;
                }
            }
        }
    }

    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

}
