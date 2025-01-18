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
            write.nextLine();
            String name = students.stream().filter(n -> n.equals(write.nextLine())).toString();
            int id = students.stream().mapToInt(n -> n.equals(write.nextLine()));
            if (!write.nextLine().isEmpty() && (write.nextLine().equalsIgnoreCase(name) || Integer.valueOf(write.nextLine()) == id)) {
                toString();
                calculateAverageGrade();
            }
        } catch (Exception e) {
            System.out.println("Exception founded : "+e.getMessage());
        }
    }
    
}