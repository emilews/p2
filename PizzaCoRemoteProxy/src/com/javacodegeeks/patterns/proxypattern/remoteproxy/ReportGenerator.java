package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReportGenerator extends Remote{
	public int logIn(String username, String password) throws RemoteException;
	public String getStoresInfo(String name) throws RemoteException;
	public String getStoreSalesData(String name, int privileges) throws RemoteException;
	public int getOverallSalesData(int privileges) throws RemoteException;
	public boolean addNewUser(String name, String pass, int privileges) throws RemoteException;
	public boolean addNewStore(String name, String address, int id, int sales) throws RemoteException;
	public String getAllStoreNames() throws RemoteException;

}
