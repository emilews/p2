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
        return -2;
    }

	@Override
	public String getStoresInfo(int id) {
		try {
			return stores.getStore(database,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getStoreSalesData(String name, int privileges) {
		return null;
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
	public boolean addNewUser(int id, String name, String surname, String bday, String gender, String curp, String rfc, String estadocivil, String tel, String email,  String rol, String username, String pass, int salario) throws RemoteException {
		return employeeList.addNewEmployee(database, id,name,surname,bday,gender, curp, rfc, estadocivil, tel,email, rol, username, pass, salario);
	}

	@Override
	public boolean addNewStore(String name, String address, String tel, int sales) throws RemoteException {
		return false;
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
