package com.javacodegeeks.patterns.proxypattern.remoteproxy.validator;

import com.javacodegeeks.patterns.proxypattern.remoteproxy.validator.user.UserValidator;

import java.util.ArrayList;
import java.util.List;

public class StoreValidator implements UserValidator<storeData> {
    @Override
    public List<String> validateUser(storeData info) {
        List<String> errors = new ArrayList<>();
        if (info.getNombre().isEmpty()){
            errors.add("Campo nombre obligatorio");
        }
        if(info.getDireccion().isEmpty()){
            errors.add("Campo dirección obligatorio");
        }
        if(info.getTelefono().isEmpty()){
            errors.add("Campo telefono obligatorio");
        }
        return errors;
    }

    @Override
    public List<String> validateStore(storeData info) {
        return null;
    }
}
