import java.util.List;
import java.util.Scanner;

public class Book {
        private String title;
        private String author;
        private static int isbnCounter = 0;
        private int isbn;
        private int year;
        public Book(String title,String author,int isbn,int year){
            this.title=title;
            this.author=author;
            isbnCounter++;
            this.isbn=isbnCounter;
            this.year=year;
        }
        public int getIsbn(){
            return isbn;
        }
        public void setTitle(String title){
            this.title=title;
        }
        public String getTitle(){
            return title;
        }
        public void setAuthor(String author){
            this.author=author;
        }
        public String getAuthor(){
            return author;
        }
        public void setYear(int year){
            this.year=year;
        }
        public int getYear(){
            return year;
        }
        @Override
        public String toString(){
            return "Book {isbn : "+isbn+",title : "+title+",author : "+author+",year : "+year+"}";
        }
        public static void sortBooks(List<Book> books, String attribute){
            try (Scanner write = new Scanner(System.in)) {
                System.out.println("enter what you want to sort books based on (title/author/isbn/year) : ");
                String response = write.nextLine();
                switch (response.toLowerCase()) {
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
                        throw new IllegalArgumentException("Invalid attribute: " + attribute);
                    break;
            }
        } catch (Exception e) {
            System.out.println("exception : "+e.getMessage());
        }


    }
}
