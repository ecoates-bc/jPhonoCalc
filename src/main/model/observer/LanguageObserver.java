package model.observer;

import model.phonology.Phoneme;

import java.util.Observable;
import java.util.Observer;

public class LanguageObserver implements Observer {
    public String sound;

    @Override
    public void update(Observable o, Object arg) {
        sound = arg.toString();
        System.out.println("Added word: " + sound);
    }
}
