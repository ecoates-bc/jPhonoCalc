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
import java.text.DecimalFormat;
import java.util.ArrayList;

public class FileTools {
    private CorpusReader reader;
    private Language language;

    public FileTools() {
    }

    // MODIFIES: this.language, reader
    // EFFECTS: creates a new reader, language for corpus work
    public void handleUpload(String path) throws IOException, OverwritingStarterException {
        if (this.reader == null) {
            language = new Language();
            reader = new CorpusReader(language, path);
        } else {
            throw new OverwritingStarterException();
        }
    }

    public void overwriteReader() {
        this.reader = null;
    }

    // MODIFIES: this.reader
    // EFFECTS: reads word data from file
    public void handleRead(String path) throws IOException, NoStarterUploadedException {
        if (this.reader != null) {
            reader.read(path);
        } else {
            throw new NoStarterUploadedException();
        }
    }

    // MODIFIES: this.reader
    // EFFECTS: reads word data using API
    public void getApiValues(String path) throws IOException, NoStarterUploadedException {
        if (this.reader != null) {
            reader.readFromAPI(path);
        } else {
            throw new NoStarterUploadedException();
        }
    }

    // EFFECTS: return a string of all phonemes for displaying
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

    // EFFECTS: gets functional load of two sounds; prepares it to be displayed
    public String getFLoadAsString(String a, String b) throws UnexpectedCharacterException, NoCorpusUploadedException {
        Phoneme first = language.getPhonemeFromString(a);
        Phoneme second = language.getPhonemeFromString(b);

        String fload = reader.getFriendlyNumber(reader.getFLoad(first, second));

        return "Functional load of " + a + " and " + b + ": " + fload + "%";
    }

    // EFFECTS: gets probability of a sound; prepares it to be displayed
    public String getProbabilityAsString(String s) throws UnexpectedCharacterException, NoCorpusUploadedException {
        Phoneme p = language.getPhonemeFromString(s);

        String prob = reader.getFriendlyNumber(reader.getProbability(p));

        return "Probability of " + s + ": " + prob + "%";
    }

    // EFFECTS: saves functional load matrix as a CSV file to path, narrows down inventory to just vowels or consonants
    //  (optional)
    public void getFLoadMatrix(String type, String path) throws NoCorpusUploadedException, NoStarterUploadedException,
            FileNotFoundException, UnsupportedEncodingException {
        if (reader != null) {
            ArrayList<String> matrix = reader.getFLoadMatrix(type);
            PrintWriter writer = new PrintWriter(path);

            for (String l: matrix) {
                writer.println(l);
            }
            writer.close();
        } else {
            throw new NoStarterUploadedException();
        }
    }

}
