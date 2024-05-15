package lk.ijse.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Dto.UserDto;
import lk.ijse.Model.Usermodel;


import java.io.IOException;
import java.sql.SQLException;

public class loginController {
    @FXML
    private PasswordField EnterPasswordid;

    @FXML
    private TextField EnterUserNameid;

    @FXML
    private AnchorPane root;

    private String name;
    UserDto userDto;

    public loginController(){
    String password = null;
    userDto = new UserDto(name,password) ;
    }



    public void btnLoginONAction(ActionEvent event) throws IOException {
        userDto.setUsername(EnterUserNameid.getText());
        userDto.setPassword(EnterPasswordid.getText());

        try {
            boolean isUpdate  = Usermodel.searchUser(userDto);

            if(isUpdate){
                FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/view/maindashbord.fxml"));
                Parent load = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(load));
                stage.show();



            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

