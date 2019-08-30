package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList {
    //Creating list of employees
    private static List<Employee> employees = new ArrayList<>();
    public static void populate(){
        //Adding employees
        employees.add(new Employee("Aileen", "1234*", 2));
        employees.add(new Employee("Luis Paco", "sanson123", 1));
        employees.add(new Employee("laslo", "123", 1));

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
                    return 0;
                }else {
                    return -1;
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
