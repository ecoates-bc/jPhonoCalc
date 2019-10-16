package ui;

import model.CorpusReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class ScannerTools {
    public Scanner input;
    private String path;

    public ScannerTools() {
        input = new Scanner(System.in);
        path = "";
    }

    public void handleUpload(CorpusReader reader) {
        System.out.println("Input file path:");
        path = "data/" + input.nextLine();
        try {
            reader.read(path);
        } catch (IOException e) {
            System.out.println("File not found. Try again.");
            handleUpload(reader);
        } finally {
            reader.analyze();
        }
    }

    public void handleSave(CorpusReader reader) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("Name output file:");
        path = input.nextLine();
        reader.save(path);
    }
}
