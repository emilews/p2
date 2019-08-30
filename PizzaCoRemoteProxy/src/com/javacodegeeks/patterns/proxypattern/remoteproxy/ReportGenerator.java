package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReportGenerator extends Remote{
	public int logIn(String username, String password);
	public String getStoresInfo(int numStore);
	public String getStoreSalesData(int numStore, int privileges);
	public int getOverallSalesData(int privileges);

}
