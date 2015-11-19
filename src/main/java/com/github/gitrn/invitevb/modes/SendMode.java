package com.github.gitrn.invitevb.modes;

/**
 * Tipo de ação.
 */
public enum SendMode {
    
    
    /**
     * Percorre toda a lista de usuários.
     */
    BEGIN_TO_END("Início ao fim"),
    
    /**
     * Percorre a lista de usuários pelo indíce, do zero até {@ code tamanho_da_lista / 2 }. 
     */
    BEGIN_TO_MIDDLE("Início ao meio"),
    
    /**
     * Percorre a lista de usuários pelo índice, de {@code tamanho_da_lista / 2} até {@code tamanho_da_lista}.
     */
    MIDDLE_TO_END("Meio ao fim");
    
    
    /**
     * Descrição do modo.
     */
    private final String description;

    private SendMode(String description) {
        this.description = description;
    }
    
    /**
     * Obtém o {@code Mode} de acordo com a descrição.
     * @param description descrição do modo.
     * @return o respectivo modo de acordo com a descrição (ignorando <i>case</i>).
     */
    public SendMode byDescription(String description){
        SendMode selected = null;
        for(SendMode mode : SendMode.values())
            if(mode.description.equalsIgnoreCase(description))
                selected = mode;
        return selected;
    }
}