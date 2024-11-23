package autofix;

import autofix.core.people.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import autofix.core.file.FileConstants;
import autofix.core.file.FileManager;

public class Applilcation implements FileConstants {

	public static void main(String[] args) {

		People customer1 = new Customer("Roman Mia", "Dhaka", 01344213, 34234324, "roman223");
		People employee1 = new Employee("Roman Mia", "Rangpur", 388232, 23232, "CEO", "dkjasf");
		People employee2 = new Customer("Siam Mia", "Dhaka", 01343213, 34634324, "rossman223");
//		People customer3 = new Customer("Siam dMia", "Dhakda", 013432133, 346334324, "rossmdfan223");
		
		List<People> peopleList = new ArrayList<>();
		List<People> empList = new ArrayList<>();
		//File file = new File(DATA_PATH + CUSTOMER_FILE);
		
		
		//FileManager.write(customer1);
		FileManager.write(employee1);
		FileManager.write(employee2);
//		FileManager.write(customer2);
//		FileManager.write(customer3);
		
		//peopleList = FileManager.read(CUSTOMER_FILE);
		empList = FileManager.read(EMPLOYEE_FILE);
		
		for(People person : empList) {
			person.toString();
		}
		
	}
}