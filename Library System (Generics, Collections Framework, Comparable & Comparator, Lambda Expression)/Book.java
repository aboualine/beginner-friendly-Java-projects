public class Book {
        private String title;
        private String author;
        private static int isbnCounter = 0;
        private int isbn;
        private int year;
        public Book(String title,String author,int year){
            this.isbn=++isbnCounter;
            this.title=title;
            this.author=author;
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
}
