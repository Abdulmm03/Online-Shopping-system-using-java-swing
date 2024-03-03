import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

public class ShoppingCart extends JFrame {

        public  ArrayList<Product> shoppingCart;
        public  ShoppingCart(){
            this.shoppingCart = new ArrayList<>();

        }
        public ShoppingCart(ArrayList<Product> shoppingCart){
            this.shoppingCart = new ArrayList<>();
        }
        public  void addProduct(Product product){
            shoppingCart.add(product);
        }
        public void deleteProduct(Product product){
            shoppingCart.remove(product);
        }
        public ArrayList<Product> getProducts(){
            return new ArrayList<>(shoppingCart);

        }
        public double calculateTotalCost() {
            double totalCost = 0.0;
            for (Product product : shoppingCart) {
                totalCost += product.getPrice();
            }
        return totalCost;
        }
}
