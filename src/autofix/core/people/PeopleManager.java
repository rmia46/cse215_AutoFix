package autofix.core.people;
import autofix.core.file.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import autofix.AppConstants;

public class PeopleManager implements AppConstants {
	
	public static boolean exists(People person, List<People> peopleList) {
		for(People find : peopleList) {
			if(find.getNid() == person.getNid()) {
				return true;
			}
		}
		return false;
	}
	
	public static void addPeople(People person) throws ClassNotFoundException, IOException {
		String filename = person instanceof Employee ? "employee.dat" : "customer.dat";
		List<People> peopleList = new ArrayList<>();
		try {
			peopleList = FileManager.read(filename);	
				
		} catch(IOException e) {
			if(peopleList.isEmpty()) {
				peopleList.add(person);
				FileManager.write(peopleList, filename);
				System.out.println(person.getName() + " Added");
				return;
			}
		}
		
		if(exists(person, peopleList)) {
			System.out.println(person.getName() + " is already on database.");
		} else {
			peopleList.add(person);
			FileManager.write(peopleList, filename);
			System.out.println(person.getName() + " Added");
		}
	}
	public static void deletePeople(People person) throws IOException, ClassNotFoundException {
		String filename = person instanceof Employee ? "employee.dat" : "customer.dat";
		List<People> peopleList = new ArrayList<>();
		List<People> newList = new ArrayList<>();
		
		peopleList = FileManager.read(filename);
		for(People user : peopleList) {
			if(!(user.getNid() == person.getNid())) {
				System.out.println(user.getNid());
				System.out.println(person.getNid());
				newList.add(user);
			}
		}
		
		FileManager.write(newList, filename);
		System.out.println(person.getName() + " deleted");
	}
	public static List<People> listPeople(String filename) throws ClassNotFoundException, IOException {
		List<People> peopleList = new ArrayList<>();
		peopleList = FileManager.read(filename);
		return peopleList;
	}
}
