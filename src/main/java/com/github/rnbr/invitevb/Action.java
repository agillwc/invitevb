package com.github.rnbr.invitevb;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

public class Action {
    
    private CookieManager cookies;
    private final WebClient client;
    private final Properties properties = PropertiesLoader.get();
    
    private String homepageAfterLoginHtml;

    public Action() {
        this.client = new WebClient(BrowserVersion.CHROME);
        initClientOptions();
    }
    
    private void initClientOptions(){
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        this.client.getOptions().setJavaScriptEnabled(false);
        this.client.getOptions().setCssEnabled(false);
    }
    
    public synchronized void login(){
        try {
            String loginUrl = properties.getProperty("resource.target");
            HtmlPage page = client.getPage(loginUrl);
            if(page != null){
                HtmlForm loginForm = page.getElementById(properties.getProperty("element.form"), true);
                if(loginForm != null){
                    HtmlTextInput userameInput = loginForm.getInputByName(properties.getProperty("element.username"));
                    HtmlPasswordInput passwordInput = loginForm.getInputByName(properties.getProperty("element.password"));
                    HtmlCheckBoxInput rememberInput = loginForm.getInputByName(properties.getProperty("element.remember"));
                    
                    userameInput.setValueAttribute(properties.getProperty("login.username"));
                    passwordInput.setValueAttribute(properties.getProperty("login.password"));
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
    
    public String getHtmlFrom(String url){
        String html = "";
        try {
            html = client.getPage(url).getWebResponse().getContentAsString();
        } catch(IOException | FailingHttpStatusCodeException err){}
        return html;
    }
    
    public boolean sendMessageTo(String profileUrl, String... args){
        try {
            
            HtmlPage page = client.getPage(profileUrl);
            if(page != null){
                
                HtmlForm messageForm = page.getElementById(properties.getProperty("message.form"), true);
                if(messageForm != null){
                    
                    HtmlTextArea messageInput = messageForm.getTextAreaByName(properties.getProperty("message.textarea"));
                    if(messageInput != null){
                        
                        String hello = properties.getProperty("message.greetings") + " " + (args.length > 0 ? args[0] : "") + "! ";
                        messageInput.setText(hello + properties.getProperty("message.content"));
                        HtmlSubmitInput sendButton = messageForm.getInputByName(properties.getProperty("message.sendbutton"));
                        HtmlPage afterSend = sendButton.click();
                        if(afterSend != null)
                            return true;
                    }
                }
            }
        } catch(IOException | FailingHttpStatusCodeException | ElementNotFoundException err){}        
        return false;
    }
}