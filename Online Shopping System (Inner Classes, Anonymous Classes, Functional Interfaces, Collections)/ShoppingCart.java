import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class ShoppingCart {
    private List<Product> products = new ArrayList<>();
    Scanner write = new Scanner(System.in);
    public void addProduct() {
        System.out.print("Enter the product's name : ");
        String name = write.nextLine();
        System.out.print("Enter the product's price : ");
        double price = write.nextDouble();
        System.out.print("Enter the product's quantity : ");
        int quantity = write.nextInt();
        System.out.print("Enter the product's category : ");
        String category = write.nextLine();
        products.add(new Product(name, price, quantity, category));
    }
    public void displayProducts(){
        if(products.isEmpty()){
            System.out.println("there is no products for now!");
        }
        else{
            products.forEach(System.out::println);
        }
    }
    @SuppressWarnings("unlikely-arg-type")
    public void removeProduct() {
        System.out.print("Enter the product's name you want to delet :");
        String name = write.nextLine();
        if (name.isEmpty()) {
            System.out.println("you need to write somthing !");
            write.nextLine();
        }
        else{
            products.remove(products.stream()
                                            .filter(n -> n.getName().equals(name))
                                            .findFirst()
                            );
        }
    }
    public void printCart() {
        products.forEach(System.out::println);
    }
    public double calculateTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }   
}
