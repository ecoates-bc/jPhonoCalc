package ui;

import model.CorpusReader;
import phonology.Language;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner input;
        Language english = new Language();
        CorpusReader reader = new CorpusReader(english, "english.txt");
        input = new Scanner(System.in);
        String path = "";

        System.out.println("Input file path:");
        path = input.nextLine();
        reader.read(path);
        reader.analyze();


        System.out.println("Name output file:");
        path = input.nextLine();
        reader.save(path);
        System.out.println("End of program");
    }
}
