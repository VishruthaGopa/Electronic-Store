import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class StockPane extends Pane implements StoreView{

    private ListView<String> stockList;
    private Button addButton;
    public ListView<String> getStockList() { return stockList; }
    public Button getAddButton() { return addButton; }

    public StockPane() {

        // Create the labels
        Label title = new Label("Stock Store:");
        title.relocate(110, 10);

        // Create the ListView
        stockList = new ListView<String>();
        stockList.relocate(10, 30);
        stockList.setPrefSize(280, 305);

        // Create the Reset button
        addButton = new Button("Add to Cart");
        addButton.relocate(90, 340);
        addButton.setPrefSize(120,40);
        addButton.setDisable(true);

        // Add all the components to the StockPane
        getChildren().addAll(title, stockList, addButton);

    }

    public void update(ElectronicStore model, int selectedProduct) {
        //ObservableList<Product> productList = FXCollections.observableArrayList(model.getStockList());
        //ObservableList<String> stringList = FXCollections.observableArrayList();
        //for (Product p : productList) {
        //    stringList.add(p.toString());
        //}
        //stockList.setItems(stringList);

        stockList.getItems().clear();

        for (Product product : model.getAvailableStockList()) {
            stockList.getItems().add(product.toString()); // add each product toString to the list
        }

    }

}
