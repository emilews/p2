package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import java.util.ArrayList;
import java.util.List;

public class Stores {
    private static ArrayList<Store> data = new ArrayList<>();
    Store store1 = new Store("Pizzas Lupita", "Luis Encinas 410", 1, 25);
    Store store2 = new Store("Rin Rin", "Progreso y Reyes 150", 2, 86);
    Store store3 = new Store("Papa Johns", "Morelos 514", 3, 43);


    public void populate(){
        data.add(store1);
        data.add(store2);
        data.add(store3);
    }


    public static String getStore(int numStore) {
        StringBuilder sb = new StringBuilder();
        for (Store t : data) {
            if (t.code == numStore) {
                sb.append(t.getName());
                sb.append(t.getAddress());
                sb.append(t.getNumber());
                break;
            }
        }
        return sb.toString();
    }

    public static String getDailySalesByStore(int numStore) {
        StringBuilder sb = new StringBuilder();
        for (Store t : data) {
            if (t.code == numStore) {
                sb.append(t.getName());
                sb.append(t.getNumber());
                sb.append(t.getDailySales());
                break;
            }
        }
        return sb.toString();
    }

    public static int getOverallSales(){
        int sales = 0;
        for(Store s : data){
            sales += s.getDailySales();
        }
        return sales;
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
