package ui;

import model.CorpusReader;
import model.phonology.Consonant;
import model.phonology.Phoneme;
import org.json.JSONException;

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
            reader.readFromAPI(path);
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

        Phoneme phP = new Consonant("m");
        Phoneme bhP = new Consonant("n");
        Phoneme chP = new Consonant("s");
        Phoneme khP = new Consonant("ʃ");
        double functionalLoad1 = reader.getFLoad(phP, bhP);
        double functionalLoad2 = reader.getFLoad(chP, khP);

        writer.println("Functional load of m and n: " + functionalLoad1);
        writer.println("Functional load of s and ʃ: " + functionalLoad2);
        writer.close();
    }
}
