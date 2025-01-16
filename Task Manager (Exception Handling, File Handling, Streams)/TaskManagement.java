import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TaskManagement {
    File file = new File("tasks.txt");
    public void updateTask(){
        Scanner userwrite = new Scanner(System.in);
        System.out.print("what task you want to modify in (task name): ");
        String name = userwrite.nextLine();
        if (file.length() == 0) {
            System.out.println("The file is empty.");
            userwrite.close();
            return;
        }
        boolean taskFound = false;
        try (Scanner fileRead = new Scanner(file)) {
            StringBuilder updatedContent = new StringBuilder();
            while (fileRead.hasNext()) {
                String lineTask = fileRead.nextLine();
                String[] taskParts = lineTask.split(":");
                if (taskParts.length != 3) {
                    System.out.println("Invalid task format in file: " + lineTask);
                    updatedContent.append(lineTask).append("\n");
                    continue;
                }
                String nameFounded = taskParts[0];
                if (nameFounded.equalsIgnoreCase(name)) {
                    taskFound = true;
                    System.out.print("enter the new name of the task : ");
                    String newName = userwrite.nextLine();
                    System.out.print("enter the new date of the task : ");
                    int newDate = userwrite.nextInt();
                    System.out.println("choose the new status (1-5) : ");
                    Status statusTask;
                    for (int i = 0; i < Status.values().length; i++) {
                        System.out.println((i + 1) + " - " + Status.values()[i]);
                    }
                    int statusChoice = userwrite.nextInt();
                    if (statusChoice >= 1 && statusChoice <= 5) {
                        statusTask = Status.values()[statusChoice - 1];
                    } else {
                        System.out.println("Invalid choice, defaulting to Pending.");
                        statusTask = Status.Pending;
                    }
                    String updatedTask = newName +":"+newDate+":"+statusTask;
                    updatedContent.append(updatedTask).append("\n");
                } else {
                    updatedContent.append(lineTask).append("\n");
                }
            }  
            if(!taskFound){
                System.out.print("this task is not exist in the file ,do you want to add it (yes/no): ");
                String response = userwrite.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    Task t = new Task();
                    t.createTask();
                } else {
                    System.out.println("good bye!");
                }
            }
            try (PrintWriter fileWrite = new PrintWriter(new FileWriter(file))) {
                fileWrite.write(updatedContent.toString());
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
        System.out.println("we didn't found any file !");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in the file: " + e.getMessage());
        }
        userwrite.close();
    }
    public void viewTasks(){
        System.out.println("all the tasks are : ");
        File file = new File("tasks.txt");
        try (Scanner readFromFile = new Scanner(file)) {
            if (file.length() == 0) {
            System.out.println("the file is empty!");
            return;
            }
            while (readFromFile.hasNext()) {
                String tasksLine = readFromFile.nextLine();
                System.out.println(tasksLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("no file have founded!");
        }
    }
    public void deleteTask(){
        Scanner mainScanner = new Scanner(System.in);
        File file = new File("tasks.txt");
        if (file.length() == 0) {
                System.out.println("The file is empty.");
                mainScanner.close();
                return;
        }
        System.out.print("What task do you want to delete (task name): ");
        String name = mainScanner.nextLine();
        boolean taskFound = false;
        StringBuilder updatedContent = new StringBuilder();
        try (Scanner fileRead = new Scanner(file)) {
            while (fileRead.hasNext()) {
                String lineTask = fileRead.nextLine();
                String[] taskParts = lineTask.split(":");
                if (taskParts.length != 3) {
                    System.out.println("Invalid task format in file: " + lineTask);
                    updatedContent.append(lineTask).append("\n");
                    continue;
                }
                String nameFounded = taskParts[0];
                if (nameFounded.equalsIgnoreCase(name)) {
                    taskFound = true;
                    System.out.println("Task deleted: " + lineTask);
                } else {
                    updatedContent.append(lineTask).append("\n");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            mainScanner.close();
            return;
        }
        if(!taskFound){
            System.out.print("this task is not exist in the file ,do you want to add it (yes/no): ");
            String response = mainScanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                Task t = new Task();
                t.createTask();
            } else {
                System.out.println("good bye!");
            }
        }
        try (PrintWriter fileWrite = new PrintWriter(new FileWriter(file))) {
            fileWrite.write(updatedContent.toString());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        mainScanner.close();
    }
}
