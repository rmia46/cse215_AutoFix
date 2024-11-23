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

		Customer customer1 = new Customer("Roman Mia", "Dhaka", 01344213, 34234324, "roman223");
//		People employee1 = new Employee("Roman Mia", "Rangpur", 388232, 23232, "CEO", "dkjasf");
//		People employee2 = new Customer("Siam Mia", "Dhaka", 01343213, 34634324, "rossman223");
		People customer3 = new Customer("Siam dMia", "Dhakda", 013432133, 346334324, "rossmdfan223");
		
		try {
			FileManager.bufferWriter(customer1);
			FileManager.bufferWriter(customer3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}