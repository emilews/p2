package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReportGenerator extends Remote{
	public int logIn(String username, String password) throws RemoteException;
	public String getStoresInfo(int numStore) throws RemoteException;
	public String getStoreSalesData(int numStore, int privileges) throws RemoteException;
	public int getOverallSalesData(int privileges) throws RemoteException;

}
