package ui;

// Represents a string of phonemes/sounds
public class Word {
    public String sound;

    // REQUIRES: sound is non-empty, no capitals
    // MODIFIES: this
    // EFFECTS: adds a new word to the inventory
    public Word(String sound) {
        this.sound = sound;
        System.out.println("New word: " + this.sound);
    }

    // MODIFIES: inventory
    // EFFECTS: Creates new phonemes for each new sound/character not found in the sound inventory
    public void analyzeWord(SoundInventory inventory) {
        outerLoop:
        for (char c: sound.toCharArray()) {
            for (Phoneme p: inventory.sounds) {
                if (p.sameSound(c) || !isAlphabet(c)) {
                    continue outerLoop;
                }
            }
            Phoneme newSound = new Phoneme(c);
            inventory.sounds.add(newSound);
        }
    }

    // REQUIRES: no capital letters
    // EFFECTS: returns true if a character is alphabetic, false otherwise
    public boolean isAlphabet(Character c) {
        return (c >= 'a' && c <= 'z');
    }
}
