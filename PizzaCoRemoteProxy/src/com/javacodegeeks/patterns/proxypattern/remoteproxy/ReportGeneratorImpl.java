package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class ReportGeneratorImpl extends UnicastRemoteObject implements ReportGenerator{

	private static final long serialVersionUID = 3107413009881629428L;
	static EmployeeList employeeList;
	static Stores stores;

	protected ReportGeneratorImpl() throws RemoteException {
	}


	@Override
	public int logIn(String username, String password) {
		System.out.println("Logging in: " + username + " with password: " + password);
		return EmployeeList.logIn(username,password);
	}

	@Override
	public String getStoresInfo(String name) {
		return stores.getStore(name);
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
		try {
			return employeeList.addNewEmployee(name,pass,privileges);
		} catch (IOException e) {
		return false;
		}
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
