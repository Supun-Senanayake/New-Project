package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import lk.ijse.Dto.Foodsdto;
import lk.ijse.Model.Foodsmodel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AddFoodsController implements Initializable {

    public static int index;
    public Label foodid;
    public static Label foodsid;
    public Button AddToCardOnAction;
    public TextField qtyid;
    public static Label foodprice;
    public Label foodname;
    public Label CustomerName;
    @FXML
    private TableView<?> AddFoodTableviwe;

    @FXML
    private Label Pricechange;

    @FXML
    private TextField amount;

    @FXML
    private TableColumn<?, ?> food_col_id;

    @FXML
    private TableColumn<?, ?> food_col_name;

    @FXML
    private TableColumn<?, ?> food_col_price;

    @FXML
    private TableColumn<?, ?> food_col_qty;

    @FXML
    private GridPane menu_GridPane;

    @FXML
    private Label priceTotal;



    @FXML
    void PaybtnOnAction(ActionEvent event) {

    }

    @FXML
    void ReceiptbtnOnAction(ActionEvent event) {

    }

    @FXML
    void RemovebtnOnAction(ActionEvent event) {

    }

    static Foodsmodel foodsmodel = new Foodsmodel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menu_GridPane.getChildren().clear();

        List<Foodsdto> foodDto = null;
        try {
            foodDto = foodsmodel.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            int column = 0;
            int row = 0;
            for (int i = 0; i < foodDto.size(); i++) {
                AddcardfoodController.index = i;

                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("/view/cardfoods.fxml"));
                    menu_GridPane.add(parent, column, row++);

                    GridPane.setMargin(parent, new Insets(5, 5, 5, 5));
                } catch (Exception e) {

                }
            }
    }

    public void qtyOnAction(ActionEvent event) {

    }

    public static void  setfooddata() throws SQLException {
        List<Foodsdto> foods = foodsmodel.getAll();


//        foodname.setText(foods.get(index).getName());
        foodprice.setText(String.valueOf(foods.get(index).getPrice()));
        foodsid.setText(String.valueOf(foods.get(index).getId()));


    }

    public void setFoodData(Foodsdto foodsdto) {

            foodsid.setText(String.valueOf(foodsdto.getId()));
            foodname.setText(foodsdto.getName());
            foodprice.setText(String.valueOf(foodsdto.getPrice()));
        }
    }


