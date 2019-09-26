package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import com.javacodegeeks.patterns.proxypattern.remoteproxy.database.DatabaseActions;
import com.javacodegeeks.patterns.proxypattern.remoteproxy.database.DatabaseFactory;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.Date;

public class ReportGeneratorImpl extends UnicastRemoteObject implements ReportGenerator{

	private static final long serialVersionUID = 3107413009881629428L;
	static EmployeeList employeeList;
	static Stores stores;
	private static DatabaseActions database = null;

	protected ReportGeneratorImpl() throws RemoteException {
	}


	@Override
	public int logIn(String username, String password) {
		System.out.println("Logging in: " + username + " with password: " + password);
		try {
			return EmployeeList.logIn(database, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 99;
	}

	@Override
	public String getStoresInfo(String name) {
		try {
			return stores.getStore(database,name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String getStoreSalesData(String name, int privileges) {
		if(privileges > 1){
			return stores.getDailySalesByStore(name);
		}else { return "Not enough privileges!"; }
	}

	@Override
	public int getOverallSalesData(int privileges) {
		if(privileges > 1){
			return stores.getOverallSales();
		}else{
			return -1;
		}
	}

	@Override
	public boolean addNewUser(String name, String pass, int privileges) throws RemoteException {
		return false;
	}

	@Override
	public boolean addNewStore(String name, String address, int id, int sales) throws RemoteException {
		try {
			stores.addNewStore(name,address,id,sales);
		}catch (Exception e){
			return false;
		}
		return true;
	}

	@Override
	public String getAllStoreNames() throws RemoteException {
		return stores.getAllStoreNames();
	}

	public static void main(String[] args) {
		employeeList = EmployeeList.getInstance();
		stores = Stores.getInstance();
		try {
			Registry registry = LocateRegistry.createRegistry(9000);
			ReportGenerator reportGenerator = new ReportGeneratorImpl();
			registry.rebind("PizzaCoRemoteGenerator", reportGenerator);
			database = DatabaseFactory.getInstance("postgresql");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
