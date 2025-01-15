import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hi! and welcome to the more advanced Task Manager .\nher you can create yoour own task ,updat it or delet it and more!");
        System.out.print("if you want to create a task write yes/no :");
        try (Scanner mainScanner = new Scanner(System.in)) {
            String response = mainScanner.nextLine();
            Task task = new Task();
            if (response.equalsIgnoreCase("yes")) {
                task.createTask();
            } else {
                System.out.println("good bye!");
            }
        } catch (Exception e) {
            System.out.println("Exception : "+e.getStackTrace());
        }
        
    }
}
