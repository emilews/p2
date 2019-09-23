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


    public String getStore(DatabaseActions database, String name) throws SQLException {
        StringBuilder sb = new StringBuilder();
        ResultSet resultSet = database.Read("SELECT * FROM store WHERE storename = " + "'" + name +"';");
        resultSet.next();
        sb.append(resultSet.getString())
        return sb.toString();
    }

    public boolean addNewStore(String name, String address, int id, int sales) throws IOException {

        return true;
    }

    public String getDailySalesByStore(String name) {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

    public int getOverallSales(){

        return 0;
    }

    public String getAllStoreNames(){
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
}
