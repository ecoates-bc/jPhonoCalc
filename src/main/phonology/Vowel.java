package phonology;

public class Vowel extends Phoneme {
    public Vowel(Character sound) {
        super(sound);
    }

    // EFFECTS: returns true if a consonant appears before phoneme, false otherwise
    @Override
    protected boolean isCluster() {
        return false;
    }

}
