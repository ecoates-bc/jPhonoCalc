package model.phonology;

import java.util.ArrayList;
import java.util.List;

public class Language {
    public List<Phoneme> inventory;

    // EFFECTS: constructs a sound inventory of a particular language
    public Language() {
        inventory = new ArrayList<>();
    }

    // EFFECTS: returns true if a phoneme is found in the sound inventory, false otherwise
    public boolean contains(Phoneme p) {
        return inventory.contains(p);
    }

    // EFFECTS: returns a string of all the sounds in the inventory
    public ArrayList<String> getInventory() {
        ArrayList<String> phonemes = new ArrayList<>();
        for (Phoneme p: inventory) {
            phonemes.add(p.sound);
        }
        return phonemes;
    }

    // MODIFIES: this
    // EFFECTS: adds another phoneme
    public void addToInventory(Phoneme p) {
        inventory.add(p);
    }

    // EFFECTS: returns a list of consonants, then vowels
    public ArrayList<String> viewPhonemesAsString() {
        ArrayList<String> phonemes = new ArrayList<>();
        String consonants = "Consonants: ";
        String vowels = "Vowels: ";

        for (Phoneme p: inventory) {
            if (p instanceof Vowel) {
                vowels += " " + p.sound;
            } else {
                consonants +=  " " + p.sound;
            }
        }
        phonemes.add(consonants);
        phonemes.add(vowels);

        return phonemes;
    }
}
