package autofix.core.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import autofix.core.people.*;

public class FileManager implements FileConstants {
	private static final String path = DATA_PATH;
	
	// Object file reader problematic on append
//	public static List<Customer> read(String type) {
//        List<People> peopleList = new ArrayList<>();
//        List<Customer> customerList = new ArrayList<>();
//        String fileName = type.equals(EMPLOYEE_FILE) ? EMPLOYEE_FILE : CUSTOMER_FILE;
//        
//        try (FileInputStream fileIn = new FileInputStream(path + fileName);
//             ObjectInputStream in = new ObjectInputStream(fileIn)) {
//            while (true) {
//            	System.out.println("Reading");
//            	Customer person = (Customer) in.readObject();
//            	
//                customerList.add(person);
////                System.out.println(peopleList);
//            }
//        } catch (EOFException e) {
//        	System.out.println("End of file reached");
//        	return customerList;
//        } catch (IOException e) {
//            System.out.println("I/O Error" + e.getMessage());
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//        	System.out.println("Class not found");
//        }
//        return customerList;
//    }
	
	// object file writer
//	public static void objectWrite(People person) {
//		
//        String fileName = person instanceof Employee ? EMPLOYEE_FILE : CUSTOMER_FILE;
//        
//        try (FileOutputStream fileOut = new FileOutputStream((path + fileName), true);
//             AppendObjectOutputStream out = new AppendObjectOutputStream(fileOut)) {
//            ;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
	
	// Existing user check
	public static boolean exists(People person) throws IOException {
		String fileName = person instanceof Employee ? EMPLOYEE_FILE : CUSTOMER_FILE;
		
        try (BufferedReader reader = new BufferedReader(new FileReader((path + fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String[] p2 = parts[3].split(" ");
                System.out.println(Integer.parseInt(p2[1]));
                if(Integer.parseInt(p2[1]) == person.getPhoneNumber()) {
                    return true;
                }
            }
        }
        return false;
    }
	
	// Using buffer Writer
	public static void bufferWriter(People person) throws IOException {
		String fileName = person instanceof Employee ? EMPLOYEE_FILE : CUSTOMER_FILE;
		if(!exists(person)) {
			try(BufferedWriter writer = new BufferedWriter(new FileWriter(path + fileName, true))) {
                writer.write(person.toString());
                writer.newLine();
                System.out.println("Write Successfull");
            } catch(IOException e) {
            	System.out.println(e.getMessage());
            	System.out.println("IO Error");
            }
		}
		else {
			System.out.println("User with ID " + person.getId() + " already exists.");
		}
	}
	
	public void deleteData(People person) throws IOException {
		String fileName = person instanceof Employee ? EMPLOYEE_FILE : CUSTOMER_FILE;
        File inputFile = new File(path + fileName);
        File tempFile = new File("tempfile.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[3]) != person.getPhoneNumber()) {
                    writer.write(line);
                    writer.newLine();
                } else {
                    found = true;
                }
            }

            if (found) {
                System.out.println("User with ID " + person.getId() + " deleted.");
            } else {
                System.out.println("User with ID " + person.getId() + " not found.");
            }
        } catch(IOException e) {
        	System.out.println(e.getMessage());
        }

        if (!inputFile.delete()) {
            System.out.println("Could not delete original file.");
            return;
        }

        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temp file.");
        }
    }
}
