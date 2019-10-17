package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.phonology.Language;

import java.io.IOException;

public class TestLanguageTool {
    Language inventory;
    LanguageTool langTool;

    @BeforeEach
    void runBefore() throws IOException {
        inventory = new Language();
        langTool = new CorpusReader(inventory, "data/english.txt");
    }
    
    @Test
    void testLanguageTool() {
        langTool.analyze();
    }
}
