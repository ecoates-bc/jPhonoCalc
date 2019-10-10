package test;

import phonology.Phoneme;
import phonology.Consonant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import phonology.Vowel;

import static org.junit.jupiter.api.Assertions.*;

public class PhonemeTest {
    Phoneme ph;
    Phoneme qh;

    @BeforeEach
    void runBefore() {
        ph = new Consonant('p');
        qh = new Vowel('q');
    }

    @Test
    void testSameSound() {
        assertTrue(ph.isEqual('p'));
        assertFalse(qh.isEqual('p'));
        assertFalse(ph.isEqual('q'));
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
}
