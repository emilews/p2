package com.javacodegeeks.patterns.proxypattern.remoteproxy.validator;

public class UserInfo {
    private String name;
    private String lastname;
    private String bday;
    private String genero;
    private String curp;
    private String rfc;
    private String estado;
    private String tel;
    private String email;
    private String rol;
    private String username;
    private String pass;
    private int salario;

    public UserInfo(String name, String lastname, String bday, String genero, String curp, String rfc, String estado, String tel, String email, String rol, String username, String pass, int salario) {
        this.name = name;
        this.lastname = lastname;
        this.bday = bday;
        this.genero = genero;
        this.curp = curp;
        this.rfc = rfc;
        this.estado = estado;
        this.tel = tel;
        this.email = email;
        this.rol = rol;
        this.username = username;
        this.pass = pass;
        this.salario = salario;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getBday() {
        return bday;
    }

    public String getGenero() {
        return genero;
    }

    public String getCurp() {
        return curp;
    }

    public String getRfc() {
        return rfc;
    }

    public String getEstado() {
        return estado;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public int getSalario() {
        return salario;
    }
}
