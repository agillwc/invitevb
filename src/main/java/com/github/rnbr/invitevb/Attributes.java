package com.github.rnbr.invitevb;

import java.io.InputStream;
import java.util.Properties;

/**
 * Faz a leitura das propriedades do arquivo {@code /resouces/invite.properties}
 * colocando-o em memória e acessível estaticamente por qualquer outro objeto.
 */
public class Attributes {
    
    /**
     * Objeto que guarda as propriedades.
     */
    private static final Properties SETTINGS;
    
    
    static {
        SETTINGS = load();
    }
    
    private static Properties load(){
        Properties properties = null;
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("invite.properties")){
            if(is != null){
                properties = new Properties();
                properties.load(is);
            }
        } catch(Exception err){}
        return properties;
    }
    
    public static Properties get(){
        if(SETTINGS == null)
            throw new NullPointerException("The 'settings' properties is null.");
        return SETTINGS;
    }
}