package ui;

import java.util.ArrayList;
import java.util.List;

public class SoundInventory {
    public List<Phoneme> sounds;

    public SoundInventory() {
        sounds = new ArrayList<>();
    }

    public boolean contains(Phoneme p) {
        return sounds.contains(p);
    }
}
