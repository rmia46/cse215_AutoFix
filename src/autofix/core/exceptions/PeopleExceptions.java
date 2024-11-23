package autofix.core.exceptions;

public class PeopleExceptions {
	
	public static class PasswordMismatchException extends Exception {
        public PasswordMismatchException(String message) {
            super(message);
        }
    }

    public static class UserNotFoundException extends Exception {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidInputException extends Exception {
        public InvalidInputException(String message) {
            super(message);
        }
    }
}
