package com.javacodegeeks.patterns.proxypattern.remoteproxy.validator;

public class storeData {
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getGanacias() {
        return ganacias;
    }

    public storeData(String nombre, String direccion, String telefono, int ganacias) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ganacias = ganacias;
    }

    private String direccion;
    private String telefono;
    private int ganacias;
}
