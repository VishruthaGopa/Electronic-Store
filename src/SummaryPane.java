import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
public class SummaryPane extends Pane implements StoreView{
    private Label title, sales, revenue, average, popular;
    private TextField salesField, revenueField, averageField;
    private ListView<String> popList;
    private Button resetButton;
    public Button getResetButton() { return resetButton; }
    public ListView<String> getPopularList() {
        return popList;
    }

    public SummaryPane() {

        Pane innerPane = new Pane();

        //Heading
        title = new Label("Store Summary:");
        title.relocate(50, 10);

        // Create the labels
        sales = new Label("# Sales:");
        sales.relocate(10, 40);
        revenue = new Label("Revenue:");
        revenue.relocate(10, 70);
        average = new Label("$ / Sale:");
        average.relocate(10, 100);
        popular = new Label("Most Popular Items:");
        popular.relocate(40, 135);

        // Create the TextFields
        salesField = new TextField();
        salesField.relocate(90, 40);
        salesField.setPrefSize(85, 25);
        salesField.setText("0");
        revenueField = new TextField();
        revenueField.relocate(90, 70);
        revenueField.setPrefSize(85, 25);
        revenueField.setText("0.00");
        averageField = new TextField();
        averageField.relocate(90, 100);
        averageField.setPrefSize(85, 25);
        averageField.setText("N/A");

        // Create the ListView
        popList = new ListView<String>();
        popList.relocate(10, 155);
        popList.setPrefSize(165, 180);

        // Create the Reset button
        resetButton = new Button("Reset Store");
        resetButton.relocate(30, 340);
        resetButton.setPrefSize(120,40);

        // Add all the components to the SummaryPane
        innerPane.getChildren().addAll(title, sales, revenue, average, popular, salesField, revenueField, averageField, popList, resetButton);

        getChildren().addAll(innerPane);
    }


    public void update(ElectronicStore model, int selectedProduct) {
        if (model != null) {
            // Update the TextFields
            salesField.setText(Integer.toString(model.getSalesNumber()));

            revenueField.setText(String.format("%.2f", model.getRevenue()));
            if (model.getSalesNumber() > 0) {
                averageField.setText(String.format("%.2f", model.getRevenue() / model.getSalesNumber()));
            } else {
                averageField.setText("N/A");
            }


            // Update the popular list view
            popList.setItems(FXCollections.observableArrayList(model.getPopularProducts()));


        }
    }

}
