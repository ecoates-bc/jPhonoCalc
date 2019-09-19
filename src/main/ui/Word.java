package ui;

// Represents a string of phonemes/sounds
public class Word {
    public String sound;

    // REQUIRES: sound is non-empty
    // MODIFIES: this
    // EFFECTS: adds a new word to the inventory
    public Word(String sound) {
        this.sound = sound;
        System.out.println("New word: " + this.sound);
    }

    public void analyzeWord(SoundInventory inventory) {
        outerLoop:
        for (char c: sound.toCharArray()) {
            for (Phoneme p: inventory.sounds) {
                if (p.sameSound(c)) {
                    continue outerLoop;
                }
            }
            Phoneme newSound = new Phoneme(c);
            inventory.sounds.add(newSound);
        }
    }
}
