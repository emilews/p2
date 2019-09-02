package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ReportGeneratorClient extends Application implements ReportGenerator{
    static ReportGenerator reportGenerator;
    private static Stage s = new Stage();
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
    public String getStoresInfo(int numStore) {
        return null;
    }

    @Override
    public String getStoreSalesData(int numStore, int privileges) {
        return null;
    }

    @Override
    public int getOverallSalesData(int privileges) {
        return 0;
    }

    @Override
	public void start(Stage primary) throws Exception {
        this.s = primary;
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		this.s.setTitle("Práctica 2: Pizzas");
		this.s.setScene(new Scene(root, 600,400));
		this.s.show();

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
                    changeToEmployeeView();
                    break;
                case 2:
                    changeToOwnerView();
                    break;
            }
        }
    }
}
