package com.github.rnbr.invitevb;

import java.util.Map;
import org.junit.Test;

public class TestScrap {

    private final Scrap scrap = new Scrap();

    @Test
    public void scrapOnlineMembers() {
        Map<String, String> members = scrap.getOnlineMembers(null);
        if (!members.isEmpty()) {
            members.entrySet().stream().forEach((member) -> {
                System.out.println("Nome de usuário: " + member.getKey() + " - Link para o perfil: " + member.getValue());
            });
        } else {
            System.out.println("Lista de usuários online vazia.");
        }
    }
}