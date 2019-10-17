package model.phonology;

import java.util.ArrayList;
import java.util.List;

public class Vowel extends Phoneme {
    public Vowel(Character sound) {
        super(sound);
    }

    // REQUIRES: corpus to have been analyzed beforehand
    // EFFECTS: returns the consonants that occur before
    @Override
    public List<Phoneme> getOppositeBefores() {
        List<Phoneme> list = new ArrayList<>();
        for (Phoneme p: pre) {
            if (p instanceof Consonant) {
                list.add(p);
            }
        }
        return list;
    }
}
