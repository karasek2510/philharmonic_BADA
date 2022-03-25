package BADA_proj.exceptions;

public class PasswordMismatchException extends Exception {
    public PasswordMismatchException(String errorMessage) {
        super(errorMessage);
    }
}
