package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SoundInventoryTest {
    SoundInventory inventory;
    Phoneme p;
    Phoneme q;

    @BeforeEach
    void runBefore() {
        inventory = new SoundInventory();
        p = new Phoneme('p');
        q = new Phoneme('q');
    }

    @Test
    void testContains() {
        inventory.sounds.add(p);

        assertTrue(inventory.contains(p));
        assertFalse(inventory.contains(q));
    }

    @Test
    void testGetInventory() {
        inventory.sounds.add(p);
        assertTrue(inventory.getInventory().equals("p"));
        assertFalse(inventory.getInventory().equals("pp"));

    }

    @Test
    void testGetInventoryLong() {
        inventory.sounds.add(p);
        inventory.sounds.add(q);
        assertTrue(inventory.getInventory().equals("pq"));
        assertFalse(inventory.getInventory().equals("qp"));
    }
}
