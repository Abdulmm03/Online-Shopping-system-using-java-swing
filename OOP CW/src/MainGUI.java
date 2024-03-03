import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class MainGUI extends JFrame {
    JLabel label;

    JFrame frame;

    JLabel totalAmountLabel;
    JFrame ShoppingCartFrame;
    JTextArea productDetail;

    ShoppingCart shoppingCart = new ShoppingCart();
    JTable ProductTable;
    DefaultTableModel model = new DefaultTableModel();
    JTable ShoppingCarTable;
    DefaultTableModel cartTableModel;
    boolean isFirstPurchase = true;
    int selectedRow;
    JComboBox comboBox;
    Object [] rowData;
    static List<Product> filteredProducts = new ArrayList<>();
    double totalPrice;


    public MainGUI() {
        this.ProductTable = new JTable(model);
        this.cartTableModel = new DefaultTableModel();
        addSampleData();
        InitialUI();

    }

    public void InitialUI() {
        frame = new JFrame();
        frame.setTitle("Westminster Shopping Centre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        label = new JLabel();
        label.setText("Select Product Category :");


        String[] productsList = {"All", "Clothing", "Electronic"};
        comboBox = new JComboBox<>(productsList);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                filterProductList();


            }
        });
        ProductTable.setModel(model);


        String[] columns = {"Product ID", "ProductName", "Category", "Price", "Available", "Product Info"};
        model.setColumnIdentifiers(columns);

        JScrollPane scrollPane = new JScrollPane(ProductTable);

        //Jtext area
        JTextArea productDetail = new JTextArea();
        productDetail.setEditable(false);
        productDetail.setBorder(BorderFactory.createTitledBorder("Selected Product Details"));


        ProductTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Get selected row
                    int selectedRow = ProductTable.getSelectedRow();

                    // Check if a particular row is selected
                    if (selectedRow != -1) {
                        // Get data from the particular row
                        String productId = (String) ProductTable.getValueAt(selectedRow, 0);
                        String name = (String) ProductTable.getValueAt(selectedRow, 1);
                        String category = (String) ProductTable.getValueAt(selectedRow, 2);
                        int price = (int) ProductTable.getValueAt(selectedRow, 3);

                        // Display product details in the JTextArea
                        productDetail.setText("Product ID: " + productId + "\n"
                                + "Name: " + name + "\n"
                                + "Category: " + category + "\n"
                                + "Price: $" + price);
                    }
                }
            }
        });


        JButton cartButton = new JButton("Shopping cart");
        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createShoppingCart();
            }
        });


        JButton addToCart = new JButton("Add to cart");
        addToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCartAction();
            }
        });



        JPanel topPanel = new JPanel();
        topPanel.add(label);
        topPanel.add(comboBox);
        topPanel.add(cartButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(productDetail);
        bottomPanel.add(addToCart);


        frame.setLayout(new BorderLayout());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

    }
    public void addSampleData(){

        WestminsterShoppingManager.products.add(new Clothing("CL-1111","Jeans",10,1200.0,32,"Blue"));
        WestminsterShoppingManager.products.add(new Clothing("CL-2222","Shirt",20,2500.0,13,"Red"));
        WestminsterShoppingManager.products.add(new Electronics("EL-1000","Phone",10,1200.0,"Nokia",1));
        WestminsterShoppingManager.products.add(new Electronics("EL-2000","TV",20,100.0,"LG",1));



    }
    public void filterProductList(){
        String selectedCategory = (String) comboBox.getSelectedItem();
        System.out.println(selectedCategory);
        filteredProducts = filterProductsByCategory(selectedCategory);
        System.out.println(filteredProducts.size());
        updateProductTable();

    }

    public List<Product> filterProductsByCategory(String selectedCategory) {
        filteredProducts.clear();

        for (Product product : WestminsterShoppingManager.products) {
            if ("All".equals(selectedCategory) || product.getProductType().equals(selectedCategory)){
                filteredProducts.add(product);
            }
        }

        return filteredProducts;

    }


    private void updateProductTable() {
        // Update the ProductTable with the new list of products
        DefaultTableModel model = (DefaultTableModel) ProductTable.getModel();
        model.setRowCount(0); // Clear existing rows
        System.out.println(model.getRowCount());

        for (Product product : filteredProducts) {
            Object[] rowData;
            if (product instanceof Electronics) {
                rowData = new Object[]{
                        product.getProductID().toString(),
                        product.getProductName().toString(),
                        product.getProductType().toString(),
                        ((int) product.getPrice()),
                        product.getNum_of_available(),
                        ((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarranty_period()
                };
            } else if (product instanceof Clothing) {
                rowData = new Object[]{
                        product.getProductID().toString(),
                        product.getProductName().toString(),
                        product.getProductType().toString(),
                        ((int) product.getPrice()),
                        product.getNum_of_available(),
                        (((Clothing) product).getColour() + ", " + ((Clothing) product).getSize())
                };
            } else {
                // Handle other product types if needed
                rowData = new Object[]{};
            }
            model.addRow(rowData);
            System.out.println(model.getRowCount());
        }
    }

    public void addToCartAction() {
        int selectedRow = ProductTable.getSelectedRow();

        if (selectedRow != -1) {
            Product selectedProduct = getProductFromRow(selectedRow);
            System.out.println(selectedProduct);

            boolean isAlreadyinCart = false;
            for (int i = 0; i < cartTableModel.getRowCount(); i++) {
                String cartProductID = cartTableModel.getValueAt(i, 0).toString();
                if (cartProductID == selectedProduct.getProductID()){
                    isAlreadyinCart = true;
                    int currentQuantity = (int) cartTableModel.getValueAt(i,2);
                    cartTableModel.setValueAt(currentQuantity+1,i,2);
                    double totalPrice = (double) cartTableModel.getValueAt(i,3) + selectedProduct.getPrice();
                    cartTableModel.setValueAt(totalPrice,i,3);
                    break;
                }
            }
            if (!isAlreadyinCart){
                shoppingCart.addProduct(selectedProduct);
                updateShoppingCart();

            }
               //updateShoppingCart
            System.out.println(selectedProduct);


        }
    }

    private void updateShoppingCart() {
        shoppingCart.getProducts().clear();
        cartTableModel.setRowCount(0);
        for (Product product : shoppingCart.getProducts()) {
            Object[] row = {
                    product.getProductID(),
                    product.getProductName(),
                    product.productQuantity() ,
                    product.getPrice()
            };
            cartTableModel.addRow(row);
        }
    }


    private Product getProductFromRow(int row) {
        String selectedProductType = (String) comboBox.getSelectedItem();
        for (int i = 0, count = 0; i < WestminsterShoppingManager.products.size(); i++) {
            Product product = WestminsterShoppingManager.products.get(i);
            if ((selectedProductType.equals("All") || (selectedProductType.equals("Electronics") && product instanceof Electronics) ||
                    (selectedProductType.equals("Clothing") && product instanceof Clothing))) {
                if (row == count) {
                    return product;
                }
                count++;
            }


        }
        return null;
    }

    public void createShoppingCart() {
        JFrame ShoppingCartFrame = new JFrame("Shopping Cart");
        ShoppingCartFrame.setSize(400, 400);


        JTable ShoppingCarTable = new JTable();
        cartTableModel = new DefaultTableModel();

        ShoppingCarTable.setModel(cartTableModel);


        String[] ShoppingCartColumn = {"Product ID", "Product Name", "Quantity", "Price"};
        cartTableModel.setColumnIdentifiers(ShoppingCartColumn);

        JScrollPane cartScrollPane = new JScrollPane(ShoppingCarTable);


        double totalPrice = 0;


        double discount = isFirstPurchase ? 0.1 + totalPrice : 0.0;
        double finalPrice = totalPrice - discount;
        isFirstPurchase = false;



        JLabel totalAmountLabel = new JLabel("Total Price: " + totalPrice );
        JLabel Discount = new JLabel("Discount: " + discount);
        JLabel FinalPrice = new JLabel("Final Price: " + finalPrice);

        JPanel Downpanel = new JPanel();
        Downpanel.setLayout(new BoxLayout(Downpanel, BoxLayout.Y_AXIS));
        Downpanel.add(cartScrollPane);
        Downpanel.add(totalAmountLabel);
        Downpanel.add(Discount);
        Downpanel.add(FinalPrice);
        ShoppingCartFrame.add(Downpanel);


        ShoppingCartFrame.setVisible(true);

    }


}


