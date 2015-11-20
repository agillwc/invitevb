package com.github.rnbr.invitevb.controllers;

import com.github.rnbr.invitevb.Attributes;
import com.github.rnbr.invitevb.Utils;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class Permissions {
    
    private JSONObject permissions = null;

    public Permissions() {
        try {
            this.permissions = getPermissions();
        } catch(JSONException | IOException ex) {}
    }    
    
    private synchronized JSONObject getPermissions() throws JSONException, IOException {
        String url = Attributes.get().getProperty("permissions");
        return new JSONObject(Jsoup.connect(url).ignoreContentType(true).execute().body());
    }
    
    public Permission isAllowed(String type, String... values){
        try {
            JSONArray friendly = permissions.getJSONArray(type);
            if(friendly != null){
                for(int i = 0; i < friendly.length(); i++){
                    String name = friendly.getJSONObject(i).getString("name").toLowerCase();
                    for(String value : values){
                        value = value.toLowerCase();
                        if(name.contains(value) || name.equalsIgnoreCase(value))
                            return Permission.FORBIDDEN;
                    }
                }
                return Permission.ALLOWED;
            } 
        } catch(Exception err){
            return Permission.NOT_FOUND;
        }
        return Permission.NOT_FOUND;
    }
    
    public enum Permission {
        ALLOWED,
        FORBIDDEN,
        NOT_FOUND
    }
}