package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhonemeTest {
    Phoneme p;
    Phoneme q;

    @BeforeEach
    void runBefore() {
        p = new Phoneme('p');
        q = new Phoneme('q');
    }

    @Test
    void testSameSound() {
        assertTrue(p.sameSound('p'));
        assertFalse(q.sameSound('p'));
        assertFalse(p.sameSound('q'));
    }
}
