import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
public class ShoppingCart {
    private List<Product> products = new ArrayList<>();
    Scanner write = new Scanner(System.in);
    File file = new File("products.txt");
    public void addProduct() throws IOException {
        System.out.print("Enter the product's name : ");
        String name = write.nextLine();
        System.out.print("Enter the product's price : ");
        double price = write.nextDouble();
        System.out.print("Enter the product's quantity : ");
        int quantity = write.nextInt();
        write.nextLine();
        System.out.print("Enter the product's category : ");
        String category = write.nextLine();
        Product product = new Product(name, price, quantity, category);
        products.add(product);
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("the file \"products.txt\" created succesfuly!");
        }
        try (PrintWriter fileWriter = new PrintWriter(new FileWriter(file,true))) {
            fileWriter.println(name + "," + price + "," + quantity + "," + category);
        }
        System.out.println("Product added successfully!");
    }
    public void displayProducts(){
        if(products.isEmpty()){
            System.out.println("there is no products for now!");
        }
        else{
            products.forEach(System.out::println);
        }
    }
    public void loadProductsFromFile() throws IOException {
        if (!file.exists()) return;
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 4) {
                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                String category = parts[3];
                products.add(new Product(name, price, quantity, category));
            }
        }
        fileReader.close();
        System.out.println("Products loaded from file.");
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
