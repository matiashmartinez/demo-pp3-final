/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pp3.st.utils;

/**
 *
 * @author Matyas
 */
public class ValidacionesCampos {

    public static boolean validateEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    
    public static boolean validateDni(String dni){
        String regex="^[\\d]{1,3}\\.?[\\d]{3,3}\\.?[\\d]{3,3}$";
        return true;
    }
}
