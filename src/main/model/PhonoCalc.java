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

}
