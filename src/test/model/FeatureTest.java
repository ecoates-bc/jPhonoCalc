package model;

import model.phonology.Consonant;
import model.phonology.Feature;
import model.phonology.Phoneme;
import model.phonology.Vowel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FeatureTest {
    Phoneme plus;
    Phoneme minus;
    Feature f;
    Feature g;

    @BeforeEach
    void runBefore() {
        plus = new Consonant("p");
        minus = new Vowel("u");
        f = new Feature("feature");
        g = new Feature("feature");
    }

    @Test
    void testAdd() {
        f.addPlus(plus);
        assertEquals(f, plus.features.get("+feature"));

        f.addMinus(minus);
        assertEquals(f, minus.features.get("-feature"));
    }

    @Test
    void addPlusAlready() {
        f.addPlus(plus);
        f.addPlus(plus);
        assertTrue(f.plus.size() == 1);
    }

    @Test
    void testRemove() {
        f.addPlus(plus);
        f.removePlus(plus);
        assertFalse(f == plus.features.get("+feature"));

        f.addMinus(plus);
        f.removeMinus(plus);
        assertFalse(f == minus.features.get("-feature"));
    }

    @Test
    void testEquals() {
        assertTrue(f.equals(g));
    }
}
