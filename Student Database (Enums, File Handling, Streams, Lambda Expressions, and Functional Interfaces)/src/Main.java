import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String fileName = "data/students.dat";

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Students");
            System.out.println("4. Filter Students");
            System.out.println("5. Sort Students");
            System.out.println("6. Save Students");
            System.out.println("7. Load Students");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    // Add logic to input student details and add to the list
                }
                case 2 -> {
                    // Remove logic based on ID
                }
                case 3 -> {
                    // Search logic by name or ID
                }
                case 4 -> {
                    // Filter logic
                }
                case 5 -> {
                    // Sorting logic
                }
                case 6 -> {
                    try {
                        FileHandler.saveToFile(fileName, studentList);
                        System.out.println("Students saved successfully!");
                    } catch (IOException e) {
                        System.err.println("Error saving to file: " + e.getMessage());
                    }
                }
                case 7 -> {
                    try {
                        studentList = FileHandler.loadFromFile(fileName);
                        System.out.println("Students loaded successfully!");
                    } catch (IOException | ClassNotFoundException e) {
                        System.err.println("Error loading from file: " + e.getMessage());
                    }
                }
                case 8 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
