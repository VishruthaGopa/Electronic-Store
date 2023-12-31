import javafx.scene.layout.Pane;

public class ElectronicStoreView extends Pane implements StoreView{
    private SummaryPane summaryPane;
    private StockPane stockPane;
    private CartPane cartPane;
    public SummaryPane getSummaryPane() {
        return summaryPane;
    }
    public StockPane getStockPane() {
        return stockPane;
    }
    public CartPane getCartPane() {
        return cartPane;
    }

    public ElectronicStoreView(){

        // Create the panes
        summaryPane = new SummaryPane();
        stockPane = new StockPane();
        cartPane = new CartPane();

        // Set the positions and sizes of the panes
        summaryPane.relocate(10, 10);
        stockPane.relocate(190, 10);
        cartPane.relocate(480, 10);

        // Add the panes to the main pane
        getChildren().addAll(summaryPane, stockPane, cartPane);

        setPrefSize(800, 400);
    }

    public void update(ElectronicStore model, int selectedProduct) {
        if (model != null){
            stockPane.update(model,-1);
            cartPane.update(model,-1);
            summaryPane.update(model,-1);
        }

    }


}
