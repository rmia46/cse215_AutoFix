package autofix.core.people;

import autofix.AppConstants;
import autofix.ObjectIdentifier;

public class Employee extends People implements AppConstants, ObjectIdentifier {
	private static final long serialVersionUID = 1002L;
	private static int employeeCounter = 0;
	private int employeeId;
	private int usernameExtI = USER_EXTENSION_I;
	
	public Employee() {
		super();
	}
	
	public Employee(String name, String address, String phoneNumber, int nid, String password) {
		super(name, address, phoneNumber, nid, password);
		super.setUsername(generateUsername());
	}
	
	@Override
	String generateUsername() {
        String[] nameParts = super.getName().toLowerCase().split(" ");
        String baseUsername = nameParts[0] + "." + nameParts[1];
        usernameExtI = (usernameExtI == 1000) ? (usernameExtI - 999) : ++usernameExtI;
        return baseUsername + "." + usernameExtI;
	}
	@Override
	public int generateId() {
		employeeCounter++;
		return 24100000 + employeeCounter;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

	public int getCounter() {
		return employeeCounter;
	}

	public int getId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
}
