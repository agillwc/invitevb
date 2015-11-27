package com.github.rnbr.invitevb;

import com.github.rnbr.invitevb.models.Member;
import com.github.rnbr.invitevb.models.Settings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scrap {
    
    private final Properties properties;

    public Scrap() {
        this.properties = Attributes.get();
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
    
    public synchronized List<Member> getModerators(String html, Settings settings, List<Member> fullList){
        List<Member> staff = new ArrayList<>();
        try {
            Document document = Jsoup.parse(html);
            if(document.hasText()){
                Elements staffers = document.select(getProperty("resouce.staffgroup1.query"));
                if(!staffers.isEmpty()){
                    for(Element staffer : staffers){
                        String username = staffer.text().trim();
                        String profileUrl = staffer.attr("href");
                        profileUrl = resolveUrl(profileUrl, settings.getHomePage());
                        
                        staff.add(new Member(username, profileUrl));
                    }
                }
                
                Elements moreStaffers = document.select(getProperty("resouce.staffgroup1.query"));
                if(!moreStaffers.isEmpty()){
                    for(Element staffer : moreStaffers){
                        String username = staffer.text().trim();
                        String profileUrl = staffer.attr("href");
                        profileUrl = resolveUrl(profileUrl, settings.getHomePage());
                        
                        staff.add(new Member(username, profileUrl));
                    }
                }
            }
            
            
            if(!staff.isEmpty()){
                // removendo duplicatas
                
                Set<Member> setStaffers = new LinkedHashSet<>();
                setStaffers.addAll(staff);
                
                staff.clear();
                staff.addAll(fullList);
                staff.removeAll(setStaffers);
            }
            
           // for(Member m : staff)
           //     System.out.println(m.getUsername());
        } catch(Exception err){}
        return staff;
    }
    
}