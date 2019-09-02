package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class OwnerController {
    @FXML
    Button newStoreButton;
    @FXML
    Button viewStoresButton;
    @FXML
    Button salesByStoreButton;
    @FXML
    Button overallSalesButton;
    @FXML
    Button newUserButton;


    //New Store data
    @FXML
    TextField storeNameField;
    @FXML
    TextField storeAddressField;
    @FXML
    TextField storeCodeField;
    @FXML
    TextField storeSalesField;


    //New User data
    @FXML
    TextField newUserNameField;
    @FXML
    TextField newUserPasswordField;
    @FXML
    ChoiceBox<String> userTierChoiceBox;
    @FXML
    Button backButton2;
    @FXML
    Button addNewUserButton;


    //Sales By Store data




    public void newStore() throws IOException {
        ReportGeneratorClient.changeToNewStore();
    }
    public void viewStores() throws IOException {
        ReportGeneratorClient.changeToViewStores();
    }
    public void salesByStore() throws IOException {
        ReportGeneratorClient.changeToSalesByStore();
    }
    public void newUser() throws IOException {
        ReportGeneratorClient.changeToNewUser();
    }
    public void overallSales() throws IOException {
        ReportGeneratorClient.changeToOverallSales();
    }



    public void addNewStorePrePass() throws RemoteException {
        boolean success = false;
        if(storeNameField.getText() != null && storeAddressField.getText() != null && storeCodeField.getText() != null && storeSalesField.getText() != null){
            int code = -1;
            int sales = -1;
            try {
                code = Integer.valueOf(storeCodeField.getText());
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No es número");
                alert.setContentText("No introdujiste un número en el campo de código.");
                alert.showAndWait();
            }
            try {
                sales = Integer.valueOf(storeSalesField.getText());
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No es número");
                alert.setContentText("No introdujiste un número en el campo de ventas.");
                alert.showAndWait();
            }
            success = ReportGeneratorClient.reportGenerator.addNewStore(storeNameField.getText(), storeAddressField.getText(), code, sales);
        }
        if(success){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText("Operación realizada");
            alert.setContentText("Añadiste una nueva tienda.");
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Operación fallida");
            alert.setContentText("El código no se puede repetir.");
            alert.showAndWait();
        }
    }

    public void newUserPrePass() throws RemoteException{
        boolean success = false;
        int newUserPrivileges = -1;
        if(!newUserNameField.getText().equals("") && !newUserPasswordField.getText().equals("")){
            switch (userTierChoiceBox.getValue()){
                case "Empleado":
                    newUserPrivileges = 1;
                    break;
                case "Jefe":
                    newUserPrivileges = 2;
                    break;
            }

            success = ReportGeneratorClient.reportGenerator.addNewUser(newUserNameField.getText(), newUserPasswordField.getText(), newUserPrivileges);
        }
        if(success){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText("Operación realizada");
            alert.setContentText("Creaste un nuevo usuario.");
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Operación fallida");
            alert.setContentText("Ocurrió un error al intentar crear el usuario.");
            alert.showAndWait();
        }
    }
    public void getBack() throws IOException {
        ReportGeneratorClient.getBack();
    }
}
