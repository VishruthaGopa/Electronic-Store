import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.event.*;

public class ElectronicStoreApp  extends Application {
    private ElectronicStore model;

    public ElectronicStoreApp() {
        model = ElectronicStore.createStore();
    }

    public void start(Stage primaryStage) {
        Pane  aPane = new Pane();

        // Create the view
        ElectronicStoreView  view = new ElectronicStoreView();
        aPane.getChildren().add(view);

        primaryStage.setTitle("Electronic Store Application");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();

        view.update(model, -1);


        // Event handler for PopularList
        view.getSummaryPane().getPopularList().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Object selectedProduct = view.getSummaryPane().getPopularList().getSelectionModel().getSelectedItem();
                if (selectedProduct != null) {
                    view.getStockPane().getAddButton().setDisable(true);
                    view.getCartPane().getRemoveButton().setDisable(true);
                    view.getStockPane().getStockList().getSelectionModel().clearSelection();
                    view.getCartPane().getCartList().getSelectionModel().clearSelection();

                }
            }
        });

        // Event handler for StockList
        view.getStockPane().getStockList().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Object selectedProduct = view.getStockPane().getStockList().getSelectionModel().getSelectedItem();
                if (selectedProduct != null) {
                    view.getStockPane().getAddButton().setDisable(false);
                    view.getCartPane().getRemoveButton().setDisable(true);
                    view.getCartPane().getCartList().getSelectionModel().clearSelection();
                    view.getSummaryPane().getPopularList().getSelectionModel().clearSelection();
                } else {
                    view.getStockPane().getAddButton().setDisable(true);
                }
            }
        });

        // Event handler for CartList
        view.getCartPane().getCartList().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Object selectedProduct = view.getCartPane().getCartList().getSelectionModel().getSelectedItem();
                if (selectedProduct != null) {
                    view.getCartPane().getRemoveButton().setDisable(false);
                    view.getStockPane().getAddButton().setDisable(true);
                    view.getStockPane().getStockList().getSelectionModel().clearSelection();
                    view.getSummaryPane().getPopularList().getSelectionModel().clearSelection();
                } else {
                    view.getCartPane().getRemoveButton().setDisable(true);
                }
            }
        });

        // Event handler for "Add to Cart" button
        view.getStockPane().getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                System.out.println("\"Add to Cart\" button was pressed");

                int selectedIndex = view.getStockPane().getStockList().getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    Product selectedProduct = model.getAvailableStockList().get(selectedIndex);

                    //System.out.println("Quantity remaining: "+ selectedProduct.getStockQuantity());
                    model.addToCart(selectedProduct);
                    view.getCartPane().update(model, 1); // Update cart view with added quantity and total value

                    //if (view.getStockPane().getStockList().getItems().size() == 0) {
                    //    view.getStockPane().getAddButton().setDisable(true);
                    //}


                    view.getStockPane().getStockList().getItems().set(selectedIndex, null);
                    view.getStockPane().getAddButton().setDisable(true);

                    view.getCartPane().getSaleButton().setDisable(false);



                    view.getStockPane().update(model, 1);

                }
            }

        });

        //Remove Button
        view.getCartPane().getRemoveButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                System.out.println("\"Remove from Cart\" button was pressed");

                int selectedIndex = view.getCartPane().getCartList().getSelectionModel().getSelectedIndex();
                String selectedProdString = (String) view.getCartPane().getCartList().getSelectionModel().getSelectedItem();

                if (selectedIndex >= 0) {
                    //System.out.println("Testing- selectedString = "+ selectedProdString);
                    //Product selectedProduct = model.getStockList().get(selectedIndex);
                    model.removeFromCart(selectedProdString);
                    view.getCartPane().update(model, -1);
                    view.getStockPane().update(model, -1);
                }

                if (view.getCartPane().getCartList().getItems().size() == 0) {
                    view.getCartPane().getSaleButton().setDisable(true);
                }
                view.getCartPane().getRemoveButton().setDisable(true);

            }
        });

        //Complete Sale Button
        view.getCartPane().getSaleButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                System.out.println("\"Complete Sale\" button was pressed");
                model.completeSale();
                view.getCartPane().getSaleButton().setDisable(true);
                view.getCartPane().update(model, -1);
                view.getSummaryPane().update(model,-1);
                view.update(model, -1);

            }
        });


        //Reset Button
        view.getSummaryPane().getResetButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                System.out.println("\"Reset Store\" button was pressed");

                // Reset the model
                model = ElectronicStore.createStore();
                // Update the GUI
                view.update(model, -1);

            }
        });



    }

    public static void main(String[] args) {
        launch(args);
    }
}