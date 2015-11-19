package com.github.rnbr.invitevb;

import java.text.NumberFormat;
import java.text.ParsePosition;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Utils {
    public static boolean isNullOrEmpty(JTextField t){
        String text = t.getText();
        return text == null || text.trim().isEmpty();
    }
    
    public static boolean isNullOrEmpty(JTextArea t){
        String text = t.getText();
        return text == null || text.trim().isEmpty();
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