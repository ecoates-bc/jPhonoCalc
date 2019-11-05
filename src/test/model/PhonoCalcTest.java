package model;

import model.phonology.Consonant;
import model.phonology.Language;
import model.phonology.Phoneme;
import model.phonology.Vowel;
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
    Phoneme aP = new Vowel("a");
    Phoneme sP = new Consonant("s");

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
        assertEquals(reader.calculator.getProbability(xP, reader.calculator.words.values()), numer/denomer);
        numer = 2;
        denomer = 3;
        assertEquals(reader.calculator.getProbability(tP, reader.calculator.words.values()), numer/denomer);

    }

    @Test
    void testEntropy() {
        assertEquals(reader.calculator.getEntropy(language.inventory, reader.calculator.words.values()),
                5.578445831169866);
    }

    @Test
    void testVoidFunctionalLoad() {
        assertEquals(reader.calculator.calculateFunctionalLoad(xP, aP, language.inventory), 0);
    }

    @Test
    void testFunctionalLoad() {
        double small = reader.calculator.calculateFunctionalLoad(xP, tP, language.inventory);
        double large = reader.calculator.calculateFunctionalLoad(tP, sP, language.inventory);

        // functional load values are negative
        System.out.println(small + ", " + large);
    }
}
