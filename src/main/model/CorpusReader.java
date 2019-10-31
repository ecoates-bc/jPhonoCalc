package model;

import model.exceptions.UnexpectedCharacterException;
import model.phonology.*;
import ui.io.LoaderSaver;

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

        for (int i = 3; i < sounds.size(); i += 4) {
            addFeatures(sounds.get(i), i + 1, i + 2, sounds);
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
            try {
                analyzeWord(w);
            } catch (UnexpectedCharacterException e) {
                e.printStackTrace();
            } finally {
                continue;
            }
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

            acc = "";
            printFeatures(p, acc, writer);

            writer.println(" ");
        }
        writer.close();
    }

    // REQUIRES: No special characters
    // MODIFIES: this.language
    // EFFECTS: adds distribution info to each phoneme in the inventory
    public void analyzeWord(String word) throws UnexpectedCharacterException {
        Phoneme before = new Consonant('#');

        OuterLoop:
        for (Character c: word.toCharArray()) {
            for (Phoneme p: language.inventory) {
                if (p.hasSound(c)) {
                    p.pre.add(before);
                    before.post.add(p);
                    before = p;
                    continue OuterLoop;
                }
            }
            throw new UnexpectedCharacterException(c.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds new features based on the input file
    private void addFeatures(String name, int plus, int minus, List<String> s) {
        Feature f = new Feature(name);
        for (Character c: s.get(plus).toCharArray()) {
            for (Phoneme p: language.inventory) {
                if (p.hasSound(c)) {
                    p.addFeature("+", f);
                }
            }
        }
        for (Character c: s.get(minus).toCharArray()) {
            for (Phoneme p: language.inventory) {
                if (p.hasSound(c)) {
                    p.addFeature("-", f);
                }
            }
        }
    }

    // EFFECTS: prints features; only exists because overridden methods can't exceed 20 lines?
    private void printFeatures(Phoneme p, String acc, PrintWriter writer) {
        for (String feature: p.features.keySet()) {
            acc += feature;
            acc += " ";
        }
        writer.println("Features: " + acc);
    }

    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

}
