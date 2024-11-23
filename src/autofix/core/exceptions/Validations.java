package autofix.core.exceptions;

import autofix.core.exceptions.PeopleExceptions.*;

public class Validations {
	public static boolean isValidName(String name) {
		if(name == null || name.trim().isEmpty())
			return false;
		
		String validity = "^[A-Za-z]+(?:\\\\s[A-Za-z]+)*$";
		
		return name.matches(validity);
		
	}
	
	public static boolean passwordMatches(String password, String confirmPassword) {
	    return password != null && password.equals(confirmPassword);
	}
	
	public static boolean isValidAddress(string address) {
		if(address == null || address.trim().isEmpty())
			return false;
		
		String validity = "^(?=.*[A-Za-z0-9#-])[A-Za-z0-9#-]{3,}$";
		return address.matches(validity)
	}
	
	public static boolean isValidNID(String nid) {
		try {
            Integer.parseInt(nid);
            
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Input is not a valid NID: " + nid);
        }
		
	}
	
	public static boolean isValidPhoneNumber(String phoneNumber) {
		try {
            Integer.parseInt(phoneNumber);    
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Input is not a valid NID: " + phoneNumber);
        }
	}
}
