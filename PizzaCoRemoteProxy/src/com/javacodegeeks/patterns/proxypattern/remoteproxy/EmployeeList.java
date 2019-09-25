package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import com.javacodegeeks.patterns.proxypattern.remoteproxy.database.DatabaseActions;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeList {
    //Instance of data holder
    private static EmployeeList instance;
    //Private constructor
    private EmployeeList(){
    }
    public static synchronized EmployeeList getInstance() {
        if(instance == null){
            instance = new EmployeeList();
        }
        return instance;
    }

    public boolean addNewEmployee(DatabaseActions database, int id, String fname, String lname, String bday, String gender,
                                  String curp, String rfc, String civilstate, String phone, String email,
                                  String roletype, String username, String pass, int salary){
        ResultSet resultSet = database.Read("SELECT * FROM users WHERE curp = '" + curp+ "'" + "AND username = "+
                "'"+username+"';");
        if(resultSet == null){
            return false;
        }
        ResultSet set = database.Read("SELECT MAX(userid) FROM users;");
        String newUserId = "";
        String lastUserId = "";
        try {
            set.next();
            lastUserId = set.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        newUserId = String.valueOf(Integer.valueOf(lastUserId) + 1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("'"+newUserId+"'");
        stringBuilder.append(", ");
        stringBuilder.append("'"+fname+"'");
        stringBuilder.append(", ");
        stringBuilder.append("'"+lname+"'");
        stringBuilder.append(", ");
        stringBuilder.append("'"+bday+"'");
        stringBuilder.append(", ");
        stringBuilder.append("'"+gender+"'");
        stringBuilder.append(", ");
        stringBuilder.append("'"+curp+"'");
        stringBuilder.append(", ");
        stringBuilder.append("'"+rfc+"'");
        stringBuilder.append(", ");
        stringBuilder.append("'"+civilstate+"'");
        stringBuilder.append(", ");
        stringBuilder.append("'"+phone+"'");
        stringBuilder.append(", ");
        stringBuilder.append("'"+email+"'");
        stringBuilder.append(", ");
        stringBuilder.append("'"+roletype+"'");
        stringBuilder.append(", ");
        stringBuilder.append("'"+username+"'");
        stringBuilder.append(", ");
        stringBuilder.append("'"+pass+"'");
        stringBuilder.append(", ");

        database.Write("INSERT INTO " + "usuarios(id, nombre, apellidos, fechanaci, genero, curp, " +
                "rfc, estadocivil, telefono, email, rol, username, pass, salary) " + "VALUES("+stringBuilder.toString()
                + salary + ", " +"'"+newUserId +"'"+ ");");

        return true;
    }
    public static int logIn(DatabaseActions database, String name, String password) throws SQLException {
        ResultSet resultSet = database.Read("SELECT * FROM users WHERE username = " + "'" + name + "'" + " AND " + "pass = " +
                    "'" + password + "';");
        if(resultSet == null){
            return -1;
        }else{
            resultSet.next();
            if(resultSet.getString(11).equals("Boss")){
                return 2;
            }else{
                return 1;
            }
        }
    }
}
