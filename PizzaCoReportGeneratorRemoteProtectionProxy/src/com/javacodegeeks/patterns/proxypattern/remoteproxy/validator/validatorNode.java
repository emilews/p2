/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Composite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mike
 */
public class validatorNode<T> extends Validator<T>{

    private List<Validator<T>> validators;
    
    public validatorNode(List<Validator<T>> validators){
        this.validators = validators;
    }
    
    @Override
    public List<String> Validate(T data) {
        List<String> errors = new ArrayList<String>();
        for (Validator validator: validators) {
            errors.addAll(validator.Validate(data));
        }
        return errors;
    }
    
}
