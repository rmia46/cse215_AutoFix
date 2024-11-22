package autofix.core.people;

public class Customer extends People implements PeropleConstants {
	private int userId;
	
	Customer(String name, String address, int phoneNumber, int nid) {
		super(name, address, phoneNumber, nid);
		userId = generateId();
		super.setUsername(generateUsername());
	}
	
	@Override
	String generateUsername() {
		
	}
	@Override
	int generateId() {
		
	}
	
}
