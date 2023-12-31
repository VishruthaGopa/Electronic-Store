import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CartPane extends Pane implements StoreView{
    private ListView<String> cartList;
    private Label titleLabel;
    private Text currentCart;
    private Button  removeButton, saleButton;

    // Getters
    public ListView<String> getCartList() {
        return cartList;
    }
    public Button getRemoveButton() { return removeButton; }
    public Button getSaleButton() { return saleButton; }


    public CartPane() {

        // Create the components
        titleLabel = new Label("Current Cart");
        titleLabel.relocate(90, 10);

        currentCart = new Text("($0.00):");
        currentCart.relocate(160, 10); // +70 for x location

        cartList = new ListView<String>();
        cartList.relocate(10, 30);
        cartList.setPrefSize(280, 305);

        removeButton = new Button("Remove from Cart");
        removeButton.relocate(25, 340);
        removeButton.setPrefSize(120,40);
        removeButton.setDisable(true);

        saleButton = new Button("Complete Sale");
        saleButton.relocate(155, 340);
        saleButton.setPrefSize(120,40);
        saleButton.setDisable(true);

        // Add the components to the pane
        getChildren().addAll(titleLabel, currentCart, cartList, removeButton, saleButton);

    }

    public void update(ElectronicStore model, int selectedProduct) {
        // Update the cart ListView
        ObservableList<String> cartItems = FXCollections.observableArrayList(model.getCartItems());
        cartList.setItems(cartItems);

        // Calculate the total value of all products in the current cart
        double totalValue = model.getTotalValueInCart();

        // Update the "Current Cart" label with the total value
        currentCart.setText("(" + String.format("$%.2f", totalValue) + ")");

    }

}


