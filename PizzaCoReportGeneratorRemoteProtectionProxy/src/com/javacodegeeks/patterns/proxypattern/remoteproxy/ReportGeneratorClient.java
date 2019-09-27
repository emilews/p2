package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ReportGeneratorClient extends Application implements ReportGenerator{
    static ReportGenerator reportGenerator;
    private static Stage s = new Stage();
    private static int userPrivileges = 0;
    private static String storeNames = "";
    @FXML
    Button logInButton;
    @FXML
    PasswordField passwordField;
    @FXML
    TextField userField;
    @FXML
    Label welcomeText;

	public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",9000);
            reportGenerator = (ReportGenerator)registry.lookup("PizzaCoRemoteGenerator");
            System.out.println("Connected!");
        } catch (Exception e) {
            e.printStackTrace();
        }
	    launch(args);
	}

	@Override
	public int logIn(String username, String password) throws RemoteException {
		return reportGenerator.logIn(username,password);
	}

    @Override
    public String getStoresInfo(String name, int id) throws RemoteException {
        return reportGenerator.getStoresInfo(name,id);
    }

    @Override
    public String getStoreSalesData(String name, int privileges) throws RemoteException {
        return reportGenerator.getStoreSalesData(name, userPrivileges);
    }

    @Override
    public int getOverallSalesData(int privileges) throws RemoteException {
        return reportGenerator.getOverallSalesData(userPrivileges);
    }

    @Override
    public boolean addNewUser(String fname, String lname, String bday, String gender, String curp, String rfc, String civilstate, String phone, String email, String roletype, String username, String pass, int salary) throws RemoteException {
        return reportGenerator.addNewUser(fname,lname,bday,gender,curp,rfc,civilstate,phone,email,roletype,username,pass,salary);
    }


    @Override
    public boolean addNewStore(String name, String address, String tel , int sales) throws RemoteException {
        return reportGenerator.addNewStore(name, address,tel, sales);
    }

    @Override
    public String getAllStoreNames() throws RemoteException {
        return reportGenerator.getAllStoreNames();
    }

    @Override
	public void start(Stage primary) throws Exception {
        this.s = primary;
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		this.s.setTitle("Práctica 2: Pizzas");
		this.s.setScene(new Scene(root, 600,400));
		this.s.show();
		storeNames = getAllStoreNames();

	}

    public void changeToOwnerView() throws IOException {
        Parent ownerRoot = FXMLLoader.load(getClass().getResource("OwnerView.fxml"));
        this.s.getScene().setRoot(ownerRoot);
    }
    public void changeToEmployeeView() throws IOException {
        Parent ownerRoot = FXMLLoader.load(getClass().getResource("EmpView.fxml"));
        this.s.getScene().setRoot(ownerRoot);
    }

    public void logInAction() throws IOException {
        if(userField != null && passwordField != null){
            int result = logIn(userField.getText(), passwordField.getText());
            switch (result){
                case -1:
                    welcomeText.setText("User doesn't exist!");
                    break;
                case 1:
                    userPrivileges = 1;
                    changeToEmployeeView();
                    break;
                case 2:
                    userPrivileges = 2;
                    changeToOwnerView();
                    break;
            }
        }
    }


    public static void changeToNewStore() throws IOException {
	    Parent newStoreRoot = FXMLLoader.load(ReportGeneratorClient.class.getResource("NewStore.fxml"));
	    s.getScene().setRoot(newStoreRoot);
    }
    public static void changeToViewStores() throws IOException {
        Parent newStoreRoot = FXMLLoader.load(ReportGeneratorClient.class.getResource("ViewStores.fxml"));
        s.getScene().setRoot(newStoreRoot);
    }
    public static void changeToSalesByStore() throws IOException {
        Parent newStoreRoot = FXMLLoader.load(ReportGeneratorClient.class.getResource("SalesByStore.fxml"));
        s.getScene().setRoot(newStoreRoot);
    }
    public static void changeToNewUser() throws IOException {
        Parent newStoreRoot = FXMLLoader.load(ReportGeneratorClient.class.getResource("NewUser.fxml"));
        s.getScene().setRoot(newStoreRoot);
    }
    public static void changeToOverallSales() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText("Ventas totales:");
        alert.setContentText("" + reportGenerator.getOverallSalesData(userPrivileges));
        alert.showAndWait();
    }

    public static void getBack() throws IOException {
        Parent root = FXMLLoader.load(ReportGeneratorClient.class.getResource("OwnerView.fxml"));
        s.getScene().setRoot(root);
    }

    public static String getStoreSales(String name) throws RemoteException {
	    return reportGenerator.getStoreSalesData(name, userPrivileges);
    }

    public static String getAllNames() throws RemoteException {
	    return storeNames;
    }
    public static void salir() throws IOException {
        Parent root = FXMLLoader.load(ReportGeneratorClient.class.getResource("Main.fxml"));
        s.getScene().setRoot(root);
    }
}
