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
import java.util.ArrayList;
import java.util.List;

public class userValidator extends Validator<userData> {

    @Override
    public List<String> Validate(userData data) {
        List<String> errors = new ArrayList<String>();

        if (data.nombre.isEmpty()) errors.add("Nombre must be populated");
        if (data.apellido.isEmpty()) errors.add("Apellido must be populated");
        if (data.fechanac.isEmpty()&& checkMajority(data.fechanac)){
            errors.add("Fecha de nacimiento must be populated and you must be "
                    + "above the avarage age");
        }
        if (data.curp.isEmpty()) errors.add("CURP must be populated");
        if (data.rfc.isEmpty()) errors.add("RFC must be populated");
        if (data.estadoc.isEmpty()) errors.add("Estado Civil must be populated");
        if (data.telefono.isEmpty()) errors.add("Telefono must be populated");
        if (data.email.isEmpty()) errors.add("EMail must be populated");
        //SUCURSAL OPCIONAL
        //OCUPACION COMBOBOX
        //USERNAME EN loginValidator
        //PASSWORD EN loginValidator
        //SALARIO OPCIONAL

        return errors;
    }
    public boolean checkMajority(String birthday){
        String[] sbirth;
        sbirth = birthday.split("/",3);
        int año = Integer.parseInt(sbirth[2]);
        if(año<=2001){
            return true;
        }
        return false;
    }

}
