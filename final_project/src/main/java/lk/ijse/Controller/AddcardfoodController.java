package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Dto.Fooddto;
import lk.ijse.Dto.Foodsdto;
import lk.ijse.Model.Foodsmodel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AddcardfoodController implements Initializable {


    public static int index;
    @FXML
    private AnchorPane CardForm;

    @FXML
    private Spinner<?> Spinner;
    @FXML
    private Button addbtnOnAction;

    @FXML
    private ImageView foodImageview;

    @FXML
    private Label foodname;

    @FXML
    private Label foodprice;


    private Foodsmodel foodsmodel = new Foodsmodel();
    private AddFoodsController addFoodsController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadAddFoodsController();
            setDataInGridPane();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void loadAddFoodsController() throws IOException, IOException {
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Addfoods.fxml"));
        Parent root = loader.load();
        addFoodsController = loader.getController();*/
    }

    private void setDataInGridPane() throws SQLException {
        List<Foodsdto> foods = foodsmodel.getAll();

        Image image = new Image(foods.get(index).getImageUrl());
        ImageView imageView1 = new ImageView(image);

        foodImageview.setImage(imageView1.getImage());
        foodname.setText(foods.get(index).getName());
        foodprice.setText(String.valueOf(foods.get(index).getPrice()));

    }


    @FXML
    void BtnOnAction(ActionEvent event) {

        try {
            List<Foodsdto> foods = foodsmodel.getAll();
            Foodsdto selectedFood = foods.get(index);

            addFoodsController.setFoodData(selectedFood);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    }

