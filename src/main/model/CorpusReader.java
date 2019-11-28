package model;

import model.exceptions.NoCorpusUploadedException;
import model.exceptions.UnexpectedCharacterException;
import model.phonology.*;
import network.ApiReader;
import org.json.JSONException;
import ui.io.Loader;
import ui.io.Saver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CorpusReader implements Loader, Saver {
    public Language language;
    public PhonoCalc calculator;

    public CorpusReader(Language language, String path) throws IOException {
        this.language = language;
        calculator = new PhonoCalc();

        makePhonemes(path);
    }

    // REQUIRES: Valid starter file
    // MODIFIES: this
    // EFFECTS: creates the phonemes
    private void makePhonemes(String path) throws IOException {
        List<String> sounds = Files.readAllLines(Paths.get(path));

        for (String s: sounds.get(0).split(",")) {
            Vowel v = new Vowel(s);
            language.addToInventory(v);
        }
        for (String s: sounds.get(1).split(",")) {
            Consonant c = new Consonant(s);
            language.addToInventory(c);
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
            // CM DICTIONARY: first item is the word, second is a pronunciation number (ignored), the rest are phonemes
            String[] entry = s.split(" ");
            List<Phoneme> phonemes;

            try {
                phonemes = calculator.analyzeWord(entry, language.inventory);
                calculator.addWord(entry[0], phonemes);

            } catch (UnexpectedCharacterException e) {
                e.printStackTrace();
            } finally {
                // TODO: find something to finally (analyze()?)
            }
        }
    }

    // MODIFIES: this.words
    // EFFECTS: like read() but using the network package
    public void readFromAPI(String path) throws IOException {
        List<String> data = Files.readAllLines(Paths.get(path));
        String[] lines = data.toArray(new String[0]);

        for (String s: lines) {
            try {
                ApiReader apiReader = new ApiReader(s);
                List<Phoneme> phonemes;
                try {
                    phonemes = calculator.analyzeWord(apiReader.getipalist(),language.inventory);
                    calculator.addWord(s, phonemes);
                } catch (UnexpectedCharacterException e) {
                    e.printStackTrace();
                    continue;
                }
            } catch (JSONException e) {
                continue;
            }
        }
    }

    // EFFECTS: Saves a file containing a list of phonemes from the inventory, named filename

    @Override
    public void save(String filename) {
        for (Phoneme p: language.inventory) {
            String acc = "";
            System.out.println(p.sound);
            for (Phoneme q: p.pre) {
                acc += q.sound + ", ";
            }
            System.out.println("Pre: " + acc);
            acc = "";
            for (Phoneme r: p.post) {
                acc += r.sound + ", ";
            }
            System.out.println("Post: " + acc);

            acc = "";
            printFeatures(p, acc);

        }
    }

    // MODIFIES: this
    // EFFECTS: adds new features based on the input file
    public void addFeatures(String name, int plus, int minus, List<String> s) {
        Feature f = new Feature(name);
        for (String c: s.get(plus).split(",")) {
            for (Phoneme p: language.inventory) {
                if (p.hasSound(c)) {
                    p.addFeature("+", f);
                }
            }
        }
        for (String c: s.get(minus).split(",")) {
            for (Phoneme p: language.inventory) {
                if (p.hasSound(c)) {
                    p.addFeature("-", f);
                }
            }
        }
    }

    //EFFECTS: calculate the functional load of two phonemes, see PhonoCalc for more info
    public double getFLoad(Phoneme p, Phoneme q) throws NoCorpusUploadedException {
        if (hasBeenRead()) {
            System.out.println("Load:" + calculator.calculateFunctionalLoad(p, q, language.inventory));
            return calculator.calculateFunctionalLoad(p, q, language.inventory);
        } else {
            throw new NoCorpusUploadedException();
        }
    }

    // EFFECTS: calculates type-based probability
    public double getProbability(Phoneme p) throws NoCorpusUploadedException {
        if (hasBeenRead()) {
            return calculator.getProbability(p, calculator.words.values());
        } else {
            throw new NoCorpusUploadedException();
        }
    }

    // EFFECTS: returns a matrix of all functional loads in language
    public ArrayList<String> getFLoadMatrix(String type) throws NoCorpusUploadedException {
        if (hasBeenRead()) {
            ArrayList<String> distanceMatrix = new ArrayList<>();
            List<Phoneme> phonemes = narrowPhonemes(type);

            makeTopLine(phonemes, distanceMatrix);

            for (Phoneme p: phonemes) {
                String row = p.sound;
                for (Phoneme p2: phonemes) {
                    row += "," + getFriendlyNumber(getFLoad(p, p2));
                }
                distanceMatrix.add(row);
            }
            return distanceMatrix;
        } else {
            throw new NoCorpusUploadedException();
        }
    }

    // EFFECTS: makes the top line of the csv file, only here because of method length limit
    private void makeTopLine(List<Phoneme> phonemes, ArrayList<String> distanceMatrix) {
        String topLine = "";
        for (Phoneme p: phonemes) {
            topLine += "," + p.sound;
        }
        distanceMatrix.add(topLine);
    }

    // EFFECTS: rounds a really long floating point decimal to two places
    public String getFriendlyNumber(double n) {
        DecimalFormat df = new DecimalFormat("#.##");
        double asPercent = n * 100;
        return df.format(asPercent);
    }

    // EFFECTS: prints features; only exists because overridden methods can't exceed 20 lines?
    private void printFeatures(Phoneme p, String acc) {
        for (String feature: p.features.keySet()) {
            acc += feature;
            acc += " ";
        }
        System.out.println("Features: " + acc);
    }

    private boolean hasBeenRead() {
        return calculator.words.values().size() > 0;
    }

    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    private List<Phoneme> narrowPhonemes(String type) {
        List<Phoneme> phonemes = new ArrayList<>();

        if (type == "Vowels") {
            for (Phoneme p : language.inventory) {
                if (p instanceof Vowel) {
                    phonemes.add(p);
                }
            }
            return phonemes;
        } else if (type == "Consonants") {
            for (Phoneme p : language.inventory) {
                if (p instanceof Consonant) {
                    phonemes.add(p);
                }
            }
            return phonemes;
        } else {
            return language.inventory;
        }
    }
}
