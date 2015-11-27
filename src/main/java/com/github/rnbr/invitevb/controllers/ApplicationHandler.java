package com.github.rnbr.invitevb.controllers;

import com.github.rnbr.invitevb.models.Member;
import com.github.rnbr.invitevb.models.MemberComparator;
import com.github.rnbr.invitevb.models.Settings;
import com.github.rnbr.invitevb.models.User;
import com.github.rnbr.invitevb.modes.SendMode;
import com.github.rnbr.invitevb.views.InviteVBWindow;
import com.github.rnbr.invitevb.Action;
import com.github.rnbr.invitevb.Attributes;
import com.github.rnbr.invitevb.Scrap;
import com.github.rnbr.invitevb.Utils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class ApplicationHandler implements ActionListener, Runnable {

    private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    
    private final InviteVBWindow window;
    private final Action actions = new Action();
    
    private Settings settings;
    
    private List<Member> members;
    private int beginIndex, endIndex, currentIndex;
    private int messagesTotal;
    
    public ApplicationHandler(InviteVBWindow window) {
        this.window = window;
        window.getBtAction().addActionListener(this);
        window.setVisible(true);
    }
    
    private boolean hasAllSettings(){
        return !Utils.isNullOrEmpty(window.getFdUsername()) &&
               !Utils.isNullOrEmpty(window.getFdPassword()) &&
               !Utils.isNullOrEmpty(window.getFdHomepage()) &&
               !Utils.isNullOrEmpty(window.getFdMessage());
    }
    
    private boolean hasValidInteger(){
        return Utils.hasValidInteger(window.getFdInterval());
    }
    
    private boolean validate(){
        boolean validate = true;
        String message = "";
        
        if(!hasAllSettings())
            message += "Um ou mais campos não foram preenchidos.\n";
        
        if(!hasValidInteger())
            message += "O valor do intervalo não é um número válido.";
        
        if(!message.isEmpty()){
            JOptionPane.showMessageDialog(window, message, "Inicialização Interrompida", JOptionPane.ERROR_MESSAGE);
            validate = false;
        }
            
        return validate;
    }
    
    private boolean hasPermission(){
        boolean permission = true;
        String message = "";
        
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(settings.getUser().getUsername());
        if(matcher.matches())
            message += "Use seu nome de usuário, não email.\n";
        
        if(message.length() == 0){
            Permissions permissions = new Permissions();
            message += permissions.isAllowed("banned", settings.getUser().getUsername(), settings.getHomePage()) == Permissions.Permission.FORBIDDEN?
                       "Você não tem permissão para usar essa aplicação." :
                       "";
        }
        
        if(!message.trim().isEmpty()){
            JOptionPane.showMessageDialog(window, message, "Inicialização Interrompida", JOptionPane.WARNING_MESSAGE);
            permission = false;
        }
        return permission;
    }
    
    private synchronized Settings getSettings(){
        User credentials = new User(window.getFdUsername().getText(), new String(window.getFdPassword().getPassword()));
        return new Settings(
            credentials,
            (SendMode) window.getCbMode().getSelectedItem(),
            window.getFdHomepage().getText(),
            window.getFdMessage().getText(),
            Integer.parseInt(window.getFdInterval().getText())
        );
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "run":
                if(!validate())
                    return;
                  
                new Thread(this).start();
                break;
                
            case "stop":
                service.shutdownNow();
                System.exit(0);
            
        }
        window.switchButtonState();
    }
    
    @Override
    public void run() {
        
        window.setMessage("Verificando/validando suas configurações...");
        settings = getSettings();
        if(!hasPermission())
            System.exit(0);
   
        
        window.setMessage("Acessando o fórum...");
        actions.login(settings);
        window.setMessage("Login efetuado, buscando usuários online...");
        
        members = new Scrap().getOnlineMembers(actions.getHtmlFrom(settings.getHomePage()), settings);
        if(members.isEmpty()){
            JOptionPane.showMessageDialog(window, "Lista de usuários vazia ou não foi possível se conectar ao fórum no momento.", "", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        
        if(!window.getCbStaff().isSelected())
            members = new Scrap().getModerators(
                actions.getHtmlFrom(settings.getHomePage() + Attributes.get().getProperty("resource.staff")),
                settings,
                members
            );
        
        Collections.sort(members, new MemberComparator());
        
        beginIndex = endIndex = messagesTotal = 0;
        switch(settings.getSendMode()){
            case BEGIN_TO_END:
                beginIndex = 0;
                endIndex = members.size();
                break;
                
            case BEGIN_TO_MIDDLE:
                beginIndex = 0;
                endIndex = members.size() / 2;
                break;
            
            case MIDDLE_TO_END:
                beginIndex = members.size() / 2;
                endIndex = members.size();
                break;
                
            case SHUFFLE:
                beginIndex = 0;
                endIndex = members.size();
                Collections.shuffle(members);
                break;
        }
        
        currentIndex = beginIndex;
        
        window.getPbProgress().setMinimum(beginIndex);
        window.getPbProgress().setMaximum(endIndex);
        window.getPbProgress().setValue(currentIndex);
        
        window.setMessage("OK! " + members.size() + " usuário(s) encontrados. Iniciando o envio das mensagens...");
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                
                Member member = members.get(currentIndex);
                boolean sended = actions.sendMessageTo(member.getProfileUrl(), settings.getMessage());
                if(sended){
                    messagesTotal++;
                    
                    String messages = "<b>" + messagesTotal + " mensage" + (messagesTotal > 1 ? "ns" : "m") + "</b>";
                    String info = ": Última foi para '" + member.getUsername() + "' - Horário: " + LocalTime.now();
                    window.setMessage(messages + " - " + info + ". Aguardando intervalo...");
                }
                
                currentIndex++;
                window.getPbProgress().setValue(currentIndex);
                if(currentIndex >= endIndex){
                    service.shutdown();
                    window.setMessage("Processo finalizado, você já pode fechar o programa.");
                }
            }
        }, 0, settings.getInterval(), TimeUnit.SECONDS);
    }
}