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

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ReportGeneratorClient extends Application implements ReportGenerator{
    ReportGenerator reportGenerator = null;
    @FXML
    Button logInButton;
    @FXML
    PasswordField passwordField;
    @FXML
    TextField userField;
    @FXML
    Label welcomeText;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public int logIn(String username, String password) {
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
	    prepareObjects();
	    logInButton.setOnAction(e -> {
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
                }
            }
        });
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		primary.setTitle("Práctica 2: Pizzas");
		primary.setScene(new Scene(root, 600,400));
		primary.show();
	}

    private void changeToOwnerView() {
    }

    private void changeToEmployeeView() {
    }

    public void prepareObjects(){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",9000);
            reportGenerator = (ReportGenerator)registry.lookup("rmi://127.0.0.1/PizzaCoRemoteGenerator");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
