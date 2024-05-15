package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class FoodController {
    @FXML
    private AnchorPane root;
    @FXML
    private Label HowManyItem;

    @FXML
    void btnchickenOnAction(ActionEvent event) {

    }
    @FXML
    void AddtoCardOnAction(ActionEvent event) {

    }

    @FXML
    void addFoodCountbtnOnAction(ActionEvent event) {
        int count = Integer.parseInt(HowManyItem.getText());
        String lblCount = String.valueOf(count+1);
        HowManyItem.setText(lblCount);
    }

    @FXML
    void minFoodCountbtnOnAction(ActionEvent event) {

        int count = Integer.parseInt(HowManyItem.getText());
        if (count != 0) {
            String lblCount = String.valueOf(count-1);
            HowManyItem.setText(lblCount);
        }

    }
}
