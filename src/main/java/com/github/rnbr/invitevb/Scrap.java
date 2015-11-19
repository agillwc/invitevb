package com.github.rnbr.invitevb;

import com.github.gitrn.invitevb.models.Member;
import com.github.gitrn.invitevb.models.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scrap {
    
    private final Properties properties;

    public Scrap() {
        this.properties = PropertiesLoader.get();
    }
    
    private String getProperty(String id){
        return properties.getProperty(id);
    }
    
    private String resolveUrl(String url, String base){
        return !url.startsWith(base) ?
                base + url           :
                url;
    }
    
    public synchronized List<Member> getOnlineMembers(String html, Settings settings){
        
        List<Member> members = new ArrayList<>();
        
        try {
            Document document = Jsoup.parse(html);

            if(document.hasText()){
                Elements onlineMembers = document.select(getProperty("onlineusers.query"));
                if(!onlineMembers.isEmpty())
                    for(Element onlineMember : onlineMembers){
                        String username = onlineMember.text();
                        String profileUrl = onlineMember.attr("href");
                        
                        profileUrl = resolveUrl(profileUrl, settings.getHomePage());
                        
                        members.add(new Member(username, profileUrl));
                    }
            }
        } catch(Exception err){}     
        return members;
    }
    
}