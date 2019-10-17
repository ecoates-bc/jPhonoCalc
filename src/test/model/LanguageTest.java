package model;

import model.phonology.Consonant;
import model.phonology.Phoneme;
import model.phonology.Language;
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
        inventory.addToInventory(ph);

        assertTrue(inventory.contains(ph));
        assertFalse(inventory.contains(qh));
    }

    @Test
    void testGetInventory() {
        inventory.addToInventory(ph);
        assertTrue(inventory.getInventory().equals("p"));
        assertFalse(inventory.getInventory().equals("pp"));

    }

    @Test
    void testGetInventoryLong() {
        inventory.addToInventory(ph);
        inventory.addToInventory(qh);
        assertTrue(inventory.getInventory().equals("pq"));
        assertFalse(inventory.getInventory().equals("qp"));
    }

    @Test
    void testAdd() {
        inventory.addToInventory(ph);
        assertTrue(inventory.inventory.contains(ph));
    }
}
