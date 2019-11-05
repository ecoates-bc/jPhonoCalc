package model;

import model.exceptions.UnexpectedCharacterException;
import model.phonology.Consonant;
import model.phonology.Phoneme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhonoCalc {
    public Map<String, List<Phoneme>> words;

    public PhonoCalc() {
        words = new HashMap<>();
    }

    // REQUIRES: valid word
    // MODIFIES: this
    // EFFECTS: adds word to key, list of phonemes to value
    public void addWord(String word, List<Phoneme> phonemes) {
        words.put(word, phonemes);
    }

    // EFFECTS: creates a list of phonemes for a word
    public List<Phoneme> analyzeWord(String[] entry, List<Phoneme> inventory) throws UnexpectedCharacterException {
        Phoneme before = new Consonant("#");
        List<Phoneme> phonemes = new ArrayList<>();

        OuterLoop:
        for (int i = 2; i < entry.length; i++) {
            String sound = entry[i].replaceAll("\\d", "");
            for (Phoneme p: inventory) {
                if (p.hasSound(sound)) {
                    p.addPre(before);
                    before.addPost(p);
                    phonemes.add(p);
                    before = p;
                    continue OuterLoop;
                }
            }
            throw new UnexpectedCharacterException(sound);
        }
        return phonemes;
    }

    // EFFECTS: finds the probability that a phoneme is in a given word in the corpus
    //          # of words with phoneme in it / # of words in corpus
    public double getProbability(Phoneme p) {
        double withP = 0;
        OuterLoop:
        for (List<Phoneme> word: words.values()) {
            for (Phoneme q: word) {
                if (q.equals(p)) {
                    withP++;
                    continue OuterLoop;
                }
            }
        }
        return withP / words.values().size();
    }

    // REQUIRES: phonemeList has unique characters in it, e.g: language inventory
    // EFFECTS: returns the entropy of system with inventory phonemeList
    public double getEntropy(List<Phoneme> phonemeList) {
        double sum = 0;
        for (Phoneme p: phonemeList) {
            double probP = getProbability(p);
            double step;
            if (probP == 0) {
                step = 1;
            } else {
                step = probP * (Math.log(probP) / Math.log(2));
            }
            sum += step;
        }
        return sum;
    }
}
