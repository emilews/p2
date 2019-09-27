package com.javacodegeeks.patterns.proxypattern.remoteproxy.validator;

import com.javacodegeeks.patterns.proxypattern.remoteproxy.validator.user.UserValidator;

import java.util.ArrayList;
import java.util.List;
public class validatorNode implements UserValidator<UserInfo> {

    private List<UserValidator> validators;
    
    public validatorNode(List<UserValidator> validators){
        this.validators =  validators;
    }
    @Override
    public List<String> validateUser(UserInfo data) {
        List<String> errors = new ArrayList<String>();
        for (UserValidator validator: validators) {
            errors.addAll(validator.validateUser(data));
        }
        return errors;
    }

    @Override
    public List<String> validateStore(UserInfo info) {
       return null;
    }


}
