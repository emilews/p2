package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmpController implements Initializable {
    @FXML
    ChoiceBox<String> storesChoiceBox;
    @FXML
    Button lookForStoreSales;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> list = new ArrayList<String>();
        String[] s = new String[0];
        try {
            s = ReportGeneratorClient.getAllNames().split(",");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        for(String st: s){
            System.out.println(st);
            list.add(st);
        }
        ObservableList<String> stores = FXCollections.observableArrayList(list);
        storesChoiceBox.getItems().removeAll(storesChoiceBox.getItems());
        storesChoiceBox.getItems().addAll(list);
    }

    public void lookForSalesByStore() throws RemoteException {
        if(!storesChoiceBox.getValue().equals("")){
            String[] store = ReportGeneratorClient.reportGenerator.getStoresInfo(storesChoiceBox.getValue()).split(",");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tienda: " + store[0]);
            alert.setHeaderText("Dirección: " + store[1]);
            alert.setContentText("Sucursal número: " + store[2]);
            alert.showAndWait();
        }
    }
}
