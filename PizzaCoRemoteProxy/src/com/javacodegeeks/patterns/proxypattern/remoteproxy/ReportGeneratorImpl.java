package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class ReportGeneratorImpl extends UnicastRemoteObject implements ReportGenerator{

	private static final long serialVersionUID = 3107413009881629428L;

	protected ReportGeneratorImpl() throws RemoteException {
	}


	@Override
	public int logIn(String username, String password) {
		System.out.println("Logging in: " + username + " with password: " + password);
		return EmployeeList.logIn(username,password);
	}

	@Override
	public String getStoresInfo(int numStore) {
		return Stores.getStore(numStore);
	}

	@Override
	public String getStoreSalesData(int numStore, int privileges) {
		if(privileges > 1){
			return Stores.getDailySalesByStore(numStore);
		}else { return "Not enough privileges!"; }
	}

	@Override
	public int getOverallSalesData(int privileges) {
		if(privileges > 1){
			return Stores.getOverallSales();
		}else{
			return -1;
		}
	}

	public static void main(String[] args) {
		EmployeeList employeeList = EmployeeList.getInstance();
		try {
			Registry registry = LocateRegistry.createRegistry(9000);
			ReportGenerator reportGenerator = new ReportGeneratorImpl();
			registry.rebind("PizzaCoRemoteGenerator", reportGenerator);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
