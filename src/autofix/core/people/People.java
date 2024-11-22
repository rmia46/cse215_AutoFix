package autofix.core.people;

public abstract class People {
    private String name, username;
    private String address;
    private int phoneNumber, nid;

    public People(String name, String address, int phoneNumber, int nid) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    abstract String generateUsername();
    abstract int generateId();
    
    @Override 
    public String toString() {
    	return "Name: " + name + "Username: " + username + "\nAddress: " + address + "Phone: " + phoneNumber;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
}
