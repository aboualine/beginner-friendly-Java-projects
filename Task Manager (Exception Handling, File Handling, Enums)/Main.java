import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hi! and welcome to the more advanced Task Manager .\nher you can create yoour own task ,updat it or delet it and more!");
        Scanner mainScanner = new Scanner(System.in);
        Task task = new Task();
        TaskManagement taskmanage = new TaskManagement();
        System.out.print("if you want to create a task write yes/no :");
        String response = mainScanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            task.createTask();
        } else {
            System.out.println("No task created.");
        }
        System.out.print("if you want to update a task write yes/no :");
        String responseupdate = mainScanner.nextLine();
        if (responseupdate.equalsIgnoreCase("yes")) {
            taskmanage.updateTask();
        } else {
            System.out.println("No task updated.");
        }
        System.out.print("if you want to view a task write yes/no :");
        String responseview = mainScanner.nextLine();
        if (responseview.equalsIgnoreCase("yes")) {
            taskmanage.viewTasks();
        } else {
            System.out.println("No tasks viewed.");
        }
        System.out.print("if you want to delete a task write yes/no :");
        String responsedelete = mainScanner.nextLine();
        if (responsedelete.equalsIgnoreCase("yes")) {
            taskmanage.deleteTask();
        } else {
            System.out.println("No task deleted.");
        }
        mainScanner.close();
    }
}
