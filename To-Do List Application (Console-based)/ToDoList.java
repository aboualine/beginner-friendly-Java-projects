import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ToDoList {
    private ArrayList<String> tasks;
    public ArrayList<String> collectionoperation() throws IOException{
        System.out.println("chose the opration you want to apply on your To-Do List (Add, view, and delete tasks) : ");
        Scanner sc = new Scanner(System.in);
        String operation = sc.nextLine();
        boolean keepgoing = true;
        tasks = new ArrayList<>();
        while (keepgoing) {
            switch (operation) {
                case "Add":
                    System.out.print("write here the task you want to add to your To-Do List : ");
                    String tasktoadd = sc.nextLine();
                    tasks.add(tasktoadd);
                    break;
                case "View":
                    System.out.print("your To-Do List : ");
                    System.out.print(tasks);
                    break;
                case "Delete":
                    System.out.print("chouse the task you want to delete : ");
                    String tasktodelete = sc.nextLine();
                    boolean exist = false;
                    for(int i = 0 ; i < tasks.size() ; i++){
                        if (tasktodelete.equalsIgnoreCase(tasks.get(i))) {
                            tasks.remove(i);
                            exist = true;
                            break;
                        }
                        else{
                            System.out.println("this task isn't exist in the To-Do List ! please try again .");
                            System.out.print("chouse the task you want to delete : ");
                            tasktodelete = sc.nextLine();
                        }
                    }
                    System.out.print(tasks);
                    break;    
                default:
                    System.out.println("you have to chouse one of the operations (Add, view, and delete tasks) ! please try again. ");
                    System.out.println("chose the opration you want to apply on your To-Do List (Add, view, and delete tasks) : ");
                    operation = sc.nextLine();
                    break;
            }
            System.out.print("if you want continue in adding or deleting or view your To-Do List write \"yes\" : ");
            String reponse = sc.nextLine();
            if (reponse.equalsIgnoreCase("yes")) {
                keepgoing = true;
                System.out.println("chose the opration you want to apply on your To-Do List (Add, view, and delete tasks) : ");
                operation = sc.nextLine();
            } else {
                keepgoing = false;
                break;
            }
        }

        System.out.println("this is your To-Do List : ");
        for (int i = 0 ; i < tasks.size() ; i++) {
            System.out.println(i+" :     "+tasks.get(i));
        }
        System.out.print("mark the task you have completed by writing it's number : ");
        int indix = sc.nextInt();
        for (int i = 0 ; i < tasks.size() ; i++) {
            if (indix == i) {
                System.out.println(indix+" :     "+tasks.get(indix)+"     [COMPLETED] ");
                break;
            }
            else{
                System.out.println("no task has been marked as completed.");
            }
        }
        File file = new File("tasks.txt");
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("your file \"tasks.txt\" has been created now. ");
        }
        PrintWriter write = new PrintWriter(new FileWriter(file));
        for (int i = 0 ; i < tasks.size() ; i++) {
            write.println(tasks.get(i));
        }
        write.close();
        sc.close();
        return tasks;
    }

}
