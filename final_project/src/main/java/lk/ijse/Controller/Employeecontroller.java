package lk.ijse.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Dto.Customerdto;
import lk.ijse.Dto.Employeedto;
import lk.ijse.Dto.tm.EmployeeTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static lk.ijse.Model.Customermodel.Save;

public class Employeecontroller {

    public TableView tblEmployee;
    public TableColumn colname;
    public TableColumn coladdress;
    public TableColumn colcontact;
    public TableColumn colNic;
    public TableColumn colService;
    public TextField txtservice;
    public TextField txtnumber;
    public TextField txtname;
    public TextField txtaddress;
    public AnchorPane roos;
    public AnchorPane root;
    public TextField txtNic;

    public void btnSaveOnaction(ActionEvent event) {
    }

    public void btnUpdateOnaction(ActionEvent event) {
    }

    public void btnDeleteOnaction(ActionEvent event) {
    }

    public void btnPrintOnaction(ActionEvent event) {
    }

    public void txtSearchOnAction(ActionEvent event) {
    }
}
