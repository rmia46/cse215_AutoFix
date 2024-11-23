package autofix.core.people;

public class Employee extends People implements PeropleConstants {
	private static final long serialVersionUID = 1002L;
	private double salary;
	private static int employeeCounter = 0;
	private int employeeId;
	private String role;
	private int usernameExtI = USER_EXTENSION_I;
	//private int usernameExtC = USER_EXTENSION_C;
	
	public Employee(String name, String address, int phoneNumber, int nid, String role, String password) {
		super(name, address, phoneNumber, nid, password);
		super.setUsername(generateUsername());
		this.role = role;
		this.salary = generateSalary(role);
	}
	
	@Override
	String generateUsername() {
        String[] nameParts = super.getName().toLowerCase().split(" ");
        String baseUsername = nameParts[0] + "." + nameParts[1];
        usernameExtI = (usernameExtI == 1000) ? (usernameExtI - 999) : ++usernameExtI;
        //usernameExtC = ((char)usernameExtC == 'Z') ? 65 : ++usernameExtC;
        return baseUsername + "." + usernameExtI;
	}
	@Override
	int generateId() {
		employeeCounter++;
		return 24100000 + employeeCounter;
	}
	
	private double generateSalary(String role) {
		switch(role) {
			case CEO:
				return CEO_Salary;
			case MECHANIC:
				return MECHANIC_Salary;
			case MANAGER:
				return MANAGER_Salary;
		}
		return 0.0;
	}
	@Override
	public String toString() {
		return super.toString();
	}
}
