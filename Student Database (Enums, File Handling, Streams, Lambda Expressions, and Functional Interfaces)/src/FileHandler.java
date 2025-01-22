import java.io.*;
import java.util.List;
public class FileHandler {
    public static void saveToFile(String filename, List<Student> students) {
        File file = new File(filename);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs(); 
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(students);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    public static List<Student> loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.err.println("Error: File does not exist.");
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            System.out.println("Data loaded successfully!");
            return (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
            return null;
        }
    }
}
