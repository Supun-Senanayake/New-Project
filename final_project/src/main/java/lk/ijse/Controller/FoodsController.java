package lk.ijse.Controller;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.Dto.Customerdto;
import lk.ijse.Dto.Foodsdto;
import lk.ijse.Dto.tm.CustomerTM;
import lk.ijse.Dto.tm.FoodsTM;
import lk.ijse.Model.Customermodel;
import lk.ijse.Model.Foodsmodel;
import lk.ijse.db.DbConnection;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static lk.ijse.Model.Customermodel.Save;

public class FoodsController implements Initializable {
    public  ObservableList<Foodsdto> observableList = FXCollections.observableArrayList();
    public Button btnAddImage;
    public ImageView imageView;
    public TextField txtId;
    public TextField txtPrice;
    public TextField txtName;
    public TableView tablefoods;
    public TableColumn colid;
    public TableColumn colname;
    public TableColumn colprice;
    public TableColumn colurl;
    private File imageFile;
    String imagePath;

    Foodsmodel foodsmodel = new Foodsmodel();

    public void btnSaveOnAction(ActionEvent event) throws IOException {

            int id = Integer.parseInt(txtId.getText());
            String name = txtName.getText();
            double price = Double.parseDouble(txtPrice.getText());

            Foodsdto foodsdto = new Foodsdto(id,name,price,imagePath);


           try {
              boolean isSave =foodsmodel.Save(foodsdto);
               if (isSave){
                   new Alert(Alert.AlertType.INFORMATION,"saved !!").show();
               }else {
                   new Alert(Alert.AlertType.ERROR, "Not saved  !!!").show();
               }
           }catch (SQLException e){
               e.printStackTrace();
           }
    }

    public void btnDeleteOnAction(ActionEvent event) {
    }

    public void btnUpdateOnAction(ActionEvent event) {
    }

    private boolean validateFoods() {
        String idText = txtId.getText();

        try {
            int id = Integer.parseInt(idText);
            if (id < 0) {
                new Alert(Alert.AlertType.ERROR, "ID cannot be negative").show();
                return false;
            }
            // Add any additional validation logic here
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid foods id: Please enter a valid integer").show();
            return false;
        }

        return true;
    }

    private void getAll() {
        var model = new Foodsmodel();

        ObservableList<FoodsTM> obList = FXCollections.observableArrayList();

        try {
            List<Foodsdto> dtoList = model.getAll();

            for (Foodsdto dto : dtoList) {
                obList.add(
                        new FoodsTM(

                                dto.getId(),
                                dto.getName(),
                                dto.getPrice(),
                                dto.getImageUrl()

                        )
                );
            }
            tablefoods.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
        populateTableView();

    }
    private void populateTableView() {
        getAll(); // Call method to retrieve data from the database

    }
    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colurl.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));


    }
    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtPrice.clear();
        imageView.setImage(null);
    }


         public  void btnAddImageOnAction(ActionEvent event) throws IOException {
             Stage stage = new Stage();
             FileChooser fileChooser = new FileChooser();
             fileChooser.setTitle("Open Image file");
             File file = fileChooser.showOpenDialog(stage);
             if (file != null) {
                 Image image = new Image(file.toURI().toString());
                  imagePath = file.getPath(); // image path
                 imageView.setImage(image);
             }
         }

    public void btnAddmenuOnAction(ActionEvent event) throws IOException {
    }
}
