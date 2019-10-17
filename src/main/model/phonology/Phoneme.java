package model.phonology;

import java.util.ArrayList;
import java.util.List;

// Represents a single sound
public abstract class Phoneme {
    public Character sound;
    public List<Phoneme> pre;
    public List<Phoneme> post;

    // MODIFIES: this
    // EFFECTS: adds a new sound to the inventory
    public Phoneme(Character sound) {
        this.sound = sound;
        pre = new ArrayList<>();
        post = new ArrayList<>();
    }

    // EFFECTS: returns true if this sound is the same as a sound found in the user input, false otherwise
    public boolean isEqual(Character s) {
        return sound.equals(s);
    }

    // MODIFIES: this
    // EFFECTS: adds observed proceeding sound
    public void addPre(Phoneme c) {
        pre.add(c);
    }

    // MODIFIES: this
    // EFFECTS: adds observed succeeding sound
    public void addPost(Phoneme c) {
        post.add(c);
    }

    public abstract List<Phoneme> getOppositeBefores();
}
