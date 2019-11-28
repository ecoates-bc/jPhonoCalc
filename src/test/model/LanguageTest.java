package model;

import model.exceptions.UnexpectedCharacterException;
import model.phonology.Consonant;
import model.phonology.Phoneme;
import model.phonology.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LanguageTest {
    Language inventory;
    Phoneme ph;
    Phoneme qh;

    @BeforeEach
    void runBefore() {
        inventory = new Language();
        ph = new Consonant("p");
        qh = new Consonant("q");
    }

    @Test
    void testContains() {
        inventory.addToInventory(ph);

        assertTrue(inventory.contains(ph));
        assertFalse(inventory.contains(qh));
    }

    // TODO: refactor this test
    @Test
    void testGetInventory() {
        inventory.addToInventory(ph);
        assertEquals(inventory.getInventory().get(0), ph.sound);
    }

    @Test
    void testVewPhonemes() {
        inventory.addToInventory(ph);
        inventory.addToInventory(qh);
        ArrayList<String> strings = inventory.viewPhonemesAsString();
        assertEquals(strings.get(0), "Consonants:  p q");
        assertEquals(strings.get(1), "Vowels: ");
    }

    @Test
    void testAdd() {
        inventory.addToInventory(ph);
        assertTrue(inventory.inventory.contains(ph));
    }

    @Test
    void testGetPhonemes() throws UnexpectedCharacterException {
        inventory.addToInventory(ph);
        inventory.addToInventory(qh);

        String p = "p";

        assertEquals(inventory.getPhonemeFromString(p), ph);
    }
}
