/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.enums;

/**
 *
 * @author higor
 */
public enum PacienteStatus {
    
    ATIVO("ATIVO"),
    INATIVO("INATIVO");
    
    private final String descricao;
    
    PacienteStatus(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public static PacienteStatus fromDescricao(String descricao) {
        for (PacienteStatus status : PacienteStatus.values()) {
            if (status.descricao.equalsIgnoreCase(descricao)) {
                return status;
            }
        }
        
        throw new IllegarArgumentException("Status do paciente inválido: " + descricao);
    }
    
}
