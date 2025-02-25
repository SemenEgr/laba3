package errors;

public class InvalidMapException extends Exception {
    public InvalidMapException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Invalid map error: " + super.getMessage();
    }
}