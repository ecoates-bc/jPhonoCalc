package phonology;

import java.util.ArrayList;
import java.util.List;

public class SoundInventory {
    public List<Phoneme> sounds;

    // EFFECTS: constructs a sound inventory of a particular language
    public SoundInventory() {
        sounds = new ArrayList<>();
    }

    // EFFECTS: returns true if a phoneme is found in the sound inventory, false otherwise
    public boolean contains(Phoneme p) {
        return sounds.contains(p);
    }

    // EFFECTS: returns a string of all the sounds in the inventory
    public String getInventory() {
        String s = new String("");
        for (Phoneme p: sounds) {
            s += p.sound;
        }
        return s;
    }
}
