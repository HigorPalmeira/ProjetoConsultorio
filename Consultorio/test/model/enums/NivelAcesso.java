/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model.enums;

/**
 *
 * @author higor
 */
public enum NivelAcesso {
    BAIXO("BAIXO"),
    MEDIO("MEDIO"),
    ALTO("ALTO"),
    ADMIN("ADMIN");
    
    private final String descricao;
    
    NivelAcesso(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public static NivelAcesso fromDescricao(String descricao) {
        for (NivelAcesso nivelAcesso : NivelAcesso.values()) {
            if (nivelAcesso.descricao.equalsIgnoreCase(descricao)) {
                return nivelAcesso;
            }
        }
        
        throw new IllegalArgumentException("Nível de acesso do usuário inválido: " + descricao);
    }
}
