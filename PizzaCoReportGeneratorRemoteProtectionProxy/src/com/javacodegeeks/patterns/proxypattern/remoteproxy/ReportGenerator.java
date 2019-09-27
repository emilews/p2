package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import javax.print.DocFlavor;
import java.rmi.RemoteException;

public interface ReportGenerator {
    public int logIn(String username, String password) throws RemoteException;
    public String getStoresInfo(String name, int id) throws RemoteException;
    public String getStoreSalesData(String name, int privileges) throws RemoteException;
    public int getOverallSalesData(int privileges) throws RemoteException;
    public boolean addNewUser(String fname, String lname, String bday, String gender,
                              String curp, String rfc, String civilstate, String phone, String email,
                              String roletype, String username, String pass, int salary) throws RemoteException;
    public boolean addNewStore(String name, String address, String tel, int sales) throws RemoteException;
    public String getAllStoreNames() throws RemoteException;
}
