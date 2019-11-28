package model;

import model.exceptions.UnexpectedCharacterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.phonology.Consonant;
import model.phonology.Phoneme;
import model.phonology.Language;
import model.phonology.Vowel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CorpusReaderTest {
    CorpusReader reader;
    Language inventory;

    Phoneme pa;
    Phoneme pe;
    Phoneme pi;

    @BeforeEach
    void runBefore() throws IOException {
        inventory = new Language();

        reader = new CorpusReader(inventory, "data/english.txt");

        pa = new Vowel("a");
        pe = new Vowel("e");
        pi = new Consonant("l");
    }

    @Test
    void testRead() throws IOException {
        reader.read("data/test.txt");
        assertTrue(reader.calculator.words.containsKey("test"));
        assertTrue(reader.calculator.words.containsKey("words"));
        assertTrue(reader.calculator.words.containsKey("input"));
        assertTrue(reader.calculator.words.containsKey("output"));
    }

    @Test
    void testAddFeatures() throws IOException {
        List<String> sounds = Files.readAllLines(Paths.get("data/english.txt"));
        reader.addFeatures("syllabic",4,5,sounds);
        assertFalse(reader.language.inventory.get(0).features.isEmpty());
    }

    @Test
    void testOutput() throws IOException {
        reader.language.inventory.add(pa);
        reader.language.inventory.add(pe);
        reader.language.inventory.add(pi);
        reader.save("lang");

    }

    @Test
    void testGetFLoadMatrix() throws IOException {
        reader.read("data/test.txt");

        try {
            System.out.println(reader.getFLoadMatrix("All"));
        } catch (Exception e) {
            fail("Exception thrown");
        }
    }

    // TODO: implement these in PhonoCalc
//    @Test
//    void testGoodException() throws IOException {
//        reader.read("data/test.txt");
//        try {
//            for (String w: reader.words) {
//                reader.analyzeWord(w);
//            }
//        } catch (UnexpectedCharacterException e) {
//            fail("Found exception");
//        }
//    }
//
//    @Test
//    void testBadException() throws IOException {
//        reader.read("data/test_bad.txt");
//        try {
//            for (String w: reader.words) {
//                reader.analyzeWord(w);
//            }
//            fail("No exceptions found");
//        } catch (UnexpectedCharacterException e) {
//            System.out.println("Exception found");
//        }
//    }
}
