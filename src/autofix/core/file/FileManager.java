package autofix.core.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import autofix.core.people.*;
import autofix.Generic;
public class FileManager implements FileConstants {
	private static String filePath;
	
    public static <T extends Serializable> void write(List<T> items, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("src/autofix/data/" + filename)))) {
            oos.writeObject(items);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> List<T> read(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("src/autofix/data/" + filename)))) {
            return (List<T>) ois.readObject();
        }
    }

}
