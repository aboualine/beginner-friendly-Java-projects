import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nLibrary Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display Books");
            System.out.println("4. Sort Books");
            System.out.println("5. Search Books");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> library.addBook();
                case 2 -> library.removeBook();
                case 3 -> library.displayBooks();
                case 4 -> {
                    System.out.print("Sort by (title/author/isbn/year): ");
                    String attribute = scanner.nextLine();
                    library.sortBooks(attribute);
                }
                case 5 -> library.searchBooks();
                case 6 -> {
                    System.out.println("Exiting Library Management System. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}