package model;

import model.phonology.Feature;
import model.phonology.Phoneme;
import model.phonology.Consonant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.phonology.Vowel;

import static org.junit.jupiter.api.Assertions.*;

public class PhonemeTest {
    Phoneme ph;
    Phoneme qh;
    Feature f;

    @BeforeEach
    void runBefore() {
        ph = new Consonant("p");
        qh = new Vowel("q");
        f = new Feature("feature");
    }

    @Test
    void testSameSound() {
        assertTrue(ph.hasSound("p"));
        assertFalse(qh.hasSound("p"));
        assertFalse(ph.hasSound("q"));
    }

    @Test
    void testAddPre() {
        ph.addPre(qh);
        assertTrue(ph.pre.contains(qh));
    }

    @Test
    void testAddPost() {
        qh.addPost(ph);
        assertTrue(qh.post.contains(ph));
    }

    @Test
    void testVowelOpposite() {
        qh.addPre(ph);
        qh.addPre(qh);
        assertTrue(qh.getOppositeBefores().contains(ph));
        assertFalse(qh.getOppositeBefores().contains(qh));
    }

    @Test
    void testConsonantOpposite() {
        ph.addPre(ph);
        ph.addPre(qh);
        assertTrue(ph.getOppositeBefores().contains(qh));
        assertFalse(ph.getOppositeBefores().contains(ph));
    }

    @Test
    void testFeatures() {
        ph.addFeature("+", f);
        qh.addFeature("-", f);
        assertEquals(f, ph.features.get("+feature"));
        assertEquals(f, qh.features.get("-feature"));
    }
}
