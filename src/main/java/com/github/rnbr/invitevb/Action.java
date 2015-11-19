package com.github.rnbr.invitevb;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import com.github.gitrn.invitevb.models.Settings;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

public class Action {
    
    private CookieManager cookies;
    private final WebClient client;
    private final Properties properties = PropertiesLoader.get();

    public Action() {
        this.client = new WebClient(BrowserVersion.CHROME);
        initClientOptions();
    }
    
    private void initClientOptions(){
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        this.client.getOptions().setJavaScriptEnabled(false);
        this.client.getOptions().setCssEnabled(false);
    }
    
    public synchronized void login(Settings settings){
        try {
            HtmlPage page = client.getPage(settings.getHomePage());
            
            if(page != null){
                HtmlForm loginForm = page.getElementById(properties.getProperty("element.form"), true);
                if(loginForm != null){
                    HtmlTextInput userameInput = loginForm.getInputByName(properties.getProperty("element.username"));
                    HtmlPasswordInput passwordInput = loginForm.getInputByName(properties.getProperty("element.password"));
                    HtmlCheckBoxInput rememberInput = loginForm.getInputByName(properties.getProperty("element.remember"));
                    
                    userameInput.setValueAttribute(settings.getUser().getUsername());
                    passwordInput.setValueAttribute(settings.getUser().getPassword());
                    rememberInput.setChecked(true);
                    
                    cookies = new CookieManager();
                    cookies = client.getCookieManager();
                    cookies.setCookiesEnabled(true);
                    client.setCookieManager(cookies);
                    
                    DomElement submitButton = page.createElement("button");
                    submitButton.setAttribute("type", "submit");
                    loginForm.appendChild(submitButton);
                    
                    submitButton.click();
                }
            }
        } catch(IOException | FailingHttpStatusCodeException | ElementNotFoundException err){}
    }
    
    public synchronized String getHtmlFrom(String url){
        String html = "";
        try {
            html = client.getPage(url).getWebResponse().getContentAsString();
        } catch(IOException | FailingHttpStatusCodeException err){
            System.out.println("err::" + err.getMessage());
        }
        return html;
    }
    
    public synchronized boolean sendMessageTo(String profileUrl, String message){
        try {
            
            HtmlPage page = client.getPage(profileUrl);
            if(page != null){
                
                HtmlForm messageForm = page.getElementById(properties.getProperty("message.form"), true);
                if(messageForm != null){
                    
                    HtmlTextArea messageInput = messageForm.getTextAreaByName(properties.getProperty("message.textarea"));
                    if(messageInput != null){
                        
                        messageInput.setText(message);
                        HtmlSubmitInput sendButton = messageForm.getInputByName(properties.getProperty("message.sendbutton"));
                        HtmlPage afterSend = sendButton.click();
                        if(afterSend != null)
                            return true;
                    }
                }
            }
        } catch(IOException | FailingHttpStatusCodeException | ElementNotFoundException err){
            return false;
        }
        return false;
    }
}