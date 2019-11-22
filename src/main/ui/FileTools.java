package ui;

import model.CorpusReader;
import model.exceptions.NoCorpusUploadedException;
import model.phonology.Consonant;
import model.phonology.Language;
import model.phonology.Phoneme;
import model.phonology.Vowel;
import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileTools {
    private String path;
    private CorpusReader reader;
    private Language language;

    public FileTools() {
        path = "";
        language = new Language();
    }

    public void handleUpload(String path) throws IOException {
        if (this.reader == null) {
            this.path = path;
            reader = new CorpusReader(language, path);
        } else {
            throw new IOException();
        }
    }

    public ArrayList<String> getPhonemes() throws NoCorpusUploadedException {
        if (reader != null) {
            return language.getPhonemes();
        } else {
            throw new NoCorpusUploadedException();
        }
    }

    public void handleSave(CorpusReader reader) throws FileNotFoundException, UnsupportedEncodingException {
//        System.out.println("Name output file:");
//        path = input.nextLine();
//        reader.save(path);
//        printProbabilities(path, reader);
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

    private void printProbabilities(String filename, CorpusReader reader) throws FileNotFoundException,
            UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("data/" + filename + ".txt", "UTF-8");

        Phoneme mhP = new Consonant("m");
        Phoneme shP = new Consonant("s");
        Phoneme ahP = new Vowel("ɑ");
        Phoneme ihP = new Vowel("i");
        double probabilityM = reader.getProbability(mhP);
        double probabilityP = reader.getProbability(shP);
        double probabilityA = reader.getProbability(ahP);
        double probabilityI = reader.getProbability(ihP);

        writer.println("Probability of [m]: " + probabilityM);
        writer.println("Probability of [s]: " + probabilityP);
        writer.println("Probability of [ɑ]: " + probabilityA);
        writer.println("Probability of [i]: " + probabilityI);
        writer.close();
    }

}