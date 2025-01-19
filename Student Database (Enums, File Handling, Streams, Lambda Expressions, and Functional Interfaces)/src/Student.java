import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
public class Student {
    private static int idCounter = 0;
    private int id;
    private String name;
    private StudentStatus status;
    private List<Double> grades;
    public int getId(){
        return id;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setStatus(StudentStatus status){
        this.status=status;
    }
    public StudentStatus getStatus(){
        return status;
    }
    public Student(int id , String name , StudentStatus status ,List<Double> grades){
        idCounter++;
        this.id=idCounter;
        if (!name.isEmpty()) {
            this.name=name;
        }
        if (grades.stream().allMatch(n -> n != 0 && n >= 0 && n <100)) {
            this.grades=grades;
        } else {
            System.out.println("the values are not valid!");
        }
        if (status != null) {
            this.status=status;
        }
    }
    @Override
    public String toString(){
        return "Student id : "+id+"\nStudent name : "+name+"\nStudent grades : "+grades+"\nStudent statu : "+status;
    }
    public void calculateAverageGrade(){
        double avg = grades.stream()
                                   .mapToDouble(Double::doubleValue)
                                   .average()
                                   .orElse(0.0);
        System.out.println("the average grade is : "+avg);
    }
    public void highGrade(){
        double hightG = grades.stream()
                              .max(Comparator.naturalOrder())
                              .orElseThrow();
        System.out.println("the maximum grade is : "+hightG);
    }
    private List<Student> students;
    public List<Student> studentService() {
        this.students = new ArrayList<>();
        return students;
    }
    public void addStudent(Student student) {
        students.add(student);
    }
    public void SearchingForStudents(){
        try (Scanner write = new Scanner(System.in)) {
            System.out.print("you can search by NAME or ID ,what do you prefere (Name/Id) : ");
            String response = write.nextLine();
            if (response.equalsIgnoreCase("name")) {
                System.out.print("Enter the name : ");
                name = write.nextLine();
                students.stream()
                        .filter(n -> n.getName().equalsIgnoreCase(name))
                        .forEach(s -> {
                            System.out.println(s.toString());
                            s.calculateAverageGrade();
                        });
            }
            else if (response.equalsIgnoreCase("id")) {
                System.out.print("Enter the id : ");
                int id = Integer.parseInt(write.nextLine());
                students.stream()
                        .filter(n -> n.getId() == id)
                        .forEach(s -> {
                            System.out.println(s.toString());
                            s.calculateAverageGrade();
                            s.highGrade();
                        });
            }
            else {
            System.out.println("Invalid choice. Please select 'Name' or 'Id'.");
            }
        } catch (Exception e) {
            System.out.println("Exception founded : "+e.getMessage());
        }
    }
    public void sortStudents(){
        students.stream()
                .sorted((s1, s2) -> Double.compare(s1.calculateAverageGrade(), s2.calculateAverageGrade()))
                .forEach(n -> {n.getName();
                               n.calculateAverageGrade();
                });
    }
    
}