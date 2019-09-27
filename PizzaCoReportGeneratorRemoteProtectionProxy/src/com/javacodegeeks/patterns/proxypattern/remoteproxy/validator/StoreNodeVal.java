package com.javacodegeeks.patterns.proxypattern.remoteproxy.validator;

import com.javacodegeeks.patterns.proxypattern.remoteproxy.validator.user.UserValidator;

import java.util.ArrayList;
import java.util.List;

public class StoreNodeVal implements UserValidator<storeData> {
    private List<UserValidator> validators;
    public StoreNodeVal(List<UserValidator> validators){
        this.validators =  validators;
    }

    @Override
    public List<String> validateUser(storeData info) {
        return null;
    }

    @Override
    public List<String> validateStore(storeData info) {
        List<String> errors = new ArrayList<String>();
        for (UserValidator validator: validators) {
            errors.addAll(validator.validateUser(info));
        }
        return errors;    }
}
