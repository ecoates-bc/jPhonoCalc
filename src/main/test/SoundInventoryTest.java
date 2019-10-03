package test;

import phonology.Phoneme;
import phonology.SoundInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SoundInventoryTest {
    SoundInventory inventory;
    Phoneme ph;
    Phoneme qh;

    @BeforeEach
    void runBefore() {
        inventory = new SoundInventory();
        ph = new Phoneme('p');
        qh = new Phoneme('q');
    }

    @Test
    void testContains() {
        inventory.sounds.add(ph);

        assertTrue(inventory.contains(ph));
        assertFalse(inventory.contains(qh));
    }

    @Test
    void testGetInventory() {
        inventory.sounds.add(ph);
        assertTrue(inventory.getInventory().equals("p"));
        assertFalse(inventory.getInventory().equals("pp"));

    }

    @Test
    void testGetInventoryLong() {
        inventory.sounds.add(ph);
        inventory.sounds.add(qh);
        assertTrue(inventory.getInventory().equals("pq"));
        assertFalse(inventory.getInventory().equals("qp"));
    }
}
