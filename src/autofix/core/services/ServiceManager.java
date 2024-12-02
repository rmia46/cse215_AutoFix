package autofix.core.services;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;

import autofix.AppConstants;
import autofix.core.file.FileManager;

public class ServiceManager implements AppConstants {
	private static String filename = SERVICE_FILE;
	private static String userFileName;
	
	public static boolean exists(Service service, List<Service> serviceList) {
		for(Service find : serviceList) {
			if(find.getVehicle().getLicensePlate().equals(service.getVehicle().getLicensePlate()) && !find.getStatus().equals("Serviced")) {
				return true;
			}
		}
		return false;
	}
	public static boolean isCustomer(String userName) {
		if(userName == null || userName.isEmpty()) {
			System.out.println("Invalid username");
			return false;
		}
		if(Character.isLetter(userName.charAt(userName.length() - 1))) {
			return true;
		}
		return false;
	}
	public static void addService(Service service) throws IOException {
		
		List<Service> serviceList = new ArrayList<>();
		List<Service> userServiceList = new ArrayList<>();
		boolean checkIfCustomer = isCustomer(service.getCallerUserName());
		if(checkIfCustomer) {
			userFileName = service.getCallerUserName() + DATA_EXT;
		}
		try {
			serviceList = FileManager.read(filename);
			if(checkIfCustomer) {
				userServiceList = FileManager.read(userFileName);
			}
		} catch(IOException e) {
			if(serviceList.isEmpty()) {
				serviceList.add(service);
				if(checkIfCustomer) {
					userServiceList.add(service);
				}
				FileManager.write(serviceList, filename);
				FileManager.write(userServiceList, userFileName);
				
				System.out.println(service.getVehicle().getBrand() + " with " + service.getVehicle().getLicensePlate() + " Added");
				return;
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(exists(service, serviceList)) {
			System.out.println(service.getVehicle().getBrand() + " is already on database.");
		} else {
			serviceList.add(service);
			FileManager.write(serviceList, filename);
			System.out.println(service.getVehicle().getBrand() + " Added");
		}
		if(exists(service, userServiceList)) {
			System.out.println(service.getVehicle().getBrand() + " is already on database.");
		}
		else {
			userServiceList.add(service);
			FileManager.write(userServiceList, userFileName);
			System.out.println(service.getVehicle().getBrand() + " Added");
		}
	}	
	
	public static void setServiceStatus(Service service, String status, String serviceDescription, String customerUserName) throws ClassNotFoundException {
		List<Service> serviceList = new ArrayList<>();
		List<Service> userServiceList = new ArrayList<>();
		userFileName = customerUserName + DATA_EXT;
		if(!status.equals("Pending") && !status.equals("Being serviced") && !status.equals("Declined") && !status.equals("Serviced")) {
			System.out.println("Invalid status");
			return;
		}
		try {
			serviceList = FileManager.read(SERVICE_FILE);
			userServiceList = FileManager.read(userFileName);
			for(Service find : serviceList) {
				if(find.getVehicle().getLicensePlate().equals(service.getVehicle().getLicensePlate()) && !find.getStatus().equals("Serviced")) {		
					find.setStatus(status);
					find.setServiceDescription(serviceDescription);
					FileManager.write(serviceList, filename);	
					System.out.println("status updated in system");
					break; 
				} else {
					System.out.println("cant find vehicle");
				}
			}
			for(Service find : userServiceList) {
				System.out.println(find.toString());
				if(find.getVehicle().getLicensePlate().equals(service.getVehicle().getLicensePlate()) && !find.getStatus().equals("Serviced")) {		
					find.setStatus(status);
					find.setServiceDescription(serviceDescription);
					FileManager.write(userServiceList, userFileName);	
					System.out.println("status updated in user");
					return; 
				} else {
					System.out.println("cant find vehicle");
				}
			}
		} catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void setServiceStatus(Service service, String status, String serviceDescription, String customerUserName, double cost, double discountFactor) throws ClassNotFoundException {
		List<Service> serviceList = new ArrayList<>();
		List<Service> userServiceList = new ArrayList<>();
		userFileName = customerUserName + DATA_EXT;
		if(!status.equals("Pending") && !status.equals("Being serviced") && !status.equals("Declined") && !status.equals("Serviced")) {
			System.out.println("Invalid status");
			return;
		}
		try {
			serviceList = FileManager.read(SERVICE_FILE);
			userServiceList = FileManager.read(userFileName);
			for(Service find : serviceList) {
				if(find.getVehicle().getLicensePlate().equals(service.getVehicle().getLicensePlate()) && !find.getStatus().equals("Serviced")) {		
					find.setStatus(status);
					find.setServiceDescription(serviceDescription);
					find.setCost(cost, discountFactor);
					FileManager.write(serviceList, filename);	
					System.out.println("status updated in system");
					break; 
				} else {
					System.out.println("cant find vehicle");
				}
			}
			for(Service find : userServiceList) {
				System.out.println(find.toString());
				if(find.getVehicle().getLicensePlate().equals(service.getVehicle().getLicensePlate()) && !find.getStatus().equals("Serviced")) {		
					find.setStatus(status);
					find.setServiceDescription(serviceDescription);
					find.setCost(cost, discountFactor);
					FileManager.write(userServiceList, userFileName);	
					System.out.println("status updated in user");
					return; 
				} else {
					System.out.println("cant find vehicle");
				}
			}
		} catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Service> getHistory(String callerUserName) throws IOException, ClassNotFoundException {
		String file = "";
		if(isCustomer(callerUserName)) {
			System.out.println("Customer");
			System.out.println(callerUserName);
			file = callerUserName + DATA_EXT;
		}
		else {
			file = filename;
		}
		
		List<Service> serviceList = new ArrayList<>();
		
		try {
			serviceList = FileManager.read(file);	
			return serviceList;
		} catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return serviceList;
		}
	}
	
	public static void deleteServiceRecord(Service service) {
		String file = "";
		List<Service> serviceList = new ArrayList<>();
		List<Service> userServiceList = new ArrayList<>();
		List<Service> newList = new ArrayList<>();
		List<Service> newList2 = new ArrayList<>();
		
		if(isCustomer(service.getCallerUserName())) {
			file = service.getCallerUserName() + DATA_EXT;
			try {
				userServiceList = FileManager.read(file);
				if(!userServiceList.isEmpty()) {
					for(Service record : userServiceList) {
						if(!record.getVehicle().getLicensePlate().equals(service.getVehicle().getLicensePlate()) && !record.getTitle().equals(service.getTitle())) {
							newList.add(record);
						}
					}
					try {
						FileManager.write(newList, file);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println(service.getTitle() + " deleted");
				}	
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		try {
			serviceList = FileManager.read(filename);
			if(!serviceList.isEmpty()) {
				for(Service record : serviceList) {
					if(!record.getVehicle().getLicensePlate().equals(service.getVehicle().getLicensePlate()) && !record.getTitle().equals(service.getTitle())) {
						newList2.add(record);
					}
				}
				try {
					FileManager.write(newList2, filename);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println(service.getTitle() + " deleted");
			}	
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}		
	}
}
