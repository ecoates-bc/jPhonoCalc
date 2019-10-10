package test;

import model.CorpusReader;
import model.LanguageTool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import phonology.Language;

import java.io.IOException;

public class TestLanguageTool {
    Language inventory;
    LanguageTool langTool;

    @BeforeEach
    void runBefore() throws IOException {
        inventory = new Language();
        langTool = new CorpusReader(inventory, "english.txt");
    }
    
    @Test
    void testLanguageTool() {
        langTool.analyze();
    }
}
