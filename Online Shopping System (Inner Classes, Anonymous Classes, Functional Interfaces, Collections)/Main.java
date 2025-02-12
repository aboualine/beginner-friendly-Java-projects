import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hi! and welcome to my online shoping system :)");
        Scanner write = new Scanner(System.in);
        ShoppingCart shope = new ShoppingCart();
        while (true) {
            System.out.println("-----Online Shoping System-----");
            System.out.println("1- Display Products.");
            System.out.println("2- Add Product.");
            System.out.println("3- Remove Product.");
            System.out.println("4- Apply Discounts.");
            System.out.println("5- Display Products.");
            System.out.println("6- Choose Payments.");
            System.out.println("7- Checkout.");
            System.out.print("Enter your choice");
            if(!write.hasNext()){
                System.out.println("enter a number between 1 and 7 !");
                write.next();
                continue;
            }
            int choice = write.nextInt();
            switch (choice) {
                case 1 -> shope.displayProducts();
                case 2 -> shope.addProduct();
                case 3 -> shope.removeProduct();
                default:
                    break;
            }
        }
    }
}
