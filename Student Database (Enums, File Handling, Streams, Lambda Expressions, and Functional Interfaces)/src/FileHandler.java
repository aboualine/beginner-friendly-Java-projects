import java.io.*;
import java.util.List;
public class FileHandler {
    public static void saveToFile(String filename, List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    public static List<Student> loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            System.out.println("Data loaded successfully!");
            return (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
            return null;
        }
    }
}
