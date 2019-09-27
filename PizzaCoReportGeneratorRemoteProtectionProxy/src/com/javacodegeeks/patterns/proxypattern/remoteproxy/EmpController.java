package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
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
    @FXML
    Button salirButton;
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
            String[] store = ReportGeneratorClient.reportGenerator.getStoresInfo(storesChoiceBox.getValue().split("-")[1],
                    Integer.valueOf(storesChoiceBox.getValue().split("-")[0])).split(",");
            System.out.println(store);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tienda: " + store[1]);
            alert.setHeaderText("Sucursal número: " + store[0]);
            alert.setContentText("Total de ventas: " + store[4] + "\n" + "Número de tel: " + store[3] +  "\n" + "Dirección: " + store[2]);
            alert.showAndWait();
        }
    }

    public void salir() throws IOException {
        ReportGeneratorClient.salir();
    }
}
