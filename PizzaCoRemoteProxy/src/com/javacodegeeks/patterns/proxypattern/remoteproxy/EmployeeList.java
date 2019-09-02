package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeList {
    //Creating list of employees
    private static List<Employee> employees = new ArrayList<>();
    //Reader
    private BufferedReader bufferedReader = null;
    //Writer
    private BufferedWriter bufferedWriter = null;
    //Variable for csv file name
    private static final String CSV_FILE_PATH = System.getProperty("user.dir") +"\\src\\com\\javacodegeeks\\patterns\\" +
            "proxypattern\\remoteproxy\\EmployeeListData.csv";
    //Instance of data holder
    private static EmployeeList instance;
    //Private constructor
    private EmployeeList(){
        try {
            populate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static synchronized EmployeeList getInstance() {
        if(instance == null){
            instance = new EmployeeList();
        }
        return instance;
    }
    public void populate() throws IOException {
        bufferedReader = new BufferedReader(new FileReader(new File(CSV_FILE_PATH)));
        String s = "";
        while((s = bufferedReader.readLine()) != null){
            String[] parts = s.split(",");
            Employee employee = new Employee(parts[0],parts[1],Integer.valueOf(parts[2]));
            employees.add(employee);
        }

    }
    public boolean addNewEmployee(String name, String pass, int privileges) throws IOException {
        Employee e = new Employee(name, pass, privileges);
        employees.add(e);
        StringBuilder sb = new StringBuilder();
        bufferedWriter = new BufferedWriter(new FileWriter(new File(CSV_FILE_PATH)));
        for(EmployeeList.Employee employee : employees){
            sb.append(employee.getName());
            sb.append(",");
            sb.append(employee.getPassword());
            sb.append(",");
            sb.append(employee.getPrivileges());
            bufferedWriter.write(sb.toString());
            bufferedWriter.write("\n");
            sb.setLength(0);
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        return true;
    }
    public static int logIn(String name, String password){
        for(Employee e : employees){
            if(e.getName().equals(name)){
                if(e.getPassword().equals(password)){
                    switch (e.getPrivileges()){
                        case 1:
                            return 1;
                        case 2:
                            return 2;
                    }
                }else {
                    return 0;
                }

            }
        }
        return -1;
    }

    //Inner class
    private static class Employee{
        private final String name;
        private final String password;
        private final int privileges;
        public Employee(String name, String password, int privileges){
            this.name = name;
            this.password = password;
            this.privileges = privileges;
        }

        public String getName() {
            return name;
        }

        public String getPassword() {
            return password;
        }

        public int getPrivileges() {
            return privileges;
        }
    }
}
