package lk.ijse.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Dto.Customerdto;
import lk.ijse.Dto.tm.CustomerTM;
import lk.ijse.Model.Customermodel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static lk.ijse.Model.Customermodel.Save;


public class CustomerController implements Initializable {

    ObservableList<Customerdto> observableList = FXCollections.observableArrayList();
    public AnchorPane roos;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colnic;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtnic;

    @FXML
    void txtNumbersearchOnAction(ActionEvent event) {
            String contact = txtContact.getText();

    //var model = new Customerdto();
            try {
                Customerdto customerdto = Customermodel.search(contact);
          // System.out.println(model);
                if (customerdto != null) {
                    txtName.setText(customerdto.getName());
                    txtAddress.setText(customerdto.getAddress());
                    txtContact.setText(customerdto.getContact());
                    txtnic.setText(customerdto.getNic());

                } else {
                    new Alert(Alert.AlertType.INFORMATION, "customer not found").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String contact = txtContact.getText();

        try {
            boolean isRemoved = Customermodel.delete(contact);

            if (isRemoved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted successfully").show();
                getAll();
                txtContact.setText("");
                txtName.setText("");
                txtAddress.setText("");
                txtnic.setText("");
                observableList.clear();

            } else {
                new Alert(Alert.AlertType.ERROR, "Delete failed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isValidated = validateCustomer();
        if (isValidated) {

            String name = txtName.getText();
            String nic = txtnic.getText();
            String address = txtAddress.getText();
            String contact = txtContact.getText();

            try {
                boolean isSaved = Save(new Customerdto(name, nic, address, contact ));

                if (isSaved) {

                    new Alert(Alert.AlertType.CONFIRMATION, "Saved  !!!").show();
                    getAll();
                    txtContact.setText("");
                    txtName.setText("");
                    txtAddress.setText("");
                    txtnic.setText("");
                    observableList.clear();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Not saved  !!!").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private void getAll() {
        var model = new Customermodel();

        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();

        try {
            List<Customerdto> dtoList = model.getAll();

            for (Customerdto dto : dtoList) {
                obList.add(
                        new CustomerTM(

                                dto.getName(),
                                dto.getNic(),
                                dto.getAddress(),
                                dto.getContact()

                        )
                );
            }

            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colnic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));


    }

    private boolean validateCustomer() {
        String contact = txtContact.getText();
        boolean matches = Pattern.matches("\\b\\d{10}\\b", contact);

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer contact").show();
            return false;
        }
        String name = txtnic.getText();
        boolean matches1 = Pattern.matches("\\b\\d{10}\\b", name);

        if (!matches1) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer nic.").show();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();


    }

    public void btnUpdateOnAction(ActionEvent event) {
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String nic = txtnic.getText();


        var dto = new Customerdto(name, nic, address, contact );
        try {
            boolean isUpdated = Customermodel.update(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                getAll();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnPrintOnAction(ActionEvent event) {
    }


    public void txtSearchOnAction(ActionEvent event) {
        String contact = txtContact.getText();

//        var model = new CustomerModel();
        try {
            Customerdto customerdto = Customermodel.search(contact);
//            System.out.println(customerDto);
            if (customerdto != null) {
                txtName.setText(customerdto.getName());
                txtAddress.setText(customerdto.getAddress());
                txtContact.setText(customerdto.getContact());
                txtnic.setText(customerdto.getNic());

            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

}



/*
    ObservableList<Customerdto> observableList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colnic;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtnic;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String contact = txtContact.getText();

        try {
            boolean isRemoved = Customermodel.delete(contact);

            if (isRemoved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted successfully").show();
                getAll();
                txtContact.setText("");
                txtName.setText("");
                txtAddress.setText("");
                txtnic.setText("");
                observableList.clear();

            } else {
                new Alert(Alert.AlertType.ERROR, "Delete failed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isValidated = validateCustomer();
        if (isValidated) {

            String name = txtName.getText();
            String nic = txtnic.getText();
            String address = txtAddress.getText();
            String contact = txtContact.getText();

            try {
                boolean isSaved = Save(new Customerdto(name,nic, address, contact));

                if (isSaved) {

                    new Alert(Alert.AlertType.CONFIRMATION, "Saved  !!!").show();
                    getAll();
                    txtContact.setText("");
                    txtName.setText("");
                    txtAddress.setText("");
                    txtnic.setText("");
                    observableList.clear();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Not saved  !!!").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private void getAll() {
        var model = new Customermodel();

        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();

        try {
            List<Customerdto> dtoList = model.getAll();

            for (Customerdto dto : dtoList) {
                obList.add(
                        new CustomerTM(

                                dto.getName(),
                                dto.getNic(),
                                dto.getAddress(),
                                dto.getContact()
                        )
                );
            }

            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colnic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

    }

    private boolean validateCustomer() {
        String contact = txtContact.getText();
        boolean matches = Pattern.matches("\\b\\d{10}\\b", contact);

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer contact").show();
            return false;
        }
        String name = txtnic.getText();
        boolean matches1 = Pattern.matches("\\b\\d{10}\\b", name);

        if (!matches1) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer nic.").show();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();



    }

    public void btnUpdateOnAction(ActionEvent event) {
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact  = txtContact.getText();
        String nic = txtnic.getText();

        var dto = new Customerdto( name,nic, address,contact);
        try {
            boolean isUpdated = Customermodel.update(dto);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                getAll();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnPrintOnAction(ActionEvent event) {
    }


    public void txtSearchOnAction(ActionEvent event) {

//        var model = new CustomerModel();
        try {
            Customerdto customerdto = Customermodel.search(contact);
//            System.out.println(customerDto);
            if (customerdto != null) {
                txtName.setText(customerdto.getName());
                txtAddress.setText(customerdto.getAddress());
                txtContact.setText(customerdto.getContact());
                txtnic.setText(customerdto.getNic());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }*/






