package autofix.core.exceptions;

@SuppressWarnings("serial")
public class Validations {

	public static class InvalidNameException extends Exception {
        public InvalidNameException(String message) {
            super(message);
        }
    }
	
	public static class InvalidPhoneNumberException extends Exception {
        public InvalidPhoneNumberException(String message) {
            super(message);
        }
    }
	
	public static class blankFieldException extends Exception {
        public blankFieldException(String message) {
            super(message);
        }
    }

    public static class LoginFailureException extends Exception {
        public LoginFailureException(String message) {
            super(message);
        }
    }

    public static class SignupFailureException extends Exception {
        public SignupFailureException(String message) {
            super(message);
        }
    }

    public static class FileSavingFailedException extends Exception {
        public FileSavingFailedException(String message) {
            super(message);
        }
    }

    public static class InvalidNIDException extends Exception {
        public InvalidNIDException(String message) {
            super(message);
        }
    }
    public static class PasswordMismatchException extends Exception {
    	public PasswordMismatchException(String message) {
    		super(message);
    	}
    }
    public static void validatePassword(String password1, String password2) throws PasswordMismatchException {
    	if(password1 == null || password2 == null || !password1.equals(password2)) {
    		throw new PasswordMismatchException("Password does not match. Please recheck.");
    	}
    } 
    public static void validateName(String name) throws InvalidNameException {
        if (name == null || name.trim().isEmpty() || !name.matches("[a-zA-Z ]+")) {
            throw new InvalidNameException("Name must be a valid string with alphabetic characters.");
        }
    }
    public static void validatePhoneNumber(String phone) throws InvalidPhoneNumberException {
        if (phone == null || phone.trim().isEmpty() || !phone.matches("\\d+")) {
            throw new InvalidPhoneNumberException("Phone number must be a valid number");
        }
    }

    public static void validateFileSave(boolean success) throws FileSavingFailedException {
        if (!success) {
            throw new FileSavingFailedException("Failed to save the file.");
        }
    }

    public static void validateNID(String nid) throws InvalidNIDException {
        if (nid == null || !nid.matches("\\d+")) {
            throw new InvalidNIDException("NID must be a valid number.");
        }
    }
    public static void blankFieldException(String text) throws blankFieldException {
        if (text == null || text.isEmpty()) {
            throw new blankFieldException("Fill out all the fields!");
        }
    }
}