package phonology;

import java.util.ArrayList;
import java.util.List;

// Represents a single sound
public abstract class Phoneme {
    public Character sound;
    public List<Character> pre;
    public List<Character> post;

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
    public void addPre(Character c) {
        pre.add(c);
    }

    // MODIFIES: this
    // EFFECTS: adds observed succeeding sound
    public void addPost(Character c) {
        post.add(c);
    }

    protected abstract boolean isCluster();
}
