package test;

import com.javacodegeeks.patterns.proxypattern.remoteproxy.EmployeeList;
import com.javacodegeeks.patterns.proxypattern.remoteproxy.database.DatabaseActions;
import com.javacodegeeks.patterns.proxypattern.remoteproxy.database.DatabaseFactory;

import java.sql.SQLException;

public class AllTests {
    public static void main(String[] args) {

        DatabaseActions database = null;
        try {
            database = DatabaseFactory.getInstance("postgresql");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //----------------------------------------------------------
        EmployeeList list = EmployeeList.getInstance();
        int result = 999;
        boolean created = false;
        //created = list.addNewEmployee(database,"ola","lugo","1995-02-12", 'f',"WOGE971203HSRNNM07", "WOGE971203123",'s',"6623499413",
         //       "ola@gmail.com","j", "ironman", "pass123", 3000);

        System.out.println(created);

    }
}
