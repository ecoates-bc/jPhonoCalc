package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordTest {
    SoundInventory inventory;

    Word simpleWord;
    Word mediumWord;
    Word longWord;
    Word funkyWord;

    @BeforeEach
    void runBefore() {
        inventory = new SoundInventory();

        simpleWord = new Word("hi");
        mediumWord = new Word("tested");
        longWord = new Word("antidisestablishmentarianism");
        funkyWord = new Word("xb3nb 39()()..==+ +axba$ @a3a");

    }

    @Test
    void testIsAlphabet() {
        assertTrue(funkyWord.isAlphabet('x'));
        assertFalse(funkyWord.isAlphabet('9'));
        assertFalse(funkyWord.isAlphabet(')'));
        assertTrue(simpleWord.isAlphabet('a'));
    }

    @Test
    void analyzeSimpleWord() {
        simpleWord.analyzeWord(inventory);
        assertTrue(inventory.getInventory().equals("hi"));
    }

    @Test
    void analyzeMediumWord() {
        mediumWord.analyzeWord(inventory);
        assertTrue(inventory.getInventory().equals("tesd"));
    }

    @Test
    void analyzeLongWord() {
        longWord.analyzeWord(inventory);
        assertTrue(inventory.getInventory().equals("antidseblhmr"));
    }

    @Test
    void analyzeFunkyWord() {
        funkyWord.analyzeWord(inventory);
        assertTrue(inventory.getInventory().equals("xbna"));
    }
}