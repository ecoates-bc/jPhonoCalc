package model;

import phonology.SoundInventory;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner input;
        SoundInventory sounds = new SoundInventory();
        CorpusReader reader = new CorpusReader(sounds);
        input = new Scanner(System.in);
        String path = "";

        System.out.println("Input file path:");
        path = input.nextLine();
        reader.read(path);
        reader.analyze();


        System.out.println("Name of language:");
        path = input.nextLine();
        reader.save(path);
        System.out.println("End of program");
    }
}
