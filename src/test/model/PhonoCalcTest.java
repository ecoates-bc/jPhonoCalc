package model;

import model.phonology.Consonant;
import model.phonology.Language;
import model.phonology.Phoneme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhonoCalcTest {
    CorpusReader reader;
    Language language;

    Phoneme xP = new Consonant("x");
    Phoneme tP = new Consonant("t");

    public double numer;
    public double denomer;

    @BeforeEach
    void runBefore() throws IOException {
        language = new Language();
        reader = new CorpusReader(language, "data/english.txt");
        reader.read("data/test.txt");
    }

    @Test
    void testAnalyzeWord() {
        assertEquals(language.inventory.get(5).pre.size(), 2);
        assertEquals(language.inventory.get(10).pre.size(), 0);
        assertEquals(language.inventory.get(25).post.size(), 0);
    }

    @Test
    void testProbability() {
        numer = 1;
        denomer = 6;
        assertEquals(reader.calculator.getProbability(xP), numer/denomer);
        numer = 2;
        denomer = 3;
        assertEquals(reader.calculator.getProbability(tP), numer/denomer);
    }

    @Test
    void testEntropy() {
        double probT = reader.calculator.getProbability(tP);
        assertEquals(reader.calculator.getEntropy(language.inventory), 5.578445831169866);
    }

    @Test
    void testNaN() {
        System.out.println();
    }
}
