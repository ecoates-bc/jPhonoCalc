package ui;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input;
        SoundInventory sounds = new SoundInventory();
        input = new Scanner(System.in);
        String word = "";

        System.out.println("New word to analyze:");
        word = input.nextLine();
        Word test = new Word(word);
        test.analyzeWord(sounds);
    }
}
