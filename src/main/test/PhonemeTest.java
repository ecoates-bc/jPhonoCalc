package test;

import phonology.Phoneme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhonemeTest {
    Phoneme ph;
    Phoneme qh;

    @BeforeEach
    void runBefore() {
        ph = new Phoneme('p');
        qh = new Phoneme('q');
    }

    @Test
    void testSameSound() {
        assertTrue(ph.checkSimilar('p'));
        assertFalse(qh.checkSimilar('p'));
        assertFalse(ph.checkSimilar('q'));
    }
}
