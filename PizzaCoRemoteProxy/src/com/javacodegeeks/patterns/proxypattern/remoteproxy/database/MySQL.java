package com.javacodegeeks.patterns.proxypattern.remoteproxy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL implements DatabaseActions {
    private Connection connection;
    private String user = "";
    private String pass = "";
    public MySQL() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbds413",user,pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] Read(String query) {
        return new String[0];
    }

    @Override
    public boolean Write(String query) {
        return false;
    }

    @Override
    public boolean Delete(String query) {
        return false;
    }

    @Override
    public boolean Update(String query) {
        return false;
    }
}
