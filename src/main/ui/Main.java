package ui;

public class Main {
    public static void main(String[] args) {
        SoundInventory sounds = new SoundInventory();

        Word test = new Word("blarbel");
        test.analyzeWord(sounds);
    }
}
