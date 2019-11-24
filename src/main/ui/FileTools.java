package ui;

import model.CorpusReader;
import model.exceptions.NoCorpusUploadedException;
import model.exceptions.NoStarterUploadedException;
import model.exceptions.OverwritingStarterException;
import model.exceptions.UnexpectedCharacterException;
import model.phonology.Consonant;
import model.phonology.Language;
import model.phonology.Phoneme;
import model.phonology.Vowel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class FileTools {
    private String path;
    private CorpusReader reader;
    private Language language;

    public FileTools() {
        path = "";
    }

    public void handleUpload(String path) throws IOException, OverwritingStarterException {
        if (this.reader == null) {
            this.path = path;
            language = new Language();
            reader = new CorpusReader(language, path);
        } else {
            throw new OverwritingStarterException();
        }
    }

    public void overwriteReader() {
        this.reader = null;
    }

    public void handleRead(String path) throws IOException, NoStarterUploadedException {
        if (this.reader != null) {
            this.path = path;
            reader.read(path);
        } else {
            throw new NoStarterUploadedException();
        }
    }

    public void getApiValues(String path) throws IOException, NoStarterUploadedException {
        if (this.reader != null) {
            reader.readFromAPI(path);
        } else {
            throw new NoStarterUploadedException();
        }
    }

    public ArrayList<String> viewPhonemesAsString() throws NoStarterUploadedException {
        if (reader != null) {
            return language.viewPhonemesAsString();
        } else {
            throw new NoStarterUploadedException();
        }
    }

    public ArrayList<String> getInventory() throws NoStarterUploadedException {
        if (reader != null) {
            return language.getInventory();
        } else {
            throw new NoStarterUploadedException();
        }
    }

    public String getFLoadAsString(String a, String b) throws UnexpectedCharacterException, NoCorpusUploadedException {
        Phoneme first = null;
        Phoneme second = null;

        for (Phoneme p: language.inventory) {
            if (a == p.sound) {
                first = p;
            } else if (b == p.sound) {
                second = p;
            }
        }
        if (a == null || b == null) {
            throw new UnexpectedCharacterException();
        }

        double fload = reader.getFLoad(first, second);

        return "Functional load of " + a + " and " + b + ": " + fload;
    }

    public void handleSave(CorpusReader reader) throws FileNotFoundException, UnsupportedEncodingException {
//        System.out.println("Name output file:");
//        path = input.nextLine();
//        reader.save(path);
//        printProbabilities(path, reader);
    }

    private void printFunctionalLoad(String filename, CorpusReader reader) throws FileNotFoundException,
            UnsupportedEncodingException, NoCorpusUploadedException {
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
