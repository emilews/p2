package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReportGenerator extends Remote{
	public int logIn(String username, String password) throws RemoteException;
	public String getStoresInfo(int id) throws RemoteException;
	public String getStoreSalesData(String name, int privileges) throws RemoteException;
	public int getOverallSalesData(int privileges) throws RemoteException;
	public boolean addNewUser(int id, String name,String surname, String bday, String gender, String curp,
							  String rfc, String estadocivil, String tel, String email, String rol,
							  String username, String pass, int salario) throws RemoteException;
	public boolean addNewStore(String name, String address, String tel, int sales) throws RemoteException;
	public String getAllStoreNames() throws RemoteException;

}
