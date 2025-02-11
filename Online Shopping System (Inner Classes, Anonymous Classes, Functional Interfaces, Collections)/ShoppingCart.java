import java.util.List;
import java.util.ArrayList;
public class ShoppingCart {
    private List<Product> products = new ArrayList<>();
    public void addProduct(Product product) {
        products.add(product);
    }
    public void removeProduct(Product product) {
        products.remove(product);
    }
    public void printCart() {
        products.forEach(System.out::println);
    }
    public double calculateTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }   
}
