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


    public String getStore(DatabaseActions database, int id) throws SQLException {
        StringBuilder sb = new StringBuilder();
        ResultSet resultSet = database.Read("SELECT * FROM store WHERE id = " + id +";");
        resultSet.next();
        sb.append(resultSet.getString(2));
        sb.append(",");
        sb.append(resultSet.getString(3));
        sb.append(",");
        sb.append(resultSet.getString(4));
        sb.append(",");
        sb.append(resultSet.getString(5));
        return sb.toString();
    }

    public boolean addNewStore(DatabaseActions database, String name, String address, int tel, int sales) throws IOException {
        database.Write("INSERT INTO tiendas(nombre, direccion, telefono, ventas) VALUES(" + name +", " +
                address + ", " + tel + ", " + sales + ");" );

        return true;
    }


    public int getOverallSales(DatabaseActions database){
        ResultSet set = database.Read("SELECT SUM(ventas) FROM tiendas;");
        if(set != null){
            try {
                set.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                return Integer.valueOf(set.getString(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;

    }

    public String getAllStoreNames(DatabaseActions database) throws SQLException {
        StringBuilder sb = new StringBuilder();
        ResultSet set = database.Read("SELECT id, nombre FROM tiendas;");
        if(set != null){
            while (true) {
                set.next();
                if (set.isLast()) {
                    sb.append(set.getString(1));
                    sb.append("-");
                    sb.append(set.getString(2));
                    break;
                } else {
                    sb.append(set.getString(1));
                    sb.append("-");
                    sb.append(set.getString(2));
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }
}
