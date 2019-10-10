package test;

import model.CorpusReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import phonology.Consonant;
import phonology.Phoneme;
import phonology.Language;
import phonology.Vowel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CorpusReaderTest {
    CorpusReader reader;
    Language inventory;

    Phoneme pa;
    Phoneme pe;
    Phoneme pi;

    @BeforeEach
    void runBefore() throws IOException {
        inventory = new Language();

        reader = new CorpusReader(inventory, "english.txt");

        pa = new Vowel('a');
        pe = new Vowel('e');
        pi = new Consonant('l');
    }

    @Test
    void testRead() throws IOException {
        reader.read("test.txt");
        assertTrue(reader.words.contains("test"));
        assertTrue(reader.words.contains("words"));
        assertTrue(reader.words.contains("input"));
        assertTrue(reader.words.contains("output"));
    }

    @Test
    void testAnalyze() throws IOException {
        reader.words.add("test");
        reader.analyze();
        for (Phoneme p: reader.language.inventory) {
            if (p.isEqual('t')) {
                assertTrue(p.pre.get(0).equals('#'));
                assertTrue(p.pre.get(1).equals('s'));
                assertTrue(p.post.get(0).equals('e'));
            }
        }
    }

    @Test
    void testOutput() throws IOException {
        reader.language.inventory.add(pa);
        reader.language.inventory.add(pe);
        reader.language.inventory.add(pi);
        reader.save("lang");

        List<String> data = Files.readAllLines(Paths.get("lang.txt"));
        assertTrue(data.get(0).equals("p"));
    }
}
