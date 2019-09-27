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
import java.sql.ResultSet;
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
	public String getStoresInfo(String name, int id) {
		try {
			return stores.getStore(database, name, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String getStoreSalesData(String name, int privileges) {
		if(privileges > 1){
			return null;
		}else{
			StringBuilder sb = new StringBuilder();
			ResultSet set = database.Read("SELECT * FROM tiendas WHERE nombre = " + name + ";");
			if(set != null){
				try {
					set.next();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					sb.append(set.getString(5));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return sb.toString();
			}
		}
		return null;
	}

	@Override
	public int getOverallSalesData(int privileges) {
		if(privileges > 1){
			try {
				return stores.getOverallSales(database);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			return -1;
		}
		return -1;
	}

	@Override
	public boolean addNewUser(String fname, String lname, String bday, String gender, String curp, String rfc, String civilstate,
							  String phone, String email, String roletype, String username, String pass, int salary) throws RemoteException {
		return employeeList.addNewEmployee(database, fname, lname, bday,gender, curp, rfc, civilstate, phone,email,roletype,username,pass,salary);
	}


	@Override
	public boolean addNewStore(String name, String address,String tel, int sales) throws RemoteException {
		try {
			stores.addNewStore(database, name,address,tel,sales);
		}catch (Exception e){
			return false;
		}
		return true;
	}

	@Override
	public String getAllStoreNames() throws RemoteException {
		try {
			return stores.getAllStoreNames(database);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
