package autofix.core.file;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import autofix.core.people.*;

public class FileManager implements FileConstants {
	private static final String path = DATA_PATH;
	
	// file reader
	public static List<People> read(String type) {
        List<People> peopleList = new ArrayList<>();
        String fileName = type.equals(EMPLOYEE_FILE) ? EMPLOYEE_FILE : CUSTOMER_FILE;
        
        try (FileInputStream fileIn = new FileInputStream(path + fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            while (true) {
            	System.out.println("Reading");
            	People person = (Employee) in.readObject();
            	System.out.println(person.getPhoneNumber());
                peopleList.add(person);
                System.out.println(peopleList);
            }
        } catch (EOFException e) {
        	System.out.println("End of file reached");
        	return peopleList;
        } catch (IOException e) {
            System.out.println("I/O Error" + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
        	System.out.println("Class not found");
        }
        return peopleList;
    }
	
	// file writer
	public static void write(People person) {
		
        String fileName = person instanceof Employee ? EMPLOYEE_FILE : CUSTOMER_FILE;
        
        try (FileOutputStream fileOut = new FileOutputStream(path + fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(person);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
