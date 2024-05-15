package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

    public class MainformeController {

        @FXML
        private TextField txtname;


        public void btnOnaction(ActionEvent actionEvent) {
           String name =  txtname.getText();


        }
    }



