package ui.io;

import model.exceptions.UnexpectedCharacterException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface Loader {
    void read(String path) throws IOException, UnexpectedCharacterException;
}
