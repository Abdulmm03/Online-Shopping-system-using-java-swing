

public class Electronics extends Product {
    private String brand;
    private int warranty_period;
    public Electronics(String productID,String productName,int num_of_available,double price,String brand, int warranty_period){
        super(productID,productName,num_of_available,price);
        this.brand = brand;
        this.warranty_period = warranty_period;
    }
    public String getBrand(){
        return brand;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }
    public int getWarranty_period(){
        return warranty_period;
    }
    public void setWarranty_period(int warranty_period){
        this.warranty_period = warranty_period;
    }

    @Override
    public String toString(){
        return "Electronics{" + super.toString() +"\n" +
                "Brand= " + brand + "\n" +
                "Warranty period= " + warranty_period + "\n" +
                "}";
    }
    public String getProductType(){
        return "Electronic";
    }

    @Override
    public int productQuantity() {
        return 1;
    }

    public static void main(String[] args) {
        Electronics E1 = new Electronics("cf","p",30,6,"hjjj",1);
        System.out.println(E1.toString());
    }
}
