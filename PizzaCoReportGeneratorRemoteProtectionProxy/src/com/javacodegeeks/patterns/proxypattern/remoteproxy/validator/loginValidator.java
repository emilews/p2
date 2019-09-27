/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Composite;

/**
 *
 * @author Mike
 */
import java.util.ArrayList;
import java.util.List;

public class loginValidator extends Validator<userData> {
    
    @Override
    public List<String> Validate(userData data) {
        List<String> errors = new ArrayList<String>();
        
        if (data.username.isEmpty()) {
            errors.add("Username must be populated");
        }
        if (data.password.isEmpty() && data.password.length() < 8) {
            errors.add("Password must be populated and longer than 8 characters");
        }  

        return errors;
    }
    
}