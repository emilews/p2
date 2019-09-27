package com.javacodegeeks.patterns.proxypattern.remoteproxy.validator;

import com.javacodegeeks.patterns.proxypattern.remoteproxy.validator.user.UserValidator;

import java.util.ArrayList;
import java.util.List;

public class PassValidator implements UserValidator<UserInfo> {
    @Override
    public List<String> validateUser(UserInfo data) {
        List<String> errors = new ArrayList<>();
        if (data.getPass().isEmpty()) {
            errors.add("Campo de password debe estar lleno");
        }
        if(data.getPass().length() < 8){
            errors.add("Password debe tener más de 8 caracteres");
        }

        if(!data.getPass().matches("^.*(?=.{8,})((?=.*[!@#$%^&*()\\-_=+{};:,<.>]){1})(?=.*\\d)((?=.*[a-z]){1})((?=.*[A-Z]){1}).*$")){
            errors.add("Passsword debe contener al menos una mayúscula");
            errors.add("Passsword debe contener al menos dos numeros");
            errors.add("Passsword debe contener al menos dos caracteres especiales");
        }
        return errors;
    }

    @Override
    public List<String> validateStore(UserInfo info) {
        return null;
    }
}
