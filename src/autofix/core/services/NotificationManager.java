package autofix.core.services;

import java.io.IOException;

import autofix.AppConstants;
import autofix.core.file.FileManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class NotificationManager implements AppConstants {
	private static String filename;
	
	public static String prepareNotification(String notification) {
		// Get time
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        
		return formattedDateTime + " -> " + notification;
	}
	
	public static void addNotification(String callerUserName, String notification) {
		List<String> notifications = new ArrayList<>();
		
		notification = prepareNotification(notification);
	
		filename = callerUserName + NOTIFICATION_EXT;
		
		try {
			notifications = FileManager.read(filename);
			notifications.add(notification);
			try {
				FileManager.write(notifications, filename);
			} catch (IOException e1) {
				System.out.println("Notification write error");
				e1.printStackTrace();
			}
		} catch(IOException e) {
			if(notifications.isEmpty()) {
				notifications.add(notification);
				try {
					FileManager.write(notifications, filename);
				} catch (IOException e1) {
					e1.printStackTrace();
				}				
				System.out.println(notification + " Added");
				return;
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deleteNotification(String callerUserName, String notification) {
		
		filename = callerUserName + NOTIFICATION_EXT;
		List<String> notifications = new ArrayList<>();
		List<String> newList = new ArrayList<>();
		try {
			notifications = FileManager.read(filename);
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Notifcation read error");
			e.printStackTrace();
		}
		for(String item : notifications) {
			if(!item.equals(notification)) {
				newList.add(item);
			}
		}
		
		try {
			FileManager.write(newList, filename);
			System.out.println(notification + " deleted");
		} catch (IOException e) {
			System.out.println("Notification Write Erorr during delete");
			e.printStackTrace();
		}
		
	}
	
	public static List<String> getNotifications(String callerUserName) {
		List<String> notifications = new ArrayList<>();
		try {
			notifications = FileManager.read(filename);	
			return notifications;
		} catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return notifications;
		}
	}
}
