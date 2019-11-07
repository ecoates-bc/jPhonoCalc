package ui;

import model.CorpusReader;
import model.phonology.Consonant;
import model.phonology.Phoneme;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
        }
    }

    public void handleSave(CorpusReader reader) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("Name output file:");
        path = input.nextLine();
        reader.save(path);
        printFunctionalLoad(path, reader);
    }

    private void printFunctionalLoad(String filename, CorpusReader reader) throws FileNotFoundException,
            UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("data/" + filename + ".txt", "UTF-8");

        Phoneme phP = new Consonant("P");
        Phoneme bhP = new Consonant("B");
        Phoneme chP = new Consonant("CH");
        Phoneme khP = new Consonant("K");
        double functionalLoad1 = reader.calculator.calculateFunctionalLoad(phP, bhP, reader.language.inventory);
        double functionalLoad2 = reader.calculator.calculateFunctionalLoad(chP, khP, reader.language.inventory);

        writer.println("Functional load of P and B: " + functionalLoad1);
        writer.println("Functional load of CH and K: " + functionalLoad2);
        writer.close();
    }
}
