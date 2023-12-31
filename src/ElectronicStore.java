//Class representing an electronic store
//Has an array of products that represent the items the store can sell

import java.util.*;

public class ElectronicStore {
    private String name;
    private int salesCounter;
    private List<Product> stock; //List to hold all products
    private HashMap<Product, Integer> cartItems; //HashMap to hold items in the cart <Product, quantity of product in cart>

    private double revenue;
    private double totalValueInCart;

    public ElectronicStore(String initName) {
        revenue = 0.0;
        name = initName;

        stock = new ArrayList<>();
        cartItems = new HashMap<>();

        salesCounter = 0;
        totalValueInCart = 0.0;
    }

    public String getName() {
        return name;
    }
    public int getSalesNumber() {
        return salesCounter;
    }
    public List<Product> getAvailableStockList(){
        List<Product> availableProducts = new ArrayList<>();
        // New list called availableProducts that only contains products with a quantity above 0
        for (Product p : stock) {
            if (p.getStockQuantity() > 0) {
                availableProducts.add(p);
            }
        }
        return availableProducts;
    }


    //public List<Product> getStock(){return stock;}


    public void completeSale(){
        // Update the sold quantity for each product in the cart
        for (Product p : cartItems.keySet()) {
            int quantityInCart = cartItems.get(p);
            p.setSoldQuantity(p.getSoldQuantity() + quantityInCart);

            //Testing
            //System.out.println(p.toString() + " sold quantity: " + p.getSoldQuantity());
        }

        // Increment the # Sales counter
        salesCounter++;

        // Clear the cartItems HashMap
        cartItems = new HashMap<>();

        //Update Revenue
        revenue += totalValueInCart;

        // Reset the Current Cart total dollar amount to $0.00
        totalValueInCart = 0;

    }

    public List<String> getPopularProducts() {
        // Top 3 popular items that exist in the store’s stock
        Collections.sort(stock, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                return Integer.compare(p2.getSoldQuantity(), p1.getSoldQuantity()); // descending order
            }
        });

        // Get the top 3 products based on sold quantity
        List<Product> topProducts = stock.subList(0, Math.min(3, stock.size()));

        //Testing
        /*
        for (Product p : stock) {
            System.out.println(p.toString() + p.getSoldQuantity());
            System.out.println("sold qty: "+ p.getSoldQuantity());

        }
        */

        //System.out.println("Top 3 Popular Products:");
        List<String> popularList = new ArrayList<>();
        for (Product p : topProducts) {
            popularList.add(p.toString());
            //System.out.println(p.toString() + " sold qty: "+ p.getSoldQuantity());
        }
        return popularList;

    }


    public Double getRevenue() {
        return revenue;
    }


    //Adds a product into List
    public boolean addProduct(Product newProduct){
        return stock.add(newProduct);
    }

    public boolean addToCart(Product product) {
        // Check if the product is available in stock and has a quantity above 0
        if (stock.contains(product) && product.getStockQuantity() > 0) {
            // If the product is already in the cart, increment its quantity by 1
            if (cartItems.containsKey(product)) {
                cartItems.put(product, cartItems.get(product) + 1);
            } else {
                // Otherwise, add the product to the cart with a quantity of 1
                cartItems.put(product, 1);
            }
            // Update the stock quantity of the selected product
            product.setStockQuantity(product.getStockQuantity() - 1);
            return true;
        }
        return false;
    }


    public boolean removeFromCart(String originalStr) {

        String[] parts = originalStr.split(" x ");
        String prodStr = parts[1];
        Product selectedProd = findProductByString(prodStr);

        // Check if the product is in the cart
        if (cartItems.containsKey(selectedProd)) {
            int currentQuantityinCart = cartItems.get(selectedProd);
            // Check if there is more than one of the product in the cart
            if (currentQuantityinCart > 1) {
                cartItems.put(selectedProd, currentQuantityinCart - 1); // Reduce the quantity by 1
            } else {
                cartItems.remove(selectedProd); // Remove the product from the cart
            }

            // Check if the product is already available in the availableProduct list
            if (!stock.contains(selectedProd)) {
                stock.add(selectedProd); // Add the product to the stock list
            }

            // Increase the quantity in stock by 1
            selectedProd.setStockQuantity(selectedProd.getStockQuantity() + 1);

            return true; // Return true to indicate that the product was successfully removed from the cart
        }
        System.out.println("Error in REMOVE FROM CART method");
        System.out.println("Product parameter: " + selectedProd);
        return false; // Return false to indicate that the product was not found in the cart

    }

    public Product findProductByString(String productString) {
        for (Product p : cartItems.keySet()) {
            if (p.toString().equals(productString)) {
                return p;
            }
        }
        return null; // product not found
    }

    public HashMap<Product, Integer> getHashMapCartItems() {
        return cartItems;
    }

    public List<String> getCartItems() {
        List<String> cartList = new ArrayList<>();
        for (Product p : cartItems.keySet()) {
            cartList.add(String.format("%d x %s", cartItems.get(p), p.toString()));
        }
        return cartList;
    }

    public double getTotalValueInCart() {
        totalValueInCart = 0.0;
        for (Product p : cartItems.keySet()) {
            totalValueInCart += (cartItems.get(p) * p.getPrice());
        }
        return totalValueInCart;
    }

    public static ElectronicStore createStore() {
        ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
        Desktop d1 = new Desktop(100, 1, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 2, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 1, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 2, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 1, 250, "White", "Sub Zero", false);
        Fridge f2 = new Fridge(750, 2, 125, "Stainless Steel", "Sub Zero", true);
        ToasterOven t1 = new ToasterOven(25, 1, 50, "Black", "Danby", false);
        ToasterOven t2 = new ToasterOven(75, 2, 50, "Silver", "Toasty", true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }
} 