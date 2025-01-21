import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
public class Student {
    private static int idCounter = 0;
    private int id;
    private String name;
    private StudentStatus status;
    private List<Double> grades;
    private List<Student> students;
    public Student(String name, StudentStatus status, List<Double> grades) {
        idCounter++;
        this.id = idCounter;
        this.name = !name.isEmpty() ? name : "Unknown";
        this.grades = grades != null && grades.stream().allMatch(n -> n >= 0 && n <= 100) ? grades : new ArrayList<>();
        this.status = status != null ? status : StudentStatus.ENROLLED;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }
    public StudentStatus getStatus() {
        return status;
    }
    public void setStatus(StudentStatus status) {
        if (status != null) {
            this.status = status;
        }
    }
    public List<Double> getGrades() {
        return grades;
    }
    public void setGrades(List<Double> grades) {
        if (grades != null && grades.stream().allMatch(n -> n >= 0 && n <= 100)) {
            this.grades = grades;
        }
    }
    @Override
    public String toString() {
        return "Student ID: " + id + "\nName: " + name + "\nStatus: " + status + "\nGrades: " + grades;
    }
    public double calculateAverageGrade() {
        return grades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
    public double getHighestGrade() {
        return grades.stream().max(Comparator.naturalOrder()).orElse(0.0);
    }
    public List<Student> studentService() {
        if (students == null) {
            students = new ArrayList<>();
        }
        return students;
    }
    public void addStudent(Student student) {
        studentService().add(student);
    }
    public void searchForStudents() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search by NAME or ID (Name/Id): ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("name")) {
            System.out.print("Enter the name: ");
            String searchName = scanner.nextLine();
            studentService().stream()
                .filter(student -> student.getName().equalsIgnoreCase(searchName))
                .forEach(student -> {
                    System.out.println(student);
                    System.out.println("Average Grade: " + student.calculateAverageGrade());
                });
        } else if (choice.equalsIgnoreCase("id")) {
            System.out.print("Enter the ID: ");
            int searchId = scanner.nextInt();
            studentService().stream()
                .filter(student -> student.getId() == searchId)
                .forEach(student -> {
                    System.out.println(student);
                    System.out.println("Average Grade: " + student.calculateAverageGrade());
                    System.out.println("Highest Grade: " + student.getHighestGrade());
                });
        } else {
            System.out.println("Invalid choice. Please select 'Name' or 'Id'.");
        }
        scanner.close();
    }
    public void sortStudents() {
        studentService().stream()
            .sorted(Comparator.comparingDouble(Student::calculateAverageGrade).reversed())
            .forEach(student -> {
                System.out.println("Name: " + student.getName());
                System.out.println("Average Grade: " + student.calculateAverageGrade());
            });
    }
}