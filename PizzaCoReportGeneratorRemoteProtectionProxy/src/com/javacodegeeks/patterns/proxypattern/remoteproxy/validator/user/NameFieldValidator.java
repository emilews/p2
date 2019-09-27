package com.javacodegeeks.patterns.proxypattern.remoteproxy.validator.user;

import java.util.ArrayList;
import java.util.List;

public class NameFieldValidator {
    static List<String> errors = new ArrayList<>();

    public static List<String> validate(UserInfo info){
        String nombre = info.getName();
        String apellidos = info.getLastname();
        String username = info.getUsername();

        if(nombre.isEmpty()){
            errors.add("Campo de nombre obligatorio.");
        }
        if(apellidos.isEmpty()){
            errors.add("Campo de apellidos obligatorio.");
        }
        if(username.isEmpty()){
            errors.add("Campo de username obligatorio");
        }
        return errors;
    }
}
