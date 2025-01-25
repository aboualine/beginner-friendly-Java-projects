import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private final ArrayList<Book> books = new ArrayList<>();
    private final Scanner write = new Scanner(System.in);
    public void addBook(){
        System.out.print("enter the book's title : ");
        String title = write.nextLine();
        System.out.print("enter the book's author : ");
        String author = write.nextLine();
        System.out.print("enter the book's year of publishing : ");
        int year = write.nextInt();
        if (!title.isEmpty() && !author.isEmpty() && (year > 0) ) {
            books.add(new Book(title ,author ,year));
            System.out.println("Book added successfully!");
        } else {
            System.out.println("Invalid input. Book not added.");
        }
    }
    public void removeBook(){
        System.out.print("enter the book's title : ");
        String title = write.nextLine();
        String bookToRemove = books.stream().filter(b -> b.getTitle().equalsIgnoreCase(title)).findFirst();
        if (!title.isEmpty()) {
            if (books.contains(bookToRemove)) {
                books.remove(bookToRemove.get());
                System.out.println("Book removed successfully!");
            }
            else{
                System.out.println("Book not found!");
            }
        }
        else{
            System.out.println("Invalid input. Book not removed.");
        }
    }
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            books.forEach(System.out::println);
        }
    }
    public void sortBooks(String attribute) {
        switch (attribute.toLowerCase()) {
            case "title":
                GenericSorter.sort(books, GenericSorter.getComparator(Book::getTitle));
                break;
            case "author":
                GenericSorter.sort(books, GenericSorter.getComparator(Book::getAuthor));
                break;
            case "isbn":
                GenericSorter.sort(books, GenericSorter.getComparator(Book::getIsbn));
                break;
            case "year":
                GenericSorter.sort(books, GenericSorter.getComparator(Book::getYear));
                break;
            default:
                System.out.println("Invalid sort attribute.");
                return;
        }
        System.out.println("Books sorted by " + attribute + ":");
        displayBooks();
    }
    public void searchBooks() {
        System.out.print("Search by (title/author): ");
        String criteria = write.nextLine().trim();
        System.out.print("Enter search term: ");
        String searchTerm = write.nextLine().trim();

        books.stream()
             .filter(book -> criteria.equalsIgnoreCase("title") && book.getTitle().equalsIgnoreCase(searchTerm) ||
                             criteria.equalsIgnoreCase("author") && book.getAuthor().equalsIgnoreCase(searchTerm))
             .forEach(System.out::println);
    }
}
