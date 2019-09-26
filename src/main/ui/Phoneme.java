package ui;

// Represents a single sound
public class Phoneme {
    public Character sound;

    // MODIFIES: this
    // EFFECTS: adds a new sound to the inventory
    public Phoneme(Character sound) {
        this.sound = sound;
        System.out.println("New sound: " + this.sound);
    }

    // EFFECTS: returns true if this sound is the same as a sound found in the user input, false otherwise
    public boolean sameSound(Character s) {
        return s == sound;
    }
}
