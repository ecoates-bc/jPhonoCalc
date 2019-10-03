package test;

import io.LoaderSaver;
import model.CorpusReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import phonology.SoundInventory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TestLoaderSaver {
    LoaderSaver loader;
    SoundInventory inventory;

    @BeforeEach
    void runBefore() {
        inventory = new SoundInventory();
        loader = new CorpusReader(inventory);
    }

    @Test
    void testLoader() throws IOException {
        loader.read("corpus.txt");
    }

    @Test
    void testSaver() throws FileNotFoundException, UnsupportedEncodingException {
        loader.save("export");
    }
}
