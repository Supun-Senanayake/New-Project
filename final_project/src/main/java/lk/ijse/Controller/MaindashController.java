package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;

public class MaindashController {

    @FXML
    private AnchorPane MainPane;

    public AnchorPane root;

    @SneakyThrows
    public void DashbordbtnOnAction(ActionEvent event) {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/maindashbord.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Maindash");
        stage.centerOnScreen();
    }

    @SneakyThrows
    public void btnCustomerOnAction(ActionEvent event) {
        this.MainPane.getChildren().clear();
        this.MainPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/Customer.fxml")));
    }

    @SneakyThrows
    public void logoutbtnOnAction(ActionEvent event) {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("login");
        stage.centerOnScreen();
    }

    @SneakyThrows
    public void OrderbtnOnAction(ActionEvent event) throws IOException {
        this.MainPane.getChildren().clear();
        this.MainPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/foods.fxml")));

    }

    public void btnFoodpannelOnAction(ActionEvent event) throws IOException {
        this.MainPane.getChildren().clear();
        this.MainPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/Addfoods.fxml")));
    }
}

