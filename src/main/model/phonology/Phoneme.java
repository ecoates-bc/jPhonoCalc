package model.phonology;

import java.util.*;

// Represents a single sound
public abstract class Phoneme {
    public Character sound;
    public List<Phoneme> pre;
    public List<Phoneme> post;
    public Map<String, Feature> features = new HashMap<>();

    // MODIFIES: this
    // EFFECTS: adds a new sound to the inventory
    public Phoneme(Character sound) {
        this.sound = sound;
        pre = new ArrayList<>();
        post = new ArrayList<>();
    }

    // EFFECTS: returns true if this sound is the same as a sound found in the user input, false otherwise
    public boolean hasSound(Character s) {
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

    // REQUIRES: value is "+" or "-"
    // MODIFIES: this
    // EFFECTS: adds a feature to the map of features
    public void addFeature(String value, Feature f) {
        if (!features.containsKey(value + f.name)) {
            features.put(value + f.name, f);
            if (value == "+") {
                f.addPlus(this);
            } else if (value == "-") {
                f.addMinus(this);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a feature to the map of features
    public void removeFeature(String value) {
        features.remove(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phoneme phoneme = (Phoneme) o;
        return Objects.equals(sound, phoneme.sound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sound);
    }
}
