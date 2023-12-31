//Base class for all products the store will sell
public abstract class Product {
    private double price;
    private int stockQuantity;
    private int soldQuantity;

    public Product(double initPrice, int initQuantity) {
        price = initPrice;
        stockQuantity = initQuantity;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
    public void setStockQuantity(int stk) {
         this.stockQuantity = stk;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int stk) { this.soldQuantity = stk; }

    public double getPrice() {
        return price;
    }

    public boolean equals(Object c) {
        if (!(c instanceof Product)) { return false; };
        return toString().equals(((Product)c).toString());
    }

    public int hashCode() { return toString().hashCode(); }


    //Returns the total revenue (price * amount) if there are at least amount items in stock
    //Return 0 otherwise (i.e., there is no sale completed)
    public double sellUnits(int amount) {
        if (amount > 0 && stockQuantity >= amount) {
            stockQuantity -= amount;
            soldQuantity += amount;
            return price * amount;
        }
        return 0.0;
    }
}