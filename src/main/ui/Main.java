package ui;

import model.CorpusReader;
import phonology.Language;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Language english = new Language();
        CorpusReader reader = new CorpusReader(english, "data/english.txt");
        ScannerTools tools = new ScannerTools();

        tools.handleUpload(reader);

        tools.handleSave(reader);
        System.out.println("End of program");
    }
}
