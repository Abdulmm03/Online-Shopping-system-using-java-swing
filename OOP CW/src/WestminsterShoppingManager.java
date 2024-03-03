import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.ArrayList;


public class WestminsterShoppingManager implements ShoppingManager {
        static final List<Product> products = new ArrayList<>();
        public final static int MAX_PRODUCTS = 50;
        Scanner scanner = new Scanner(System.in);
        private int freeslots = MAX_PRODUCTS;


        public void addProduct(Product product) {
                if (products.contains(product)) {
                        System.out.println("The product is already in the product list");
                } else if (freeslots == 0) {
                        System.out.println("The Product list is full");
                        return;

                }
                products.add(product);
                System.out.println("The product has been successfully added");
                freeslots = freeslots - 1;
                System.out.println("Free slots remaining: " + freeslots);

        }

        public void deleteProduct(String productID) {
                boolean found = false;
                for (Product product : products){
                        if (product.getProductID().equals(productID)){
                                found = true;
                                products.remove(product);
                                System.out.println(product.toString() + "has been removed successfully");
                                freeslots = freeslots + 1;
                                System.out.println("Total number of slots left : " + freeslots);
                                break;

                        }
                }
                if (!found){
                        System.out.println("Invalid ProductID!!! Please check and try again!!!");
                }


        }
        public  void displayProducts(){
                if (products.isEmpty()) {
                        System.out.println("<-----No Products in the list----->");
                }
                else {
                        System.out.println("List of all the Products");
                        for (Product product: products){
                                System.out.println(product);
                        }
                }

        }


        public void saveFile() {
                try {
                        FileWriter myWriter = new FileWriter("Product File");
                        for (Product product : products) {
                                String ProductID = product.getProductID();
                                String ProductName = product.getProductName();
                                int num_of_available = product.getNum_of_available();
                                double Price = product.getPrice();

                                String data = ProductID + ","
                                        + ProductName + ","
                                        + num_of_available + ","
                                        + Price + ",";

                                if (product instanceof Clothing) {
                                        int size = ((Clothing) product).getSize();
                                        String colour = ((Clothing) product).getColour();
                                        data = "Clothing," + data + size + "," + colour + "\n";
                                }

                                if (product instanceof Electronics) {
                                        String brand = ((Electronics) product).getBrand();
                                        int warranty = ((Electronics) product).getWarranty_period();
                                        data = "Electronics," + data + brand + "," + warranty + "\n";
                                }


                                myWriter.write(data);
                        }
                        myWriter.close();
                        System.out.println("|---Information successfully stored in a file---|");

                } catch (Exception exception) {
                        System.out.println("---------------An error occurred----------------");
                        exception.printStackTrace();
                }
        }

        @Override
        public List<Product> LoadFile() {
                try {
                        BufferedReader reader = new BufferedReader(new FileReader("Product File"));
                        String line;

                        while ((line = reader.readLine()) != null) {
                                // Split the line into individual values
                                String[] values = line.split(",");

                                String Type = values[0];
                                String productID = values[1];
                                String productName = values[2];
                                int num_of_available = Integer.parseInt(values[3]);
                                double price = Double.parseDouble(values[4]);
                                if (Objects.equals(Type, "Clothing")) {
                                        int size = Integer.parseInt(values[5]);
                                        String colour = values[6];
                                        Product product = new Clothing(productID, productName, num_of_available, price, size, colour);
                                        ;
                                        products.add(product);
                                        freeslots = freeslots - 1;
                                } else if (Objects.equals(Type, "Electronics")) {
                                        String brand = values[5];
                                        int warranty = Integer.parseInt(values[6]);
                                        Product product = new Electronics(productID, productName, num_of_available, price, brand, warranty);
                                        products.add(product);
                                        freeslots = freeslots - 1;
                                } else {
                                        System.out.println("Invalid Type of Product");
                                        break;
                                }
                        }

                        reader.close();
                        System.out.println("|---Information successfully loaded from the file---|");

                } catch (Exception exception) {
                        System.out.println("---------------An error occurred while loading the file----------------");
                        exception.printStackTrace();
                }

                return products;
        }



        public static void main(String[] args) {
                Electronics E1 = new Electronics("e","f",45,56,"k",56);
                Clothing C1 = new Clothing("23e","Abdul",8,2300,4,"red");
                WestminsterShoppingManager w1 = new WestminsterShoppingManager();
                w1.addProduct(E1);
                w1.addProduct(C1);


        }
}
