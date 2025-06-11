/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.enums;

/**
 *
 * @author higor
 */
public enum ConsultaStatus {
    AGENDADA("AGENDADA"),
    REALIZADA("REALIZADA"),
    CANCELADA("CANCELADA");
    
    private final String descricao;
    
    ConsultaStatus(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public static ConsultaStatus fromDescricao(String descricao) {
        for (ConsultaStatus status : ConsultaStatus.values()) {
            if (status.descricao.equalsIgnoreCase(descricao)) {
                return status;
            }
        }
        
        throw new IllegalArgumentException("Status de consulta inválido: " + descricao);
    }
}
