package test;

import model.CorpusReader;
import model.LanguageTool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import phonology.SoundInventory;

public class TestLanguageTool {
    SoundInventory inventory;
    LanguageTool langTool;

    @BeforeEach
    void runBefore() {
        inventory = new SoundInventory();
        langTool = new CorpusReader(inventory);
    }
    
    @Test
    void testLanguageTool() {
        langTool.analyze();
    }
}
