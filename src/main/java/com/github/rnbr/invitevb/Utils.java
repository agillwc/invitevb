package com.github.rnbr.invitevb;

import java.text.NumberFormat;
import java.text.ParsePosition;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Utils {
    
    public static boolean isNullOrEmpty(String t){
        return t == null || t.trim().isEmpty();
    }
    
    public static boolean isNullOrEmpty(JTextField t){
        return isNullOrEmpty(t.getText());
    }
    
    public static boolean isNullOrEmpty(JTextArea t){
        return isNullOrEmpty(t.getText());
    }
    
    public static boolean hasValidInteger(JTextField t){
        boolean valid = false;
        
        if(!isNullOrEmpty(t)){
            valid = true;
            try {
                Integer.parseInt(t.getText().trim());
            } catch (NumberFormatException ex){
                valid = false;
            }
        }
        
        return valid;
    }
}