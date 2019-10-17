package model.exceptions;

public class UnexpectedCharacterException extends CorpusReaderException {
    public UnexpectedCharacterException() {

    }

    public UnexpectedCharacterException(String c) {
        super("Unexpected character: " + c);
    }
}
