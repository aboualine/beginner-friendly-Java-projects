import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    ArrayList<Book> books = new ArrayList<>();
    Scanner write = new Scanner(System.in);
    public void addBook(){
        System.out.print("enter the book's title : ");
        String title = write.nextLine();
        System.out.print("enter the book's author : ");
        String author = write.nextLine();
        System.out.print("enter the book's year of publishing : ");
        int year = write.nextInt();
        if (!title.equals(null) && !author.equals(null) && year != 0 ) {
            Book b1 = new Book();
            b1.setTitle(title);
            b1.setAuthor(author);
            b1.setYear(year);
            books.add(b1);
        } else {
            System.out.println("an attribut is null !");
        }
    }
}
