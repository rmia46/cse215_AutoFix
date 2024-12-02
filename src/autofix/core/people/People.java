package autofix.core.people;
import autofix.Generic;
import java.io.Serializable;

public abstract class People implements Serializable, Generic {
	private static final long serialVersionUID = 1001L;
    private String name, username, password;
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String address, phoneNumber;
    private int nid;
    
    public People() {
    	
    }
    
    public People(String name, String address, String phoneNumber, int nid, String password) {
    	this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.nid = nid;
        this.password = password;
    }
    
    abstract String generateUsername();
    abstract public int getId();
    abstract protected int getCounter();
     
    @Override 
    public String toString() {
    	return "Name: " + name + "\nUsername: " + username + "\nAddress: " + address + "\nPhone: " + phoneNumber + " Pass: " + password;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	} 
}
