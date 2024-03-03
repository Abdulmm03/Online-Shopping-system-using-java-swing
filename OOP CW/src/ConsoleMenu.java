import java.util.Scanner;
import java.util.regex.*;

public class ConsoleMenu {
    static WestminsterShoppingManager manager = new WestminsterShoppingManager();
    static User user = new User("Abdul","ssss");


    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menuloop:
        while (true){
            displayMenu();
            System.out.println("Please enter your option: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> addProduct();
                case 2 -> deleteProduct();
                case 3 -> manager.displayProducts();
                case 4 -> manager.saveFile();
                case 5 -> manager.LoadFile();
                case 6 -> new MainGUI();
                case 0 -> {
                    System.out.println("Exit the Product Management System!");
                    break menuloop;
                }
                default -> System.out.println("Invalid choice...Please try again!");
            }
        }
    }




    public static void displayMenu(){
        System.out.println("-----Welcome to the Shopping Management Menu-----");
        System.out.println("1.Add Product");
        System.out.println("2.Delete Product");
        System.out.println("3.Print the list of All Products");
        System.out.println("4.Save to file");
        System.out.println("5.Load from file");
        System.out.println("6.Launch GUI");
        System.out.println("0.Exit");
    }
    public static void deleteProduct(){
        System.out.println("Please enter the productID :(CL-XXXX/EL-XXXX)");
        String productID = scanner.next();
        manager.deleteProduct(productID);
    }
    public static void addProduct(){
        Product product;

        System.out.println("Please enter the product type : electronics/clothing ");
        String type = scanner.next().toLowerCase();

        System.out.println("Please enter the productID:(CL-XXXX/EL-XXXX) ");
        String productID = scanner.next().toUpperCase();
        String regex = "^(EL|CL)-\\d{4}";
        if (productID.matches(regex)){

        }else{
            System.out.println("Please enter a valid productID!!!");
            return;
        }
        name:
        System.out.println("Please enter the product name :");
        String productName = scanner.next();

        System.out.println("Please enter the number of available items: ");
        int num_of_available = scanner.nextInt();

        System.out.println("Please enter the price of the product: ");
        double price = scanner.nextDouble();

        if (type.equals("clothing")){
            System.out.println("Please enter the size of the clothing: ");
            int size = scanner.nextInt();

            System.out.println("Please enter the color of the clothing: ");
            String colour = scanner.next();

            product = new Clothing(productID,productName,num_of_available,price,size,colour);
            manager.addProduct(product);


        }else if (type.equals("electronics")) {
            System.out.println("Please enter the brand of the product: ");
            String brand = scanner.next();

            System.out.println("Please enter the warranty period of the product: ");
            int warranty_period = scanner.nextInt();

            product = new Electronics(productID,productName,num_of_available,price,brand,warranty_period);
            manager.addProduct(product);
        }



    }

}
