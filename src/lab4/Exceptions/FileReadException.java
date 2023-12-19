package lab4.Exceptions;

public class FileReadException extends Exception {
    String message;
    public FileReadException(String message) {
        this.message = message;
    }
    public String toString() {
        return message;
    }
}

