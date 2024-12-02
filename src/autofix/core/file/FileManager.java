package autofix.core.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import autofix.AppConstants;

public class FileManager implements AppConstants{
	private static String filePath = FILE_PATH;
	
    public static <T extends Serializable> void write(List<T> items, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filePath + filename)))) {
            oos.writeObject(items);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> List<T> read(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(filePath + filename)))) {
            return (List<T>) ois.readObject();
        }
    }
}
