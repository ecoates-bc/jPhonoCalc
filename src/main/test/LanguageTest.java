package test;

import phonology.Consonant;
import phonology.Phoneme;
import phonology.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LanguageTest {
    Language inventory;
    Phoneme ph;
    Phoneme qh;

    @BeforeEach
    void runBefore() {
        inventory = new Language();
        ph = new Consonant('p');
        qh = new Consonant('q');
    }

    @Test
    void testContains() {
        inventory.inventory.add(ph);

        assertTrue(inventory.contains(ph));
        assertFalse(inventory.contains(qh));
    }

    @Test
    void testGetInventory() {
        inventory.inventory.add(ph);
        assertTrue(inventory.getInventory().equals("p"));
        assertFalse(inventory.getInventory().equals("pp"));

    }

    @Test
    void testGetInventoryLong() {
        inventory.inventory.add(ph);
        inventory.inventory.add(qh);
        assertTrue(inventory.getInventory().equals("pq"));
        assertFalse(inventory.getInventory().equals("qp"));
    }
}