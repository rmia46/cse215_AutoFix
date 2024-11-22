package autofix.core.people;

public class Employee extends People implements PeropleConstants {
	private double salary;
	private int employeeId;
	private String role;
	
	Employee(String name, String address, int phoneNumber, int nid, String role) {
		super(name, address, phoneNumber, nid);
		super.setUsername(generateUsername());
		this.role = role;
	}
	
	@Override 
	String generateUsername() {
		
	}
	@Override 
	int generateId() {
		
	}
}
