package autofix.core.people;

public class Customer extends People {
	private static final long serialVersionUID = 1003L;
	private static int customerCounter = 0;
	private int usernameExtI = USER_EXTENSION_I;
	private int usernameExtC = USER_EXTENSION_C;
	private int customerId;
	
	public Customer(String name, String address, int phoneNumber, int nid, String password) {
		super(name, address, phoneNumber, nid, password);
		customerId = generateId();
		super.setUsername(generateUsername());
	}
	
	@Override
	String generateUsername() {
        String[] nameParts = super.getName().toLowerCase().split(" ");
        String baseUsername = nameParts[0] + "." + nameParts[1];
        usernameExtI = (usernameExtI == 10000) ? (usernameExtI - 9999) : ++usernameExtI;
        usernameExtC = ((char)usernameExtC == 'Z') ? 65 : ++usernameExtC;
        return baseUsername + "." + usernameExtI + (char)usernameExtC;
	}
	@Override
	int generateId() {
		customerCounter++;
		return 24300000 + customerCounter;
	}
	@Override
	public String toString() {
		return super.toString();
	}

	public int getCounter() {
		return customerCounter;
	}

	public static void setCustomerCounter(int customerCounter) {
		Customer.customerCounter = customerCounter;
	}

	public int getId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
}
