package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.Dto.Productdto;
import lk.ijse.Model.Productmodel;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductController implements Initializable{


        @FXML
        private TableView<Productdto> inventory_tableview;

        @FXML
        private ImageView inventory_imageview;

        @FXML
        private TextField inventory_productStock;

        @FXML
        private TextField inventory_productPrice;

        @FXML
        private ComboBox<String> inventory_Statuse;

        @FXML
        private ComboBox<String> inventory_type;

        @FXML
        private TextField inventory_productName;

        @FXML
        private TextField inventory_idproduct;

        private Productmodel productModel;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            productModel = new Productmodel();
            inventoryTypelist();
            inventoryStatusList();
            inventoryShowdata();
        }

        @FXML
        void inventoryAddbtnOnAction(ActionEvent event) throws SQLException {
            String productId = inventory_idproduct.getText();
            String productName = inventory_productName.getText();
            String type= inventory_type.getValue();
            int stock= Integer.parseInt(inventory_productStock.getText());
            double price = Double.parseDouble(inventory_productPrice.getText());
            String status = inventory_Statuse.getValue();

           Productdto newProduct = new Productdto(productId, productName, type, stock, price, status);

            boolean isAdded = productModel.addProduct(newProduct);

            if (isAdded) {
                inventoryShowdata();
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Product Added", "Product added successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Add Product Error", "Failed to add product!");
            }
        }


        @FXML
        void inventoryUpdatebtnOnAction(ActionEvent event) throws SQLException {
            // Get the selected product from the table view
            Productdto selectedProduct = inventory_tableview.getSelectionModel().getSelectedItem();

            // Check if a product is selected
            if (selectedProduct == null) {
                showAlert(Alert.AlertType.WARNING, "No Product Selected", "Please select a product to update.");
                return;
            }

            // Get the values from the input fields

            String productId = inventory_idproduct.getText();
            String productName = inventory_productName.getText();
            String type = inventory_type.getValue();
            int stock = Integer.parseInt(inventory_productStock.getText());
            double price = Double.parseDouble(inventory_productPrice.getText());
            String status = inventory_Statuse.getValue();

            // Create a new ProductDTO object with updated values
            Productdto updatedProduct = new Productdto(productId,productName,type,stock,price,status);

            // Call the updateProduct method in the ProductModel class
            boolean isUpdated = productModel.updateProduct(updatedProduct);

            // Check if the product is successfully updated
            if (isUpdated) {
                // Refresh the table view with updated data
                inventoryShowdata();
                // Clear input fields
                clearFields();
                // Show a success message
                showAlert(Alert.AlertType.INFORMATION, "Product Updated", "Product updated successfully!");
            } else {
                // Show an error message if update fails
                showAlert(Alert.AlertType.ERROR, "Update Product Error", "Failed to update product!");
            }
        }


    @FXML
        void inventoryDeletebtnOnAction(ActionEvent event) {
            // Delete product implementation
        }

        @FXML
        void inventoryClearbtnOnAction(ActionEvent event) {
            clearFields();
        }

        private void clearFields() {
            inventory_idproduct.clear();
            inventory_productName.clear();
            inventory_type.getSelectionModel().clearSelection();
            inventory_productStock.clear();
            inventory_productPrice.clear();
            inventory_Statuse.getSelectionModel().clearSelection();
            inventory_imageview.setImage(null);
        }

        private void inventoryTypelist() {
            ObservableList<String> types = FXCollections.observableArrayList("Type 1", "Type 2", "Type 3");
            inventory_type.setItems(types);
        }

        private void inventoryStatusList() {
            ObservableList<String> statuses = FXCollections.observableArrayList("Active", "Inactive");
            inventory_Statuse.setItems(statuses);
        }

        private void inventoryShowdata() {
            try {
                ObservableList<Productdto> products = FXCollections.observableArrayList(productModel.getAllProduct());
                inventory_tableview.setItems(products);
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Error occurred while fetching products from database: " + e.getMessage());
            }
        }

        private void showAlert(Alert.AlertType alertType, String title, String content) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }

    public void inventorySelectData(MouseEvent mouseEvent) {
    }

    public void inventoryimportbtnOnAction(ActionEvent event) {

        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File imageFile = fileChooser.showOpenDialog(null);
        if (imageFile != null) {
            inventory_imageview.setImage(new javafx.scene.image.Image(imageFile.toURI().toString()));
        }
    }
    }




