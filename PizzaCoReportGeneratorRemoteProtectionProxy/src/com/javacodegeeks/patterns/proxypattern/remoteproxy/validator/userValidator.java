/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegeeks.patterns.proxypattern.remoteproxy.validator;


import com.javacodegeeks.patterns.proxypattern.remoteproxy.validator.user.UserValidator;

import java.util.ArrayList;
import java.util.List;

public class userValidator implements UserValidator<UserInfo> {


    public boolean checkMajority(String birthday){
        String[] sbirth;
        sbirth = birthday.split("-",3);
        int año = Integer.parseInt(sbirth[0]);
        if(año<=2001){
            return true;
        }
        return false;
    }

    @Override
    public List<String> validateUser(UserInfo data) {
        List<String> errors = new ArrayList<>();
        if (data.getName().isEmpty()) {
            errors.add("Campo nombre debe estar lleno");
        }
        if (data.getLastname().isEmpty()) {
            errors.add("Campo apellidos debe estar lleno");
        }

        if (data.getCurp().isEmpty()) errors.add("Campo de curp obligatorio");
        if (data.getRfc().isEmpty()) errors.add("Campo de rfc obligatotrio");
        if (data.getEstado().isEmpty()) errors.add("Campo de estado civil obligatorio");
        if (data.getTel().isEmpty()) errors.add("Campo de telefono obligatorio");
        if (data.getEmail().isEmpty()) errors.add("Campo de email obligatorio");
        if(!checkMajority(data.getBday())){
            errors.add("Debe ser mayor de edad");
        }
        if (data.getBday().isEmpty()){
            errors.add("Campo de fecha de nacimiento debe estar lleno");
        }
        return errors;
    }

    @Override
    public List<String> validateStore(UserInfo info) {
        return null;
    }
}
