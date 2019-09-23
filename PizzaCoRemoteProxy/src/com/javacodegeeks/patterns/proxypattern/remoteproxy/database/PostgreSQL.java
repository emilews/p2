package com.javacodegeeks.patterns.proxypattern.remoteproxy.database;

import org.postgresql.core.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQL implements DatabaseActions {
    //Conexión a postgresql
    private Connection connection;
    private String user = "postgres";
    private String pass = "";


    public PostgreSQL() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbds413", user, pass);
        }catch (SQLException e){

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
