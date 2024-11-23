package autofix.core.people;
import autofix.core.exceptions.*;
import autofix.core.exceptions.PeopleExceptions.*;
import java.io.Serializable;

public abstract class People implements Serializable {
	private static final long serialVersionUID = 1001L;
    private String name, username, password;
    private String address;
    private int phoneNumber, nid;
    
    public People(String name, String address, int phoneNumber, int nid, String password) {
    	this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.nid = nid;
        this.password = password;
    }
    
    abstract String generateUsername();
    abstract public int getId();
    abstract int generateId();
    abstract protected int getCounter();
     
    @Override 
    public String toString() {
    	return "Name: " + name + ",Username: " + username + ",Address: " + address + ",Phone: " + phoneNumber;
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

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}
    
}
