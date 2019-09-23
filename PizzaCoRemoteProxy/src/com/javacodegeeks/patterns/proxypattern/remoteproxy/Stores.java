package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import java.io.*;
import java.util.ArrayList;

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


    public String getStore(String name) {
        StringBuilder sb = new StringBuilder();
        for (Store t : data) {
            if (t.getName().equals(name)) {
                sb.append(t.getName());
                sb.append(",");
                sb.append(t.getAddress());
                sb.append(",");
                sb.append(t.getNumber());
                break;
            }
        }
        return sb.toString();
    }

    public boolean addNewStore(String name, String address, int id, int sales) throws IOException {
        for(Store s: data){
            if(s.getNumber() == id){
                return false;
            }
        }
        Store store = new Store(name, address, id, sales);
        data.add(store);
        StringBuilder sb = new StringBuilder();
        bufferedWriter = new BufferedWriter(new FileWriter(new File(CSV_FILE_PATH)));
        for(Store s : data){
            try {
                sb.append(s.getName());
                sb.append(",");
                sb.append(s.getAddress());
                sb.append(",");
                sb.append(s.getNumber());
                sb.append(",");
                sb.append(s.getDailySales());
                bufferedWriter.write(sb.toString());
                bufferedWriter.write("\n");
                sb.setLength(0);
            }catch (Exception e){
                return false;
            }
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        return true;
    }

    public String getDailySalesByStore(String name) {
        StringBuilder sb = new StringBuilder();
        for (Store t : data) {
            if (t.getName().equals(name)) {
                sb.append(t.getName());
                sb.append(",");
                sb.append(t.getNumber());
                sb.append(",");
                sb.append(t.getDailySales());
                break;
            }
        }
        return sb.toString();
    }

    public int getOverallSales(){
        int sales = 0;
        for(Store s : data){
            sales += s.getDailySales();
        }
        return sales;
    }

    public String getAllStoreNames(){
        StringBuilder sb = new StringBuilder();
        int size = data.size();
        int i = 0;
        for(Store s : data){
            if(i != size){
                sb.append(s.getName());
                sb.append(",");
            }else {
                sb.append(s.getName());
            }
            i++;
        }
        return sb.toString();
    }


    private class Store {
        private String name;
        private String address;
        private int code;
        private int dailySales;
        public Store(String name, String address, int code, int dailySales) {
            this.name = name;
            this.address = address;
            this.code = code;
            this.dailySales = dailySales;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public int getNumber() {
            return code;
        }

        public int getDailySales(){ return dailySales; }
    }
}
