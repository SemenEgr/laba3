package errors;

public class MoodConflictException extends RuntimeException {
    public MoodConflictException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Mood conflict error: " + super.getMessage();
    }
}
