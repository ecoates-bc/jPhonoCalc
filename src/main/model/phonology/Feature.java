package model.phonology;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// A category that can describe articulation characteristics of a phoneme.
public class Feature {
    public String name;
    public List<Phoneme> plus;
    public List<Phoneme> minus;

    public Feature(String name) {
        this.name = name;
        this.plus = new ArrayList<>();
        this.minus = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the phoneme to the plus values
    public void addPlus(Phoneme p) {
        if (!plus.contains(p)) {
            plus.add(p);
            p.addFeature("+", this);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the phoneme to the minus values
    public void addMinus(Phoneme p) {
        if (!minus.contains(p)) {
            minus.add(p);
            p.addFeature("-", this);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes the phoneme from the plus values
    public void removePlus(Phoneme p) {
        plus.remove(p);
        p.removeFeature("+" + this.name);
    }

    // MODIFIES: this
    // EFFECTS: removes the phoneme from the minus values
    public void removeMinus(Phoneme p) {
        minus.remove(p);
        p.removeFeature("-" + this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Feature feature = (Feature) o;
        return Objects.equals(name, feature.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
