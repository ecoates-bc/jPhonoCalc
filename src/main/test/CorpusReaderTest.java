package test;

import io.LoaderSaver;
import model.CorpusReader;
import model.LanguageTool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import phonology.Phoneme;
import phonology.SoundInventory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CorpusReaderTest {
    CorpusReader reader;
    SoundInventory inventory;

    String simpleWord;
    String mediumWord;
    String longWord;

    Phoneme pa;
    Phoneme pe;
    Phoneme pi;

    @BeforeEach
    void runBefore() {
        inventory = new SoundInventory();

        reader = new CorpusReader(inventory);

        simpleWord = ("hi");
        mediumWord = ("tested");
        longWord = ("antidisestablishmentarianism");

        pa = new Phoneme('a');
        pe = new Phoneme('e');
        pi = new Phoneme('i');
    }

    @Test
    void testLanguageTool(LanguageTool l) {}

    @Test
    void testRead() throws IOException {
        reader.read("test.txt");
        assertTrue(reader.words.contains("test"));
        assertTrue(reader.words.contains("words"));
        assertTrue(reader.words.contains("input"));
        assertTrue(reader.words.contains("output"));
    }

    @Test
    void analyzeSimpleWord() {
        reader.analyzeWord(simpleWord);
        assertTrue(reader.inventory.getInventory().equals("hi"));
    }

    @Test
    void analyzeMediumWord() {
        reader.analyzeWord(mediumWord);
        assertTrue(reader.inventory.getInventory().equals("tesd"));
    }

    @Test
    void analyzeLongWord() {
        reader.analyzeWord(longWord);
        assertTrue(reader.inventory.getInventory().equals("antidseblhmr"));
    }

    @Test
    void analyzeManyWords() throws IOException {
        reader.read("test.txt");
        reader.analyze();
        assertTrue(reader.inventory.getInventory().equals("tesworduchxinp"));
    }

    @Test
    void testOutput() throws IOException {
        reader.inventory.sounds.add(pa);
        reader.inventory.sounds.add(pe);
        reader.inventory.sounds.add(pi);
        reader.save("lang");

        List<String> data = Files.readAllLines(Paths.get("lang.txt"));
        assertTrue(data.get(0).equals("aei"));
    }
}
