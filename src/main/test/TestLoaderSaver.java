package test;

import io.LoaderSaver;
import model.CorpusReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import phonology.Language;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TestLoaderSaver {
    LoaderSaver loader;
    Language inventory;

    @BeforeEach
    void runBefore() throws IOException {
        inventory = new Language();
        loader = new CorpusReader(inventory, "data/english.txt");
    }

    @Test
    void testLoader() throws IOException {
        loader.read("data/corpus.txt");
    }

    @Test
    void testSaver() throws FileNotFoundException, UnsupportedEncodingException {
        loader.save("export");
    }
}
