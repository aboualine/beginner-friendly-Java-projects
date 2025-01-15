import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Task{
    public int dateTask;
    private Status statusTask;
    public void createTask(){
        try (Scanner write = new Scanner(System.in)) {
            System.out.print("type here the name of the task : ");
            String taskName = write.nextLine();
            System.out.print("type here the date to finish the task : ");
            this.dateTask = write.nextInt();
            if (this.dateTask <= 0) {
                throw new ExceptionDateisNegative("Date cannot be negative or zero.");
            }
            System.out.println("choose between the following status (1-2-3-4-5) : ");
            int counter = 0;
            for(Status task : Status.values()){
                counter++;
                System.out.println(counter+" - "+task);
            }
            int  numberOfTask = write.nextInt();
            switch (numberOfTask) {
                case 1:
                    this.statusTask = Status.Pending;
                    break;
                case 2:
                    this.statusTask = Status.InProgress;
                    break;
                case 3:
                    this.statusTask = Status.Completed;
                    break;
                case 4:
                    this.statusTask = Status.On_Hold;
                    break;
                case 5:
                    this.statusTask = Status.Cancelled;
                    break;
                default:
                    System.out.println("your choice is out of band! so by default the statu is Pending.");
                    this.statusTask = Status.Pending;
                    break;
            }
            String fullTask = taskName +":"+this.dateTask+":"+this.statusTask;
            File file = new File("tasks.txt");
            try {
                if (!file.exists()) {
                    file.createNewFile();
                    System.out.println("the file "+file+" has created succesfully!");
                }
            } catch(IOException e){
                System.out.println("file not found!");
            }
            try (PrintWriter fileWrite = new PrintWriter(new FileWriter(file , true))) {
                fileWrite.println(fullTask);
                System.out.println("Task saved successfully!");
            } catch (IOException e) {
                System.out.println("Exception : "+e.getMessage());
            } 
        } catch (NoSuchElementException e) {
            System.err.println("No more input available. " + e.getMessage());
        } catch (ExceptionDateisNegative e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }
}
enum Status{
    Pending,
    InProgress,
    Completed,
    On_Hold,
    Cancelled
}