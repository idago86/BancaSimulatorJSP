/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Israel Dago
 */
public final class ConvertDate {
    
    private ConvertDate(){}
    
    public static String toString(Date date){
        SimpleDateFormat dateformatDate = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss ");
        String dateformatString = dateformatDate.format(date);
        return dateformatString;
    }
    
}
    

