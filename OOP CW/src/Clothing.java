public class Clothing extends Product{
    private int size;
    private String colour;
    public Clothing(String productID,String productName,int num_of_available,double price,int size, String colour){
        super(productID,productName,num_of_available,price);
        this.size = size;
        this.colour = colour;
    }

    public int getSize()
    {
        return size;
    }
    public void setSize(int size)
    {
        this.size = size;
    }
    public String getColour(){
        return colour;
    }
    public void setColour(String colour){
        this.colour =colour;
    }

    @Override
    public String toString() {
        return "Clothing{" +
                super.toString() +
                "Size = " + size + "\n" +
                "Color = " + colour +
        "}";
    }
    public String getProductType()
    {
        return "Clothing";
    }

    @Override
    public int productQuantity(){
        return 1;

    }



}
