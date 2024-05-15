package lk.ijse.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Dto.UserDto;
import lk.ijse.Model.Registermodel;

import java.io.IOException;
import java.sql.SQLException;


public class RegisterController {
 public PasswordField txtpasswordid;
 public TextField txtUserNameid;
 @FXML
 private PasswordField EnterPasswordId;

 @FXML
 private TextField EnterUserNameid;

 @FXML
 private AnchorPane RegisterPane;

 @FXML
 private ImageView txtEnterUserNameId;

 public void HyperOnAction(ActionEvent event) throws IOException {
  FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/view/login.fxml"));
  Parent load = fxmlLoader.load();

  Stage stage = new Stage();
  stage.setScene(new Scene(load));
  stage.show();

 }

 public void btnLoginONAction(ActionEvent event) {
 }

 public void btnRegisterONAction(ActionEvent event) throws IOException, SQLException {

  String username = txtUserNameid.getText();
  String password = txtpasswordid.getText();

  var userDto = new UserDto(username, password);
  try {
   boolean register = Registermodel.register(userDto);
   if (register) {
    new Alert(Alert.AlertType.CONFIRMATION, "Register Successful").show();
    // clearField();
   }
  } catch (SQLException e) {
   e.printStackTrace();
   new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
  }
 }




}








