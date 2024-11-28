package autofix; 

import autofix.core.people.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import autofix.core.file.FileConstants;
import autofix.core.file.FileManager;

public class Applilcation implements FileConstants {

	public static void main(String[] args) {

		List<People> peopleList = new ArrayList<>();
		List<People> readList = new ArrayList<>();
		People emp1 = new Employee("Roman Mia", "1/2 Bashundhara", 1912345678, 23333333, "CEO", "xyz");
		People emp2 = new Employee("Siam Mia", "1/2 Savar", 1912345678, 24142222, "Manager", "xyz");
		People cus1 = new Customer("Roman Mia", "xyz", 12121, 12321312, "skjwsadf");
		
		peopleList.add(emp1);
		peopleList.add(emp2);
		File mf = new File("src/autofix/data/employee.data");
		
		if(mf.exists()) System.out.println(mf.getAbsolutePath());
		try {
			PeopleManager.addPeople(emp1);
			PeopleManager.addPeople(emp2);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			readList = PeopleManager.listPeople(EMPLOYEE_FILE);
			for(People person : readList) {
				System.out.println(person);
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		try {
			PeopleManager.deletePeople(emp2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			readList = PeopleManager.listPeople(EMPLOYEE_FILE);
			for(People person : readList) {
				System.out.println(person);
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
}