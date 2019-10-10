package phonology;

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
    public String getInventory() {
        String s = new String("");
        for (Phoneme p: inventory) {
            s += p.sound;
        }
        return s;
    }

    // MODIFIES: this
    // EFFECTS: adds another phoneme
    public void addToInventory(Phoneme p) {
        inventory.add(p);
    }
}
