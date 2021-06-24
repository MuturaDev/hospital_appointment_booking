package com.robert.hospitalappointmentbookingsystem.Utils;

public class Utils {

    public static boolean checkForValidName(final String name){
        if(name != null){
            if(name.isEmpty()){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }
}
