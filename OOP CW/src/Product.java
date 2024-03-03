 public abstract class Product{
    private String productID;
    private String productName;
    private int num_of_available;
    private double price;
    public Product(String productID,String productName, int num_of_available, double price) {
        this.productID = productID;
        this.productName = productName;
        this.num_of_available = num_of_available;
        this.price = price;
    }
    public Product(){}

     public Product(String productID, String productName, int numOfAvailable) {
     }

     public String getProductID(){
        return productID;
    }
    public void setProductID(String productID){
        this.productID = productID;
    }
    public String getProductName(){
        return productName;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }

     public int getNum_of_available() {
         return num_of_available;
     }

     public void setNum_of_available(int num_of_available) {
         this.num_of_available = num_of_available;
     }
     public double getPrice(){
        return price;
     }
     public void setPrice(int price){
        this.price = price;
     }

     @Override
     public String toString() {
         return "Product{" + "\n" +
                 "Product ID = " + productID + "\n" +
                 "productName = " + productName + "\n" +
                 "Number of available = " + num_of_available + "\n" +
                 "Price = " + price + "Rs" + "\n" +
                 '}';
     }
     public abstract String getProductType();
    public abstract  int productQuantity();
 }
