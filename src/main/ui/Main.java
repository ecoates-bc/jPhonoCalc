package ui;

import model.CorpusReader;
import model.phonology.Language;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Language english = new Language();
        CorpusReader reader = new CorpusReader(english, "data/englishAPI.txt");
        ScannerTools tools = new ScannerTools();

        tools.handleUpload(reader);

        tools.handleSave(reader);
        System.out.println("End of program");
    }
}
