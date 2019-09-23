package com.javacodegeeks.patterns.proxypattern.remoteproxy.database;
import java.sql.*;

public class PostgreSQL implements DatabaseActions {
    //Conexión a postgresql
    private Connection connection;
    private String user = "postgres";
    private String pass = "1234";


    public PostgreSQL() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbds413", user, pass);
        }catch (SQLException e){

        }
    }

    @Override
    public ResultSet Read(String query) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(statement != null){
            try {
                resultSet = statement.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(resultSet != null){
            return resultSet;
        }else{
            return null;
        }
    }

    @Override
    public boolean Write(String query) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        try {
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
