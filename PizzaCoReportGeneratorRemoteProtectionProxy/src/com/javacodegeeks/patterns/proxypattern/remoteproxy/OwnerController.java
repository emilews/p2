package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import com.javacodegeeks.patterns.proxypattern.remoteproxy.validator.*;
import com.javacodegeeks.patterns.proxypattern.remoteproxy.validator.user.UserValidator;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
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
    @FXML
    TextField newUserLNameField;
    @FXML
    DatePicker newUserBDAYFIELD;
    @FXML
    ComboBox newUserGENDER;
    @FXML
    TextField curpField;
    @FXML
    TextField rfcField;
    @FXML
    ComboBox estateField;
    @FXML
    TextField telField;
    @FXML
    TextField emailField;
    @FXML
    TextField nickField;
    @FXML
    TextField newUserSalary;
    @FXML
    Button salirButton;


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
            int sales = -1;
            try {
                sales = Integer.valueOf(storeSalesField.getText());
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No es número");
                alert.setContentText("No introdujiste un número en el campo de ventas.");
                alert.showAndWait();
            }
            storeData d = new storeData(storeNameField.getText(), storeAddressField.getText(), storeCodeField.getText(), sales);
            StoreValidator st = new StoreValidator();
            List<UserValidator> validators = new ArrayList<>();
            validators.add(st);
            StoreNodeVal va = new StoreNodeVal(validators);
            List<String> errors = va.validateStore(d);
            if(!errors.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Operación fallida");
                StringBuilder sg = new StringBuilder();
                for(String s : errors){
                    sg.append(s);
                    sg.append("\n");
                }
                alert.setContentText(sg.toString());
                alert.showAndWait();
            }else{
                success = ReportGeneratorClient.reportGenerator.addNewStore(storeNameField.getText(), storeAddressField.getText(), storeCodeField.getText(), sales);
            }

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
       String roletype = "";
        if(!newUserNameField.getText().equals("") && !newUserPasswordField.getText().equals("")){
            switch (userTierChoiceBox.getValue()){
                case "Empleado":
                    roletype = "e";
                    break;
                case "Jefe":
                    roletype = "j";
                    break;
            }
            LocalDate localDate = newUserBDAYFIELD.getValue();
            String gender = newUserGENDER.getValue().toString();
            String g = "";
            switch(gender){
                case "Hombre":
                    g = "m";
                    break;
                case "Mujer":
                    g = "f";
                    break;
            }
            String est = estateField.getValue().toString();
            String es = "";
            switch(est){
                case "Soltero/a":
                    es = "s";
                    break;
                case "Casado/a":
                    es = "c";
                    break;
            }


            UserInfo user = new UserInfo( newUserNameField.getText(), newUserLNameField.getText(), localDate.toString(), g, curpField.getText(), rfcField.getText(),
                    es, telField.getText(), emailField.getText(),
                    roletype, nickField.getText(), newUserPasswordField.getText(), Integer.valueOf(newUserSalary.getText()));
            userValidator uv = new userValidator();
            PassValidator passValidator = new PassValidator();
            List<UserValidator> lista = new ArrayList<>();
            lista.add(uv);
            lista.add(passValidator);
            validatorNode vn = new validatorNode(lista);
            List<String> s = vn.validateUser(user);
            if(!s.isEmpty()){
                StringBuilder f = new StringBuilder();
                for(String as : s){
                    f.append(as);
                    f.append("\n");
                }
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Operación fallida");
                alert.setContentText(f.toString());
                alert.showAndWait();
            }else{
                success = ReportGeneratorClient.reportGenerator.addNewUser(newUserNameField.getText(), newUserLNameField.getText(), localDate.toString(), g,
                        curpField.getText(), rfcField.getText(),
                        es, telField.getText(), emailField.getText(),
                        roletype, nickField.getText(), newUserPasswordField.getText(), Integer.valueOf(newUserSalary.getText()));
            }
        }
        if(success){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText("Operación realizada");
            alert.setContentText("Creaste un nuevo usuario.");
            alert.showAndWait();
        }
    }
    public void getBack() throws IOException {
        ReportGeneratorClient.getBack();
    }
    public void salir() throws IOException {
        ReportGeneratorClient.salir();
    }
}
