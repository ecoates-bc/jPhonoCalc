package test;

import phonology.Phoneme;
import phonology.Consonant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhonemeTest {
    Phoneme ph;
    Phoneme qh;

    @BeforeEach
    void runBefore() {
        ph = new Consonant('p');
        qh = new Consonant('q');
    }

    @Test
    void testSameSound() {
        assertTrue(ph.isEqual('p'));
        assertFalse(qh.isEqual('p'));
        assertFalse(ph.isEqual('q'));
    }
}
