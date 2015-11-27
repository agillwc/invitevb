package com.github.rnbr.invitevb.modes;

/**
 * Tipo de ação.
 */
public enum SendMode {
    
    SHUFFLE("Embaralhado"),
    
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

    @Override
    public String toString() {
        return this.description;
    }
}