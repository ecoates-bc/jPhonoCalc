package ui.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface LoaderSaver {
    void read(String path) throws IOException;

    void save(String filename) throws FileNotFoundException, UnsupportedEncodingException;
}
