package ui.io;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface Saver {
    void save(String filename) throws FileNotFoundException, UnsupportedEncodingException;
}
