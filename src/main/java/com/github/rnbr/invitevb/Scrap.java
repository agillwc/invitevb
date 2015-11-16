package com.github.rnbr.invitevb;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scrap {
    
    private final Properties properties;

    public Scrap() {
        this.properties = Settings.get();
    }
    
    private String getProperty(String id){
        return properties.getProperty(id);
    }
    
    private String resolveUrl(String url, String base){
        return !url.startsWith(base) ?
                base + url           :
                url;
    }
    
    public synchronized HashMap<String, String> getOnlineMembers(String html){
        
        HashMap<String, String> members = new HashMap<>();
        
        try {
            Document document = html != null ?
                                Jsoup.parse(html) :
                                Jsoup.connect(getProperty("resource.target")).timeout(10000).userAgent("Mozilla").get();

            if(document.hasText()){
                Elements onlineMembers = document.select(getProperty("onlineusers.query"));
                if(!onlineMembers.isEmpty())
                    for(Element onlineMember : onlineMembers){
                        String username = onlineMember.text();
                        String profileUrl = onlineMember.attr("href");
                        
                        profileUrl = resolveUrl(profileUrl, getProperty("resource.target"));
                        members.put(username, profileUrl);
                    }
            }
        } catch(Exception err){}     
        return members;
    }
    
}