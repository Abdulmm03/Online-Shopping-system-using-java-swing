import java.util.List;

public interface ShoppingManager {
    public abstract void addProduct(Product product);
    public abstract void deleteProduct(String productID);
    public abstract void displayProducts();
    public abstract void saveFile();

    List<Product> LoadFile();
}
