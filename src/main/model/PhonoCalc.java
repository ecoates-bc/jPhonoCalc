package model;

import model.exceptions.UnexpectedCharacterException;
import model.observer.LanguageObserver;
import model.phonology.Consonant;
import model.phonology.Phoneme;

import java.util.*;

public class PhonoCalc extends Observable {
    public Map<String, List<Phoneme>> words;

    public PhonoCalc() {
        words = new HashMap<>();
        addObserver(new LanguageObserver());
    }

    // REQUIRES: valid word
    // MODIFIES: this
    // EFFECTS: adds word to key, list of phonemes to value
    public void addWord(String word, List<Phoneme> phonemes) {
        setChanged();
        notifyObservers(word);
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
    public double getProbability(Phoneme p, Collection<List<Phoneme>> values) {
        double withP = 0;
        double totalSegments = 0;
        OuterLoop:
        for (List<Phoneme> word: values) {
            for (Phoneme q: word) {
                if (q.equals(p)) {
                    withP++;
                }
                totalSegments++;
            }
            // The number of segments is increased by 2, to account for word boundaries
            totalSegments = totalSegments + 2;
        }

        System.out.println(withP + " by " + totalSegments + " = " + withP / totalSegments);
        return withP / totalSegments;
    }

    // REQUIRES: phonemeList has unique characters in it, e.g: language inventory
    // EFFECTS: returns the entropy of system with inventory phonemeList
    public double getEntropy(List<Phoneme> phonemeList, Collection<List<Phoneme>> values) {
        double sum = 0;
        for (Phoneme p: phonemeList) {
            double probP = getProbability(p, values);
            double step;
            if (probP == 0) {
                continue;
            } else {
                step = probP * (Math.log(probP) / Math.log(2));
            }
            sum += step;
        }
        return -sum;
    }

    // EFFECTS: calculates the functional load of two phonemes (change in entropy of system after two sounds are merged)
    public double calculateFunctionalLoad(Phoneme p, Phoneme q, List<Phoneme> phonemeList) {
        Collection<List<Phoneme>> mergedList = new ArrayList<>();
        List<Phoneme> mergedInventory = new ArrayList<>(phonemeList);
        for (List<Phoneme> word: words.values()) {
            List<Phoneme> mergedWord = new ArrayList<>();
            for (Phoneme sound: word) {
                if (sound.equals(q)) {
                    mergedWord.add(p);
                } else {
                    mergedWord.add(sound);
                }
            }
            mergedList.add(mergedWord);
        }

        mergedInventory.remove(q);

        double beforeEntropy = getEntropy(phonemeList, words.values());
        double afterEntropy = getEntropy(mergedInventory, mergedList);

        return beforeEntropy - afterEntropy;
    }
}
