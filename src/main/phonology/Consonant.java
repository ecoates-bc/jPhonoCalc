package phonology;

public class Consonant extends Phoneme {
    public Consonant(Character sound) {
        super(sound);
    }

    // EFFECTS: returns true if a vowel appears before phoneme, false otherwise
    @Override
    protected boolean isCluster() {
        return false;
    }
}
