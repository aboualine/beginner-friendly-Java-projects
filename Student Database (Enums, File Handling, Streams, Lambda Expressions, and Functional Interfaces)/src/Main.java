import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        List<Student> studentList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String fileName = "data/students.dat";
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
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter student name: ");
                    String name = scanner.next();
                    System.out.print("Enter student status (ENROLLED, GRADUATED, DROPPED_OUT): ");
                    String statusStr = scanner.next();
                    StudentStatus status = StudentStatus.valueOf(statusStr.toUpperCase());
                    System.out.print("Enter grades (comma-separated): ");
                    scanner.nextLine(); 
                    String gradesInput = scanner.nextLine();
                    List<Double> grades = Arrays.stream(gradesInput.split(","))
                                                .map(Double::parseDouble)
                                                .collect(Collectors.toList());
                    Student student = new Student(name, status, grades);
                    studentList.add(student);
                    System.out.println("Student added successfully!");
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
                    scanner.nextLine();
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
                    scanner.nextLine();
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
                    FileHandler.saveToFile(fileName, studentList);
                    System.out.println("Students saved successfully!");
                }
                case 7 -> {
                    studentList = FileHandler.loadFromFile(fileName);
                    System.out.println("Students loaded successfully!");
                }
                case 8 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            scanner.close();
        }
    }
}
