package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import com.javacodegeeks.patterns.proxypattern.remoteproxy.database.DatabaseActions;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Stores {
    //Now we use a database omg
    private static Stores instance;
    //Private constructor
    private Stores(){
    }
    public static synchronized Stores getInstance() {
        if(instance == null){
            instance = new Stores();
        }
        return instance;
    }


    public String getStore(DatabaseActions database, String name, int id) throws SQLException {
        StringBuilder sb = new StringBuilder();
        ResultSet resultSet = database.Read("SELECT * FROM tiendas WHERE id = " + id + " AND  nombre = " + name + ";");
        if(resultSet == null){
            return null;
        }
        resultSet.next();
        sb.append(resultSet.getString(1));
        sb.append(",");
        sb.append(resultSet.getString(2));
        sb.append(",");
        sb.append(resultSet.getString(3));
        sb.append(",");
        sb.append(resultSet.getString(4));
        sb.append(",");
        sb.append(resultSet.getString(5));
        sb.append(",");
        sb.append(resultSet.getString(6));
        return sb.toString();
    }

    public boolean addNewStore(DatabaseActions database, String name, String address,String tel, int enc, int sales) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("'" + name + "'");
        sb.append(",");
        sb.append("'"+address+"'");
        sb.append("'"+tel+"'");
        sb.append(",");
        if(enc == 0){
            sb.append("null");
        }else{
            sb.append(enc);
        }
        sb.append(",");
        sb.append(sales);
        boolean result = database.Write("INSERT INTO tiendas(nombre, direccion, telefono, encargadoid, ventas) VALUES (" + sb.toString()+ ");");
        if(result){
            return true;
        }else{
            return false;
        }
    }

    public int getOverallSales(DatabaseActions database) throws SQLException {
        ResultSet set = database.Read("SELECT SUM(ventas) FROM tiendas;");
        if(set == null){
            return 0;
        }
        set.next();
        int ventas = Integer.valueOf(set.getString(1));
        return ventas;
    }

    public String getAllStoreNames(){
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
}
