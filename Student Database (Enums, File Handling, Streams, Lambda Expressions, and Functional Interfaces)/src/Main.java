import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String fileName = "../data/students.dat";

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Students");
            System.out.println("4. Filter Students");
            System.out.println("5. Sort Students");
            System.out.println("6. Save Students");
            System.out.println("7. Load Students");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            // Ensure we handle invalid inputs gracefully
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                scanner.next(); // Clear invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student status (ENROLLED, GRADUATED, DROPPED_OUT): ");
                    String statusStr = scanner.nextLine();
                    try {
                        StudentStatus status = StudentStatus.valueOf(statusStr.toUpperCase());
                        System.out.print("Enter grades (comma-separated): ");
                        String gradesInput = scanner.nextLine();
                        List<Double> grades = Arrays.stream(gradesInput.split(","))
                                                    .map(Double::parseDouble)
                                                    .toList();
                        Student student = new Student(name, status, grades);
                        studentList.add(student);
                        System.out.println("Student added successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid status or grades format. Please try again.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter student ID to remove: ");
                    int id = scanner.nextInt();
                    boolean removed = studentList.removeIf(student -> student.getId() == id);
                    if (removed) {
                        System.out.println("Student removed successfully!");
                    } else {
                        System.out.println("Student with ID " + id + " not found.");
                    }
                }
                case 3 -> {
                    System.out.print("Search by NAME or ID (Name/Id): ");
                    String choiceSearch = scanner.nextLine();
                    if (choiceSearch.equalsIgnoreCase("name")) {
                        System.out.print("Enter name: ");
                        String searchName = scanner.nextLine();
                        studentList.stream()
                                   .filter(student -> student.getName().equalsIgnoreCase(searchName))
                                   .forEach(System.out::println);
                    } else if (choiceSearch.equalsIgnoreCase("id")) {
                        System.out.print("Enter ID: ");
                        int searchId = scanner.nextInt();
                        studentList.stream()
                                   .filter(student -> student.getId() == searchId)
                                   .forEach(System.out::println);
                    } else {
                        System.out.println("Invalid search choice.");
                    }
                }
                case 4 -> {
                    System.out.print("Filter by ACTIVE or HIGH_GRADES (Active/High): ");
                    String filterChoice = scanner.nextLine();
                    StudentFilter filter;
                    if (filterChoice.equalsIgnoreCase("active")) {
                        filter = student -> student.getStatus() == StudentStatus.ENROLLED;
                    } else if (filterChoice.equalsIgnoreCase("high")) {
                        filter = student -> student.calculateAverageGrade() >= 80;
                    } else {
                        System.out.println("Invalid filter choice.");
                        continue;
                    }
                    studentList.stream()
                               .filter(filter::filtre)
                               .forEach(System.out::println);
                }
                case 5 -> {
                    studentList.stream()
                               .sorted(Comparator.comparingDouble(Student::calculateAverageGrade).reversed())
                               .forEach(student -> {
                                   System.out.println(student);
                                   System.out.println("Average Grade: " + student.calculateAverageGrade());
                               });
                }
                case 6 -> {
                    try {
                        FileHandler.saveToFile(fileName, studentList);
                    } catch (Exception e) {
                        System.err.println("Error saving students: " + e.getMessage());
                    }
                }
                case 7 -> {
                    try {
                        List<Student> loadedStudents = FileHandler.loadFromFile(fileName);
                        if (loadedStudents != null) {
                            studentList = loadedStudents;
                        }
                    } catch (Exception e) {
                        System.err.println("Error loading students: " + e.getMessage());
                    }
                }
                case 8 -> {
                    System.out.println("Exiting...");
                    return; // End program
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
