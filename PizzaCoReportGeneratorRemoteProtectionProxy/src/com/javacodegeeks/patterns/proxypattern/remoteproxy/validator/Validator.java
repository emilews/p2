/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Composite;

import java.util.List;

/**
 *
 * @author Mike
 */
public abstract class Validator<T> {
    
    public abstract List<String> Validate(T data);
    //add childrenString nombre = data.nombre;
    public void addValidator(Validator validator) {
        throw new UnsupportedOperationException("Current operation is not support for this object");
    }

    //remove children
    public void removeValidator(Validator validator) {
        throw new UnsupportedOperationException("Current operation is not support for this object");
    }
    
    //get children
    public List<Validator> getValidators() {
        throw new UnsupportedOperationException("Current operation is not support for this object");
    }
    
    
}
