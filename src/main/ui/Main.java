package ui;

public class Main {
    public static void main(String[] args) {
        Phoneme el = new Phoneme("l");
        analyzeSound(el);
    }

    public static void analyzeSound(Phoneme phoneme) {
        String s;
        s = phoneme.sound;
        System.out.println("It sounds like a(n): " + s);
    }
}
